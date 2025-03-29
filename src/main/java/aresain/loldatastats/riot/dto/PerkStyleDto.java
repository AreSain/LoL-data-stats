package aresain.loldatastats.riot.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class PerkStyleDto {
	private String description;
	private List<PerkStyleSelectionDto> selections;
	private int style;
}
