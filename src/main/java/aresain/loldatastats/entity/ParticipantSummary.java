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
public class ParticipantSummary extends BaseEntity {
    @Id
    @Column(name = "participant_id")
    private Long participantId;

    @Id
    @Column(name = "match_id", length = 20)
    private String matchId;

    @Column(name = "puuid", length = 100, nullable = false)
    private String puuid;

    @Column(name = "riot_id_game_name", length = 50)
    private String riotIdGameName;

    @Column(name = "riot_id_tagline", length = 20)
    private String riotIdTagline;

    @Column(name = "profile_icon")
    private Integer profileIcon;

    @Column(name = "team_id", nullable = false)
    private int teamId;

    @Column(name = "champion_id", nullable = false)
    private int championId;

    @Column(name = "champion_name", length = 50)
    private String championName;

    @Column(name = "champ_level")
    private Integer champLevel;

    @Column(name = "kills")
    private Short kills;

    @Column(name = "deaths")
    private Short deaths;

    @Column(name = "assists")
    private Short assists;

    @Column(name = "total_minions_killed")
    private Integer totalMinionsKilled;

    @Column(name = "gold_earned")
    private Integer goldEarned;

    @Column(name = "item0")
    private Integer item0;

    @Column(name = "item1")
    private Integer item1;

    @Column(name = "item2")
    private Integer item2;

    @Column(name = "item3")
    private Integer item3;

    @Column(name = "item4")
    private Integer item4;

    @Column(name = "item5")
    private Integer item5;

    @Column(name = "item6")
    private Integer item6;

    @Column(name = "summoner1_id")
    private Integer summoner1Id;

    @Column(name = "summoner2_id")
    private Integer summoner2Id;

    @Column(name = "vision_score")
    private Integer visionScore;

    @Column(name = "wards_placed")
    private Integer wardsPlaced;

    @Column(name = "wards_killed")
    private Integer wardsKilled;

    @Column(name = "stealth_wards_placed")
    private Integer stealthWardsPlaced;

    @Column(name = "detector_wards_placed")
    private Integer detectorWardsPlaced;

    @Column(name = "vision_wards_bought")
    private Integer visionWardsBought;

    @Column(name = "team_position", length = 20)
    private String teamPosition;

    @Column(name = "individual_position", length = 20)
    private String individualPosition;

    @Column(name = "physical_damage_dealt_to_champions")
    private Integer physicalDamageDealtToChampions;

    @Column(name = "magic_damage_dealt_to_champions")
    private Integer magicDamageDealtToChampions;

    @Column(name = "true_damage_dealt_to_champions")
    private Integer trueDamageDealtToChampions;

    @Column(name = "total_damage_taken")
    private Integer totalDamageTaken;

    @Column(name = "double_kills")
    private Integer doubleKills;

    @Column(name = "triple_kills")
    private Integer tripleKills;

    @Column(name = "quadra_kills")
    private Integer quadraKills;

    @Column(name = "penta_kills")
    private Integer pentaKills;

    @Column(name = "win")
    private Boolean win;
} 