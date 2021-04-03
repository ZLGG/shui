package com.gs.lshly.common.enums;

/**
 * 性别[10=男  20=女 30=保密]
 *
 * 
 * @author yingjun
 * @date 2021年3月17日 下午11:29:29
 */
public enum UserSexEnum implements EnumMessage {

    男(10, "男"),
    女(20, "女"),
    保密(30, "保密"),
    ;

    UserSexEnum(Integer code, String remark){
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
	public static UserSexEnum getEnum(Integer code) {
		if (code != null) {
			for (UserSexEnum yesnoEnum : UserSexEnum.values()) {
				if (yesnoEnum.code.equals(code)) {
					return yesnoEnum;
				}
			}
			return UserSexEnum.保密;
		} else {
			return UserSexEnum.保密;
		}

	}
    
}
