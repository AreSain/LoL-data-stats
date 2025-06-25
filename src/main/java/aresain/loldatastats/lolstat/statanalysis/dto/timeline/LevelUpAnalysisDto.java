package aresain.loldatastats.lolstat.statanalysis.dto.timeline;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LevelUpAnalysisDto {
	private double winFirst2LevelRate;
	private double loseFirst2LevelRate;
	private double winFirst6LevelRate;
	private double loseFirst6LevelRate;
	private int winCount;
	private int loseCount;
}
