package aresain.loldatastats.loldata.ban.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BanInfoDto {
    private Long banId;
    private int teamId;
    private int championId;
    private int pickTurn;
} 