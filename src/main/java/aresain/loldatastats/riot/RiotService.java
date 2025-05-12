package aresain.loldatastats.riot;

import aresain.loldatastats.datastats.Player;
import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.match.MatchDto;
import aresain.loldatastats.riot.dto.timeline.TimelineDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiotService {
	private final RiotClient riotClient;
	private final PlayerRepository playerRepository;

	public void	createAccount(String gameName, String tagLine) {
		Player player = playerRepository.findByGameNameAndTagLine(gameName, tagLine).orElseGet(() -> {
			AccountDto accountDto = riotClient.getAccountByRiotId(gameName, tagLine);
			Player firstSearchPlayer = new Player(accountDto.getPuuid(), accountDto.getGameName(),
				accountDto.getTagLine());
			return playerRepository.save(firstSearchPlayer);
		});
	}

	public List<String> getMatchIdByPuuid(String puuid, Long startTime, Long endTime, Integer queue, String type,
		Integer start, Integer count) {
		return riotClient.getMatchIdByPuuid(puuid, startTime, endTime, queue, type, start, count);
	}

	public MatchDto getMatchById(String matchId) {
		return riotClient.getMatchById(matchId);
	}

	public TimelineDto getTimelineByMatchId(String matchId) {
		return riotClient.getTimelineByMatchId(matchId);
	}
}
