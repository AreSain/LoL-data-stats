package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.ParticipantPerk;
import aresain.loldatastats.loldata.participant.dto.ParticipantPerkDto;

@Mapper(componentModel = "spring")
public interface ParticipantPerkMapper {
    ParticipantPerkDto toDto(ParticipantPerk participantPerk);
} 
