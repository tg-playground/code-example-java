package com.taogen.example.mybatisplus.generator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

/**
 * @author Taogen
 */
@Data
@Configuration
//@EnableConfigurationProperties(MyBatisPlusCodeGeneratorConfig.class)
@PropertySource("classpath:mybatisplus-generator.properties")
@ConfigurationProperties
public class MyBatisPlusCodeGeneratorConfig {
    private String author;
    private String dataSourceUrl;
    private String dataSourceDriverName;
    private String dataSourceUsername;
    private String dataSourcePasswd;
    private List<TableConfig> tableConfigs;

    @Data
    public static class TableConfig {
        private String parentPackage;
        private String moduleName;
        private String superEntityClass;
        private String[] superEntityClassFields;
        private String superControllerClass;
        private String tableName;
        private String tablePrefix;
    }

}
