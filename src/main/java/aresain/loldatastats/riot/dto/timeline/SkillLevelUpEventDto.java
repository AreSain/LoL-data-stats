package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class SkillLevelUpEventDto extends EventsTimeLineDto {
	public enum LevelUpType { NORMAL, EVOLVE, SPECIAL }
	private LevelUpType levelUpType;
	private int participantId;
	//스킬 슬롯 (0=Q, 1=W, 2=E, 3=R)
	private int skillSlot;
}
