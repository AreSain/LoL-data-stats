package aresain.loldatastats.riot;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.MatchDto;

@FeignClient(name = "riotClient", url = "${feign.riot.api.url.asia}", configuration = RiotClientConfig.class)
public interface RiotClient {

	@GetMapping("/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
	AccountDto getAccountByRiotId(
		@PathVariable("gameName") String gameName,
		@PathVariable("tagLine") String tagLine
	);

	@GetMapping("/lol/match/v5/matches/by-puuid/{puuid}/ids")
	List<String> getMatchIdByPuuid(
		@PathVariable("puuid") String puuid,
		@RequestParam(value = "startTime", required = false) Long startTime,
		@RequestParam(value = "endTime", required = false) Long endTime,
		@RequestParam(value = "queue", required = false) Integer queue,
		@RequestParam(value = "type", required = false) String type,
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "count", defaultValue = "20") Integer count
	);

	@GetMapping("/lol/match/v5/matches/{matchId}")
	MatchDto getMatchById(
		@PathVariable("matchId") String matchId
	);

}
