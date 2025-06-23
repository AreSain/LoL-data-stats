package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillPointEventInfoDto {
    private String levelUpType;
    private Byte participantId;
    private Byte skillSlot;
    private Integer timestamp;
    private String type;
} 
