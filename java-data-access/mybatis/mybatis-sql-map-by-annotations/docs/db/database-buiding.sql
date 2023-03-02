/***********************************
Database: mybatis-sql-map-by-annotations
************************************/

CREATE DATABASE IF NOT EXISTS `mybatis-sql-map-by-annotations` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mybatis-sql-map-by-annotations`;

/***********************************
Table: t_department
************************************/

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'department ID',
    name VARCHAR(64) NOT NULL COMMENT 'department name',
    delete_flag BOOL NOT NULL DEFAULT 0 comment 'delete flag',
    create_time TIMESTAMP NOT NULL DEFAULT NOW(),
    modify_time TIMESTAMP NULL)
ENGINE='InnoDB'
COMMENT='department table';

-- t_department Index

CREATE INDEX index_for_name USING BTREE ON `t_department` (name(64));

-- t_department Stored procedure

DELIMITER //
   DROP PROCEDURE IF EXISTS get_dept_by_id //
   CREATE PROCEDURE get_dept_by_id (IN dept_id INT)
   BEGIN 
      SELECT * FROM `t_department` WHERE id = dept_id; 
   END// 
DELIMITER ;

/***********************************
Table: t_employee
************************************/

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

-- t_employee Index

CREATE INDEX index_for_name_and_deptid USING BTREE ON `t_employee` (name(64), dept_id);

-- t_employee Stored procedure

DELIMITER //
   DROP PROCEDURE IF EXISTS get_emp_by_id //
   CREATE PROCEDURE get_emp_by_id (IN emp_id INT)
   BEGIN 
      SELECT * FROM `t_employee` WHERE id = emp_id; 
   END// 
DELIMITER ;