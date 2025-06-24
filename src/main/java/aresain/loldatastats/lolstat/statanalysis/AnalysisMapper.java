package aresain.loldatastats.lolstat.statanalysis;

import java.util.List;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.AnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.ObjectiveAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.SummaryAnalysisDto;

@Mapper(componentModel = "spring")
public interface AnalysisMapper {
	ObjectiveAnalysisDto toObjectiveAnalysisDto(String type, double winAvgKills, double loseAvgKills);
	SummaryAnalysisDto toSummaryAnalysisDto(
		Double winAvgKill,
		Double winAvgDeath,
		Double winAvgAssist,
		Double winAvgVisionScore,
		Double winAvgWardPlaced,
		Double winAvgWardKilled,
		Double winAvgDetectionWardPlaced,
		Double loseAvgKill,
		Double loseAvgDeath,
		Double loseAvgAssist,
		Double loseAvgVisionScore,
		Double loseAvgWardPlaced,
		Double loseAvgWardKilled,
		Double loseAvgDetectionWardPlaced);

	default AnalysisListDto toDtoWithRelations(List<ObjectiveAnalysisDto> objectiveAnalysisDto,
			SummaryAnalysisDto summaryAnalysisDto) {
		return AnalysisListDto.builder()
				.objectiveAnalysisDto(objectiveAnalysisDto)
				.summaryAnalysisDto(summaryAnalysisDto)
				.build();
	}
}
