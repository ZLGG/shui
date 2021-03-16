package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author Starry
* @since 2021-01-30
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_user2b_apply_log")
public class UserUser2bApplyLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
     * 企业认证id(主表id)
     */
    private String user2bApplyId;


    /**
     * 用户名字
     */
    private String userName;

    /**
    * 法人类型[10=个人 20=企业]
    */
    private Integer legalType;

    /**
    * 商业类型[10=买家 20=卖家 30=买家卖家]
    */
    private Integer businessType;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 企业类型ID
    */
    private String corpTypeId;

    /**
    * 企业类型名称
    */
    private String corpTypeName;

    /**
    * 公司名称
    */
    private String corpName;

    /**
    * 省代码
    */
    private String corpProvince;

    /**
    * 市代码
    */
    private String corpCity;

    /**
    * 县代码
    */
    private String corpCounty;

    /**
    * 公司所在地址（省）
    */
    private String corpProvinceAddress;

    /**
    * 公司所在地址（市）
    */
    private String corpCityAddress;

    /**
    * 公司所在地址（县区）
    */
    private String corpCountyAddress;

    /**
    * 公司详细地址
    */
    private String corpRealAddress;

    /**
    * 公司电话
    */
    private String corpTelephone;

    /**
    * 企业邮箱
    */
    private String corpEmail;

    /**
    * 公司联系人
    */
    private String corpPersal;

    /**
    * 公司联系人手机号
    */
    private String corpPhone;

    /**
    * 法人姓名
    */
    private String personName;

    /**
    * 统一社会信用代码
    */
    private String unifiedSocialCreditCode;

    /**
    * 审核状态[10=待审 20=通过 30=拒审]
    */
    private Integer state;

    /**
    * 拒审原因
    */
    private String revokeWhy;

    /**
    * 拒审时间
    */
    private Date reovkeTime;

    /**
    * 审核通过时间
    */
    private Date okpassTime;

    /**
    * 店铺ID
    */
    private String fromShopId;

    /**
    * 邀请码
    */
    private String code;

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


}
