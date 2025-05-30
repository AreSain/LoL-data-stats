package aresain.loldatastats.riot.dto.timeline;

import aresain.loldatastats.common.type.WardType;
import lombok.Getter;

@Getter
public class WardEventDto extends EventsTimeLineDto {
	private Integer creatorId;
	private Integer killerId;
	private WardType wardType;
}
