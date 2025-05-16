package aresain.loldatastats.loldata.gamematch;

import org.springframework.http.ResponseEntity;

import aresain.loldatastats.common.dto.ListDto;
import aresain.loldatastats.loldata.gamematch.dto.GameMatchInfoDto;
import io.swagger.v3.oas.annotations.Operation;

public interface GameMatchApi {
    @Operation(
        summary = "플레이어의 매치 기록 조회",
        description = """
            PUUID로 플레이어의 매치 기록을 조회하고 DB와 동기화합니다.
            - 매치가 DB에 없는 경우: Riot API를 통해 매치 정보를 조회하여 저장
            - 매치가 DB에 있는 경우: 저장된 매치 정보를 반환
            """
    )
    ResponseEntity<ListDto<GameMatchInfoDto>> saveOrFindGameInfoList(String puuid, String type, Integer start, Integer count);
} 
