package aresain.loldatastats.loldata.gamematch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.entity.GameMatch;
import aresain.loldatastats.loldata.ban.BanService;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import aresain.loldatastats.loldata.objective.ObjectiveService;
import aresain.loldatastats.loldata.participant.ParticipantService;
import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.match.InfoDto;
import aresain.loldatastats.riot.dto.match.MatchDto;
import aresain.loldatastats.riot.dto.match.TeamDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameMatchService {
    private final RiotService riotService;
    private final GameMatchMapper gameMatchMapper;
    private final GameMatchRepository gameMatchRepository;
    private final BanService banService;
    private final ObjectiveService objectiveService;
    private final ParticipantService participantService;

    @Transactional
    public ListDto<GameMatchInfoDto> saveOrFindGameInfoList(String puuid, String type) {
        List<String> matchIds = riotService.getMatchIdListByPuuid(puuid, null, null, null, type, 0, 10);
        List<GameMatchInfoDto> matchInfoList = matchIds.stream()
            .map(this::saveOrFindGameInfo)
            .collect(Collectors.toList());
        return ListDto.of(matchInfoList);
    }

    private GameMatchInfoDto saveOrFindGameInfo(String matchId) {
        GameMatch gameMatch = gameMatchRepository.findByMatchId(matchId);
        if (gameMatch == null) {
            gameMatch = saveMatch(matchId);
        }
        return createGameMatchInfoDto(gameMatch, matchId);
    }

    private GameMatchInfoDto createGameMatchInfoDto(GameMatch gameMatch, String matchId) {
        return gameMatchMapper.toDtoWithRelations(
            gameMatch,
            banService.findBansByMatchId(matchId),
            objectiveService.findObjectivesByMatchId(matchId),
            participantService.findParticipantsByMatchId(matchId)
        );
    }

    private GameMatch saveMatch(String matchId) {
        MatchDto matchDto = riotService.getMatchById(matchId);
        InfoDto info = matchDto.getInfo();
        List<TeamDto> teams = info.getTeams();

        GameMatch gameMatch = createAndSaveGameMatch(matchId, info, teams);
        saveRelatedEntities(matchId, teams, info);

        return gameMatch;
    }

    private GameMatch createAndSaveGameMatch(String matchId, InfoDto info, List<TeamDto> teams) {
        int winTeamId = findWinningTeamId(teams);
        GameMatch gameMatch = gameMatchMapper.toEntity(info, matchId, winTeamId);
        return gameMatchRepository.save(gameMatch);
    }

    private void saveRelatedEntities(String matchId, List<TeamDto> teams, InfoDto info) {
        banService.saveBans(matchId, teams);
        objectiveService.saveObjectives(matchId, teams);
        participantService.saveParticipants(matchId, info.getParticipants());
    }

    private int findWinningTeamId(List<TeamDto> teams) {
        return teams.stream()
            .filter(TeamDto::isWin)
            .findFirst()
            .map(TeamDto::getTeamId)
            .orElse(0);
    }
} 
