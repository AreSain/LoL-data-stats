package aresain.loldatastats.riot.dto.timeline;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.Getter;

@Getter
public class IgnoredEventDto extends EventsTimeLineDto {
	@JsonAnySetter
	private Map<String, Object> unknownFields = new HashMap<>();

	public String getRawData() {
		return unknownFields.toString();
	}
}
