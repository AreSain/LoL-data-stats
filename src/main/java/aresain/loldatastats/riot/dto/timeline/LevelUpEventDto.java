package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class LevelUpEventDto extends EventsTimeLineDto {
	private int level;
	private int participantId;
}
