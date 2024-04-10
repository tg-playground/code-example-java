drop database if exists spring_boot_with_mybatis;
create database spring_boot_with_mybatis;
use spring_boot_with_mybatis;

CREATE TABLE `user`
(
    `user_id`   int         NOT NULL AUTO_INCREMENT,
    `user_name` varchar(64) NOT NULL,
    `password`  varchar(64) DEFAULT NULL,
    `email`     varchar(64) DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `users_table_un` (`user_name`)
) ENGINE=InnoDB;

CREATE TABLE `address`
(
    `address_id` int         NOT NULL AUTO_INCREMENT,
    `user_id`    int         NOT NULL,
    `city`       varchar(64) NOT NULL,
    `street`     varchar(64) NOT NULL,
    PRIMARY KEY (`address_id`)
) ENGINE=InnoDB;
