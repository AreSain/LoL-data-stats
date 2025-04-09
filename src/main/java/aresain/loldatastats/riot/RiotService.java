package aresain.loldatastats.riot;

import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.MatchDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiotService {
	private final RiotClient riotClient;

	public AccountDto getAccountByRiotId(String gameName, String tagLine) {
		return riotClient.getAccountByRiotId(gameName, tagLine);
	}

	public List<String> getMatchIdByPuuid(String puuid, Long startTime, Long endTime, Integer queue, String type,
		Integer start, Integer count) {
		return riotClient.getMatchIdByPuuid(puuid, startTime, endTime, queue, type, start, count);
	}

	public MatchDto getMatchById(String matchId) {
		return riotClient.getMatchById(matchId);
	}
}
