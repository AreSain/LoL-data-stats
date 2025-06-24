package aresain.loldatastats.lolstat.statanalysis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectiveAnalysisDto {
	private String type;
	private double winAvgKills;
	private double loseAvgKills;
}
