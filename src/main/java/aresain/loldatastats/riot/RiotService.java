package aresain.loldatastats.riot;

import java.util.List;

import org.springframework.stereotype.Service;

import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.match.MatchDto;
import aresain.loldatastats.riot.dto.timeline.TimelineDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RiotService {
	private final RiotClient riotClient;

	public AccountDto getAccountByGameNameAndTagLine(String gameName, String tagLine) {
		return riotClient.getAccountByGameNameAndTagLine(gameName, tagLine);
	}

	public List<String> getMatchIdListByPuuid(String puuid, Long startTime, Long endTime, Integer queue, String type,
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
