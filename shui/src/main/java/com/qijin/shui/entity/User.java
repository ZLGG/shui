package com.qijin.shui.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zlg
 * @Description
 * @Date 2023/4/18 14:10
 */
@Data
public class User {
    private Long id;

    private String username;

    private Integer age;

    private String gender;
}
