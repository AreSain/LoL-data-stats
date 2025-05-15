package aresain.loldatastats.loldata.gamematch;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.GameMatch;

public interface GameMatchRepository extends JpaRepository<GameMatch, String> {
    GameMatch findByMatchId(String matchId);
} 