package aresain.loldatastats.loldata.ban;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.Ban;
import aresain.loldatastats.loldata.ban.dto.BanInfoDto;

@Mapper(componentModel = "spring")
public interface BanMapper {
    BanInfoDto toDto(Ban ban);
} 