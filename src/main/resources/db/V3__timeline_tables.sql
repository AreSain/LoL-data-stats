CREATE TABLE `champion_level_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `level` TINYINT NOT NULL,
        `participant_id` TINYINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `champion_kill_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `kill_streak_length` TINYINT NOT NULL,
        `killer_id` TINYINT NOT NULL,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `shutdown_bounty` SMALLINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `assisting_participant_ids` TEXT NOT NULL,
        `victim_id` TINYINT NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `item_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `item_id` SMALLINT NOT NULL,
        `after_id` SMALLINT NOT NULL,
        `before_id` SMALLINT,
        `gold_gain` SMALLINT,
        `participant_id` TINYINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `building_kill_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `building_type` VARCHAR(20) NOT NULL,
        `killer_id` TINYINT NOT NULL,
        `lane_type` VARCHAR(10) NOT NULL,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `team_id` SMALLINT NOT NULL,
        `timestamp` INT NOT NULL,
        `tower_type` VARCHAR(20) NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `ward_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `timestamp` INT NOT NULL,
        `creator_id` TINYINT,
        `killer_id` TINYINT,
        `type` VARCHAR(20) NOT NULL,
        `ward_type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `elite_monster_kill_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `bounty` SMALLINT NOT NULL,
        `killer_id` TINYINT NOT NULL,
        `monster_type` VARCHAR(20) NOT NULL,
        `position_x` SMALLINT NOT NULL,
        `position_y` SMALLINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);

CREATE TABLE `skill_point_event` (
        `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
        `match_id` VARCHAR(20) NOT NULL,
        `level_up_type` VARCHAR(20) NOT NULL,
        `participant_id` TINYINT NOT NULL,
        `skill_slot` TINYINT NOT NULL,
        `timestamp` INT NOT NULL,
        `type` VARCHAR(20) NOT NULL,
        `created_at` DATETIME NOT NULL,
        `modified_at` DATETIME NOT NULL
);
