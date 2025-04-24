package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class EliteMonsterKillEventDto extends EventsTimeLineDto {
	public enum MonsterType {
		BARON_NASHOR, DRAGON, RIFTHERALD,
		HORDE, ELDER_DRAGON, ATAKHAN
	}
	private int bounty;
	private int killerId;
	// 팀 ID 100=블루, 200=레드
	private int killerTeamId;
	private MonsterType monsterType;
	private PositionDto position;
}
