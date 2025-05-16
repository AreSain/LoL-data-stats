package aresain.loldatastats.loldata.gamematch;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.GameMatch;
import aresain.loldatastats.loldata.ban.dto.BanInfoDto;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import aresain.loldatastats.loldata.objective.dto.ObjectiveInfoDto;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;
import aresain.loldatastats.riot.dto.match.InfoDto;

@Mapper(componentModel = "spring")
public interface GameMatchMapper {
    
    @Mapping(target = "bans", ignore = true)
    @Mapping(target = "objectives", ignore = true)
    @Mapping(target = "participants", ignore = true)
    GameMatchInfoDto toDto(GameMatch gameMatch);

    @Mapping(target = "matchId", source = "matchId")
    @Mapping(target = "gameDuration", expression = "java((int) info.getGameDuration())")
    @Mapping(target = "winTeamId", source = "winTeamId")
    GameMatch toEntity(InfoDto info, String matchId, int winTeamId);

    default GameMatchInfoDto toDtoWithRelations(GameMatch gameMatch, List<BanInfoDto> bans,
        List<ObjectiveInfoDto> objectives, List<ParticipantSummaryDto> participants) {
        GameMatchInfoDto dto = toDto(gameMatch);
        dto.setArgs(bans, objectives, participants);
        return dto;
    }
}
