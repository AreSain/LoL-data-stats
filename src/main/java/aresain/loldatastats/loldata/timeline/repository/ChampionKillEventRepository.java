package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.ChampionKillEvent;

@Repository
public interface ChampionKillEventRepository extends JpaRepository<ChampionKillEvent, Long> {
    boolean existsByMatchId(String matchId);
    List<ChampionKillEvent> findByMatchId(String matchId);
} 
