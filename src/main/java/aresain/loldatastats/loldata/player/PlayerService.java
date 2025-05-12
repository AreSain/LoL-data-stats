package aresain.loldatastats.loldata.player;

import java.util.Optional;

import org.springframework.stereotype.Service;

import aresain.loldatastats.entity.Player;
import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.AccountDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerService {
	private final RiotService riotService;
	private final PlayerMapper playerMapper;
	private final PlayerRepository playerRepository;

	/**
	 * Riot API를 통해 플레이어 정보를 조회하고 DB와 동기화합니다.
	 * 
	 * @param gameName 라이엇 게임 닉네임
	 * @param tagLine 라이엇 태그라인 (예: KR1, NA1)
	 * @return 동기화된 플레이어 정보
	 * @throws RiotApiException Riot API 호출 중 오류 발생 시
	 * @implNote
	 * - 플레이어가 DB에 없는 경우: 새로운 플레이어로 생성
	 * - 플레이어가 DB에 있는 경우: Riot 정보와 비교하여 변경사항이 있을 때만 업데이트
	 */
	public PlayerDto syncPlayerWithRiot(String gameName, String tagLine) {
		AccountDto riotAccount = fetchRiotAccount(gameName, tagLine);
		return findByGameNameAndTagLine(gameName, tagLine)
			.map(player -> updatePlayerIfChanged(player, riotAccount))
			.orElseGet(() -> createPlayer(riotAccount));
	}

	/**
	 * 게임 닉네임과 태그라인으로 플레이어를 조회합니다.
	 * 
	 * @param gameName 라이엇 게임 닉네임
	 * @param tagLine 라이엇 태그라인
	 * @return 플레이어 정보 (없는 경우 Optional.empty())
	 */
	private Optional<Player> findByGameNameAndTagLine(String gameName, String tagLine) {
		return playerRepository.findByGameNameAndTagLine(gameName, tagLine);
	}

	/**
	 * Riot API를 통해 계정 정보를 조회합니다.
	 * 
	 * @param gameName 라이엇 게임 닉네임
	 * @param tagLine 라이엇 태그라인
	 * @return Riot API로부터 받은 계정 정보
	 * @throws RiotApiException API 호출 실패 시
	 */
	private AccountDto fetchRiotAccount(String gameName, String tagLine) {
		return riotService.getAccountByGameNameAndTagLine(gameName, tagLine);
	}

	/**
	 * DB의 플레이어 정보를 Riot 계정 정보와 비교하여 필요한 경우 업데이트합니다.
	 * 
	 * @param player 현재 DB에 저장된 플레이어 정보
	 * @param riotAccount Riot API에서 조회한 최신 계정 정보
	 * @return 업데이트된 플레이어 정보
	 * @implNote 게임 닉네임이나 태그라인이 변경된 경우에만 DB를 업데이트합니다.
	 */
	private PlayerDto updatePlayerIfChanged(Player player, AccountDto riotAccount) {
		boolean needsUpdate = false;

		if (!player.getGameName().equals(riotAccount.getGameName())) {
			player.updateGameName(riotAccount.getGameName());
			needsUpdate = true;
		}
		if (!player.getTagLine().equals(riotAccount.getTagLine())) {
			player.updateTagLine(riotAccount.getTagLine());
			needsUpdate = true;
		}

		if (needsUpdate) {
			playerRepository.save(player);
		}
		return playerMapper.toDto(player);
	}

	/**
	 * Riot 계정 정보를 기반으로 새로운 플레이어를 생성합니다.
	 * 
	 * @param riotAccount Riot API에서 조회한 계정 정보
	 * @return 생성된 플레이어 정보
	 */
	private PlayerDto createPlayer(AccountDto riotAccount) {
		Player newPlayer = playerMapper.toEntity(riotAccount);
		playerRepository.save(newPlayer);
		return playerMapper.toDto(newPlayer);
	}
}
