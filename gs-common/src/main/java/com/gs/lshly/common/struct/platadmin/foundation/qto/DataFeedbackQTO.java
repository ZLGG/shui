package com.gs.lshly.common.struct.platadmin.foundation.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataFeedbackQTO implements Serializable {

    @Data
    @ApiModel("DataFeedbackQTO.QTO")
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询类型[10=用户名 20=邮箱  30=电话]")
        private Integer queryType;

        @ApiModelProperty("查询内容")
        private String queryValue;

        @ApiModelProperty("处理状态[0=否 1=是 2=全部]")
        private Integer state;

        @ApiModelProperty(value = "反馈参与者[10-商家,20-会员]",hidden = true)
        private Integer operatorType;
    }

}
