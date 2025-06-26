package aresain.loldatastats.lolstat.statanalysis.dto.timeline;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimelineAnalysisListDto {
	private LevelUpAnalysisDto levelUpAnalysisDto;
	private List<KillAnalysisDto> killAnalysisDto;
}
