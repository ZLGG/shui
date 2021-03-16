package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 活动关联会员表
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fy_activity_user")
public class ActivityUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 用户id
     */
    private String userId;


}
