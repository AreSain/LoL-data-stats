package aresain.loldatastats.loldata.participant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.ParticipantId;
import aresain.loldatastats.entity.ParticipantSummary;

public interface ParticipantSummaryRepository extends JpaRepository<ParticipantSummary, ParticipantId> {
    List<ParticipantSummary> findByMatchId(String matchId);
} 
