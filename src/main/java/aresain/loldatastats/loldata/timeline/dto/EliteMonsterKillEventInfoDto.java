package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EliteMonsterKillEventInfoDto {
    private Short bounty;
    private Byte killerId;
    private String monsterType;
    private Short positionX;
    private Short positionY;
    private Integer timestamp;
    private String type;
} 
