package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ban extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ban_id")
	private Long banId;

	@Column(name = "match_id", length = 20, nullable = false)
	private String matchId;

	@Column(name = "team_id", nullable = false)
	private int teamId;

	@Column(name = "champion_id", nullable = false)
	private int championId;

	@Column(name = "pick_turn", nullable = false)
	private int pickTurn;
}
