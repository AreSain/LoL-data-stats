package aresain.loldatastats.loldata.ban;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import aresain.loldatastats.entity.Ban;
import aresain.loldatastats.loldata.ban.dto.BanInfoDto;
import aresain.loldatastats.riot.dto.match.BanDto;
import aresain.loldatastats.riot.dto.match.TeamDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BanService {
    private final BanRepository banRepository;
    private final BanMapper banMapper;

    public List<Ban> saveBans(String matchId, List<TeamDto> teams) {
        List<Ban> bans = teams.stream()
            .filter(team -> team.getBans() != null)
            .flatMap(team -> team.getBans().stream()
                .map(banDto -> createBan(matchId, team.getTeamId(), banDto)))
            .collect(Collectors.toList());
        
        return banRepository.saveAll(bans);
    }

    private Ban createBan(String matchId, int teamId, BanDto banDto) {
        return Ban.builder()
            .matchId(matchId)
            .teamId(teamId)
            .championId(banDto.getChampionId())
            .pickTurn(banDto.getPickTurn())
            .build();
    }

    public List<BanInfoDto> findBansByMatchId(String matchId) {
        return banRepository.findByMatchId(matchId).stream()
            .map(banMapper::toDto)
            .collect(Collectors.toList());
    }
} 