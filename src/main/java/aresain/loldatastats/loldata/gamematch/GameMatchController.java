package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/by-puuid/{puuid}")
    public ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(@PathVariable String puuid, @RequestParam(required = false) String type) {
        return ResponseEntity.ok(gameMatchService.saveOrFindGameInfoList(puuid, type));
    }
} 
