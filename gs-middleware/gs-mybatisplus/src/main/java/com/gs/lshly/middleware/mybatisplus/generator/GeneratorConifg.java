
package com.gs.lshly.middleware.mybatisplus.generator;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * 控制器层
 *  * com.gs.lshly.facade.platadmin.controller.platadmin
 *  *
 *  * RPC层
 *  * com.gs.lshly.biz.support.platadmin.rpc
 *  *
 *  * 服务层
 *  * com.gs.lshly.biz.support.platadmin.service
 *  * com.gs.lshly.biz.support.platadmin.service.platadmin.impl
 *  *
 *  * 仓库层
 *  * com.gs.lshly.biz.support.platadmin.entity
 *  * com.gs.lshly.biz.support.platadmin.mapper
 *  * com.gs.lshly.biz.support.platadmin.repository
 *  *
 *  * DTO QTO VO层
 *  * com.gs.lshly.common.struct.platadmin.platadmin.dto
 *  * com.gs.lshly.common.struct.platadmin.platadmin.qto
 *  * com.gs.lshly.common.struct.platadmin.platadmin.vo
 */
public class GeneratorConifg {

    public static AutoGenerator autoGenerator = new AutoGenerator();
    public static GlobalConfig globalConfig = new GlobalConfig();
    public static DataSourceConfig dataSourceConfig = new DataSourceConfig();
    public static StrategyConfig strategy = new StrategyConfig();
    public static PackageConfig packageConfig = new PackageConfig();
    private static List<FileOutConfig> focList = new ArrayList<>();

    private static List<String> clientNameList = new ArrayList<>();
    private static List<String> pcH5List = new ArrayList<>();
    private static Map<String, String> dddMap = new HashMap<>();
    private static Map<String, String> templateMap = new HashMap<>();
    private static Map<String, String> itemTypeMap = new HashMap<>();

    public static String projectPath = "D:\\generator\\code\\lshly\\";
    public static String modelName = "flatfrom";
    public static String controllerBasePath = "flatform";
    public static boolean isLogicTable = false;
    public static boolean isCutRepository = false;
    public static boolean isCutController = false;
    private static Map<String, Object> injectionDataMap = new HashMap<>();

    static {
        pcH5List.add("pc");
        pcH5List.add("h5");
        initGenerItemTypeMapList();
    }

    public static void configInit() {

        GeneratorConifg.autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        GeneratorConifg.globalConfig.setAuthor("ABCD");
        GeneratorConifg.globalConfig.setBaseResultMap(true);
        /** 有文件不再生成 **/
        GeneratorConifg.globalConfig.setFileOverride(false);
        GeneratorConifg.globalConfig.setDateType(DateType.ONLY_DATE);
        GeneratorConifg.globalConfig.setSwagger2(false);
        GeneratorConifg.globalConfig.setServiceName("I%sRepository");
        GeneratorConifg.globalConfig.setServiceImplName("%sRepositoryImpl");
        GeneratorConifg.globalConfig.setOpen(false);
        // 数据源配置
        GeneratorConifg.dataSourceConfig.setUrl("jdbc:mysql://114.116.35.42:3306/gs-lshly?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8");
        GeneratorConifg.dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        GeneratorConifg.dataSourceConfig.setUsername("dev_gs_lshly");
        GeneratorConifg.dataSourceConfig.setPassword("Dev_gs_lshly@2020");
        // 包配置
        GeneratorConifg.packageConfig.setModuleName("");
        // 策略配置
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass(Model.class);
        strategy.setControllerMappingHyphenStyle(true);

    }

    public static void updateDefaultConfig() {

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("/gen_templates/entity.java");
        templateConfig.setEntity("");
        templateConfig.setMapper("");
        templateConfig.setService("");
        templateConfig.setServiceImpl("");
        templateConfig.setController("");
        templateConfig.setXml(null);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        GeneratorConifg.globalConfig.setOutputDir(GeneratorConifg.projectPath);
        GeneratorConifg.autoGenerator.setGlobalConfig(GeneratorConifg.globalConfig);
        GeneratorConifg.autoGenerator.setDataSource(GeneratorConifg.dataSourceConfig);
        GeneratorConifg.autoGenerator.setPackageInfo(GeneratorConifg.packageConfig);
        GeneratorConifg.autoGenerator.setStrategy(GeneratorConifg.strategy);
    }

    public static void injectionDeleteBatch(boolean bool) {
        if (bool) {
            injectionDataMap.put("delBatch", "YES");
        } else {
            injectionDataMap.put("delBatch", "NOT");
        }
    }

    public static void bindInjectionConfig() {
        InjectionConfig injection = new InjectionConfig() {
            @Override
            public void initMap() {
                injectionDataMap.put("modelName", modelName);
                injectionDataMap.put("controllerBasePath", controllerBasePath);
                this.setMap(injectionDataMap);
            }
        };
        injection.setFileOutConfigList(focList);
        GeneratorConifg.autoGenerator.setCfg(injection);
    }


