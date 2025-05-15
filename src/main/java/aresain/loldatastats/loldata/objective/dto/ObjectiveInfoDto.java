package aresain.loldatastats.loldata.objective.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectiveInfoDto {
    private Long objectiveId;
    private int teamId;
    private String type;
    private int kills;
} 