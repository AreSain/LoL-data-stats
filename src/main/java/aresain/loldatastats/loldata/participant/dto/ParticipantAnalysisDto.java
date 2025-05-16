package aresain.loldatastats.loldata.participant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantAnalysisDto {
    private Long participantId;
    private String matchId;
    private Integer damageDealtToChampions;
    private Integer damageDealtToBuildings;
    private Integer damageDealtToObjectives;
    private Integer damageDealtToTurrets;
    private Integer totalTimeCCDealt;
    private Float jungleCSBefore10Min;
    private Integer laneMinionFirst10Min;
    private Integer scuttleCrabKills;
    private Integer quickSoloKills;
    private Integer killsOnLanersEarlyJungle;
    private Integer statPerksDefense;
    private Integer statPerksFlex;
    private Integer statPerksOffense;
} 
