package aresain.loldatastats.loldata.objective;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import aresain.loldatastats.entity.Objective;
import aresain.loldatastats.loldata.objective.dto.ObjectiveInfoDto;
import aresain.loldatastats.riot.dto.match.ObjectiveDto;
import aresain.loldatastats.riot.dto.match.ObjectivesDto;
import aresain.loldatastats.riot.dto.match.TeamDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ObjectiveService {
    private final ObjectiveRepository objectiveRepository;
    private final ObjectiveMapper objectiveMapper;

    public List<Objective> saveObjectives(String matchId, List<TeamDto> teams) {
        List<Objective> objectives = teams.stream()
            .filter(team -> team.getObjectives() != null)
            .flatMap(team -> createObjectivesForTeam(matchId, team))
            .collect(Collectors.toList());
        
        return objectiveRepository.saveAll(objectives);
    }

    private Stream<Objective> createObjectivesForTeam(String matchId, TeamDto team) {
        ObjectivesDto objectivesDto = team.getObjectives();
        return Stream.of(
            createObjective(matchId, team.getTeamId(), "baron", objectivesDto.getBaron()),
            createObjective(matchId, team.getTeamId(), "champion", objectivesDto.getChampion()),
            createObjective(matchId, team.getTeamId(), "dragon", objectivesDto.getDragon()),
            createObjective(matchId, team.getTeamId(), "inhibitor", objectivesDto.getInhibitor()),
            createObjective(matchId, team.getTeamId(), "riftHerald", objectivesDto.getRiftHerald()),
            createObjective(matchId, team.getTeamId(), "tower", objectivesDto.getTower())
        )
        .filter(objective -> objective != null);
    }

    private Objective createObjective(String matchId, int teamId, String type, ObjectiveDto objectiveDto) {
        if (objectiveDto == null) {
            return null;
        }
        return objectiveMapper.toEntity(matchId, teamId, type, objectiveDto);
    }

    public List<ObjectiveInfoDto> findObjectivesByMatchId(String matchId) {
        return objectiveRepository.findByMatchId(matchId).stream()
            .map(objectiveMapper::toDto)
            .collect(Collectors.toList());
    }
} 