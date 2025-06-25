package aresain.loldatastats.lolstat.statanalysis;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.timeline.LevelUpAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.TimelineAnalysisListDto;

@Mapper(componentModel = "spring")
public interface TimelineAnalysisMapper {
	TimelineAnalysisListDto toTimelineAnalysisListDto(LevelUpAnalysisDto levelUpAnalysisDto);
	LevelUpAnalysisDto toLevelUpAnalysisDto(
		Double winFirst2LevelRate, Double loseFirst2LevelRate,
		Double winFirst6LevelRate, Double loseFirst6LevelRate,
		Integer winCount, Integer loseCount
	);
}
