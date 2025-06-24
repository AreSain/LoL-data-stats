package aresain.loldatastats.lolstat.statanalysis.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummaryAnalysisDto {
	private double winAvgKill;
	private double winAvgDeath;
	private double winAvgAssist;
	private double winAvgVisionScore;
	private double winAvgWardPlaced;
	private double winAvgWardKilled;
	private double winAvgDetectionWardPlaced;

	private double loseAvgKill;
	private double loseAvgDeath;
	private double loseAvgAssist;
	private double loseAvgVisionScore;
	private double loseAvgWardPlaced;
	private double loseAvgWardKilled;
	private double loseAvgDetectionWardPlaced;
}
