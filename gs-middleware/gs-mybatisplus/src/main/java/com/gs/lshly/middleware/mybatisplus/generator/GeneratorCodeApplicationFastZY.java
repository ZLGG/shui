package com.gs.lshly.middleware.mybatisplus.generator;//package com.gs.lshly.common.generator;
//
///**
// *
// * MyBatis自动生成代码
// * @author LiYuan
// * @date 2018/10/19
// **/
//public class GeneratorCodeApplicationFastZY {
//
//    public static void main(String[] args) {
//        /**
//         * 初始化配置,需要更改的自已设置
//         */
//        GeneratorConifg.configInit();
//        GeneratorConifg.strategy.setTablePrefix("gs_");
//
//        /**
//         *
//         * 不要直接使用这个文件.复制一份改成自已的文件名后。再使用
//         *
//         * ************************************************
//         * -------------------自已的配置开始----------------
//         * ************************************************
//         */
//
//        GeneratorConifg.projectPath = "E:\\Users\\zhao\\lshly\\";
//        //如果要拷贝项目进工程.注释掉这行。（建议:先在本地测一下）
//        //FileSynchro.trueWorkSpacePath = "E:\\Users\\zhao\\lshlycopy\\";
//
//        GeneratorConifg.globalConfig.setAuthor("zhaoyan");
//
//        //数据为表格
//        GeneratorConifg.strategy.setInclude("gs_order_regoods");
//        //表格说明
//        GeneratorConifg.setConfigIncludeDesc("退货单");
//
//        // foundation ,merchant,commodity,stock,trade,user
//        GeneratorConifg.modelName = "trade";
//
//        //是否是逻辑表(如果是逻辑表，只会留下Repository层)
//        GeneratorConifg.isLogicTable = false;
//
//        //是否去掉Repository层
//        GeneratorConifg.setIsCutRepository(false);
//
//        //是否去掉控制器层
//        GeneratorConifg.setIsCutController(false);
//
//        //是否开启批量删除
//        GeneratorConifg.injectionDeleteBatch(false);
//
//        // 前端工程
//        GeneratorConifg.facadeNodeName = "gs-merchant-admin";
//
//        //控制器的路由(PATH)根路径
//        GeneratorConifg.controllerBasePath = "trade";
//
//        /**
//         * ****************************************************************
//         * 要开启哪个业务管理端（各端生成的java文件，自已需要确定业务是不是需要使用，如果不需要，一定要删除掉）
//         * 默认是不会把Controller加到工程里的。自已到本地的代码目录里。按需复制到工程
//         * ****************************************************************
//         */
//        GeneratorConifg.addClient("platadmin");
//        GeneratorConifg.addClient("bbb");
//        GeneratorConifg.addClient("bbc");
//        GeneratorConifg.addClient("merchadmin");
//
//
//        /**
//         * ************************************************
//         * -------------------自已的配置结束----------------
//         * ************************************************
//         */
//
//
//        /**
//         * Mybatis-plus 默认包配置
//         */
//        GeneratorConifg.packageConfig.setParent("");
//        GeneratorConifg.packageConfig.setEntity("com.gs.lshly.biz.support." + GeneratorConifg.modelName + ".entity");
//        GeneratorConifg.packageConfig.setMapper("com.gs.lshly.biz.support." + GeneratorConifg.modelName + ".mapper");
//        GeneratorConifg.packageConfig.setService("com.gs.lshly.biz.support." + GeneratorConifg.modelName + ".repository");
//        GeneratorConifg.packageConfig.setServiceImpl("com.gs.lshly.biz.support." + GeneratorConifg.modelName + ".repository.impl");
//
//
//
//        /** 更新配置 **/
//        GeneratorConifg.updateDefaultConfig();
//
//        /** dto qto vo producerService rpc 扩展代码包配置,（全路径包名）**/
//        if(GeneratorConifg.isLogicTable == false){
//            GeneratorConifg.generatorDTO("com.gs.lshly.common.struct.{client}.{centerName}.dto");
//            GeneratorConifg.generatorQTO("com.gs.lshly.common.struct.{client}.{centerName}.qto");
//            GeneratorConifg.generatorVO("com.gs.lshly.common.struct.{client}.{centerName}.vo");
//            GeneratorConifg.generatorSrv("com.gs.lshly.biz.support.{centerName}.producerService.{client}");
//            GeneratorConifg.generatorSrvImpl("com.gs.lshly.biz.support.{centerName}.producerService.{client}.impl");
//            GeneratorConifg.generatorRpc("com.gs.lshly.rpc.api.{client}.{centerName}");
//            GeneratorConifg.generatorRpcImpl("com.gs.lshly.biz.support.{centerName}.rpc.{client}");
//            GeneratorConifg.generatorController();
//        }
//
//        /** 扩展数据邦定*/
//        GeneratorConifg.bindInjectionConfig();
//
//
//        GeneratorConifg.autoGenerator.execute();
//
//        /** paramFacadeNodeName 是gs-facade目录下具体的前端工程 **/
//        FileSynchro.synchroWorkSpace(GeneratorConifg.modelName,GeneratorConifg.facadeNodeName);
//
//    }
//}
