package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Player extends BaseEntity {
	@Id
	@Column(name = "puuid", length = 100)
	private String puuid;

	@Column(name = "game_name", length = 50, nullable = false)
	private String gameName;

	@Column(name = "tag_line", length = 8, nullable = false)
	private String tagLine;

	public Player(String puuid, String gameName, String tagLine) {
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}

	public void updateGameName(String gameName) {
		this.gameName = gameName;
	}

	public void updateTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
}
