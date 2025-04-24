package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class ItemEventDto extends EventsTimeLineDto {
	private int itemId;
	private Long afterId;
	private Long beforeId;
	private Long goldGain;
	private int participantId;
}
