package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class BuildingKillEventDto extends EventsTimeLineDto {
	private String buildingType;
	private String laneType;
	private int killerId;
	private int teamId;
	private String towerType;
	private PositionDto position;
}
