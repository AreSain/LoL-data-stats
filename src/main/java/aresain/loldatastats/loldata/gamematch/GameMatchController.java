package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/puuid/{puuid}")
    public ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(
        @PathVariable String puuid,
        @RequestParam(required = false) String type,
        @RequestParam(required = false, defaultValue = "0") Integer start,
        @RequestParam(required = false, defaultValue = "10") Integer count) {
        return ResponseEntity.ok(gameMatchService.saveOrFindGameInfoList(puuid, type, start, count));
    }
} 
