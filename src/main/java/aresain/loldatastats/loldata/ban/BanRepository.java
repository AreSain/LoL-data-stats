package aresain.loldatastats.loldata.ban;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.Ban;

public interface BanRepository extends JpaRepository<Ban, Long> {
    List<Ban> findByMatchId(String matchId);
} 