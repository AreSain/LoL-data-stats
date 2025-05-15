package aresain.loldatastats.loldata.gamematch;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.GameMatch;

@Mapper(componentModel = "spring")
public interface GameMatchMapper {
    GameMatchInfoDto toDto(GameMatch gameMatch);
    
}
