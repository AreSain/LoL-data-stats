package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aresain.loldatastats.common.dto.ListDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class GameMatchController implements GameMatchApi {
    private final GameMatchService gameMatchService;

    @Override
    @PatchMapping("/{puuid}")
    public ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(@PathVariable String puuid) {
        return ResponseEntity.ok(gameMatchService.saveOrFindGameInfoList(puuid));
    }
} 