CREATE TABLE `game_match` (
        `match_id` VARCHAR(20) PRIMARY KEY NOT NULL,
        `end_of_game_result` VARCHAR(20),
        `game_creation` BIGINT NOT NULL,
        `game_start_timestamp` BIGINT NOT NULL,
        `game_end_timestamp` BIGINT NOT NULL,
        `game_duration` INT NOT NULL,
        `game_mode` VARCHAR(20) NOT NULL,
        `game_type` VARCHAR(20) NOT NULL,
        `map_id` INT NOT NULL,
        `game_version` VARCHAR(20),
        `win_team_id` INT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `ban` (
        `ban_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `team_id` INT NOT NULL,
        `champion_id` INT NOT NULL,
        `pick_turn` INT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `objective` (
        `objective_id` BIGINT AUTO_INCREMENT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `team_id` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `kills` INT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `participant` (
         `participant_id` BIGINT NOT NULL,
         `match_id` VARCHAR(20) NOT NULL,
         `puuid` VARCHAR(100) NOT NULL,
         `team_id` INT NOT NULL,
         `champion_id` INT NOT NULL,
         `kills` SMALLINT,
         `deaths` SMALLINT,
         `assists` SMALLINT,
         `gold_earned` INT,
         `damage_dealt` INT,
         `damage_taken` INT,
         `skill_trees` VARCHAR(16),
         `rune_trees` VARCHAR(11),
         `created_at` DATETIME NOT NULL,
         `modified_at` DATETIME NOT NULL,
         PRIMARY KEY (participant_id, match_id)
);

CREATE TABLE `player` (
        `puuid` VARCHAR(100) PRIMARY KEY NOT NULL,
        `game_name` VARCHAR(50) NOT NULL,
        `tag_line` VARCHAR(8) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);
