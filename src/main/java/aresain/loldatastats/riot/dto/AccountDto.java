package aresain.loldatastats.riot.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountDto {
	private String puuid;
	private String gameName;
	private String tagLine;

	@Builder
	public AccountDto(String puuid, String gameName, String tagLine) {
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}
}
