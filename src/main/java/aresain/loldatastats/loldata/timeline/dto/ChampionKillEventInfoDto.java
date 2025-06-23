package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChampionKillEventInfoDto {
    private Short bounty;
    private Byte killStreakLength;
    private Byte killerId;
    private Short positionX;
    private Short positionY;
    private Short shutdownBounty;
    private Integer timestamp;
    private String type;
    private String assistingParticipantIds;
    private Byte victimId;
} 
