package aresain.loldatastats.riot.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MetadataDto {
	private String dataVersion;
	private String matchId;
	private List<String> participants;
}


