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

| Name | Type         | Length | NULL     | Default | Key  | Description |
| ---- | ------------ | ------ | -------- | ------- | ---- | ----------- |
| id   | INT UNSIGNED |        | not null |         | P    |             |
| name | VARCHAR      | 64     | not null |         |      |             |

t_employee

| Name     | Type         | Length | NULL     | Default | Key  |    Comment    |
| -------- | ------------ | ------ | -------- | ------- | ---- | :-----------: |
| id       | INT UNSIGNED |        | not null |         | P    |               |
| name     | VARCHAR      | 64     | not null |         |      |               |
| nickname | VARCHAR      | 64     | null     |         |      |               |
| age      | INT          |        | null     |         |      |               |
| dept_id  | INT          |        | null     |         |      | department id |

```sql
CREATE DATABASE IF NOT EXISTS `mybatis-sql-map-by-xml` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `t_department`;

CREATE TABLE `t_department` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'department ID',
    name VARCHAR(64) NOT NULL COMMENT 'department name')
ENGINE='InnoDB'
COMMENT='department table';

CREATE INDEX index_for_name USING BTREE ON `t_department` (name(64));

DROP TABLE IF EXISTS `t_employee`;

CREATE TABLE `t_employee` (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'employee ID',
    name VARCHAR(64) NOT NULL COMMENT 'employee name',
    nickname VARCHAR(64) NULL COMMENT '',
    age INT NULL COMMENT '',
    dept_id INT UNSIGNED COMMENT 'department ID of employee')
ENGINE='InnoDB'
COMMENT='employee table';

CREATE INDEX index_for_name_and_deptid USING BTREE ON `t_employee` (name(64), dept_id);
```



## Detailed Design

> - Project Creation Description (project files' structure)
> - Modules' UML Class Diagrams (classes hierarchy and classes supported interfaces)
> - Modules' UML Sequence Diagram (classes' interfaces invoke sequence)
> - Modules' Core Function Implementation Algorithm

Implementation Process

- Generating basic entities, mappers and XML SQL map by the mybatis-generator project
- Write all mapper interfaces
- Write all SqlSessionFactory service and mapper service interfaces
- Write all service unit tests
- Write all service implementaions
- Write all XML SQL map.
- Pass all unit tests

Class Diagram

![](class_diagram.png)

Sequence Diagram

TODO