package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.ParticipantAnalysis;
import aresain.loldatastats.loldata.participant.dto.ParticipantAnalysisDto;
import aresain.loldatastats.riot.dto.match.ParticipantDto;

@Mapper(componentModel = "spring")
public interface ParticipantAnalysisMapper {
    
    @Mapping(target = "participantId", expression = "java((long) participant.getParticipantId())")
    @Mapping(target = "jungleCSBefore10Min", source = "participant.challenges.jungleCsBefore10Minutes")
    @Mapping(target = "laneMinionFirst10Min", source = "participant.challenges.laneMinionsFirst10Minutes")
    @Mapping(target = "scuttleCrabKills", source = "participant.challenges.scuttleCrabKills")
    @Mapping(target = "quickSoloKills", source = "participant.challenges.quickSoloKills")
    @Mapping(target = "killsOnLanersEarlyJungle", source = "participant.challenges.killsOnLanersEarlyJungleAsJungler")
    @Mapping(target = "statPerksDefense", source = "participant.perks.statPerks.defense")
    @Mapping(target = "statPerksFlex", source = "participant.perks.statPerks.flex")
    @Mapping(target = "statPerksOffense", source = "participant.perks.statPerks.offense")
    ParticipantAnalysis toEntity(String matchId, ParticipantDto participant);

    ParticipantAnalysisDto toDto(ParticipantAnalysis participantAnalysis);
} 
