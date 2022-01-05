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

START TRANSACTION;
USE `forbidden_polls`;
INSERT INTO `forbidden_polls`.`option_types` (`id`, `type`)
VALUES (DEFAULT, 'select');
INSERT INTO `forbidden_polls`.`option_types` (`id`, `type`)
VALUES (DEFAULT, 'multi-select');
INSERT INTO `forbidden_polls`.`option_types` (`id`, `type`)
VALUES (DEFAULT, 'custom');

COMMIT;

START TRANSACTION;
USE `forbidden_polls`;
INSERT INTO `forbidden_polls`.`user_roles` (`id`, `name`)
VALUES (DEFAULT, 'admin');
INSERT INTO `forbidden_polls`.`user_roles` (`id`, `name`)
VALUES (DEFAULT, 'user');
INSERT INTO `forbidden_polls`.`user_roles` (`id`, `name`)
VALUES (DEFAULT, 'anonymous');
INSERT INTO `forbidden_polls`.`user_roles` (`id`, `name`)
VALUES (DEFAULT, 'banned');

COMMIT;

START TRANSACTION;
USE `forbidden_polls`;
INSERT INTO `forbidden_polls`.`genders` (`id`, `name`)
VALUES (DEFAULT, 'female');
INSERT INTO `forbidden_polls`.`genders` (`id`, `name`)
VALUES (DEFAULT, 'male');
INSERT INTO `forbidden_polls`.`genders` (`id`, `name`)
VALUES (DEFAULT, 'other');

COMMIT;

