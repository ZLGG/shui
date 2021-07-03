package com.gs.lshly.biz.support.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.gs.lshly.common.utils.AESUtil;

import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员
 * </p>
 *
 * @author xxfc
 * @since 2020-10-05
 */
@SuppressWarnings("rawtypes")
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String userPwd;

    /**
     * 会员状态[10=可用 20=禁用]
     */
    private Integer state;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 会员类型[10=2b 20=2c]
     */
    private Integer type;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 省/市/县
     */
    private String region;

    /**
     * 性别[10=男  20=女]
     */
    private Integer sex;

    /**
     * 会员积分
     */
    private Integer integral;

    /**
     * 会员经验
     */
    private Integer exp;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 省代码
     */
    private String province;

    /**
     * 市代码
     */
    private String city;

    /**
     * 县代码
     */
    private String county;

    /**
     * 省名称
     */
    private String provinceText;

    /**
     * 市名称
     */
    private String cityText;

    /**
     * 县名称
     */
    private String countyText;

    /**
     * 详细地址
     */
    private String realAddress;


    /**
     * 法人单位ID
     */
    private String legalId;

    /**
     * 会员等级ID
     */
    private String leveId;

    /**
     * 会员等级名称
     */
    private String leveName;

    /**
     * 注册来源店铺ID
     */
    private String fromShopId;

    /**
     * 支付密码
     */
    private String payPwd;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

    /**
     * 用户类型(1-普通用户 2-电信用户)
     */
    private Integer memberType;

    /**
     * 是否为in会员(1-是 0-否)
     */
    private Integer isInUser;

    /**
     * 电信星级
     */
    private String telecomsLevel;

    /**
     * 电信积分
     */
    private Integer telecomsIntegral;

    /**
     * 年底过期积分（电信）
     */
    private Integer telecomsPass;

    /**
     * 定向积分
     */
    private Integer directionIntegral;
    
    /**
     * IN会员结束时间
     */
    private LocalDate inUserEndDate;

	/**
	 * 最近登录时间
	 */
	private LocalDateTime loginDate;
	
	public LocalDateTime getLoginDate() {
		return loginDate;
	}

	public String getId() {
		return id;
	}

	public String getUserName() {
		if(StringUtils.isNotEmpty(userName)&&!userName.startsWith("1"))
			userName = AESUtil.aesDecrypt(userName);
		return userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public Integer getState() {
		return state;
	}

	public String getNick() {
		return nick;
	}

	public String getHeadImg() {
		return headImg;
	}

	public Integer getType() {
		return type;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		if(StringUtils.isNotEmpty(phone)&&!phone.startsWith("1"))
			phone = AESUtil.aesDecrypt(phone);
		return phone;
	}

	public String getAESPhone() {
		return phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getRegion() {
		return region;
	}

	public Integer getSex() {
		return sex;
	}

	public Integer getIntegral() {
		return integral;
	}

	public Integer getExp() {
		return exp;
	}

	public String getRealName() {
		if(StringUtils.isNotEmpty(realName))
			realName = AESUtil.aesDecrypt(realName);
		return realName;
	}

	public String getProvince() {
		return province;
	}

	public String getCity() {
		return city;
	}

	public String getCounty() {
		return county;
	}

	public String getProvinceText() {
		return provinceText;
	}

	public String getCityText() {
		return cityText;
	}

	public String getCountyText() {
		return countyText;
	}

	public String getRealAddress() {
		return realAddress;
	}

	public String getLegalId() {
		return legalId;
	}

	public String getLeveId() {
		return leveId;
	}

	public String getLeveName() {
		return leveName;
	}

	public String getFromShopId() {
		return fromShopId;
	}

	public String getPayPwd() {
		return payPwd;
	}

	public LocalDateTime getCdate() {
		return cdate;
	}

	public LocalDateTime getUdate() {
		return udate;
	}

	public Boolean getFlag() {
		return flag;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public Integer getIsInUser() {
		return isInUser;
	}

	public String getTelecomsLevel() {
		return telecomsLevel;
	}

	public Integer getTelecomsIntegral() {
		return telecomsIntegral;
	}

	public Integer getTelecomsPass() {
		return telecomsPass;
	}

	public Integer getDirectionIntegral() {
		return directionIntegral;
	}

	public LocalDate getInUserEndDate() {
		return inUserEndDate;
	}
    
}
