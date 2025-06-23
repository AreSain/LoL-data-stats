package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.EliteMonsterKillEvent;

@Repository
public interface EliteMonsterKillEventRepository extends JpaRepository<EliteMonsterKillEvent, Long> {
    List<EliteMonsterKillEvent> findByMatchId(String matchId);
}
