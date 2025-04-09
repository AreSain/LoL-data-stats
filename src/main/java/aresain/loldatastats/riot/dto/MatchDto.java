package aresain.loldatastats.riot.dto;

import lombok.Getter;

@Getter
public class MatchDto {
	MetadataDto metadata;
	InfoDto info;

	@Override
	public String toString() {
		return "MatchDto{" +
			"metadata=" + metadata +
			", info=" + info +
			'}';
	}
}
