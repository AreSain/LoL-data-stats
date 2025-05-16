package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;
import aresain.loldatastats.riot.dto.match.ParticipantDto;

@Mapper(componentModel = "spring")
public interface ParticipantSummaryMapper {
    @Mapping(target = "kda", expression = "java(calculateKda(participantSummary))")
    ParticipantSummaryDto toDto(ParticipantSummary participantSummary);

    @Mapping(target = "participantId", expression = "java((long) participant.getParticipantId())")
    @Mapping(target = "kills", expression = "java((short) participant.getKills())")
    @Mapping(target = "deaths", expression = "java((short) participant.getDeaths())")
    @Mapping(target = "assists", expression = "java((short) participant.getAssists())")
    @Mapping(target = "stealthWardsPlaced", source = "participant.sightWardsBoughtInGame")
    ParticipantSummary toEntity(String matchId, ParticipantDto participant);

    default Double calculateKda(ParticipantSummary participantSummary) {
        if (participantSummary.getDeaths() == 0) {
            return (double) (participantSummary.getKills() + participantSummary.getAssists());
        }
        return (double) (participantSummary.getKills() + participantSummary.getAssists()) / participantSummary.getDeaths();
    }
} 
