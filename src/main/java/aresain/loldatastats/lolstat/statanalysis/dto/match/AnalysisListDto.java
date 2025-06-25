package aresain.loldatastats.lolstat.statanalysis.dto.match;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnalysisListDto {
	List<ObjectiveAnalysisDto> objectiveAnalysisDto;
	SummaryAnalysisDto summaryAnalysisDto;
}
