package com.gs.lshly.biz.support.commodity.mapper.view;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 12:08 2020/12/2
 */
@Data
public class GoodsQaView extends Model {
    private String id;

    private String goodId;

    private String merchantId;

    private String shopId;

    private Integer quizType;

    private String quizContent;

    private String content;

    private String contactWay;

    private Integer isAnonymous;

    private Integer isShowQuizContent;

    private Integer isShowContent;

    private Integer isReply;

    private String ip;

    private String operator;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime cdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime udate;

    private String goodsName;

    private String goodsTitle;

    private String goodsImage;
}