START TRANSACTION;
USE forbidden_polls;
INSERT INTO `countries` (`id`, `iso_code`, `name`)
VALUES (1, 'AF', 'Afghanistan'),
       (2, 'AX', 'Aland Islands'),
       (3, 'AL', 'Albania'),
       (4, 'DZ', 'Algeria'),
       (5, 'AS', 'American Samoa'),
       (6, 'AD', 'Andorra'),
       (7, 'AO', 'Angola'),
       (8, 'AI', 'Anguilla'),
       (9, 'AQ', 'Antarctica'),
       (10, 'AG', 'Antigua and Barbuda'),
       (11, 'AR', 'Argentina'),
       (12, 'AM', 'Armenia'),
       (13, 'AW', 'Aruba'),
       (14, 'AU', 'Australia'),
       (15, 'AT', 'Austria'),
       (16, 'AZ', 'Azerbaijan'),
       (17, 'BS', 'Bahamas'),
       (18, 'BH', 'Bahrain'),
       (19, 'BD', 'Bangladesh'),
       (20, 'BB', 'Barbados'),
       (21, 'BY', 'Belarus'),
       (22, 'BE', 'Belgium'),
       (23, 'BZ', 'Belize'),
       (24, 'BJ', 'Benin'),
       (25, 'BM', 'Bermuda'),
       (26, 'BT', 'Bhutan'),
       (27, 'BO', 'Bolivia'),
       (28, 'BQ', 'Bonaire, Sint Eustatius and Saba'),
       (29, 'BA', 'Bosnia and Herzegovina'),
       (30, 'BW', 'Botswana'),
       (31, 'BV', 'Bouvet Island'),
       (32, 'BR', 'Brazil'),
       (33, 'IO', 'British Indian Ocean Territory'),
       (34, 'BN', 'Brunei Darussalam'),
       (35, 'BG', 'Bulgaria'),
       (36, 'BF', 'Burkina Faso'),
       (37, 'BI', 'Burundi'),
       (38, 'KH', 'Cambodia'),
       (39, 'CM', 'Cameroon'),
       (40, 'CA', 'Canada'),
       (41, 'CV', 'Cape Verde'),
       (42, 'KY', 'Cayman Islands'),
       (43, 'CF', 'Central African Republic'),
       (44, 'TD', 'Chad'),
       (45, 'CL', 'Chile'),
       (46, 'CN', 'China'),
       (47, 'CX', 'Christmas Island'),
       (48, 'CC', 'Cocos (Keeling) Islands'),
       (49, 'CO', 'Colombia'),
       (50, 'KM', 'Comoros'),
       (51, 'CG', 'Congo'),
       (52, 'CD', 'Congo, Democratic Republic of the Congo'),
       (53, 'CK', 'Cook Islands'),
       (54, 'CR', 'Costa Rica'),
       (55, 'CI', 'Cote D\'Ivoire'),
       (56, 'HR', 'Croatia'),
       (57, 'CU', 'Cuba'),
       (58, 'CW', 'Curacao'),
       (59, 'CY', 'Cyprus'),
       (60, 'CZ', 'Czech Republic'),
       (61, 'DK', 'Denmark'),
       (62, 'DJ', 'Djibouti'),
       (63, 'DM', 'Dominica'),
       (64, 'DO', 'Dominican Republic'),
       (65, 'EC', 'Ecuador'),
       (66, 'EG', 'Egypt'),
       (67, 'SV', 'El Salvador'),
       (68, 'GQ', 'Equatorial Guinea'),
       (69, 'ER', 'Eritrea'),
       (70, 'EE', 'Estonia'),
       (71, 'ET', 'Ethiopia'),
       (72, 'FK', 'Falkland Islands (Malvinas)'),
       (73, 'FO', 'Faroe Islands'),
       (74, 'FJ', 'Fiji'),
       (75, 'FI', 'Finland'),
       (76, 'FR', 'France'),
       (77, 'GF', 'French Guiana'),
       (78, 'PF', 'French Polynesia'),
       (79, 'TF', 'French Southern Territories'),
       (80, 'GA', 'Gabon'),
       (81, 'GM', 'Gambia'),
       (82, 'GE', 'Georgia'),
       (83, 'DE', 'Germany'),
       (84, 'GH', 'Ghana'),
       (85, 'GI', 'Gibraltar'),
       (86, 'GR', 'Greece'),
       (87, 'GL', 'Greenland'),
       (88, 'GD', 'Grenada'),
       (89, 'GP', 'Guadeloupe'),
       (90, 'GU', 'Guam'),
       (91, 'GT', 'Guatemala'),
       (92, 'GG', 'Guernsey'),
       (93, 'GN', 'Guinea'),
       (94, 'GW', 'Guinea-Bissau'),
       (95, 'GY', 'Guyana'),
       (96, 'HT', 'Haiti'),
       (97, 'HM', 'Heard Island and Mcdonald Islands'),
       (98, 'VA', 'Holy See (Vatican City State)'),
       (99, 'HN', 'Honduras'),
       (100, 'HK', 'Hong Kong'),
       (101, 'HU', 'Hungary'),
       (102, 'IS', 'Iceland'),
       (103, 'IN', 'India'),
       (104, 'ID', 'Indonesia'),
       (105, 'IR', 'Iran, Islamic Republic of'),
       (106, 'IQ', 'Iraq'),
       (107, 'IE', 'Ireland'),
       (108, 'IM', 'Isle of Man'),
       (109, 'IL', 'Israel'),
       (110, 'IT', 'Italy'),
       (111, 'JM', 'Jamaica'),
       (112, 'JP', 'Japan'),
       (113, 'JE', 'Jersey'),
       (114, 'JO', 'Jordan'),
       (115, 'KZ', 'Kazakhstan'),
       (116, 'KE', 'Kenya'),
       (117, 'KI', 'Kiribati'),
       (118, 'KP', 'Korea, Democratic People\'s Republic of'),
       (119, 'KR', 'Korea, Republic of'),
       (120, 'XK', 'Kosovo'),
       (121, 'KW', 'Kuwait'),
       (122, 'KG', 'Kyrgyzstan'),
       (123, 'LA', 'Lao People\'s Democratic Republic'),
       (124, 'LV', 'Latvia'),
       (125, 'LB', 'Lebanon'),
       (126, 'LS', 'Lesotho'),
       (127, 'LR', 'Liberia'),
       (128, 'LY', 'Libyan Arab Jamahiriya'),
       (129, 'LI', 'Liechtenstein'),
       (130, 'LT', 'Lithuania'),
       (131, 'LU', 'Luxembourg'),
       (132, 'MO', 'Macao'),
       (133, 'MK', 'Macedonia, the Former Yugoslav Republic of'),
       (134, 'MG', 'Madagascar'),
       (135, 'MW', 'Malawi'),
       (136, 'MY', 'Malaysia'),
       (137, 'MV', 'Maldives'),
       (138, 'ML', 'Mali'),
       (139, 'MT', 'Malta'),
       (140, 'MH', 'Marshall Islands'),
       (141, 'MQ', 'Martinique'),
       (142, 'MR', 'Mauritania'),
       (143, 'MU', 'Mauritius'),
       (144, 'YT', 'Mayotte'),
       (145, 'MX', 'Mexico'),
       (146, 'FM', 'Micronesia, Federated States of'),
       (147, 'MD', 'Moldova, Republic of'),
       (148, 'MC', 'Monaco'),
       (149, 'MN', 'Mongolia'),
       (150, 'ME', 'Montenegro'),
       (151, 'MS', 'Montserrat'),
       (152, 'MA', 'Morocco'),
       (153, 'MZ', 'Mozambique'),
       (154, 'MM', 'Myanmar'),
       (155, 'NA', 'Namibia'),
       (156, 'NR', 'Nauru'),
       (157, 'NP', 'Nepal'),
       (158, 'NL', 'Netherlands'),
       (159, 'AN', 'Netherlands Antilles'),
       (160, 'NC', 'New Caledonia'),
       (161, 'NZ', 'New Zealand'),
       (162, 'NI', 'Nicaragua'),
       (163, 'NE', 'Niger'),
       (164, 'NG', 'Nigeria'),
       (165, 'NU', 'Niue'),
       (166, 'NF', 'Norfolk Island'),
       (167, 'MP', 'Northern Mariana Islands'),
       (168, 'NO', 'Norway'),
       (169, 'OM', 'Oman'),
       (170, 'PK', 'Pakistan'),
       (171, 'PW', 'Palau'),
       (172, 'PS', 'Palestinian Territory, Occupied'),
       (173, 'PA', 'Panama'),
       (174, 'PG', 'Papua New Guinea'),
       (175, 'PY', 'Paraguay'),
       (176, 'PE', 'Peru'),
       (177, 'PH', 'Philippines'),
       (178, 'PN', 'Pitcairn'),
       (179, 'PL', 'Poland'),
       (180, 'PT', 'Portugal'),
       (181, 'PR', 'Puerto Rico'),
       (182, 'QA', 'Qatar'),
       (183, 'RE', 'Reunion'),
       (184, 'RO', 'Romania'),
       (185, 'RU', 'Russian Federation'),
       (186, 'RW', 'Rwanda'),
       (187, 'BL', 'Saint Barthelemy'),
       (188, 'SH', 'Saint Helena'),
       (189, 'KN', 'Saint Kitts and Nevis'),
       (190, 'LC', 'Saint Lucia'),
       (191, 'MF', 'Saint Martin'),
       (192, 'PM', 'Saint Pierre and Miquelon'),
       (193, 'VC', 'Saint Vincent and the Grenadines'),
       (194, 'WS', 'Samoa'),
       (195, 'SM', 'San Marino'),
       (196, 'ST', 'Sao Tome and Principe'),
       (197, 'SA', 'Saudi Arabia'),
       (198, 'SN', 'Senegal'),
       (199, 'RS', 'Serbia'),
       (200, 'CS', 'Serbia and Montenegro'),
       (201, 'SC', 'Seychelles'),
       (202, 'SL', 'Sierra Leone'),
       (203, 'SG', 'Singapore'),
       (204, 'SX', 'Sint Maarten'),
       (205, 'SK', 'Slovakia'),
       (206, 'SI', 'Slovenia'),
       (207, 'SB', 'Solomon Islands'),
       (208, 'SO', 'Somalia'),
       (209, 'ZA', 'South Africa'),
       (210, 'GS', 'South Georgia and the South Sandwich Islands'),
       (211, 'SS', 'South Sudan'),
       (212, 'ES', 'Spain'),
       (213, 'LK', 'Sri Lanka'),
       (214, 'SD', 'Sudan'),
       (215, 'SR', 'Suriname'),
       (216, 'SJ', 'Svalbard and Jan Mayen'),
       (217, 'SZ', 'Swaziland'),
       (218, 'SE', 'Sweden'),
       (219, 'CH', 'Switzerland'),
       (220, 'SY', 'Syrian Arab Republic'),
       (221, 'TW', 'Taiwan, Province of China'),
       (222, 'TJ', 'Tajikistan'),
       (223, 'TZ', 'Tanzania, United Republic of'),
       (224, 'TH', 'Thailand'),
       (225, 'TL', 'Timor-Leste'),
       (226, 'TG', 'Togo'),
       (227, 'TK', 'Tokelau'),
       (228, 'TO', 'Tonga'),
       (229, 'TT', 'Trinidad and Tobago'),
       (230, 'TN', 'Tunisia'),
       (231, 'TR', 'Turkey'),
       (232, 'TM', 'Turkmenistan'),
       (233, 'TC', 'Turks and Caicos Islands'),
       (234, 'TV', 'Tuvalu'),
       (235, 'UG', 'Uganda'),
       (236, 'UA', 'Ukraine'),
       (237, 'AE', 'United Arab Emirates'),
       (238, 'GB', 'United Kingdom'),
       (239, 'US', 'United States'),
       (240, 'UM', 'United States Minor Outlying Islands'),
       (241, 'UY', 'Uruguay'),
       (242, 'UZ', 'Uzbekistan'),
       (243, 'VU', 'Vanuatu'),
       (244, 'VE', 'Venezuela'),
       (245, 'VN', 'Viet Nam'),
       (246, 'VG', 'Virgin Islands, British'),
       (247, 'VI', 'Virgin Islands, U.s.'),
       (248, 'WF', 'Wallis and Futuna'),
       (249, 'EH', 'Western Sahara'),
       (250, 'YE', 'Yemen'),
       (251, 'ZM', 'Zambia'),
       (252, 'ZW', 'Zimbabwe');