    private static void initGenerItemTypeMapList() {
        dddMap.put("dto", "com.gs.lshly.common.struct.{client}.{pch5}.{centerName}.dto");
        dddMap.put("qto", "com.gs.lshly.common.struct.{client}.{pch5}.{centerName}.qto");
        dddMap.put("vo", "com.gs.lshly.common.struct.{client}.{pch5}.{centerName}.vo");
        dddMap.put("srv", "com.gs.lshly.biz.support.{centerName}.service.{client}.{pch5}");
        dddMap.put("srvImpl", "com.gs.lshly.biz.support.{centerName}.service.{client}.{pch5}.impl");
        dddMap.put("rpc", "com.gs.lshly.rpc.api.{client}.{pch5}.{centerName}");
        dddMap.put("rpcImpl", "com.gs.lshly.biz.support.{centerName}.rpc.{client}.{pch5}");
        dddMap.put("controller", "com.gs.lshly.facade.{client}.controller.{pch5}.{centerName}");
        dddMap.put("entity", "com.gs.lshly.biz.support.{centerName}.entity");
        dddMap.put("mapper", "com.gs.lshly.biz.support.{centerName}.mapper");
        dddMap.put("dao", "com.gs.lshly.biz.support.{centerName}.repository");
        dddMap.put("daoImpl", "com.gs.lshly.biz.support.{centerName}.repository.impl");

        templateMap.put("dto", "/gen_templates/dto.java.ftl");
        templateMap.put("qto", "/gen_templates/qto.java.ftl");
        templateMap.put("vo", "/gen_templates/vo.java.ftl");
        templateMap.put("srv", "/gen_templates/srv.java.ftl");
        templateMap.put("srvImpl", "/gen_templates/srv_impl.java.ftl");
        templateMap.put("rpc", "/gen_templates/rpc.java.ftl");
        templateMap.put("rpcImpl", "/gen_templates/rpc_impl.java.ftl");
        templateMap.put("controller", "/gen_templates/controller.java.ftl");
        templateMap.put("entity", "/gen_templates/entity.java.ftl");
        templateMap.put("mapper", "/gen_templates/mapper.java.ftl");
        templateMap.put("dao", "/gen_templates/dao.java.ftl");
        templateMap.put("daoImpl", "/gen_templates/dao_impl.java.ftl");

        itemTypeMap.put("dto", "DTO");
        itemTypeMap.put("qto", "QTO");
        itemTypeMap.put("vo", "VO");
        itemTypeMap.put("srv", "Service");
        itemTypeMap.put("srvImpl", "ServiceImpl");
        itemTypeMap.put("rpc", "Rpc");
        itemTypeMap.put("rpcImpl", "Rpc");
        itemTypeMap.put("controller", "Controller");
        itemTypeMap.put("entity", "");
        itemTypeMap.put("mapper", "Mapper");
        itemTypeMap.put("dao", "Repository");
        itemTypeMap.put("daoImpl", "RepositoryImpl");

    }

    public static void createrItemList() {
        bindInjectionConfig();
        String centerName = GeneratorConifg.modelName;
        List<GItem> gItemList = new ArrayList<>();
        for (String clientName : clientNameList) {
            for (String pch5 : pcH5List) {
                gItemList.clear();
                if (clientName.equals("merchadmin") || clientName.equals("bbb")) {
                    for (Map.Entry<String, String> entry : dddMap.entrySet()) {
                        GItem item = new GItem();
                        item.setClientName(clientName);
                        item.setGkey(entry.getKey());
                        item.setItemType(itemTypeMap.get(entry.getKey()));
                        item.setCenterName(centerName);
                        item.setPackageKey(entry.getKey() + "Package");
                        item.setTemplatePath(templateMap.get(entry.getKey()));
                        String holderPackage = entry.getValue();
                        if (entry.getKey().equals("dao") || entry.getKey().equals("daoImpl") || entry.getKey().equals("entity") || entry.getKey().equals("mapper")) {
                            item.setPch5(null);
                            item.setPackageName(holderStr(holderPackage, clientName, centerName, null));
                        } else {
                            item.setPch5(pch5);
                            item.setPackageName(holderStr(holderPackage, clientName, centerName, pch5));
                        }
                        gItemList.add(item);
                    }
                    letGo(gItemList,pch5,clientName);
                }else{
                    for (Map.Entry<String, String> entry : dddMap.entrySet()) {
                        GItem item = new GItem();
                        item.setClientName(clientName);
                        item.setGkey(entry.getKey());
                        item.setItemType(itemTypeMap.get(entry.getKey()));
                        item.setCenterName(centerName);
                        item.setPackageKey(entry.getKey() + "Package");
                        String holderPackage = entry.getValue();
                        item.setPackageName(holderStr(holderPackage, clientName, centerName, null));
                        item.setTemplatePath(templateMap.get(entry.getKey()));
                        gItemList.add(item);
                    }
                    letGo(gItemList,"",clientName);
                    break;
                }

            }

        }
}

