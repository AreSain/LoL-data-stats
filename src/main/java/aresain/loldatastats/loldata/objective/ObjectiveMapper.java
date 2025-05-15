package aresain.loldatastats.loldata.objective;

import org.mapstruct.Mapper;

import aresain.loldatastats.entity.Objective;
import aresain.loldatastats.loldata.objective.dto.ObjectiveInfoDto;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {
    ObjectiveInfoDto toDto(Objective objective);
} 