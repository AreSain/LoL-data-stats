package aresain.loldatastats.lolstat.statanalysis.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnalysisListDto {
	List<ObjectiveAnalysisDto> objectiveAnalysisDto;
}
