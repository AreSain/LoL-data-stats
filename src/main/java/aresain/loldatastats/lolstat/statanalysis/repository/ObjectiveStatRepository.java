package aresain.loldatastats.lolstat.statanalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.Objective;

public interface ObjectiveStatRepository extends JpaRepository<Objective, Long> {
    List<Objective> findByMatchIdIn(List<String> matchIds);
}
