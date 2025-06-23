package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class GameMatchController {
    private final GameMatchService gameMatchService;

    @PostMapping("/info")
    public ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(
        @RequestParam                           String puuid,
        @RequestParam(defaultValue = "ranked")  String type,
        @RequestParam(defaultValue = "0")       Integer start,
        @RequestParam(defaultValue = "10")      Integer count) {
        return ResponseEntity.ok(gameMatchService.saveOrFindGameInfoList(puuid, type, start, count));
    }
}
