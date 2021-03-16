package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:30
 */
@Data
@Accessors(chain = true)
public class QueryAccountTradeListParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "二三类户卡号:开户成功时返回")
    private String accountNo;

    @ApiModelProperty(value = "开始时间:yyyymmdd")
    private String beginDate;

    @ApiModelProperty(value = "结束时间:yyyymmdd")
    private String endDate;

    @ApiModelProperty(value = "币种:156-人民币")
    private String currType;

    @ApiModelProperty(value = "操作类型:C-入账，D-出账 为空全部")
    private String transType;

    @ApiModelProperty(value = "总记录数:首次查询送0，翻页时 将上次核心返回的结果 再次送入核心即可")
    private String totalNum;

    @ApiModelProperty(value = "每页显示记录数:最大为10，不填默认为 最大值10")
    private String pageRow;

    @ApiModelProperty(value = "当前页:首次查询时送0，翻页 查询时第1页，页码是 1，第2页，页码是 2，以此类推")
    private String currPage;
}
