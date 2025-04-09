package aresain.loldatastats.riot.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MetadataDto {
	private String dataVersion;
	private String matchId;
	private List<String> participants;

	@Override
	public String toString() {
		return "MetadataDto{" +
			"dataVersion='" + dataVersion + '\'' +
			", matchId='" + matchId + '\'' +
			", participants=" + participants +
			'}';
	}
}


