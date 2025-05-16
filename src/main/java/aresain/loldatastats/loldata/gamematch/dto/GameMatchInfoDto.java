package aresain.loldatastats.loldata.gamematch.dto;

import java.util.List;

import aresain.loldatastats.loldata.ban.dto.BanInfoDto;
import aresain.loldatastats.loldata.objective.dto.ObjectiveInfoDto;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameMatchInfoDto {
    private String matchId;
    private String endOfGameResult;
    private Long gameCreation;
    private Long gameStartTimestamp;
    private Long gameEndTimestamp;
    private int gameDuration;
    private String gameMode;
    private String gameType;
    private int mapId;
    private String gameVersion;
    private int winTeamId;
    
    private List<BanInfoDto> bans;
    private List<ObjectiveInfoDto> objectives;
    private List<ParticipantSummaryDto> participants;
}
