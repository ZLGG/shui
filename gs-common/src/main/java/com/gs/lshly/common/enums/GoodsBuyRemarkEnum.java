package com.gs.lshly.common.enums;

/**
 * 商品不能购买的原因
 *
 * 
 * @author yingjun
 * @date 2021年5月27日 下午6:39:21
 */
public enum GoodsBuyRemarkEnum implements EnumMessage{
    
	已下架(10, "已下架"),
    待审核(30, "待审核"),
    审核失败(40, "审核失败"),
    库存不足(50, "库存不足");


    GoodsBuyRemarkEnum(Integer code, String remark){
        this.code = code;
        this.remark = remark;
    }

    private Integer code;

    private String remark;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getRemark() {
        return remark;
    }
    
	public static String getRemark(Integer code) {
		if (code != null) {
			for (GoodsBuyRemarkEnum goodsBuyRemarkEnum : GoodsBuyRemarkEnum.values()) {
				if (goodsBuyRemarkEnum.code.equals(code)) {
					return goodsBuyRemarkEnum.getRemark();
				}
			}
			return GoodsBuyRemarkEnum.已下架.getRemark();
		} else {
			return GoodsBuyRemarkEnum.已下架.getRemark();
		}

	}
}
