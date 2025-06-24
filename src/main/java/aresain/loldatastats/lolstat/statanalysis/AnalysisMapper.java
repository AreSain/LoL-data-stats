package aresain.loldatastats.lolstat.statanalysis;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.ObjectiveAnalysisDto;

@Mapper(componentModel = "spring")
public interface AnalysisMapper {
	ObjectiveAnalysisDto toObjectiveAnalysisDto(String type, double winAvgKills, double loseAvgKills);
}
