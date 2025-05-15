package aresain.loldatastats.riotservice;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.AccountDto;
import aresain.loldatastats.riot.dto.match.MatchDto;
import aresain.loldatastats.riot.dto.timeline.TimelineDto;

@SpringBootTest
public class RiotServiceTest {
	@Autowired
	private RiotService riotService;

	@Value("${feign.riot.api.key}")
	String apiKey;

	@Test
	@DisplayName("Riot ID로 계정 정보 가져오기")
	void testGetAccountByRiotId() {
		// given
		String gameName = "정승수";
		String tagLine = "Mk2";

		// when
		AccountDto account = riotService.getAccountByGameNameAndTagLine(gameName, tagLine);

		// then
		assertThat(account).isNotNull();
		assertThat(account.getGameName()).isEqualTo(gameName);
		assertThat(account.getTagLine()).isEqualTo(tagLine);
		assertThat(account.getPuuid()).isNotNull();
	}

	@Test
	@DisplayName("PUUID로 매치 ID 가져오기")
	void testGetMatchIdByPuuid() {
		// given
		String puuid = "VaBYPz6sXMJea-dHPC8hnmBLKj7TRnhVo1dFNNB2c_446E3ZXeM2dDqOrwsRK6_CdwcXrVKOctRD0w";
		Long startTime = null;
		Long endTime = null;
		Integer queue = 420;
		String type = null;
		Integer start = 0;
		Integer count = 20;

		// when
		List<String> matchIds = riotService.getMatchIdListByPuuid(puuid, startTime, endTime, queue, type, start, count);

		// then
		assertThat(matchIds).isNotEmpty();
	}

	@Test
	@DisplayName("매치 ID로 매치 정보 가져오기")
	void testGetMatchById() {
		// given
		String matchId = "KR_7571479972";

		// when
		MatchDto matchDto = riotService.getMatchById(matchId);

		// then
		assertThat(matchDto).isNotNull();
	}

	@Test
	@DisplayName("매치 ID로 타임라인 정보 가져오기")
	void testGetTimelineByMatchId() {
		// given
		String matchId = "KR_7571479972";

		// when
		TimelineDto timelineDto = riotService.getTimelineByMatchId(matchId);

		// then
		assertThat(timelineDto).isNotNull();
		assertThat(timelineDto.getInfo().getFrames()).isNotEmpty();
	}
}
