package aresain.loldatastats.loldata.player;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/players")
@RequiredArgsConstructor
public class PlayerController implements PlayerApi {
	private final PlayerService playerService;

	@Override
	@PatchMapping("/{gameName}/{tagLine}")
	public PlayerDto syncPlayer(@PathVariable String gameName, @PathVariable String tagLine) {
		return playerService.syncPlayerWithRiot(gameName, tagLine);
	}
	
}
