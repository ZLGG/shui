package com.gs.lshly.facade.platform.security;

import com.gs.lshly.middleware.auth.rbac.MenuMessage;

/**
 * 顶部菜单枚举
 * @author lxus
 * @since 2020-12-09
 */
public enum MenuEnum implements MenuMessage {

    站点("site", "站点", 1),
    会员("members", "会员", 2),
    类目("category", "类目", 3),
    交易("transaction", "交易", 4),
    商家("bussiness", "商家", 5),
    商品("commodity", "商品", 6),
    财务("finance", "财务", 7),
    物流("logistics", "物流", 8),
    系统("system", "系统", 9),
    企业("enterprise", "企业", 10),
    营销("marketing", "营销", 11),
    设置("set", "设置", 12),
    报表("report", "报表", 13);

    MenuEnum(String code, String name, Integer index){
        this.code = code;
        this.name = name;
        this.index = index;
    }

    private String code;
    private String name;
    private Integer index;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getIndex() {
        return index;
    }
}
