package aresain.loldatastats.loldata.objective;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import aresain.loldatastats.entity.Objective;
import aresain.loldatastats.loldata.objective.dto.ObjectiveInfoDto;
import aresain.loldatastats.riot.dto.match.ObjectiveDto;

@Mapper(componentModel = "spring")
public interface ObjectiveMapper {
    ObjectiveInfoDto toDto(Objective objective);

    @Mapping(target = "objectiveId", ignore = true)
    @Mapping(target = "matchId", source = "matchId")
    @Mapping(target = "teamId", source = "teamId")
    @Mapping(target = "type", source = "type")
    @Mapping(target = "kills", source = "objectiveDto.kills")
    Objective toEntity(String matchId, int teamId, String type, ObjectiveDto objectiveDto);
} 