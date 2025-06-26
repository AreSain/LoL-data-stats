package aresain.loldatastats.lolstat.statanalysis;

import java.util.List;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.timeline.KillAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.LevelUpAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.TimelineAnalysisListDto;

@Mapper(componentModel = "spring")
public interface TimelineAnalysisMapper {
	LevelUpAnalysisDto toLevelUpAnalysisDto(
		Double winFirst2LevelRate, Double loseFirst2LevelRate,
		Double winFirst6LevelRate, Double loseFirst6LevelRate,
		Integer winCount, Integer loseCount
	);

	default TimelineAnalysisListDto toDtoWithRelations(LevelUpAnalysisDto levelUpAnalysisDto, List<KillAnalysisDto> killAnalysisDto) {
		return TimelineAnalysisListDto.builder()
			.levelUpAnalysisDto(levelUpAnalysisDto)
			.killAnalysisDto(killAnalysisDto)
			.build();
	}
}
