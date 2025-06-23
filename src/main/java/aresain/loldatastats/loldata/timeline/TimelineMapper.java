package aresain.loldatastats.loldata.timeline;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import aresain.loldatastats.entity.BuildingKillEvent;
import aresain.loldatastats.entity.ChampionKillEvent;
import aresain.loldatastats.entity.ChampionLevelEvent;
import aresain.loldatastats.entity.EliteMonsterKillEvent;
import aresain.loldatastats.entity.ItemEvent;
import aresain.loldatastats.entity.SkillPointEvent;
import aresain.loldatastats.entity.WardEvent;
import aresain.loldatastats.loldata.timeline.dto.BuildingKillEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.ChampionKillEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.ChampionLevelEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.EliteMonsterKillEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.ItemEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.SkillPointEventInfoDto;
import aresain.loldatastats.loldata.timeline.dto.TimelineInfoDto;
import aresain.loldatastats.loldata.timeline.dto.WardEventInfoDto;
import aresain.loldatastats.riot.dto.timeline.BuildingKillEventDto;
import aresain.loldatastats.riot.dto.timeline.ChampionKillEventDto;
import aresain.loldatastats.riot.dto.timeline.EliteMonsterKillEventDto;
import aresain.loldatastats.riot.dto.timeline.ItemEventDto;
import aresain.loldatastats.riot.dto.timeline.LevelUpEventDto;
import aresain.loldatastats.riot.dto.timeline.SkillLevelUpEventDto;
import aresain.loldatastats.riot.dto.timeline.WardEventDto;

@Mapper(componentModel = "spring")
public interface TimelineMapper {

    ChampionLevelEventInfoDto toDto(ChampionLevelEvent entity);
    ChampionKillEventInfoDto toDto(ChampionKillEvent entity);
    ItemEventInfoDto toDto(ItemEvent entity);
    BuildingKillEventInfoDto toDto(BuildingKillEvent entity);
    WardEventInfoDto toDto(WardEvent entity);
    SkillPointEventInfoDto toDto(SkillPointEvent entity);
    EliteMonsterKillEventInfoDto toDto(EliteMonsterKillEvent entity);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "level", source = "event.level"),
        @Mapping(target = "participantId", source = "event.participantId"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "type", source = "event.type")
    })
    ChampionLevelEvent toChampionLevelEntity(String matchId, LevelUpEventDto event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "bounty", source = "event.bounty"),
        @Mapping(target = "killStreakLength", source = "event.killStreakLength"),
        @Mapping(target = "killerId", source = "event.killerId"),
        @Mapping(target = "positionX", source = "event.position.x"),
        @Mapping(target = "positionY", source = "event.position.y"),
        @Mapping(target = "shutdownBounty", source = "event.shutdownBounty"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "type", source = "event.type"),
        @Mapping(target = "assistingParticipantIds", expression = "java(joinAssistingIds(event.getAssistingParticipantIds()))"),
        @Mapping(target = "victimId", source = "event.victimId")
    })
    ChampionKillEvent toChampionKillEntity(String matchId, ChampionKillEventDto event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "afterId", source = "event.itemId"),
        @Mapping(target = "beforeId", source = "event.beforeId"),
        @Mapping(target = "goldGain", source = "event.goldGain"),
        @Mapping(target = "participantId", source = "event.participantId"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "type", source = "event.type")
    })
    ItemEvent toItemEntity(String matchId, ItemEventDto event);


    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "bounty", source = "event.bounty"),
        @Mapping(target = "buildingType", expression = "java(event.getBuildingType() != null ? event.getBuildingType().name() : null)"),
        @Mapping(target = "killerId", source = "event.killerId"),
        @Mapping(target = "laneType", expression = "java(event.getLaneType() != null ? event.getLaneType().name() : null)"),
        @Mapping(target = "positionX", source = "event.position.x"),
        @Mapping(target = "positionY", source = "event.position.y"),
        @Mapping(target = "teamId", source = "event.teamId"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "towerType", expression = "java(event.getTowerType() != null ? event.getTowerType().name() : \"INHIBITOR\")"),
        @Mapping(target = "type", source = "event.type")
    })
    BuildingKillEvent toBuildingKillEntity(String matchId, BuildingKillEventDto event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "creatorId", source = "event.creatorId"),
        @Mapping(target = "killerId", source = "event.killerId"),
        @Mapping(target = "type", source = "event.type"),
        @Mapping(target = "wardType", expression = "java(event.getWardType() != null ? event.getWardType().name() : null)")
    })
    WardEvent toWardEntity(String matchId, WardEventDto event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "levelUpType", expression = "java(event.getLevelUpType() != null ? event.getLevelUpType().name() : null)"),
        @Mapping(target = "participantId", source = "event.participantId"),
        @Mapping(target = "skillSlot", source = "event.skillSlot"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "type", source = "event.type")
    })
    SkillPointEvent toSkillPointEntity(String matchId, SkillLevelUpEventDto event);

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "matchId", source = "matchId"),
        @Mapping(target = "bounty", source = "event.bounty"),
        @Mapping(target = "killerId", source = "event.killerId"),
        @Mapping(target = "monsterType", expression = "java(event.getMonsterType() != null ? event.getMonsterType().name() : null)"),
        @Mapping(target = "positionX", source = "event.position.x"),
        @Mapping(target = "positionY", source = "event.position.y"),
        @Mapping(target = "timestamp", expression = "java(event.getTimestamp().intValue())"),
        @Mapping(target = "type", source = "event.type")
    })
    EliteMonsterKillEvent toEliteKillEntity(String matchId, EliteMonsterKillEventDto event);

    TimelineInfoDto toTimelineDto(String matchId,
            List<ItemEventInfoDto> itemPurchaseEvents,
            List<SkillPointEventInfoDto> skillPointEvents,
            List<ChampionLevelEventInfoDto> championLevelEvents,
            List<ChampionKillEventInfoDto> championKillEvents,
            List<BuildingKillEventInfoDto> buildingKillEvents,
            List<EliteMonsterKillEventInfoDto> objectEvents,
            List<WardEventInfoDto> wardEvents);
    
    default String joinAssistingIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return "";
        }
        return String.join(",", ids.stream().map(String::valueOf).toList());
    }
}
