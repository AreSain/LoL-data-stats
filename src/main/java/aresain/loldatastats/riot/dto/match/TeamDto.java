package aresain.loldatastats.riot.dto.match;

import java.util.List;

import lombok.Getter;

@Getter
public class TeamDto {
	private List<BanDto> bans;
	private ObjectivesDto objectives;
	private int teamId;
	private boolean win;
}
