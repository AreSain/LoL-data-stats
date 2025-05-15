package aresain.loldatastats.loldata.objective;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.Objective;

public interface ObjectiveRepository extends JpaRepository<Objective, Long> {
    List<Objective> findByMatchId(String matchId);
} 