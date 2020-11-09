CREATE DATABASE IF NOT EXISTS `springboot-crud` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `springboot-crud`;

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'user ID',
    name VARCHAR(64) NOT NULL COMMENT 'user name',
    password VARCHAR(64) NOT NULL COMMENT 'user password',
    nickname VARCHAR(64) NULL COMMENT '',
    age INT NULL COMMENT '',
    delete_flag BOOL NOT NULL DEFAULT 0 comment 'delete flag',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    modify_time TIMESTAMP NULL)
ENGINE='InnoDB'
COMMENT='user table';

DROP TABLE IF EXISTS `t_file`;

CREATE TABLE `t_file` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'file ID',
    name VARCHAR(64) NOT NULL COMMENT 'file name',
    uri VARCHAR(255) NOT NULL COMMENT 'file uri',
    delete_flag BOOL NOT NULL DEFAULT 0 comment 'delete flag',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    modify_time TIMESTAMP NULL)
ENGINE='InnoDB'
COMMENT='file table';

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'department ID',
    name VARCHAR(64) NOT NULL COMMENT 'department name',
    delete_flag BOOL NOT NULL DEFAULT 0 comment 'delete flag',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    modify_time TIMESTAMP NULL)
ENGINE='InnoDB'
COMMENT='department table';

CREATE INDEX index_for_name USING BTREE ON `t_department` (name(64));

DELIMITER //
   DROP PROCEDURE IF EXISTS get_dept_by_id //
   CREATE PROCEDURE get_dept_by_id (IN dept_id INT)
   BEGIN 
      SELECT * FROM `t_department` WHERE id = dept_id; 
   END// 
DELIMITER ;

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'employee ID',
    name VARCHAR(64) NOT NULL COMMENT 'employee name',
    nickname VARCHAR(64) NULL COMMENT '',
    age INT NULL COMMENT '',
    dept_id INT UNSIGNED COMMENT 'department ID of employee',
    delete_flag BOOL NOT NULL DEFAULT 0 comment 'delete flag',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    modify_time TIMESTAMP NULL)
ENGINE='InnoDB'
COMMENT='employee table';

CREATE INDEX index_for_name_and_deptid USING BTREE ON `t_employee` (name(64), dept_id);

DELIMITER //
   DROP PROCEDURE IF EXISTS get_emp_by_id //
   CREATE PROCEDURE get_emp_by_id (IN emp_id INT)
   BEGIN 
      SELECT * FROM `t_employee` WHERE id = emp_id; 
   END// 
DELIMITER ;