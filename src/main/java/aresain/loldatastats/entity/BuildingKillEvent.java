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
public class BuildingKillEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id", length = 20, nullable = false)
    private String matchId;

    @Column(name = "bounty", nullable = false)
    private Short bounty;

    @Column(name = "building_type", length = 20, nullable = false)
    private String buildingType;

    @Column(name = "killer_id", nullable = false)
    private Byte killerId;

    @Column(name = "lane_type", length = 10, nullable = false)
    private String laneType;

    @Column(name = "position_x", nullable = false)
    private Short positionX;

    @Column(name = "position_y", nullable = false)
    private Short positionY;

    @Column(name = "team_id", nullable = false)
    private Short teamId;

    @Column(name = "timestamp", nullable = false)
    private Integer timestamp;

    @Column(name = "tower_type", length = 20, nullable = false)
    private String towerType;

    @Column(name = "type", length = 20, nullable = false)
    private String type;
}
