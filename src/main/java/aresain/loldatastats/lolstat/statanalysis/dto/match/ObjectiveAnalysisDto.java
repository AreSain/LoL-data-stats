package aresain.loldatastats.lolstat.statanalysis.dto.match;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ObjectiveAnalysisDto {
	private String type;
	private double winAvgKills;
	private double loseAvgKills;
}
