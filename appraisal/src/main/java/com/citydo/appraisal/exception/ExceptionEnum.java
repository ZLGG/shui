/*
 * Copyright (c) 2018, dreamkaylee@foxmail.com All Rights Reserved.
 */

package com.citydo.appraisal.exception;


import lombok.Getter;

/**
 * 异常枚举
 *
 * @author vip
 * @date 2018-04-20 15:40
 */
@Getter
public enum ExceptionEnum {

    /**
     * 文件上传
     */
    FILE_NOT_FOUND(400, "文件不存在"),
    FILE_MAX_SIZE(400, "上传文件大小超过限制"),
    FILE_UPLOAD_FAIL(400, "上传文件失败"),

    /**
     * 服务器错误
     */
    SERVER_ERROR(500,"服务器错误"),

    /**
     * 请求方式错误 (POST GET)
     */
    TYPE_ERROR(415, "请求方式错误"),

    /**
     * 参数传递不正确(表单、json)
     */
    PARAM_ERROR(404,"参数传递不正确"),

    /**
     * Token验证错误
     */
    TOKEN_INVALID(401, "Token验证错误"),

    /**
     * 登陆
     */
    NO_LOGIN(1001,"未登陆，请先登陆"),

    /**
     * 权限
     */
    UNAUTHORIZED(403, "对不起，您没有权限访问"),

    DELETE_SCENE_FAILED(1002, "已有租户开通该场景，需先关闭开通记录"),

    UPDATE_USER_INFO_FAILED(1003, "存在已开通场景，需先关闭已开通场景");

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

}
