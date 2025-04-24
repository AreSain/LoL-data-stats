package aresain.loldatastats.riot.dto.timeline;

import aresain.loldatastats.common.MonsterType;
import lombok.Getter;

@Getter
public class EliteMonsterKillEventDto extends EventsTimeLineDto {
	private int bounty;
	private int killerId;
	// 팀 ID 100=블루, 200=레드
	private int killerTeamId;
	private MonsterType monsterType;
	private PositionDto position;
}
