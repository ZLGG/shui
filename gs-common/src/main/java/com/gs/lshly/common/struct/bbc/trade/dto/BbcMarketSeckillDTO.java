package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年4月15日 上午10:51:19
 */
public abstract class BbcMarketSeckillDTO implements Serializable {

    @Data
    @ApiModel(value = "DTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "秒杀ID")
        private String id;

    }




}
