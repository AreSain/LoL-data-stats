package aresain.loldatastats.riot.dto.match;

import java.util.List;

import lombok.Getter;

@Getter
public class PerksDto {
	private PerkStatsDto perkStats;
	private List<PerkStyleDto> styles;
}
