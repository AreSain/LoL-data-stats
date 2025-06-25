package aresain.loldatastats.loldata.player;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.Player;
import aresain.loldatastats.riot.dto.AccountDto;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
	PlayerDto toDto(Player entity);
	Player toEntity(AccountDto accountDto);
}
