package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;

public interface GameMatchApi {
    ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(String puuid);
} 
