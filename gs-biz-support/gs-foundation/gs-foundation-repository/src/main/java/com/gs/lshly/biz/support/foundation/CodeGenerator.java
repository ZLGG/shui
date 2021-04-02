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
        dataSourceConfig.setUrl("jdbc:mysql://114.116.35.42:3306/gs-lshly?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("dev_gs_lshly");
        dataSourceConfig.setPassword("Dev_gs_lshly@2020");

        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("gs_");

        // 包配置
        packageConfig.setModuleName("");
        packageConfig.setParent("com.gs.lshly");
        packageConfig.setEntity("biz.support.platadmin.entity");
        packageConfig.setMapper("biz.support.platadmin.mapper");
        packageConfig.setService("biz.support.platadmin.repository");
        packageConfig.setServiceImpl("biz.support.platadmin.repository.impl");
        packageConfig.setController("facade.platadmin.controller.platadmin");

        //路径G
        globalConfig.setOutputDir("D:/GenCode/lshly/" + "/src/main/java");
        //表名
        strategy.setInclude("gs_site");
        // 作者
        globalConfig.setAuthor("tangjiemin");

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setPackageInfo(packageConfig);
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }
}
