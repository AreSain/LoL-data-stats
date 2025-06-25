package aresain.loldatastats.lolstat.statanalysis;

import java.util.List;

import org.mapstruct.Mapper;

import aresain.loldatastats.lolstat.statanalysis.dto.match.MatchAnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.match.ObjectiveAnalysisDto;
import aresain.loldatastats.lolstat.statanalysis.dto.match.SummaryAnalysisDto;

@Mapper(componentModel = "spring")
public interface MatchAnalysisMapper {
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

	default MatchAnalysisListDto toDtoWithRelations(List<ObjectiveAnalysisDto> objectiveAnalysisDto,
			SummaryAnalysisDto summaryAnalysisDto) {
		return MatchAnalysisListDto.builder()
				.objectiveAnalysisDto(objectiveAnalysisDto)
				.summaryAnalysisDto(summaryAnalysisDto)
				.build();
	}
}
