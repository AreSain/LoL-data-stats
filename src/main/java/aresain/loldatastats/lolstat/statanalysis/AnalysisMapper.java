package aresain.loldatastats.lolstat.statanalysis;

import java.util.List;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.AnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.ObjectiveAnalysisDto;

@Mapper(componentModel = "spring")
public interface AnalysisMapper {
	ObjectiveAnalysisDto toObjectiveAnalysisDto(String type, double winAvgKills, double loseAvgKills);

	default AnalysisListDto toDtoWithRelations(List<ObjectiveAnalysisDto> objectiveAnalysisDto) {
		return AnalysisListDto.builder()
				.objectiveAnalysisDto(objectiveAnalysisDto)
				.build();
	}
}
