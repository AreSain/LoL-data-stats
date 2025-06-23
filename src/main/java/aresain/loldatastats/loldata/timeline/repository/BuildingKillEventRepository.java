package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.BuildingKillEvent;

@Repository
public interface BuildingKillEventRepository extends JpaRepository<BuildingKillEvent, Long> {
	List<BuildingKillEvent> findByMatchId(String matchId);
}
