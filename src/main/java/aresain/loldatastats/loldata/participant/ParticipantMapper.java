package aresain.loldatastats.loldata.participant;

import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.ParticipantAnalysis;
import aresain.loldatastats.entity.ParticipantPerk;
import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.riot.dto.match.ParticipantDto;
import aresain.loldatastats.riot.dto.match.PerkStyleDto;
import aresain.loldatastats.riot.dto.match.PerkStyleSelectionDto;

@Mapper(componentModel = "spring")
public interface ParticipantMapper {
    
    @Mapping(target = "participantId", expression = "java((long) participant.getParticipantId())")
    @Mapping(target = "kills", expression = "java((short) participant.getKills())")
    @Mapping(target = "deaths", expression = "java((short) participant.getDeaths())")
    @Mapping(target = "assists", expression = "java((short) participant.getAssists())")
    @Mapping(target = "stealthWardsPlaced", source = "participant.sightWardsBoughtInGame")
    ParticipantSummary toSummaryEntity(String matchId, ParticipantDto participant);

    @Mapping(target = "participantId", expression = "java((long) participant.getParticipantId())")
    @Mapping(target = "jungleCSBefore10Min", source = "participant.challenges.jungleCsBefore10Minutes")
    @Mapping(target = "laneMinionFirst10Min", source = "participant.challenges.laneMinionsFirst10Minutes")
    @Mapping(target = "scuttleCrabKills", source = "participant.challenges.scuttleCrabKills")
    @Mapping(target = "quickSoloKills", source = "participant.challenges.quickSoloKills")
    @Mapping(target = "killsOnLanersEarlyJungle", source = "participant.challenges.killsOnLanersEarlyJungleAsJungler")
    @Mapping(target = "statPerksDefense", source = "participant.perks.perkStats.defense")
    @Mapping(target = "statPerksFlex", source = "participant.perks.perkStats.flex")
    @Mapping(target = "statPerksOffense", source = "participant.perks.perkStats.offense")
    ParticipantAnalysis toAnalysisEntity(String matchId, ParticipantDto participant);

    @Mapping(target = "participantId", source = "participantId")
    @Mapping(target = "matchId", source = "matchId")
    @Mapping(target = "styleType", source = "styleType")
    @Mapping(target = "style", source = "style.style")
    @Mapping(target = "perkId", source = "selection.perk")
    @Mapping(target = "var1", source = "selection.var1")
    @Mapping(target = "var2", source = "selection.var2")
    @Mapping(target = "var3", source = "selection.var3")
    ParticipantPerk toPerkEntity(Long participantId, String matchId, String styleType, PerkStyleDto style, PerkStyleSelectionDto selection);
} 
