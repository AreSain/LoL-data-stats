package aresain.loldatastats.lolstat.statanalysis;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.entity.Objective;
import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.gamematch.GameMatchService;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import aresain.loldatastats.lolstat.statanalysis.dto.AnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.ObjectiveAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.repository.ObjectiveStatRepository;
import aresain.loldatastats.lolstat.statanalysis.repository.ParticipantSummaryStatRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class StatAnalysisService {
	private final AnalysisMapper analysisMapper;
	private final GameMatchService gameMatchService;
	private final ObjectiveStatRepository objectiveStatRepository;
	private final ParticipantSummaryStatRepository participantSummaryStatRepository;

	public AnalysisListDto getStatAnalysis(String puuid, String type, Integer start, Integer count) {
		ListDto<GameMatchInfoDto> gameMatchInfoDtoListDto = gameMatchService.saveOrFindGameInfoList(puuid, type, start, count);
		List<GameMatchInfoDto> gameMatchInfoList = gameMatchInfoDtoListDto.getItems();
		List<String> matchIds = gameMatchInfoList.stream()
			.map(GameMatchInfoDto::getMatchId)
			.toList();

		List<ParticipantSummary> summaries = participantSummaryStatRepository.findByMatchIdInAndPuuid(matchIds, puuid);


		List<ObjectiveAnalysisDto> objectiveAnalysisDtos = objectiveAnalysis(summaries, matchIds);

		AnalysisListDto analysisListDto = analysisMapper.toAnalysisListDto(objectiveAnalysisDtos);
		AnalysisListDto analysisListDto = analysisMapper.toDtoWithRelations(objectiveAnalysisDtos);
		return analysisListDto;
	}

	private List<ObjectiveAnalysisDto> objectiveAnalysis(List<ParticipantSummary> summaries, List<String> matchIds) {
		Map<Boolean, List<Objective>> objectiveMap = getObjective(summaries, matchIds);
		List<Objective> winObjectives = objectiveMap.getOrDefault(true, List.of());
		List<Objective> loseObjectives = objectiveMap.getOrDefault(false, List.of());

		Set<String> allTypes = Stream.concat(winObjectives.stream(), loseObjectives.stream())
			.map(Objective::getType)
			.collect(Collectors.toSet());

		List<ObjectiveAnalysisDto> result = new ArrayList<>();
		for (String type : allTypes) {
			double winAvgKills = winObjectives.stream()
				.filter(o -> o.getType().equals(type))
				.mapToDouble(Objective::getKills)
				.average()
				.orElse(0.0);

			double loseAvgKills = loseObjectives.stream()
				.filter(o -> o.getType().equals(type))
				.mapToDouble(Objective::getKills)
				.average()
				.orElse(0.0);

			ObjectiveAnalysisDto dto = analysisMapper.toObjectiveAnalysisDto(type, winAvgKills, loseAvgKills);
			result.add(dto);
		}
		return result;
	}


	private Map<Boolean, List<Objective>> getObjective(List<ParticipantSummary> summaries, List<String> matchIds) {
		List<Objective> allObjectives = objectiveStatRepository.findByMatchIdIn(matchIds);

		return allObjectives.stream()
			.flatMap(obj -> summaries.stream()
				.filter(s -> s.getMatchId().equals(obj.getMatchId()) && s.getTeamId() == obj.getTeamId())
				.map(ParticipantSummary::getWin)
				.distinct()
				.map(win -> new AbstractMap.SimpleEntry<>(win, obj))
			)
			.collect(Collectors.groupingBy(
				Map.Entry::getKey,
				Collectors.mapping(Map.Entry::getValue, Collectors.toList())
			));
	}
}
