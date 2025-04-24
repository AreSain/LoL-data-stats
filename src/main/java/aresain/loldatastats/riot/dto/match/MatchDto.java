package aresain.loldatastats.riot.dto.match;

import aresain.loldatastats.riot.dto.match.InfoDto;
import aresain.loldatastats.riot.dto.match.MetadataDto;
import lombok.Getter;

@Getter
public class MatchDto {
	MetadataDto metadata;
	InfoDto info;
}
