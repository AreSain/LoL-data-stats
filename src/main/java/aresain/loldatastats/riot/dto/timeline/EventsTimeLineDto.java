package aresain.loldatastats.riot.dto.timeline;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;

@JsonTypeInfo(use = Id.NAME, property = "type", visible = true, defaultImpl = IgnoredEventDto.class)
@JsonSubTypes({
	@Type(name = "CHAMPION_KILL", value = ChampionKillEventDto.class),
	@Type(name = "ITEM_PURCHASED", value = ItemEventDto.class),
	@Type(name = "ITEM_DESTROYED", value = ItemEventDto.class),
	@Type(name = "ITEM_UNDO", value = ItemEventDto.class),
	@Type(name = "ITEM_SOLD", value = ItemEventDto.class),
	@Type(name = "BUILDING_KILL", value = BuildingKillEventDto.class),
	@Type(name = "TURRET_PLATE_DESTROYED", value = BuildingKillEventDto.class),
	@Type(name = "WARD_PLACED", value = WardEventDto.class),
	@Type(name = "WARD_KILL", value = WardEventDto.class),
	@Type(name = "LEVEL_UP", value = LevelUpEventDto.class),
	@Type(name = "SKILL_LEVEL_UP", value = SkillLevelUpEventDto.class),
	@Type(name = "ELITE_MONSTER_KILL", value = EliteMonsterKillEventDto.class)
})
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class EventsTimeLineDto {
	private Long timestamp;
	private Long realTimestamp;
	private String type;
}
