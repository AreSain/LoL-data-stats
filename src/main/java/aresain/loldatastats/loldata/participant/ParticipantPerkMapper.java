package aresain.loldatastats.loldata.participant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.ParticipantPerk;
import aresain.loldatastats.loldata.participant.dto.ParticipantPerkDto;
import aresain.loldatastats.riot.dto.match.PerkStyleDto;
import aresain.loldatastats.riot.dto.match.PerkStyleSelectionDto;

@Mapper(componentModel = "spring")
public interface ParticipantPerkMapper {
    
    @Mapping(target = "participantId", source = "participantId")
    @Mapping(target = "matchId", source = "matchId")
    @Mapping(target = "styleType", source = "styleType")
    @Mapping(target = "style", source = "style.style")
    @Mapping(target = "perkId", source = "selection.perk")
    @Mapping(target = "var1", source = "selection.var1")
    @Mapping(target = "var2", source = "selection.var2")
    @Mapping(target = "var3", source = "selection.var3")
    ParticipantPerk toEntity(Long participantId, String matchId, String styleType, PerkStyleDto style, PerkStyleSelectionDto selection);

    ParticipantPerkDto toDto(ParticipantPerk participantPerk);
} 
