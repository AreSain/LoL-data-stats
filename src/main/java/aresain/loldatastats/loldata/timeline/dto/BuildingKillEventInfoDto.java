package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BuildingKillEventInfoDto {
    private Short bounty;
    private String buildingType;
    private Byte killerId;
    private String laneType;
    private Short positionX;
    private Short positionY;
    private Byte teamId;
    private Integer timestamp;
    private String towerType;
    private String type;
} 
