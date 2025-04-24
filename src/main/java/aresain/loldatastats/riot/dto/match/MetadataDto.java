package aresain.loldatastats.riot.dto.match;

import java.util.List;

import lombok.Getter;

@Getter
public class MetadataDto {
	private String dataVersion;
	private String matchId;
	private List<String> participants;
}


