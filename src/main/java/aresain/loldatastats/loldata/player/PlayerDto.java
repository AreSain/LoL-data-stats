package aresain.loldatastats.loldata.player;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayerDto {
	private String puuid;
	private String gameName;
	private String tagLine;

	@Builder
	public PlayerDto(String puuid, String gameName, String tagLine) {
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}
}
