package aresain.loldatastats.riot.dto.timeline;

import aresain.loldatastats.common.BuildingType;
import aresain.loldatastats.common.LaneType;
import aresain.loldatastats.common.TowerType;
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
