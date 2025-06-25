package aresain.loldatastats.lolstat.statanalysis;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.timeline.TimelineService;
import aresain.loldatastats.loldata.timeline.dto.TimelineInfoDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.LevelUpAnalysisDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TimelineAnalysisService {
	private final TimelineAnalysisMapper timelineAnalysisMapper;
	private final TimelineService timelineService;

	public LevelUpAnalysisDto getTimelineAnalysis(List<ParticipantSummary> summaries, List<String> matchIds) {
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

		return firstToLevel2Analysis(MatchTimelineMap, matchParticipantIdMap);
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
}
