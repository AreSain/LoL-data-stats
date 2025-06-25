package aresain.loldatastats.lolstat.statanalysis.dto;

import aresain.loldatastats.lolstat.statanalysis.dto.match.MatchAnalysisListDto;
import aresain.loldatastats.lolstat.statanalysis.dto.timeline.TimelineAnalysisListDto;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnalysisListDto {
	private MatchAnalysisListDto matchAnalysisListDto;
	private TimelineAnalysisListDto timelineAnalysisListDto;
}
