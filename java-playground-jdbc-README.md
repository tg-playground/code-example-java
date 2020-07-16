# Database Programming Play



<h3 id="content">Content</h3>

- Connection Database
  - jdbc-basic
  - java-redis-client-library-jedis
  - java-driver-for-mongodb
  - create-database-tables-by-java
- Connection Pool
  - apache dbcp
  - druid
- Java ORM
  - hibernate
    - hibernate-generator
  - mybatis
    - mybatis-generator
  - paoding-rose-jade
- Java NoSQL
  - MongoDB
  - redis
  - hadoop
  - hive
  - hbase
- Java Cache
  - ehcache
  - guava cache
  - memcached
- Java Database
  - mysql

### Main

### Connection Database



<h3 id="">JDBC Basic</h3>

Common JDBC URL refer to [JDBC Driver Connection URL](#jdcu)

This play contains

- Establishing a connection for MySQL.
- Processing SQL statement with JDBC
- Connecting with DataSource Objects
- ...

This play steps

- Creating a new Maven project.
- Adding dependencies in pom.xml. `mysql-connector-java`



References:

[1] [The Java Tutorials - doc](https://docs.oracle.com/javase/tutorial/jdbc/basics/sqldatasources.html)

[`back to content`](#content)

---

### Appendix

<h3 id="jdcu">JDBC Driver Connection URL</h3>

Oracle

| **JDBC Driver**       | **oracle.jdbc.OracleDriver**                  |
| --------------------- | --------------------------------------------- |
| **JDBC Url**          | **jdbc:oracle:thin:@localhost:1521/orclpdb1** |
| **Hibernate Dialect** | **org.hibernate.dialect.Oracle12cDialect**    |

MySQL

| **JDBC Driver**       | **com.mysql.jdbc.Driver**                                    |
| --------------------- | ------------------------------------------------------------ |
| **JDBC Url**          | **jdbc:mysql://localhost/high_performance_java_persistence** |
| **Hibernate Dialect** | **org.hibernate.dialect.MySQL57Dialect**                     |

PostgreSQL

| **JDBC Driver**       | **org.postgresql.Driver**                                    |
| --------------------- | ------------------------------------------------------------ |
| **JDBC Url**          | **jdbc:postgresql://localhost/high_performance_java_persistence** |
| **Hibernate Dialect** | **org.hibernate.dialect.PostgreSQL95Dialect**                |

SQL Server

| **JDBC Driver**       | **com.microsoft.sqlserver.jdbc.SQLServerDriver**             |
| --------------------- | ------------------------------------------------------------ |
| **JDBC Url**          | **jdbc:sqlserver://localhost;instance=SQLEXPRESS;databaseName=high_performance_java_persistence** |
| **Hibernate Dialect** | **org.hibernate.dialect.SQLServer2012Dialect**               |

MariaDB

| **JDBC Driver**       | **org.mariadb.jdbc.Driver**                                  |
| --------------------- | ------------------------------------------------------------ |
| **JDBC Url**          | **jdbc:mariadb://127.0.0.1/high_performance_java_persistence** |
| **Hibernate Dialect** | **org.hibernate.dialect.MariaDB53Dialect**                   |

Db2 Express-C

| **JDBC Driver**       | **com.ibm.db2.jcc.DB2Driver**        |
| --------------------- | ------------------------------------ |
| **JDBC Url**          | **jdbc:db2://127.0.0.1:50000/hpjp**  |
| **Hibernate Dialect** | **org.hibernate.dialect.DB2Dialect** |

H2

| **JDBC Driver**       | **org.h2.Driver**                                 |
| --------------------- | ------------------------------------------------- |
| **JDBC Url**          | **jdbc:h2:mem:high_performance_java_persistence** |
| **Hibernate Dialect** | **org.hibernate.dialect.H2Dialect**               |

Others: SAP HANA, Informix, HSQLDB, Derby
