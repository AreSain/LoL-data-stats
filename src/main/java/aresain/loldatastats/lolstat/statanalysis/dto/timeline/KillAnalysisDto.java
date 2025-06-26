package aresain.loldatastats.lolstat.statanalysis.dto.timeline;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KillAnalysisDto {
    private String lane;
    private double averageDeath;
    private double soloKillCount;
    private double gankCount;
    private double midCount;
    private double supportCount;
}
