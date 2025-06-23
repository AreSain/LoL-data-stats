package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.WardEvent;

@Repository
public interface WardEventRepository extends JpaRepository<WardEvent, Long> {
    List<WardEvent> findByMatchId(String matchId);
}
