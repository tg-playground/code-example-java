package com.taogen.example.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Plus Generator
 * 输入模块和表名，自动生成 Controller, Service, Mapper interface, Mapper XML 代码
 *
 * @author Taogen
 */
@Component
public class MyBatisPlusCodeGenerator {

    private static final Logger logger = LogManager.getLogger();
    private static final String projectPath = System.getProperty("user.dir");

    @Resource
    private MyBatisPlusCodeGeneratorConfig config;

    public Boolean generate() {
        config.getTableConfigs().forEach(tableConfig -> generateTable(getGenerator(config), tableConfig));
        return true;
    }

    private AutoGenerator getGenerator(MyBatisPlusCodeGeneratorConfig config) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(config.getAuthor());
        gc.setOpen(false);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(config.getDataSourceUrl());
        dsc.setDriverName(config.getDataSourceDriverName());
        dsc.setUsername(config.getDataSourceUsername());
        dsc.setPassword(config.getDataSourcePasswd());
        mpg.setDataSource(dsc);
        return mpg;
    }

    private void generateTable(AutoGenerator generator, MyBatisPlusCodeGeneratorConfig.TableConfig tableConfig) {
        logger.info("table config is {}", tableConfig);
        // 包配置
        PackageConfig pc = new PackageConfig();
        // TODO UPDATE_ME
        if (tableConfig.getModuleName() != null) {
            pc.setModuleName(tableConfig.getModuleName());
        }
        pc.setParent(tableConfig.getParentPackage());
        generator.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        generator.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        generator.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        if (tableConfig.getSuperEntityClass() != null) {
            strategy.setSuperEntityClass(tableConfig.getSuperEntityClass());
            // 写于父类中的公共字段
            if (tableConfig.getSuperEntityClassFields() != null) {
                strategy.setSuperEntityColumns(tableConfig.getSuperEntityClassFields());
            }
        }
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // TODO UPDATE ME
        if (tableConfig.getSuperControllerClass() != null) {
            // 公共父类
            strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        }
        strategy.setInclude(tableConfig.getTableName());
        strategy.setControllerMappingHyphenStyle(true);
        // TODO UPDATE_ME
        if (tableConfig.getTablePrefix() != null && !tableConfig.getTablePrefix().isEmpty()) {
            strategy.setTablePrefix(tableConfig.getTablePrefix());
        }
        generator.setStrategy(strategy);
        generator.setTemplateEngine(new FreemarkerTemplateEngine());

        generator.execute();
        logger.info("generate {}...", tableConfig.getTableName());
    }
}
