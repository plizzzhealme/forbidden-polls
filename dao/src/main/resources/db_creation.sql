-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema forbidden_polls
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema forbidden_polls
-- -----------------------------------------------------
DROP DATABASE IF EXISTS forbidden_polls;
CREATE SCHEMA IF NOT EXISTS `forbidden_polls` DEFAULT CHARACTER SET utf8;
USE `forbidden_polls`;

-- -----------------------------------------------------
-- Table `forbidden_polls`.`categories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`categories`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`countries`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`countries`
(
    `id`       INT         NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(45) NULL,
    `iso_code` VARCHAR(45) NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`option_types`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`option_types`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `type` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`surveys`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`surveys`
(
    `id`            INT         NOT NULL AUTO_INCREMENT,
    `name`          VARCHAR(45) NOT NULL,
    `creation_date` DATETIME    NULL,
    `description`   VARCHAR(45) NULL,
    `instructions`  VARCHAR(45) NULL,
    `image_url`     VARCHAR(45) NULL,
    `category_id`   INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_surveys_categories1_idx` (`category_id` ASC) VISIBLE,
    CONSTRAINT `fk_surveys_categories1`
        FOREIGN KEY (`category_id`)
            REFERENCES `forbidden_polls`.`categories` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`questions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`questions`
(
    `id`                   INT         NOT NULL AUTO_INCREMENT,
    `index_number`         INT         NOT NULL,
    `body`                 VARCHAR(45) NOT NULL,
    `image_url`            VARCHAR(45) NULL,
    `question_description` VARCHAR(45) NULL,
    `survey_id`            INT         NOT NULL,
    `option_type_id`       INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_question_surveys1_idx` (`survey_id` ASC) VISIBLE,
    INDEX `fk_questions_option_types1_idx` (`option_type_id` ASC) VISIBLE,
    CONSTRAINT `fk_question_surveys1`
        FOREIGN KEY (`survey_id`)
            REFERENCES `forbidden_polls`.`surveys` (`id`),
    CONSTRAINT `fk_questions_option_types1`
        FOREIGN KEY (`option_type_id`)
            REFERENCES `forbidden_polls`.`option_types` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`options`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `body`        VARCHAR(45) NULL,
    `question_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_possible_answers_questions1_idx` (`question_id` ASC) VISIBLE,
    CONSTRAINT `fk_possible_answers_questions1`
        FOREIGN KEY (`question_id`)
            REFERENCES `forbidden_polls`.`questions` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`user_roles`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `body_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`genders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`genders`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`users`
(
    `id`                INT          NOT NULL AUTO_INCREMENT,
    `name`              VARCHAR(45)  NULL,
    `email`             VARCHAR(45)  NOT NULL,
    `hashed_password`   VARCHAR(222) NOT NULL,
    `registration_date` DATETIME     NULL,
    `birthday`          DATE         NULL,
    `user_role_id`      INT          NOT NULL,
    `country_id`        INT          NOT NULL,
    `gender_id`         INT          NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `fk_users_user_roles1_idx` (`user_role_id` ASC) VISIBLE,
    INDEX `fk_users_countries1_idx` (`country_id` ASC) VISIBLE,
    INDEX `fk_users_gender1_idx` (`gender_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_countries1`
        FOREIGN KEY (`country_id`)
            REFERENCES `forbidden_polls`.`countries` (`id`),
    CONSTRAINT `fk_users_user_roles1`
        FOREIGN KEY (`user_role_id`)
            REFERENCES `forbidden_polls`.`user_roles` (`id`),
    CONSTRAINT `fk_users_gender1`
        FOREIGN KEY (`gender_id`)
            REFERENCES `forbidden_polls`.`genders` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`passed_surveys`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`passed_surveys`
(
    `id`              INT      NOT NULL AUTO_INCREMENT,
    `completion_date` DATETIME NULL,
    `survey_id`       INT      NOT NULL,
    `user_id`         INT      NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    INDEX `fk_passed_surveys_surveys1_idx` (`survey_id` ASC) VISIBLE,
    INDEX `fk_passed_surveys_users1_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `fk_passed_surveys_surveys1`
        FOREIGN KEY (`survey_id`)
            REFERENCES `forbidden_polls`.`surveys` (`id`),
    CONSTRAINT `fk_passed_surveys_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `forbidden_polls`.`users` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `forbidden_polls`.`picked_options`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `forbidden_polls`.`picked_options`
(
    `id`          INT         NOT NULL AUTO_INCREMENT,
    `answer_text` VARCHAR(45) NULL,
    `user_id`     INT         NOT NULL,
    `option_id`   INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_picked_options_users1_idx` (`user_id` ASC) VISIBLE,
    INDEX `fk_picked_options_options1_idx` (`option_id` ASC) VISIBLE,
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
    CONSTRAINT `fk_picked_options_options1`
        FOREIGN KEY (`option_id`)
            REFERENCES `forbidden_polls`.`options` (`id`),
    CONSTRAINT `fk_picked_options_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `forbidden_polls`.`users` (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