COMMIT

USE forbidden_polls;

INSERT INTO forbidden_polls.users (name, email, hashed_password, registration_date, birthday, user_role_id, country_id,
                                   gender_id)
VALUES ('Dzianis', 'plizzz.healme@gmail.com',
        '$s0$41010$+LvX+y5UQqJUljC8ViCIuA==$LoqQQKZ15D+wLMVn6GcSMEPScy9HKDkhhGCLP2wXzT8=', '2022-01-05 11:11:56',
        '1989-09-05 11:11:59', 1, 21, 2);

INSERT INTO forbidden_polls.users (name, email, hashed_password, registration_date, birthday, user_role_id, country_id,
                                   gender_id)
VALUES ('Alesia', 'alesia@gmail.com', '$s0$41010$irG01IpqRr3338XIBNascA==$o2pZtEmPtQPKyFHxQso+fXSRuRVs7LdOzfNSDalSPiw=',
        '2022-01-05 11:16:56', '1989-09-21 11:16:59', 2, 21, 1);

-- add surveys categories

INSERT INTO forbidden_polls.categories (name)
VALUES ('politics');

INSERT INTO forbidden_polls.categories (name)
VALUES ('health');

INSERT INTO forbidden_polls.categories (name)
VALUES ('sport');

-- add polls
START TRANSACTION;
INSERT INTO forbidden_polls.surveys (name, creation_date, description, instructions, image_url, category_id)
VALUES ('Poll about smoking', '2022-01-05 11:27:52', null, null, null, 2);

INSERT INTO forbidden_polls.questions (index_number, body, image_url, question_description, survey_id, option_type_id)
VALUES (1, 'Do you smoke?', null, null, 1, 1);

INSERT INTO forbidden_polls.options (body, question_id)
VALUES ('yes', 1);

INSERT INTO forbidden_polls.options (body, question_id)
VALUES ('no', 1);


COMMIT;



