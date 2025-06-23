package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.SkillPointEvent;

@Repository
public interface SkillPointEventRepository extends JpaRepository<SkillPointEvent, Long> {
	List<SkillPointEvent> findByMatchId(String matchId);
}
