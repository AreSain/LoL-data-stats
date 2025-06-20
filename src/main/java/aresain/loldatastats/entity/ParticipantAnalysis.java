package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ParticipantId.class)
public class ParticipantAnalysis extends BaseEntity {
    @Id
    @Column(name = "participant_id")
    private Long participantId;

    @Id
    @Column(name = "match_id", length = 20)
    private String matchId;

    @Column(name = "damage_dealt_to_buildings")
    private Integer damageDealtToBuildings;

    @Column(name = "damage_dealt_to_objectives")
    private Integer damageDealtToObjectives;

    @Column(name = "damage_dealt_to_turrets")
    private Integer damageDealtToTurrets;

    @Column(name = "total_time_cc_dealt")
    private Integer totalTimeCCDealt;

    @Column(name = "jungle_cs_before_10_min")
    private Float jungleCSBefore10Min;

    @Column(name = "lane_minions_first_10_min")
    private Integer laneMinionFirst10Min;

    @Column(name = "scuttle_crab_kills")
    private Integer scuttleCrabKills;

    @Column(name = "quick_solo_kills")
    private Integer quickSoloKills;

    @Column(name = "kills_on_laners_early_jungle")
    private Integer killsOnLanersEarlyJungle;

    @Column(name = "stat_perks_defense", nullable = true)
    private Integer statPerksDefense;

    @Column(name = "stat_perks_flex", nullable = true)
    private Integer statPerksFlex;

    @Column(name = "stat_perks_offense", nullable = true)
    private Integer statPerksOffense;
} 
