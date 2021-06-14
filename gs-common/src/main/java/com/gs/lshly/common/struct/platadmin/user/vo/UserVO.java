package com.gs.lshly.common.struct.platadmin.user.vo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.utils.StringManageUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-05
*/
@SuppressWarnings("serial")
public abstract class UserVO implements Serializable {

	
	@Data
    @ApiModel("UserVO.ExportListVO")
    @Accessors(chain = true)
    public static class ExportListVO implements Serializable{

        @ApiModelProperty(value ="id",position = 1)
        private String id;
        @ApiModelProperty(value = "手机号",position = 2)
        private String phone;
        @ApiModelProperty(value="姓名",position = 3)
        private String realName;
        @ApiModelProperty(value="地址",position = 4)
        private String address;
        @ApiModelProperty(value = "性别",position = 5)
        private String sex;
        @ApiModelProperty(value = "年龄",position = 6)
        private Integer age;
        @ApiModelProperty(value = "生日",position = 7)
        private String birthday;
        @ApiModelProperty(value = "积分",position = 8)
        private Integer telecomsIntegral;
        @ApiModelProperty(value = "星级",position = 9)
        private Integer telecomsLevel;
        @ApiModelProperty(value = "IN会员",position = 10)
        private String isInUser;
        @ApiModelProperty(value = "会员标签",position = 11 )
        private String tags;
        @ApiModelProperty(value = "注册IP",position = 12)
        private String regIp;
        @ApiModelProperty(value = "注册时间",position = 13)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
        @ApiModelProperty(value = "最后登录时间",position = 14)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime loginDate;
        
    }
	
    @Setter
    @ApiModel("UserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("会员标签" )
        private List<UserLabelDictVO.UserLabelItemVO> userLabelList;

        @ApiModelProperty(value = "用户名",position = 1)
        private String userName;

        @ApiModelProperty(value = "会员状态[10=可用 20=禁用]",position = 2)
        private Integer state;

        @ApiModelProperty(value = "注册来源店铺ID",position = 3)
        private String fromShopId;

        @ApiModelProperty(value = "注册来源店铺名称",position = 4)
        private String fromShopName;

        @ApiModelProperty(value = "邮箱",position = 5)
        private String email;

        @ApiModelProperty(value = "手机号",position = 6)
        private String phone;

        @ApiModelProperty(value = "生日",position = 7)
        private String birthday;

        @ApiModelProperty(value = "性别[10=男  20=女]",position = 8)
        private Integer sex;

        @ApiModelProperty(value = "注册IP",position = 9)
        private String regIp;

        @ApiModelProperty(value = "注册时间",position = 10)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "会员等级名称",position = 11)
        private String leveName;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer type;

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("电信积分")
        private Integer telecomsIntegral;

        @ApiModelProperty("星级")
        private String telecomsLevel;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;

        @ApiModelProperty("省名称")
        private String provinceText;

        @ApiModelProperty("市名称")
        private String cityText;
        
        @ApiModelProperty("年纪")
        private Integer age;

        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime loginDate;

        public LocalDateTime getLoginDate() {
            return loginDate;
        }

        public Integer getTelecomsIntegral() {
            return telecomsIntegral;
        }

        public String getTelecomsLevel() {
            return telecomsLevel;
        }

        public String getProvinceText() {
            return provinceText;
        }

        public String getCityText() {
            return cityText;
        }

        public Integer getIsInUser() {
            return isInUser;
        }

        public String getId() {
			return id;
		}

		public List<UserLabelDictVO.UserLabelItemVO> getUserLabelList() {
			return userLabelList;
		}

		public String getUserName() {
			return StringManageUtil.hideUserName(userName);
		}

		public Integer getState() {
			return state;
		}

		public String getFromShopId() {
			return fromShopId;
		}

		public String getFromShopName() {
			return fromShopName;
		}

		public String getEmail() {
			return StringManageUtil.hideMail(email);
		}

		public String getPhone() {
			return StringManageUtil.hidePhone(phone);
		}

		public String getBirthday() {
			return birthday;
		}

		public Integer getSex() {
			return sex;
		}

		public String getRegIp() {
			return regIp;
		}

		public LocalDateTime getCdate() {
			return cdate;
		}

		public String getLeveName() {
			return leveName;
		}

		public Integer getType() {
			return type;
		}

		public String getRealName() {
			return StringManageUtil.hideUserName(realName);
		}

		public Integer getAge() {
			if (StringUtils.isNotEmpty(birthday)) {
				age = StringManageUtil.getAge(birthday);
			}
			return age;
		}
		 
        
    }

    @Data
    @ApiModel("UserVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("用户id")
        private String id;

        @ApiModelProperty("用户姓名")
        private String userName;

        @ApiModelProperty("出生日期（日期字符串）")
        private String birthday;

        @ApiModelProperty("注册时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("注册IP")
        private String regIp;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("性别[10=男  20=女]")
        private Integer sex;

        @ApiModelProperty("会员昵称")
        private String nick;

        @ApiModelProperty("等级")
        private String leveName;

        @ApiModelProperty("经验")
        private Integer exp;

        @ApiModelProperty("积分")
        private Integer integral;

        @ApiModelProperty("要过期积分")
        private Integer passIntegral;

        @ApiModelProperty("用户类型(1-普通用户 2-电信用户)")
        private Integer memberType;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;

        @ApiModelProperty("电信星级")
        private String telecomsLevel;

        @ApiModelProperty("电信积分")
        private Integer telecomsIntegral;

        @ApiModelProperty("in会员券码")
        private Integer inCoupon;
    }

    @Data
    @ApiModel("UserVO.MiniVO")
    public static class MiniVO implements Serializable {

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("用户手机号")
        private String phone;
    }


    @Data
    @ApiModel("UserVO.ExportVO")
    public static class ExportVO implements Serializable {

        @ApiModelProperty("用户名")
        private String userName;
    }

}
