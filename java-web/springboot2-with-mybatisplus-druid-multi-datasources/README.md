# springboot2-with-mybatisplus-druid-multi-datasources

MyBatisPlus is similar to MyBatis. Just replace SqlSessionFactoryBean to MybatisSqlSessionFactoryBean 
for to use mybatis plus CRUD methods in multiple data sources.
```java
// SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
```

## References

- [What to do with the introduction of mybatis plus report invalid bound statement error? Just move your finger to change a place](https://cdmana.com/2021/06/20210627060546688a.html)
