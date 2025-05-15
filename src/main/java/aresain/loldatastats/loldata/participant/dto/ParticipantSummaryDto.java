package aresain.loldatastats.loldata.participant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantSummaryDto {
    private Long participantId;
    private String matchId;
    private String puuid;
    private String riotIdGameName;
    private String riotIdTagline;
    private Integer profileIcon;
    private int teamId;
    private int championId;
    private String championName;
    private Integer champLevel;
    private Short kills;
    private Short deaths;
    private Short assists;
    private Double kda;
    private Integer totalMinionsKilled;
    private Integer goldEarned;
    private Integer item0;
    private Integer item1;
    private Integer item2;
    private Integer item3;
    private Integer item4;
    private Integer item5;
    private Integer item6;
    private Integer summoner1Id;
    private Integer summoner2Id;
    private Integer visionScore;
    private Integer wardsPlaced;
    private Integer wardsKilled;
    private Integer stealthWardsPlaced;
    private Integer detectorWardsPlaced;
    private Integer visionWardsBought;
    private String teamPosition;
    private String individualPosition;
    private Integer physicalDamageDealtToChampions;
    private Integer magicDamageDealtToChampions;
    private Integer trueDamageDealtToChampions;
    private Integer totalDamageTaken;
    private Integer doubleKills;
    private Integer tripleKills;
    private Integer quadraKills;
    private Integer pentaKills;
    private Boolean win;
} 
