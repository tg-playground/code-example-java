# Software Design Document for mybatis-sql-map-by-xml

Content

- Architecture Design
- System Design
  - System User Cases
  - System Modules
  - Database Design
  - System Interface Design
  - User Interface Design
- Detailed Design
  - xxx Module

## System Design

### Database Design

t_department

| Name        | Type         | Length | NULL     | Default | Key  | Description |
| ----------- | ------------ | ------ | -------- | ------- | ---- | ----------- |
| id          | INT UNSIGNED |        | not null |         | P    |             |
| name        | VARCHAR      | 64     | not null |         |      |             |
| delete_flag | BOOL         |        | not null | 0       |      |             |
| create_time | TIMESTAMP    |        | not null | NOW()   |      |             |
| modify_time | TIMESTAMP    |        | null     |         |      |             |

t_employee

| Name        | Type         | Length | NULL     | Default | Key  |    Comment    |
| ----------- | ------------ | ------ | -------- | ------- | ---- | :-----------: |
| id          | INT UNSIGNED |        | not null |         | P    |               |
| name        | VARCHAR      | 64     | not null |         |      |               |
| nickname    | VARCHAR      | 64     | null     |         |      |               |
| age         | INT          |        | null     |         |      |               |
| dept_id     | INT          |        | null     |         |      | department id |
| delete_flag | BOOL         |        | not null | 0       |      |               |
| create_time | TIMESTAMP    |        | not null | NOW()   |      |               |
| modify_time | TIMESTAMP    |        | null     |         |      |               |

```sql
CREATE DATABASE IF NOT EXISTS `mybatis-sql-map-by-xml` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `mybatis-sql-map-by-xml`;

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
```



## Detailed Design

> - Project Creation Description (project files' structure)
> - Modules' UML Class Diagrams (classes hierarchy and classes supported interfaces)
> - Modules' UML Sequence Diagram (classes' interfaces invoke sequence)
> - Modules' Core Function Implementation Algorithm

Implementation Process

- Generating basic entities, mappers and XML SQL map by the mybatis-generator project.
- Add MyBatis Configurations.
- Add classes hierarchy files.
- Write all service interfaces methods
- Write all service unit tests
- Write all service implementations
- Write all mapper interfaces methods
- Write all mapper unit tests
- Write all XML SQL map.
- Finally, Pass all Mapper unit tests
- Update documents

Class Diagram

![](class_diagram.png)

Sequence Diagram

--END--