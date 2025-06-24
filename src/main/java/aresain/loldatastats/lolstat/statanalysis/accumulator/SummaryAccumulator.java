package aresain.loldatastats.lolstat.statanalysis.accumulator;
import aresain.loldatastats.entity.ParticipantSummary;
import lombok.Getter;

@Getter
public class SummaryAccumulator {
	private double winVisionSum = 0, winWardPlacedSum = 0, winWardKilledSum = 0, winDetectionSum = 0;
	private double winKillSum = 0, winDeathSum = 0, winAssistSum = 0;
	private double loseVisionSum = 0, loseWardPlacedSum = 0, loseWardKilledSum = 0, loseDetectionSum = 0;
	private double loseKillSum = 0, loseDeathSum = 0, loseAssistSum = 0;
	private int winCount = 0, loseCount = 0;

	public void accumulate(ParticipantSummary s) {
		if (s.getWin()) {
			winKillSum += s.getKills();
			winDeathSum += s.getDeaths();
			winAssistSum += s.getAssists();
			winVisionSum += s.getVisionScore();
			winWardPlacedSum += s.getWardsPlaced();
			winWardKilledSum += s.getWardsKilled();
			winDetectionSum += s.getDetectorWardsPlaced();
			winCount++;
		} else {
			loseKillSum += s.getKills();
			loseDeathSum += s.getDeaths();
			loseAssistSum += s.getAssists();
			loseVisionSum += s.getVisionScore();
			loseWardPlacedSum += s.getWardsPlaced();
			loseWardKilledSum += s.getWardsKilled();
			loseDetectionSum += s.getDetectorWardsPlaced();
			loseCount++;
		}
	}

	public void combine(SummaryAccumulator other) {
		winKillSum += other.winKillSum;
		winDeathSum += other.winDeathSum;
		winAssistSum += other.winAssistSum;
		winVisionSum += other.winVisionSum;
		winWardPlacedSum += other.winWardPlacedSum;
		winWardKilledSum += other.winWardKilledSum;
		winDetectionSum += other.winDetectionSum;
		winCount += other.winCount;

		loseKillSum += other.loseKillSum;
		loseDeathSum += other.loseDeathSum;
		loseAssistSum += other.loseAssistSum;
		loseVisionSum += other.loseVisionSum;
		loseWardPlacedSum += other.loseWardPlacedSum;
		loseWardKilledSum += other.loseWardKilledSum;
		loseDetectionSum += other.loseDetectionSum;
		loseCount += other.loseCount;
	}

	public double getWinAvgKill() {
		return winCount == 0 ? 0 : winKillSum / winCount;
	}
	public double getWinAvgDeath() {
		return winCount == 0 ? 0 : winDeathSum / winCount;
	}
	public double getWinAvgAssist() {
		return winCount == 0 ? 0 : winAssistSum / winCount;
	}
	public double getLoseAvgKill() {
		return loseCount == 0 ? 0 : loseKillSum / loseCount;
	}
	public double getLoseAvgDeath() {
		return loseCount == 0 ? 0 : loseDeathSum / loseCount;
	}
	public double getLoseAvgAssist() {
		return loseCount == 0 ? 0 : loseAssistSum / loseCount;
	}
	public double getWinAvgVision() {
		return winCount == 0 ? 0 : winVisionSum / winCount;
	}
	public double getWinAvgWardPlaced() {
		return winCount == 0 ? 0 : winWardPlacedSum / winCount;
	}
	public double getWinAvgWardKilled() {
		return winCount == 0 ? 0 : winWardKilledSum / winCount;
	}
	public double getWinAvgDetectionWardPlaced() {
		return winCount == 0 ? 0 : winDetectionSum / winCount;
	}
	public double getLoseAvgVision() {
		return loseCount == 0 ? 0 : loseVisionSum / loseCount;
	}
	public double getLoseAvgWardPlaced() {
		return loseCount == 0 ? 0 : loseWardPlacedSum / loseCount;
	}
	public double getLoseAvgWardKilled() {
		return loseCount == 0 ? 0 : loseWardKilledSum / loseCount;
	}
	public double getLoseAvgDetectionWardPlaced() {
		return loseCount == 0 ? 0 : loseDetectionSum / loseCount;
	}
}
