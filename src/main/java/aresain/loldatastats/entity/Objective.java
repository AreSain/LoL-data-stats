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
@AllArgsConstructor
@Builder
public class Objective extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "objective_id")
	private Long objectiveId;

	@Column(name = "match_id", length = 20, nullable = false)
	private String matchId;

	@Column(name = "team_id", nullable = false)
	private int teamId;

	@Column(name = "type", length = 20, nullable = false)
	private String type;

	@Column(name = "kills", nullable = false)
	private int kills;
}
