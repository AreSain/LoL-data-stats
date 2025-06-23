package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEventInfoDto {
    private Short itemId;
    private Short afterId;
    private Short beforeId;
    private Short goldGain;
    private Byte participantId;
    private Integer timestamp;
    private String type;
} 
