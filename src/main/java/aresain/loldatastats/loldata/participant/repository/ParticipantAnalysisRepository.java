package aresain.loldatastats.loldata.participant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.ParticipantAnalysis;
import aresain.loldatastats.entity.ParticipantId;

public interface ParticipantAnalysisRepository extends JpaRepository<ParticipantAnalysis, ParticipantId> {
    List<ParticipantAnalysis> findByMatchId(String matchId);
} 
