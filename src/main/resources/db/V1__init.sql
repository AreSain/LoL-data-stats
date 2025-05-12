CREATE TABLE `match` (
        `match_id` VARCHAR(20) PRIMARY KEY NOT NULL,
        `game_start` TIMESTAMP NOT NULL,
        `game_end` TIMESTAMP NOT NULL,
        `game_duration` INT NOT NULL,
        `queue_type` VARCHAR(20) NOT NULL,
        `game_version` VARCHAR(10),
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `participant` (
        `participant_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `puuid` VARCHAR(100) NOT NULL,
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
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `player` (
        `puuid` VARCHAR(100) PRIMARY KEY NOT NULL,
        `game_name` VARCHAR(50) NOT NULL,
        `tag_line` VARCHAR(8) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `player_detail` (
        `puuid` VARCHAR(100) PRIMARY KEY NOT NULL,
        `summoner_level` INT NOT NULL,
        `solo_tier` varchar(20) NOT NULL,
        `solo_rank` varchar(2) NOT NULL,
        `solo_rank_points` INT NOT NULL,
        `solo_rank_wins` INT NOT NULL,
        `solo_rank_losses` INT NOT NULL,
        `flex_tier` varchar(20) NOT NULL,
        `flex_rank` varchar(2) NOT NULL,
        `flex_rank_points` INT NOT NULL,
        `flex_rank_wins` INT NOT NULL,
        `flex_rank_losses` INT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `champion_kill_event` (
        `event_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `kill_streak_length` TINYINT NOT NULL,
        `killer_id` TINYINT,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `shutdown_bounty` SMALLINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `assisting_participant_ids` TEXT,
        `victim_id` TINYINT,
        `frame` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `item_event` (
        `event_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `afterId` SMALLINT,
        `beforeId` SMALLINT,
        `goldGain` SMALLINT,
        `participant_id` TINYINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `frame` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `building_kill_event` (
        `event_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `building_type` VARCHAR(20) NOT NULL,
        `killer_id` TINYINT,
        `lane_type` VARCHAR(10) NOT NULL,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `team_id` TINYINT NOT NULL,
        `timestamp` INT NOT NULL,
        `tower_type` VARCHAR(20) NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `frame` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `ward_event` (
        `event_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `timestamp` INT NOT NULL,
        `creator_id` TINYINT,
        `killer_id` TINYINT,
        `type` VARCHAR(20) NOT NULL,
        `ward_type` VARCHAR(20) NOT NULL,
        `frame` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `object_event` (
        `event_id` BIGINT PRIMARY KEY,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `killer_id` tinyint,
        `monster_type` VARCHAR(20) NOT NULL,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `frame` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);
