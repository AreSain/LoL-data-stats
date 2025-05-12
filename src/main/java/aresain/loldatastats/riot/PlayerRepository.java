package aresain.loldatastats.riot;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.datastats.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {
	Optional<Player> findByGameNameAndTagLine(String gameName, String tagLine);
}
