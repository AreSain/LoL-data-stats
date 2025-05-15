package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;

import aresain.loldatastats.common.dto.ListDto;

public interface GameMatchApi {
    ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(String puuid);
} 