    private static void letGo(List<GItem> gItemList, String pch5, String centerName){
        focList.clear();
        for (GItem item : gItemList) {
            injectionDataMap.put(item.getPackageKey(), item.getPackageName());
            injectionDataMap.put("pch5", pch5);
            injectionDataMap.put("pch5Case", toUpperCase(pch5));
            injectionDataMap.put("clientName", getClientWrapName(item));
            injectionDataMap.put("centerName", centerName);
            focList.add(new FileOutConfig(item.getTemplatePath()) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    String outPath = genOutfline(item, tableInfo);
                    return outPath;
                }
            });
        }
        GeneratorConifg.autoGenerator.execute();
    }

    private static String genOutfline(GItem item, TableInfo tableInfo){
        String infaceSubfix = "";
        String pch5Subfix = "";
        String clientSubfix = "";
        if(item.getGkey().equals("rpc") || item.getGkey().equals("srv") || item.getGkey().equals("dao")){
            infaceSubfix = "I";
        }
        if(ObjectUtils.isNotEmpty(item.getPch5())){
            if(item.getPch5().equals("pc")){
                pch5Subfix = "PC";
            }
            if(item.getPch5().equals("h5")){
                pch5Subfix = "H5";
            }
        }
        String type = item.getGkey();
        if (type.equals("dao") || type.equals("daoImpl") || type.equals("entity") || type.equals("mapper")){

        }else{
            clientSubfix = getClientWrapName(item);
        }
        String outfile;
        String projectPathExt = projectPath + item.getExtDir();
        if(item.getClientName().equals("merchadmin") || item.getClientName().equals("bbb") || item.getClientName().equals("bbc")){
            if(item.getClientName().equals("bbc")){
                outfile = projectPathExt + infaceSubfix + clientSubfix + toUpperCase(item.getCenterName()) +  tableInfo.getEntityName() + item.getItemType() + ".java";
            }else {
                outfile = projectPathExt + infaceSubfix + pch5Subfix + clientSubfix + toUpperCase(item.getCenterName()) +  tableInfo.getEntityName() + item.getItemType() + ".java";
            }

        }else{
            outfile =  projectPathExt  + infaceSubfix + tableInfo.getEntityName() + item.getItemType() + ".java";
        }
        return outfile;
    }


    private static String getClientWrapName(GItem item){
        String wrapName = "";
        if(item.getClientName().equals("merchadmin")){
            wrapName = "Merch";
        }else if(item.getClientName().equals("bbb")){
            wrapName = "Bbb";
        }else if(item.getClientName().equals("bbc")){
            wrapName = "Bbc";
        }
        return wrapName;
    }

    public static void setConfigIncludeDesc(String modelDesc){
        injectionDataMap.put("modelDesc",modelDesc);
    }

    public static void addClient(String clientName){
       if(!clientNameList.contains(clientName)){
           clientNameList.add(clientName);
       }
    }

    public static List<String> getClientNameList(){
       return clientNameList;
    }

    /** 取注入包的全路径 **/
    public static String getInjectionPackage(String keyName){
        return String.valueOf(injectionDataMap.get(keyName));
    }

    /**
     * 包名转路径
     * @param packageName
     * @return
     */
    public static String packageCoverDirPath(String packageName){
       return  StringUtils.replace(packageName,".","\\") + "\\";
    }

    /**
     * 工程路径到maven工程路径
     * @return
     */
    public static String getProjectPathToMavenProjectPath(){
        return  GeneratorConifg.projectPath + "\\src\\main\\java\\";
    }

    private static String holderStr(String holderStr,String clientName,String centerName,String pch5){
        holderStr = StringUtils.replace(holderStr,"{client}",clientName);
        holderStr = StringUtils.replace(holderStr,"{centerName}",centerName);
        if(ObjectUtils.isEmpty(pch5)){
            holderStr = StringUtils.replace(holderStr,".{pch5}","");
        }else{
            holderStr = StringUtils.replace(holderStr,"{pch5}",pch5);
        }
        return  holderStr;
    }


    public static String toUpperCase(String strs) {
        String str = "";
        if(strs.equals("merchadmin")){
            str = "Merch";
        }else if(strs.equals("bbb")){
            str = "Bbb";
        }else if(strs.equals("bbc")){
            str = "Bbc";
        }else if(strs.equals("h5")){
            str = "H5";
        }else if(strs.equals("pc")){
            str = "PC";
        }else if(strs.equals("dto")){
            str = "DTO";
        }else if(strs.equals("qto")){
            str = "QTO";
        }else if(strs.equals("vo")){
            str = "VO";
        }
        return str;
    }

}
