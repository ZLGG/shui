package com.gs.lshly.biz.support.user.mapper.view;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserPrivateUserView {

    /**
     * id
     */
    private String id;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 会员用户名
     */
    private String userName;


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
     * 法人单位ID
     */
    private String legalId;

    /**
     * 店铺私域会员类型ID
     */
    private String userTypeId;

    /**
     * 审核状态
     */
    private Integer state;

    /**
     * 关联解除状态
     */
    private Integer bindState;

    /**
     * 最后修改时间
     */
    private LocalDateTime cdate;

    /**
     * 最后修改时间
     */
    private LocalDateTime udate;

    private Integer sex;

    private String shopId;

    private String rejectWhy;


}
