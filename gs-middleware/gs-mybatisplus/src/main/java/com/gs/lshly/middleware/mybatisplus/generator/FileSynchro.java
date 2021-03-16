package com.gs.lshly.middleware.mybatisplus.generator;

import cn.hutool.core.io.FileUtil;
import org.springframework.util.StringUtils;

public class FileSynchro {

    private static final String mavenDir = "\\src\\main\\java\\";

    public static  String trueWorkSpacePath = System.getProperty("user.dir");

    public static  String modelName = "platadmin";

    public static void synchroWorkSpace(String paramModelName,String paramFacadeNodeName){
        if(!StringUtils.isEmpty(paramModelName)){
            modelName = paramModelName;
        }
        System.out.println("GeneratorConifg.isCutRepository：" + GeneratorConifg.isCutRepository);
        System.out.println("GeneratorConifg.isLogicTable：" + GeneratorConifg.isLogicTable);
        System.out.println("GeneratorConifg.isCutController：" + GeneratorConifg.isCutController);
        if(GeneratorConifg.isCutRepository == false){
            synchroRpository();
        }

        if(GeneratorConifg.isLogicTable == false){
           synchroCommon();
        }

        if(GeneratorConifg.isLogicTable == false){
            synchroSrvAndSrvImpl();
        }


        if(GeneratorConifg.isLogicTable == false){
            synchroRpcApi();
        }

        if(GeneratorConifg.isLogicTable == false){
            synchroRpcImpl();
        }

        if(GeneratorConifg.isLogicTable == false && GeneratorConifg.isCutController == false){
           // synchroFacade();
        }

    }


    private static void synchroRpository(){
        String sourceRootPath = GeneratorConifg.getProjectPathToMavenProjectPath();
        String toRootPath;
        /** mybatis-plus 三大模块 不包括控制器 **/
        toRootPath = getTargetSupportRootPath("repository",true);
        String mapper =   GeneratorConifg.packageConfig.getMapper();
        String entity =   GeneratorConifg.packageConfig.getEntity();
        String repository =  GeneratorConifg.packageConfig.getService();

        FileUtil.copy(sourceRootPath + utilSortPackagePath(mapper),
                toRootPath +utilSortPackagePath(utilUpPackage(mapper)),
                false);

        FileUtil.copy(sourceRootPath + utilSortPackagePath(entity),
                toRootPath +utilSortPackagePath(utilUpPackage(entity)),
                false);

        FileUtil.copy(sourceRootPath + utilSortPackagePath(repository),
                toRootPath +utilSortPackagePath(utilUpPackage(repository)),
                false);

    }

    private static void synchroSrvAndSrvImpl() {

        /** srv 接口和实现 **/
        if (GeneratorConifg.isLogicTable == false) {
            String sourceRootPath = GeneratorConifg.getProjectPathToMavenProjectPath();
            String toRootPath = getTargetSupportRootPath("srv", true);
            for(String clientName: GeneratorConifg.getClientNameList()){
                String srv = GeneratorConifg.getInjectionPackage(clientName + "srvPackage");
                FileUtil.copy(sourceRootPath + utilSortPackagePath(srv),
                        toRootPath + utilSortPackagePath(utilUpPackage(srv)),
                        false);
            }
        }
    }

    private static void synchroRpcImpl(){
        String sourceRootPath = GeneratorConifg.getProjectPathToMavenProjectPath();
        String toRootPath = getTargetSupportRootPath("rpc",true);
        for(String clientName: GeneratorConifg.getClientNameList()){
            String rpcImpl = GeneratorConifg.getInjectionPackage(clientName + "rpcImplPackage");
            FileUtil.copy(sourceRootPath + utilSortPackagePath(rpcImpl),
                    toRootPath +utilSortPackagePath(utilUpPackage(rpcImpl)),
                    false);
        }

    }

    /** common struts **/
    private static void synchroCommon(){
        String sourceRootPath = GeneratorConifg.getProjectPathToMavenProjectPath();
        String toRootPath = getTargetCommonRootPath(true);
        for(String clientName: GeneratorConifg.getClientNameList()){
            String dto = GeneratorConifg.getInjectionPackage(clientName + "dtoPackage");
            String qto = GeneratorConifg.getInjectionPackage(clientName + "qtoPackage");
            String vo = GeneratorConifg.getInjectionPackage(clientName+ "voPackage");
            FileUtil.copy(sourceRootPath + utilSortPackagePath(dto),
                    toRootPath +utilSortPackagePath(utilUpPackage(dto)),
                    false);
            FileUtil.copy(sourceRootPath + utilSortPackagePath(qto),
                    toRootPath +utilSortPackagePath(utilUpPackage(qto)),
                    false);

            FileUtil.copy(sourceRootPath + utilSortPackagePath(vo),
                    toRootPath +utilSortPackagePath(utilUpPackage(vo)),
                    false);
        }


    }
    /** rpc api **/
    private static void synchroRpcApi(){
        String sourceRootPath = GeneratorConifg.getProjectPathToMavenProjectPath();
        String toRootPath = getTargetRpcApiRootPath(true);
        for(String clientName: GeneratorConifg.getClientNameList()){
            String rpcApi = GeneratorConifg.getInjectionPackage(clientName + "rpcPackage");
            FileUtil.copy(sourceRootPath + utilSortPackagePath(rpcApi),
                    toRootPath +utilSortPackagePath(utilUpPackage(rpcApi)),
                    false);
        }

    }


    /** 取仓储各工程目录**/
    private static String getTargetSupportRootPath(String suffixName,boolean maven){
        StringBuilder builder = new StringBuilder();
        builder.append("\\")
                .append(trueWorkSpacePath)
                .append("\\")
                .append("gs-biz-support")
                .append("\\")
                .append("gs-" + modelName)
                .append("\\")
                //gs-platadmin-（repository）
                .append("gs-"  + modelName + "-" + suffixName);
        if(maven){
            builder.append(mavenDir);
        }
        return builder.toString();
    }

    private static String getTargetCommonRootPath(boolean maven){
        StringBuilder builder = new StringBuilder();
        builder.append("\\")
                .append(trueWorkSpacePath)
                .append("\\")
                .append("gs-common");
        if(maven){
            builder.append(mavenDir);
        }
        return builder.toString();
    }

    private static String getTargetFacadeRootPath(String child,boolean maven){
        StringBuilder builder = new StringBuilder();
        builder.append("\\")
                .append(trueWorkSpacePath)
                .append("\\")
                .append("gs-facade")
                .append("\\")
                .append(child);
        if(maven){
            builder.append(mavenDir);
        }
        return builder.toString();
    }

    private static String getTargetRpcApiRootPath(boolean maven){
        StringBuilder builder = new StringBuilder();
        builder.append("\\")
                .append(trueWorkSpacePath)
                .append("\\")
                .append("gs-rpc-api");
        if(maven){
            builder.append(mavenDir);
        }
        return builder.toString();
    }


    private static  String utilSortPackagePath(String packageName){
       return   GeneratorConifg.packageCoverDirPath(packageName);
    }


    private static  String utilUpPackage(String s){
       if(!StringUtils.isEmpty(s)){
           return  s.substring(0,s.lastIndexOf( ".") );
       }
       return "";
    }


}
