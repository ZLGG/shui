package com.qijin.shui.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zlg
 * @Description
 * @Date 2023/4/18 14:53
 *
 */
@Data
public class UserRequest {

    @ApiModelProperty(value = "id主键", example = "1") // 添加 Swagger 属性描述
    private Long id;

    @ApiModelProperty(value = "用户名", example = "john.doe") // 添加 Swagger 属性描述
    private String username;

    @ApiModelProperty(value = "年龄", example = "30") // 添加 Swagger 属性描述
    private Integer age;

    @ApiModelProperty(value = "性别", example = "男") // 添加 Swagger 属性描述
    private String gender;
}
