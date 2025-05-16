package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;

@Mapper(componentModel = "spring")
public interface ParticipantSummaryMapper {
    @Mapping(target = "kda", expression = "java(calculateKda(participantSummary))")
    ParticipantSummaryDto toDto(ParticipantSummary participantSummary);

    default Double calculateKda(ParticipantSummary participantSummary) {
        if (participantSummary.getDeaths() == 0) {
            return (double) (participantSummary.getKills() + participantSummary.getAssists());
        }
        return (double) (participantSummary.getKills() + participantSummary.getAssists()) / participantSummary.getDeaths();
    }
} 
