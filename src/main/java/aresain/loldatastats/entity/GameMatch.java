package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class GameMatch extends BaseEntity {
    @Id
    @Column(name = "match_id", length = 20)
    private String matchId;

    @Column(name = "end_of_game_result", length = 20)
    private String endOfGameResult;

    @Column(name = "game_creation", nullable = false)
    private Long gameCreation;

    @Column(name = "game_start_timestamp", nullable = false)
    private Long gameStartTimestamp;

    @Column(name = "game_end_timestamp", nullable = false)
    private Long gameEndTimestamp;

    @Column(name = "game_duration", nullable = false)
    private int gameDuration;

    @Column(name = "game_mode", length = 20, nullable = false)
    private String gameMode;

    @Column(name = "game_type", length = 20, nullable = false)
    private String gameType;

    @Column(name = "map_id", nullable = false)
    private int mapId;

    @Column(name = "game_version", length = 20)
    private String gameVersion;

    @Column(name = "win_team_id", nullable = false)
    private int winTeamId;
} 