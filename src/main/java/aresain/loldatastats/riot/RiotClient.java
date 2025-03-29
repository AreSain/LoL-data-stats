package aresain.loldatastats.riot;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import aresain.loldatastats.riot.dto.AccountDto;

@FeignClient(name = "riotClient")
public interface RiotClient {
	@GetMapping("https://{region}.api.riotgames.com/riot/account/v1/accounts/by-riot-id/{gameName}/{tagLine}")
	AccountDto getAccountByRiotId(@PathVariable("region") String region,
		@PathVariable("gameName") String gameName,
		@PathVariable("tagLine") String tagLine,
		@RequestParam("api_key") String apiKey);

	@GetMapping("https://{region}.api.riotgames.com/lol/match/v5/matches/by-puuid/{puuid}/ids")
	List<String> getMatchIds(@PathVariable("region") String region,
		@PathVariable("puuid") String puuid,
		@RequestParam("start") int start,
		@RequestParam("count") int count,
		@RequestParam("api_key") String apiKey);

	@GetMapping("https://{region}.api.riotgames.com/lol/match/v5/matches/{matchId}")
	String getMatch(@PathVariable("region") String region,
		@PathVariable("matchId") String matchId,
		@RequestParam("api_key") String apiKey);

}
