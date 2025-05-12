package aresain.loldatastats.riot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/riot")
public class RiotController {
	private final RiotService riotService;

	@PostMapping("/account")
	public ResponseEntity<Void> createAccount(@RequestParam String gameName, @RequestParam String tagLine) {
		riotService.createAccount(gameName, tagLine);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
