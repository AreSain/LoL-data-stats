package aresain.loldatastats.loldata.player;

import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Player", description = "플레이어 관리 API")
public interface PlayerApi {
    
    @Operation(
        summary = "플레이어 정보 동기화",
        description = """
            Riot API를 통해 플레이어 정보를 조회하고 DB와 동기화합니다.
            - 플레이어가 DB에 없는 경우: 새로운 플레이어로 생성
            - 플레이어가 DB에 있는 경우: Riot 정보와 비교하여 변경사항이 있을 때만 업데이트
            """
    )
    ResponseEntity<PlayerDto> syncPlayer(String gameName, String tagLine);
} 
