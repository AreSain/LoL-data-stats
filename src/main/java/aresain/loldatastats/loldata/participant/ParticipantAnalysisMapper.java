package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.ParticipantAnalysis;
import aresain.loldatastats.loldata.participant.dto.ParticipantAnalysisDto;

@Mapper(componentModel = "spring")
public interface ParticipantAnalysisMapper {
    ParticipantAnalysisDto toDto(ParticipantAnalysis participantAnalysis);
} 
