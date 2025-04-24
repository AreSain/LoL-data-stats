package aresain.loldatastats.riot.dto.timeline;

import java.util.List;

import lombok.Getter;

@Getter
public class InfoTimeLineDto {
	private String endOfGameResult;
	private Long frameInterval;
	private Long gameId;
	private List<ParticipantTimeLineDto> participants;
	private List<FramesTimeLineDto> frames;
}
