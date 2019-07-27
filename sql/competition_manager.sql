CREATE DATABASE `competition_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

use `competition_manager`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `club_name` varchar(100) DEFAULT NULL,
  `user_role` varchar(25) DEFAULT NULL,
  `activate_status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `access_token` (
  `token` varchar(256) NOT NULL,
  `user_id` int(11) NOT NULL,
  `expires_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`token`),
  KEY `fk_access_token_user1_idx` (`user_id`),
  CONSTRAINT `fk_access_token_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `competition` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `description` tinytext,
  `registration_status` varchar(20) NOT NULL DEFAULT 'OPEN',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `competition_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `display_name` varchar(255) NOT NULL,
  `lower_age` int(11) DEFAULT NULL,
  `upper_age` int(11) DEFAULT NULL,
  `lower_weight` int(11) DEFAULT NULL,
  `upper_weight` int(11) DEFAULT NULL,
  `lower_experience` int(11) DEFAULT NULL,
  `upper_experience` int(11) DEFAULT NULL,
  `male` varchar(20) DEFAULT NULL,
  `section` varchar(30) NOT NULL DEFAULT 'Лайт контакт',
  PRIMARY KEY (`id`),
  KEY `competition_category_section_idx` (`section`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `competition_has_competition_category` (
  `competition_id` int(11) NOT NULL,
  `competition_category_id` int(11) NOT NULL,
  PRIMARY KEY (`competition_id`,`competition_category_id`),
  KEY `fk_competition_has_competition_category_competition_categor_idx` (`competition_category_id`),
  KEY `fk_competition_has_competition_category_competition1_idx` (`competition_id`),
  CONSTRAINT `fk_competition_has_competition_category_competition1` FOREIGN KEY (`competition_id`) REFERENCES `competition` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_competition_has_competition_category_competition_category1` FOREIGN KEY (`competition_category_id`) REFERENCES `competition_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `registrated_sportsman` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `age` int(10) NOT NULL,
  `experience` int(10) NOT NULL,
  `weight` double NOT NULL,
  `club_name` varchar(50) NOT NULL,
  `competition_id` int(10) NOT NULL,
  `coach` varchar(100) NOT NULL,
  `male` varchar(10) NOT NULL,
  `competition_category_id` int(10) NOT NULL,
  `section` varchar(30) NOT NULL DEFAULT 'Лайт контакт',
  PRIMARY KEY (`id`),
  KEY `competition_category_id_fk` (`competition_category_id`),
  CONSTRAINT `registrated_sportsman_ibfk_1` FOREIGN KEY (`competition_category_id`) REFERENCES `competition_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `generated_grid` (
    `id` int(10) NOT NULL AUTO_INCREMENT,
    `grid_key` varchar(50) NOT NULL UNIQUE,
    `body` BLOB,
    `created_date` TIMESTAMP,
    primary key (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;