package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;

@Mapper(componentModel = "spring")
public interface ParticipantSummaryMapper {
    ParticipantSummaryDto toDto(ParticipantSummary participantSummary);


} 
