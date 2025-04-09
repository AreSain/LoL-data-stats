package aresain.loldatastats.riot;

import aresain.loldatastats.riot.dto.AccountDto;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RiotService {
	private final RiotClient riotClient;

	public AccountDto getAccountByRiotId(String gameName, String tagLine, String apiKey) {
		return riotClient.getAccountByRiotId(gameName, tagLine);
	}

	public List<String> getMatchIdByPuuid(String puuid, long startTime, long endTime, int queue, String type,
		int start, int count, String apiKey) {
		return riotClient.getMatchIdByPuuid(puuid, startTime, endTime, queue, type, start, count);
	}
}
