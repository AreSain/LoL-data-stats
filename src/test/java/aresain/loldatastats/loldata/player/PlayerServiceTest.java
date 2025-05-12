package aresain.loldatastats.loldata.player;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.entity.Player;
import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.AccountDto;

@Transactional
@SpringBootTest
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Mock
    private RiotService riotService;

    @Autowired
    private PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(playerService, "riotService", riotService);
    }

    @Test
    @DisplayName("새로운 플레이어 생성 테스트")
    void createNewPlayerTest() {
        // given
        String gameName = "testGameName";
        String tagLine = "test";
        String puuid = "test-puuid-123";

        AccountDto mockAccount = AccountDto.builder()
            .gameName(gameName)
            .tagLine(tagLine)
            .puuid(puuid)
            .build();

        when(riotService.getAccountByGameNameAndTagLine(gameName, tagLine))
            .thenReturn(mockAccount);

        // when
        PlayerDto result = playerService.syncPlayerWithRiot(gameName, tagLine);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getGameName()).isEqualTo(gameName);
        assertThat(result.getTagLine()).isEqualTo(tagLine);
        assertThat(result.getPuuid()).isEqualTo(puuid);

        Optional<Player> savedPlayer = playerRepository.findByGameNameAndTagLine(gameName, tagLine);
        assertThat(savedPlayer).isPresent();
        assertThat(savedPlayer.get().getPuuid()).isEqualTo(puuid);

        verify(riotService).getAccountByGameNameAndTagLine(gameName, tagLine);
    }

    @Test
    @DisplayName("기존 플레이어 정보 업데이트 테스트")
    void updateExistingPlayerTest() {
        // given
        String originalGameName = "testGameName";
        String tagLine = "test";
        String puuid = "test-puuid-456";
        String updatedGameName = "newGameName";

        Player existingPlayer = new Player(puuid, originalGameName, tagLine);
        playerRepository.save(existingPlayer);

        AccountDto mockAccount = AccountDto.builder()
            .gameName(updatedGameName)
            .tagLine(tagLine)
            .puuid(puuid)
            .build();

        when(riotService.getAccountByGameNameAndTagLine(originalGameName, tagLine))
            .thenReturn(mockAccount);

        // when
        PlayerDto result = playerService.syncPlayerWithRiot(originalGameName, tagLine);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getGameName()).isEqualTo(updatedGameName);
        assertThat(result.getTagLine()).isEqualTo(tagLine);
        assertThat(result.getPuuid()).isEqualTo(puuid);

        Optional<Player> updatedPlayer = playerRepository.findByGameNameAndTagLine(updatedGameName, tagLine);
        assertThat(updatedPlayer).isPresent();
        assertThat(updatedPlayer.get().getGameName()).isEqualTo(updatedGameName);

        verify(riotService).getAccountByGameNameAndTagLine(originalGameName, tagLine);
    }

    @Test
    @DisplayName("변경사항이 없는 플레이어 동기화 테스트")
    void syncUnchangedPlayerTest() {
        // given
        String gameName = "testGameName";
        String tagLine = "test";
        String puuid = "test-puuid-789";

        Player existingPlayer = new Player(puuid, gameName, tagLine);
        playerRepository.save(existingPlayer);

        AccountDto mockAccount = AccountDto.builder()
            .gameName(gameName)
            .tagLine(tagLine)
            .puuid(puuid)
            .build();

        when(riotService.getAccountByGameNameAndTagLine(gameName, tagLine))
            .thenReturn(mockAccount);

        // when
        PlayerDto result = playerService.syncPlayerWithRiot(gameName, tagLine);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getGameName()).isEqualTo(gameName);
        assertThat(result.getTagLine()).isEqualTo(tagLine);
        assertThat(result.getPuuid()).isEqualTo(puuid);

        Optional<Player> unchangedPlayer = playerRepository.findByGameNameAndTagLine(gameName, tagLine);
        assertThat(unchangedPlayer).isPresent();
        assertThat(unchangedPlayer.get().getGameName()).isEqualTo(gameName);
        assertThat(unchangedPlayer.get().getTagLine()).isEqualTo(tagLine);

        verify(riotService).getAccountByGameNameAndTagLine(gameName, tagLine);
    }
} 
