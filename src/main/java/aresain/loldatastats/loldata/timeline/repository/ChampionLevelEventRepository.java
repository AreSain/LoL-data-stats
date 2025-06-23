package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.ChampionLevelEvent;

@Repository
public interface ChampionLevelEventRepository extends JpaRepository<ChampionLevelEvent, Long> {
    boolean existsByMatchId(String matchId);
    List<ChampionLevelEvent> findByMatchId(String matchId);
} 
