package com.gs.lshly.biz.support.foundation;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * MyBatis自动生成代码
 *
 * @author LiYuan
 * @date 2018/10/19
 **/
public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());


        globalConfig.setBaseResultMap(true);
        globalConfig.setOpen(false);
        globalConfig.setDateType(DateType.ONLY_DATE);
        //globalConfig.setSwagger2(true);
        globalConfig.setServiceName("I%sRepository");
        globalConfig.setServiceImplName("%sRepositoryImpl");

        // 数据源配置
        dataSourceConfig.setUrl("jdbc:mysql://rm-bp15j440rs952i1cm6o.mysql.rds.aliyuncs.com:3306/fy_mall?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("bWzdYytdf6");

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("gs_");

        // 包配置
        packageConfig.setModuleName("");
        packageConfig.setParent("com.gs.lshly");
        packageConfig.setEntity("biz.support.foundation.entity");
        packageConfig.setMapper("biz.support.foundation.mapper");
        packageConfig.setService("biz.support.foundation.repository");
        packageConfig.setServiceImpl("biz.support.foundation.repository.impl");
        //packageConfig.setController("facade.foundation.controller.foundation");

        //路径G
        globalConfig.setOutputDir("C:/works/program/citydo/gs-mall/gs-biz-support/gs-foundation/gs-foundation-repository/src/main/java");
        //表名
        strategy.setInclude("gs_site_topic","gs_site_topic_goods");
        // 作者
        globalConfig.setAuthor("yingjun");

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }
}
