package aresain.loldatastats.riot.dto.timeline;

import aresain.loldatastats.common.type.BuildingType;
import aresain.loldatastats.common.type.LaneType;
import aresain.loldatastats.common.type.TowerType;
import lombok.Getter;

@Getter
public class BuildingKillEventDto extends EventsTimeLineDto {
	private BuildingType buildingType;
	private LaneType laneType;
	private int killerId;
	private int teamId;
	private TowerType towerType;
	private PositionDto position;
}
