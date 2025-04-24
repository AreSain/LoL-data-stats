package aresain.loldatastats.riot.dto.timeline;

import lombok.Getter;

@Getter
public class WardEventDto extends EventsTimeLineDto {
	private Integer creatorId;
	private Integer killerId;
	private String wardType;
}
