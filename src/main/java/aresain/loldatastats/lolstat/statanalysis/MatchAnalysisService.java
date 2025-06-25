package aresain.loldatastats.lolstat.statanalysis;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.entity.Objective;
import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.gamematch.GameMatchService;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import aresain.loldatastats.lolstat.statanalysis.accumulator.SummaryAccumulator;
import aresain.loldatastats.lolstat.statanalysis.dto.match.MatchAnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.match.ObjectiveAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.match.SummaryAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.repository.ObjectiveStatRepository;
import aresain.loldatastats.lolstat.statanalysis.repository.ParticipantSummaryStatRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MatchAnalysisService {
	private final MatchAnalysisMapper matchAnalysisMapper;
	private final GameMatchService gameMatchService;
	private final ObjectiveStatRepository objectiveStatRepository;
	private final ParticipantSummaryStatRepository participantSummaryStatRepository;

	public MatchAnalysisListDto getStatAnalysis(String puuid, String type, Integer start, Integer count) {
		ListDto<GameMatchInfoDto> gameMatchInfoDtoListDto = gameMatchService.saveOrFindGameInfoList(puuid, type, start, count);
		List<GameMatchInfoDto> gameMatchInfoList = gameMatchInfoDtoListDto.getItems();
		List<String> matchIds = gameMatchInfoList.stream()
			.map(GameMatchInfoDto::getMatchId)
			.toList();

		List<ParticipantSummary> summaries = participantSummaryStatRepository.findByMatchIdInAndPuuid(matchIds, puuid);


		List<ObjectiveAnalysisDto> objectiveAnalysisDtos = objectiveAnalysis(summaries, matchIds);
		SummaryAnalysisDto summaryAnalysisDtos = summaryAnalysis(summaries);

		MatchAnalysisListDto matchAnalysisListDto = matchAnalysisMapper.toDtoWithRelations(objectiveAnalysisDtos, summaryAnalysisDtos);
		return matchAnalysisListDto;
	}

	private List<ObjectiveAnalysisDto> objectiveAnalysis(List<ParticipantSummary> summaries, List<String> matchIds) {
		Map<Boolean, List<Objective>> objectiveMap = getObjective(summaries, matchIds);

		List<Objective> winObjectives = objectiveMap.getOrDefault(true, List.of());
		List<Objective> loseObjectives = objectiveMap.getOrDefault(false, List.of());

		Map<String, Double> winAvgKillsByType = winObjectives.stream()
			.collect(Collectors.groupingBy(
				Objective::getType,
				Collectors.averagingDouble(Objective::getKills)
			));
		Map<String, Double> loseAvgKillsByType = loseObjectives.stream()
			.collect(Collectors.groupingBy(
				Objective::getType,
				Collectors.averagingDouble(Objective::getKills)
			));

		Set<String> allTypes = new HashSet<>(winAvgKillsByType.keySet());

		List<ObjectiveAnalysisDto> result = new ArrayList<>();
		for (String type : allTypes) {
			double winAvgKills = winAvgKillsByType.getOrDefault(type, 0.0);
			double loseAvgKills = loseAvgKillsByType.getOrDefault(type, 0.0);

			ObjectiveAnalysisDto dto = matchAnalysisMapper.toObjectiveAnalysisDto(type, winAvgKills, loseAvgKills);
			result.add(dto);
		}
		return result;
	}

	private SummaryAnalysisDto summaryAnalysis(List<ParticipantSummary> summaries) {
		SummaryAccumulator acc = summaries.stream()
			.collect(
				SummaryAccumulator::new,
				SummaryAccumulator::accumulate,
				SummaryAccumulator::combine
			);
		double winAvgKill, winAvgDeath, winAvgAssist, winAvgVision, winAvgWardPlaced, winAvgWardKilled, winAvgDetection;
		winAvgKill = acc.getWinAvgKill();
		winAvgDeath = acc.getWinAvgDeath();
		winAvgAssist = acc.getWinAvgAssist();
		winAvgVision = acc.getWinAvgVision();
		winAvgWardPlaced = acc.getWinAvgWardPlaced();
		winAvgWardKilled = acc.getWinAvgWardKilled();
		winAvgDetection = acc.getWinAvgDetectionWardPlaced();
		double loseAvgKill, loseAvgDeath, loseAvgAssist, loseAvgVision, loseAvgWardPlaced, loseAvgWardKilled, loseAvgDetection;
		loseAvgKill = acc.getLoseAvgKill();
		loseAvgDeath = acc.getLoseAvgDeath();
		loseAvgAssist = acc.getLoseAvgAssist();
		loseAvgVision = acc.getLoseAvgVision();
		loseAvgWardPlaced = acc.getLoseAvgWardPlaced();
		loseAvgWardKilled = acc.getLoseAvgWardKilled();
		loseAvgDetection = acc.getLoseAvgDetectionWardPlaced();


		return matchAnalysisMapper.toSummaryAnalysisDto(
			winAvgKill, winAvgDeath, winAvgAssist,
			winAvgVision, winAvgWardPlaced, winAvgWardKilled, winAvgDetection,
			loseAvgKill, loseAvgDeath, loseAvgAssist,
			loseAvgVision, loseAvgWardPlaced, loseAvgWardKilled, loseAvgDetection
		);
	}

	private Map<Boolean, List<Objective>> getObjective(List<ParticipantSummary> summaries, List<String> matchIds) {
		List<Objective> allObjectives = objectiveStatRepository.findByMatchIdIn(matchIds);

		return allObjectives.stream()
			.flatMap(obj -> summaries.stream()
				.filter(s -> s.getMatchId().equals(obj.getMatchId()) && s.getTeamId() == obj.getTeamId())
				.map(ParticipantSummary::getWin)
				.map(win -> new AbstractMap.SimpleEntry<>(win, obj))
			)
			.collect(Collectors.groupingBy(
				Map.Entry::getKey,
				Collectors.mapping(Map.Entry::getValue, Collectors.toList())
			));
	}
}
