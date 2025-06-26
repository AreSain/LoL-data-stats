package aresain.loldatastats.lolstat.statanalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.timeline.TimelineService;
import aresain.loldatastats.loldata.timeline.dto.ChampionKillEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.TimelineInfoDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.KillAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.LevelUpAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.TimelineAnalysisListDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TimelineAnalysisService {
	private final TimelineAnalysisMapper timelineAnalysisMapper;
	private final TimelineService timelineService;

	public TimelineAnalysisListDto getTimelineAnalysis(List<ParticipantSummary> summaries, List<String> matchIds) {
		Map<String, TimelineInfoDto> timelineInfoMap = matchIds.stream()
			.collect(Collectors.toMap(
				matchId -> matchId,
				timelineService::saveOrFindTimelineInfo
			));

		Map<String, Integer> matchParticipantIdMap = summaries.stream()
			.collect(Collectors.toMap(
				ParticipantSummary::getMatchId,
				s -> s.getParticipantId().intValue()
			));

		Map<Boolean, Map<String, TimelineInfoDto>> MatchTimelineMap = summaries.stream()
			.collect(Collectors.groupingBy(
				ParticipantSummary::getWin,
				Collectors.toMap(
					ParticipantSummary::getMatchId,
					s -> timelineInfoMap.get(s.getMatchId())
				)
			));

		LevelUpAnalysisDto levelUpAnalysisDto = firstToLevel2Analysis(MatchTimelineMap, matchParticipantIdMap);
		List<KillAnalysisDto> killAnalysisListDto = killAnalysisByLane(MatchTimelineMap, matchParticipantIdMap);

		return timelineAnalysisMapper.toDtoWithRelations(levelUpAnalysisDto, killAnalysisListDto);
	}

	private LevelUpAnalysisDto firstToLevel2Analysis(Map<Boolean, Map<String, TimelineInfoDto>> matchTimelineMap,
		Map<String, Integer> matchParticipantIdMap) {
		int winCount = 0, loseCount = 0;
		int winFirst2Level = 0, loseFirst2Level = 0;
		int winFirst6Level = 0, loseFirst6Level = 0;

		for (Boolean win : List.of(true, false)) {
			Map<String, TimelineInfoDto> timelineMap = matchTimelineMap.getOrDefault(win, Map.of());

			for (Map.Entry<String, TimelineInfoDto> entry : timelineMap.entrySet()) {
				String matchId = entry.getKey();
				TimelineInfoDto timelineInfo = entry.getValue();
				Integer myParticipantId = matchParticipantIdMap.get(matchId);

				if (timelineInfo == null || myParticipantId == null) continue;

				Integer my2LevelTimestamp = null;
				Integer enemy2LevelTimestamp = null;
				Integer my6LevelTimestamp = null;
				Integer enemy6LevelTimestamp = null;

				for (var event : timelineInfo.getChampionLevelEvents()) {
					if (event.getParticipantId().intValue() == myParticipantId) {
						if (event.getLevel() == 2) my2LevelTimestamp = event.getTimestamp();
						if (event.getLevel() == 6) my6LevelTimestamp = event.getTimestamp();
					}
					if (((event.getParticipantId().intValue() + 5 - 1) % 10 + 1) == myParticipantId) {
						if (event.getLevel() == 2) enemy2LevelTimestamp = event.getTimestamp();
						if (event.getLevel() == 6) enemy6LevelTimestamp = event.getTimestamp();
					}
				}

				if (win) winCount++;
				else loseCount++;

				if (my2LevelTimestamp != null && enemy2LevelTimestamp != null) {
					if (my2LevelTimestamp < enemy2LevelTimestamp) {
						if (win) winFirst2Level++;
						else loseFirst2Level++;
					}
				}
				if (my6LevelTimestamp != null && enemy6LevelTimestamp != null) {
					if (my6LevelTimestamp < enemy6LevelTimestamp) {
						if (win) winFirst6Level++;
						else loseFirst6Level++;
					}
				}
			}
		}

		double winFirst2LevelRate = winCount > 0 ? (winFirst2Level * 100.0 / winCount) : 0.0;
		double loseFirst2LevelRate = loseCount > 0 ? (loseFirst2Level * 100.0 / loseCount) : 0.0;
		double winFirst6LevelRate = winCount > 0 ? (winFirst6Level * 100.0 / winCount) : 0.0;
		double loseFirst6LevelRate = loseCount > 0 ? (loseFirst6Level * 100.0 / loseCount) : 0.0;

		return timelineAnalysisMapper.toLevelUpAnalysisDto(
			winFirst2LevelRate, loseFirst2LevelRate,
			winFirst6LevelRate, loseFirst6LevelRate,
			winCount, loseCount
		);
	}

	public List<KillAnalysisDto> killAnalysisByLane(
		Map<Boolean, Map<String, TimelineInfoDto>> matchTimelineMap,
		Map<String, Integer> matchParticipantIdMap) {

		// [earlyDeathCount, solo, gank, mid, support, gameCount]
		Map<String, int[]> statMap = new HashMap<>();

		for (Map<String, TimelineInfoDto> timelineMap : matchTimelineMap.values()) {
			for (Map.Entry<String, TimelineInfoDto> entry : timelineMap.entrySet()) {
				String matchId = entry.getKey();
				TimelineInfoDto timelineInfo = entry.getValue();
				Integer myParticipantId = matchParticipantIdMap.get(matchId);

				if (timelineInfo == null || myParticipantId == null) continue;

				String lane = getLaneByParticipantId(myParticipantId);
				int[] stat = statMap.computeIfAbsent(lane, k -> new int[6]);

				List<ChampionKillEventInfoDto> kills = timelineInfo.getChampionKillEvents();
				for (ChampionKillEventInfoDto kill : kills) {
					if (kill.getVictimId() == null || kill.getVictimId().intValue() != myParticipantId) continue;
					int ts = kill.getTimestamp();
					if (ts > 840_000) continue;

					stat[0]++;

					Integer killerId = Integer.valueOf(kill.getKillerId());
					if (killerId == 2 || killerId == 7)	stat[2]++;
					if (killerId == 3 || killerId == 8) stat[3]++;
					if (killerId == 5 || killerId == 10) stat[4]++;

					List<Integer> assists = parseAssistString(kill.getAssistingParticipantIds());
					boolean hasJungler = assists.stream().anyMatch(id -> id == 2 || id == 7);
					boolean hasSupport = assists.stream().anyMatch(id -> id == 5 || id == 10);
					boolean hasMid = assists.stream().anyMatch(id -> id == 3 || id == 8);

					if (assists.isEmpty()) stat[1]++;
					if (hasJungler) stat[2]++;
					if (hasMid) stat[3]++;
					if (hasSupport) stat[4]++;
				}
				stat[5]++;
			}
		}

		List<KillAnalysisDto> result = new ArrayList<>();
		for (var entry : statMap.entrySet()) {
			String lane = entry.getKey();
			int[] stat = entry.getValue();
			double averageDeath = stat[5] > 0 ? (double) stat[0] / stat[5] : 0.0;
			double soloAvg = stat[5] > 0 ? (double) stat[1] / stat[5] : 0.0;
			double gankAvg = stat[5] > 0 ? (double) stat[2] / stat[5] : 0.0;
			double midAvg = stat[5] > 0 ? (double) stat[3] / stat[5] : 0.0;
			double supportAvg = stat[5] > 0 ? (double) stat[4] / stat[5] : 0.0;

			result.add(KillAnalysisDto.builder()
				.lane(lane)
				.averageDeath(averageDeath)
				.soloKillCount(soloAvg)
				.gankCount(gankAvg)
				.midCount(midAvg)
				.supportCount(supportAvg)
				.build());
		}
		return result;
	}

	private List<Integer> parseAssistString(String assistsStr) {
		if (assistsStr == null || assistsStr.isBlank()) return List.of();
		return java.util.Arrays.stream(assistsStr.split(","))
			.map(String::trim)
			.filter(s -> !s.isEmpty())
			.map(Integer::parseInt)
			.collect(Collectors.toList());
	}

	private String getLaneByParticipantId(int participantId) {
		switch (participantId) {
			case 1: case 6: return "TOP";
			case 2: case 7: return "JUNGLE";
			case 3: case 8: return "MID";
			case 4: case 9: return "ADC";
			case 5: case 10: return "SUPPORT";
			default: return "UNKNOWN";
		}
	}
}
