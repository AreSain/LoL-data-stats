package aresain.loldatastats.loldata.timeline.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WardEventInfoDto {
    private Integer timestamp;
    private Byte creatorId;
    private Byte killerId;
    private String type;
    private String wardType;
} 
