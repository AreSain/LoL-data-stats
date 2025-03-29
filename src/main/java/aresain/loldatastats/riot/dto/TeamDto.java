package aresain.loldatastats.riot.dto;

import lombok.Getter;

@Getter
public class TeamDto {
	private List<BanDto> bans;
	private ObjectivesDto objectives;
	private int teamId;
	private boolean win;
}
