package aresain.loldatastats.loldata.timeline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aresain.loldatastats.entity.ItemEvent;

@Repository
public interface ItemEventRepository extends JpaRepository<ItemEvent, Long> {
    boolean existsByMatchId(String matchId);
    List<ItemEvent> findByMatchId(String matchId);
} 
