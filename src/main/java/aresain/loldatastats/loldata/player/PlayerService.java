package aresain.loldatastats.loldata.player;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.entity.Player;
import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.AccountDto;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {
	private final RiotService riotService;
	private final PlayerMapper playerMapper;
	private final PlayerRepository playerRepository;

	/**
	 * Riot API를 통해 플레이어 정보를 조회하고, DB에 동기화합니다.
	 * 플레이어가 존재하지 않으면 생성하고, 존재하면 최신 정보로 업데이트합니다.
	 */
	public PlayerDto syncPlayerWithRiot(String gameName, String tagLine) {
		AccountDto riotAccount = fetchRiotAccount(gameName, tagLine);
		return findByGameNameAndTagLine(gameName, tagLine)
			.map(player -> updatePlayerIfChanged(player, riotAccount))
			.orElseGet(() -> createPlayer(riotAccount));
	}

	private Optional<Player> findByGameNameAndTagLine(String gameName, String tagLine) {
		return playerRepository.findByGameNameAndTagLine(gameName, tagLine);
	}

	private AccountDto fetchRiotAccount(String gameName, String tagLine) {
		return riotService.getAccountByGameNameAndTagLine(gameName, tagLine);
	}

	private PlayerDto updatePlayerIfChanged(Player player, AccountDto riotAccount) {
		if (!player.getGameName().equals(riotAccount.getGameName()) ||
			!player.getTagLine().equals(riotAccount.getTagLine())) {
			player.updateGameName(riotAccount.getGameName());
			player.updateTagLine(riotAccount.getTagLine());
			playerRepository.save(player);
		}
		return playerMapper.toDto(player);
	}

	private PlayerDto createPlayer(AccountDto riotAccount) {
		Player newPlayer = playerMapper.toEntity(riotAccount);
		playerRepository.save(newPlayer);
		return playerMapper.toDto(newPlayer);
	}
}
