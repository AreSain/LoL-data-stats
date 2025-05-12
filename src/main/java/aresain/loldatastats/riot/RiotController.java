package aresain.loldatastats.riot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/riot")
public class RiotController {
	private final RiotService riotService;

	@Operation(
		summary = "Riot 유저 조회 및 Player 생성",
		description = "Riot API를 이용해 유저를 조회하고, DB에 Player의 puuid를 저장합니다.",
		responses = {
			@ApiResponse(responseCode = "201", description = "생성 성공")
		}
	)
	@PostMapping("/account")
	public ResponseEntity<Void> createAccount(@RequestParam String gameName, @RequestParam String tagLine) {
		riotService.createAccount(gameName, tagLine);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
