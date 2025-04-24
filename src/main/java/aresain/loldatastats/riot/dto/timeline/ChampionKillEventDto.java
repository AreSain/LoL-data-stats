package aresain.loldatastats.riot.dto.timeline;

import java.util.List;

import lombok.Getter;

@Getter
public class ChampionKillEventDto extends EventsTimeLineDto {
	private List<Integer> assistingParticipantIds;
	private int bounty;
	private int killStreakLength;
	private int killerId;
	private PositionDto position;
	private int shutdownBounty;
	private int victimId;
}
