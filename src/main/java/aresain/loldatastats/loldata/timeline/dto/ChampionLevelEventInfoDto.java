package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChampionLevelEventInfoDto {
    private Byte level;
    private Byte participantId;
    private Integer timestamp;
    private String type;
} 
