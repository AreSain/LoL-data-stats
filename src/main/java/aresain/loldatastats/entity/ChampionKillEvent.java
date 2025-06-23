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
public class ChampionKillEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id", length = 20, nullable = false)
    private String matchId;

    @Column(name = "bounty", nullable = false)
    private Short bounty;

    @Column(name = "kill_streak_length", nullable = false)
    private Byte killStreakLength;

    @Column(name = "killer_id", nullable = false)
    private Byte killerId;

    @Column(name = "position_x", nullable = false)
    private Short positionX;

    @Column(name = "position_y", nullable = false)
    private Short positionY;

    @Column(name = "shutdown_bounty", nullable = false)
    private Short shutdownBounty;

    @Column(name = "timestamp", nullable = false)
    private Integer timestamp;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @Column(name = "assisting_participant_ids", columnDefinition = "TEXT", nullable = false)
    private String assistingParticipantIds;

    @Column(name = "victim_id", nullable = false)
    private Byte victimId;
}
