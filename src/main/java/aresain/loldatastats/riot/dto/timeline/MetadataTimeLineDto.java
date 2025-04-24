package aresain.loldatastats.riot.dto.timeline;

import java.util.List;

import lombok.Getter;

@Getter
public class MetadataTimeLineDto {
	private String dataVersion;
	private String matchId;
	private List<String> participants;
}



