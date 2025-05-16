package aresain.loldatastats.loldata.player;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController implements PlayerApi {
	private final PlayerService playerService;

	@Override
	@PatchMapping("/{gameName}/{tagLine}")
	public ResponseEntity<PlayerDto> syncPlayer(@PathVariable String gameName, @PathVariable String tagLine) {
		PlayerDto playerDto = playerService.syncPlayerWithRiot(gameName, tagLine);
		return ResponseEntity.status(HttpStatus.CREATED).body(playerDto);
	}
	
}
