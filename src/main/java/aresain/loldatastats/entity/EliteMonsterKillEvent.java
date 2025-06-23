package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EliteMonsterKillEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id", length = 20, nullable = false)
    private String matchId;

    @Column(name = "bounty", nullable = false)
    private Short bounty;

    @Column(name = "killer_id", nullable = false)
    private Byte killerId;

    @Column(name = "monster_type", length = 20, nullable = false)
    private String monsterType;

    @Column(name = "position_x", nullable = false)
    private Short positionX;

    @Column(name = "position_y", nullable = false)
    private Short positionY;

    @Column(name = "timestamp", nullable = false)
    private Integer timestamp;

    @Column(name = "type", length = 20, nullable = false)
    private String type;
}
