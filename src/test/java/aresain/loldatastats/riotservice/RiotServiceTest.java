package aresain.loldatastats.riotservice;

import static org.assertj.core.api.Assertions.*;

import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.MatchDto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RiotServiceTest {
	@Autowired
	private RiotService riotService;

	@Value("${feign.riot.api.key}")
	String apiKey;

	@Test
	@DisplayName("Riot ID로 계정 정보 가져오기")
	void testGetAccountByRiotId() {
		String gameName = "정승수";
		String tagLine = "Mk2";

		AccountDto account = riotService.getAccountByRiotId(gameName, tagLine);
		System.out.println("Account getPuuid: " + account.getPuuid());
		System.out.println("Account getGameName: " + account.getGameName());
		System.out.println("Account getTagLine: " + account.getTagLine());

		assertThat(account.getPuuid()).isNotNull();
		assertThat(account.getGameName()).isEqualTo(gameName);
		assertThat(account.getTagLine()).isEqualTo(tagLine);
	}

	@Test
	@DisplayName("PUUID로 매치 ID 가져오기")
	void testGetMatchIdByPuuid() {
		String puuid = "VaBYPz6sXMJea-dHPC8hnmBLKj7TRnhVo1dFNNB2c_446E3ZXeM2dDqOrwsRK6_CdwcXrVKOctRD0w";
		Long startTime = null;
		Long endTime = null;
		Integer queue = 420;
		String type = null;
		Integer start = 0;
		Integer count = 20;

		List<String> matchIds = riotService.getMatchIdByPuuid(puuid, startTime, endTime, queue, type, start, count);
		System.out.println("Match IDs: " + matchIds);

		assertThat(matchIds).isNotEmpty();
	}

	@Test
	@DisplayName("매치 ID로 매치 정보 가져오기")
	void testGetMatchById() {
		String matchId = "KR_7571479972";

		MatchDto matchDto = riotService.getMatchById(matchId);
		System.out.println("MatchDto :" + matchDto);

		assertThat(matchDto).isNotNull();
	}
}

