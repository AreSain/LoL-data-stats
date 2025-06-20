package aresain.loldatastats.loldata.participant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.ParticipantPerk;
import aresain.loldatastats.entity.ParticipantPerkId;

public interface ParticipantPerkRepository extends JpaRepository<ParticipantPerk, ParticipantPerkId> {
    List<ParticipantPerk> findByMatchId(String matchId);
} 
