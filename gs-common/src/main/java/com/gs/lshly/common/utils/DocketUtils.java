package com.gs.lshly.common.utils;

import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.constants.SecurityConstants;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

public class DocketUtils {

    private static String PlatAdminTerminal = "平台运营管理";
    private static String MerchantAdminTerminal = "商家运营管理";
    private static String BBBTerminal = "2B商城";
    private static String BBCTerminal = "2C商城";


    public static Docket createPlatAdmin(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, null, "");
    }

    private static Docket createPlatAdmin(String swaggerTitle, String groupName, String module){
        return createAPI(swaggerTitle, "", groupName, PlatAdminTerminal, module);
    }

    public static Docket createPlatAdminFoundation(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "基础设置", "foundation");
    }
    public static Docket createPlatAdminCommodity(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "商品管理", "commodity");
    }
    public static Docket createPlatAdminMerchant(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "商家管理", "merchant");
    }
    public static Docket createPlatAdminStock(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "仓储物流管理", "stock");
    }
    public static Docket createPlatAdminUser(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "会员管理", "user");
    }
    public static Docket createPlatAdminTrade(String swaggerTitle){
        return createPlatAdmin(swaggerTitle, "订单-交易管理", "trade");
    }

    private static Docket createMerchantAdmin(String swaggerTitle, String groupName, String module){
        return createAPI(swaggerTitle, "", groupName,MerchantAdminTerminal, module);
    }

    public static Docket createMerchantAdmin(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, null, "");
    }
    public static Docket createMerchantAdminPCFoundation(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-基础设置", "pc.foundation");
    }
    public static Docket createMerchantAdminPCMerchant(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-店铺管理", "pc.merchant");
    }
    public static Docket createMerchantAdminPCCommodity(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-商品管理", "pc.commodity");
    }
    public static Docket createMerchantAdminPCStock(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-仓储物流管理", "pc.stock");
    }
    public static Docket createMerchantAdminPCUser(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-会员管理", "pc.user");
    }
    public static Docket createMerchantAdminPCTrade(String swaggerTitle){
        return createMerchantAdmin(swaggerTitle, "PC-交易管理", "pc.trade");
    }

    public static Docket createMerchantAdminH5(String swaggerTitle) {
        return createMerchantAdmin(swaggerTitle, "H5", "h5");
    }

    public static Docket create2BPCApi(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "PC端", BBBTerminal, "pc");
    }

    public static Docket create2BH5Api(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "H5端", BBBTerminal, "h5");
    }

    public static Docket create2CPCApi(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "PC端", BBCTerminal, "pc");
    }

    public static Docket create2CH5Api(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "H5端", BBCTerminal, "h5");
    }

    public static Docket create2CFYApi(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "测试", BBCTerminal, "test");
    }
    
    public static Docket createCtccApi(String swaggerTitle) {
        return createAPI(swaggerTitle, "", "电信接口", BBCTerminal, "ctcc");
    }

    private static Docket createAPI(String swaggerTitle, String defaultJWTValue, String groupName, String terminal, String module){
        String terminalPackage = "";
        if (PlatAdminTerminal.equals(terminal)) {
            terminalPackage = "platform";
        }
        if (MerchantAdminTerminal.equals(terminal)) {
            terminalPackage = "merchant";
        }
        if (BBBTerminal.equals(terminal)) {
            terminalPackage = "bbb";
        }
        if (BBCTerminal.equals(terminal)) {
            terminalPackage = "bbc";
        }
        if (StrUtil.isNotBlank(module)) {
            module = "controller." + module;
        }

        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name(SecurityConstants.TOKEN_HEADER).description("user token")
                .defaultValue(defaultJWTValue)
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(groupName)
                .apiInfo(DocketUtils.apiInfo(swaggerTitle, terminal))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gs.lshly.facade." + terminalPackage + "." + module))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    public static ApiInfo apiInfo(String swaggerTitle, String terminal) {
        return new ApiInfoBuilder()
                .title(swaggerTitle + " API(" + terminal + ")")
                .version("1.0")
                .description(swaggerTitle + " API文档(" + terminal + ")<br/>" +
                        "认证地址：/auth/login?username=用户名&password=密码&vcId=验证码id&vcode=验证码<br/>")
                .build();
    }

}
