package com.gs.lshly.middleware.mybatisplus.generator;

/**
 *
 * MyBatis自动生成代码
 * @author LiYuan
 * @date 2018/10/19
 **/
public class GeneratorCodeApplicationFastOY {

    public static void main(String[] args) {

        GeneratorConifg.configInit();
        GeneratorConifg.strategy.setTablePrefix("gs_");

        /**
         *
         * 不要直接使用这个文件.复制一份改成自已的文件名后。再使用
         *
         * ************************************************
         * -------------------自已的配置开始----------------
         * ************************************************
         */

        GeneratorConifg.projectPath = "D:\\Users\\oy\\BBC\\gs_trade_rights_refund\\";

        GeneratorConifg.globalConfig.setAuthor("oy");

        //数据为表格
        GeneratorConifg.strategy.setInclude("gs_trade_rights_refund");
        //表格说明
        GeneratorConifg.setConfigIncludeDesc("交易售后退款表");

        // foundation ,merchant,commodity,stock,trade,user
        GeneratorConifg.modelName = "trade";

        //是否开启批量删除
        GeneratorConifg.injectionDeleteBatch(false);

        /** ---------------------建议开发哪个端生成哪个端，文件太多容易晕-------**/
//         GeneratorConifg.addClient("platadmin");
         //GeneratorConifg.addClient("bbb");
         GeneratorConifg.addClient("bbc");
//         GeneratorConifg.addClient("merchadmin");

        /**-------------------自已的配置结束---------------- **/
        GeneratorConifg.updateDefaultConfig();
        GeneratorConifg.createrItemList();
    }
}
