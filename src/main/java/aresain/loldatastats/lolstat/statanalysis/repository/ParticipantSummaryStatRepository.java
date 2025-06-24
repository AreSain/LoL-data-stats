package aresain.loldatastats.lolstat.statanalysis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aresain.loldatastats.entity.ParticipantId;
import aresain.loldatastats.entity.ParticipantSummary;

public interface ParticipantSummaryStatRepository extends JpaRepository<ParticipantSummary, ParticipantId> {

    List<ParticipantSummary> findByMatchIdInAndPuuid(List<String> matchIds, String puuid);
}
