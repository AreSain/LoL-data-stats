package aresain.loldatastats.riot.dto.timeline;

import java.util.List;
import java.util.Map;

import lombok.Getter;

@Getter
public class FramesTimeLineDto {
	private List<EventsTimeLineDto> events;
	private Map<String, ParticipantFrameDto> participantFrames;
	private int timestamp;
}
