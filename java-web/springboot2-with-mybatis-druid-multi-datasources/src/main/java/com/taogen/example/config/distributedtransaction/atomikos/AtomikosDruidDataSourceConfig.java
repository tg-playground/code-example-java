package com.taogen.example.config.distributedtransaction.atomikos;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;
import javax.transaction.UserTransaction;
import java.util.Properties;

/**
 * @author Taogen
 */
@Configuration
@Profile("distributed-transaction-atomikos")
public class AtomikosDruidDataSourceConfig {

    @Bean(name = "distributedMasterDataSource")
    @Primary
    @Autowired
    public DataSource distributedMasterDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("master");
        ds.setMaxPoolSize(5);
        Properties prop = build(env, "spring.datasource.druid.master.");
        ds.setXaProperties(prop);
        return ds;
    }

    @Bean(name = "distributedSlaveDataSource")
    @Autowired
    public DataSource distributedSlaveDataSource(Environment env) {
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName("slave");
        ds.setMaxPoolSize(5);
        Properties prop = build(env, "spring.datasource.druid.slave.");
        ds.setXaProperties(prop);
        return ds;
    }

    /**
     * Inject Things Manager
     *
     * @return
     */
    @Bean(name = "xatx")
    public JtaTransactionManager regTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        UserTransaction userTransaction = new UserTransactionImp();
        return new JtaTransactionManager(userTransaction, userTransactionManager);
    }


    private Properties build(Environment env, String prefix) {
        Properties prop = new Properties();
        putProperty(env, prefix, "url", String.class, prop);
        putProperty(env, prefix, "username", String.class, prop);
        putProperty(env, prefix, "password", String.class, prop);
        putProperty(env, prefix, "driverClassName", String.class, prop);
        putProperty(env, prefix, "initialSize", Integer.class, prop);
        putProperty(env, prefix, "maxActive", Integer.class, prop);
        putProperty(env, prefix, "minIdle", Integer.class, prop);
        putProperty(env, prefix, "maxWait", Integer.class, prop);
        putProperty(env, prefix, "poolPreparedStatements", Boolean.class, prop);
        putProperty(env, prefix, "maxPoolPreparedStatementPerConnectionSize", Integer.class, prop);
        return prop;
    }

    private void putProperty(Environment env, String prefix, String propName, Class cls, Properties props) {
        Object value = env.getProperty(prefix + propName, cls);
        if (value != null) {
            props.put(propName, value);
        }
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");

        //Console administers users, add the following two lines to enter druid Background needs to be logged in
        //servletRegistrationBean.addInitParameter("loginUsername", "admin");
        //servletRegistrationBean.addInitParameter("loginPassword", "admin");
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setLogSlowSql(true); //slowSqlMillis Used for configuration SQL Slow standards, execution time exceeded slowSqlMillis What's more, it's slow.
        statFilter.setMergeSql(true); //SQL Merge configuration
        statFilter.setSlowSqlMillis(1000);//slowSqlMillis The default value is 3000, or 3 seconds.
        return statFilter;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        //Enforcement of multiple items is permitted SQL
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        wallFilter.setConfig(config);
        return wallFilter;
    }
}
