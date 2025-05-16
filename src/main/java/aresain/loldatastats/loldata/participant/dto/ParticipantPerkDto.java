package aresain.loldatastats.loldata.participant.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParticipantPerkDto {
    private Long participantId;
    private String matchId;
    private String styleType;
    private int style;
    private int perkId;
    private Integer var1;
    private Integer var2;
    private Integer var3;
} 
