package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午5:27
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "查询交易请求信息")
public class QueryAccountTradeParamsDTO extends FyBaseDTO.DTO implements Serializable {

    @ApiModelProperty(value = "原交易流水号")
    private String transFlowNo;

    @ApiModelProperty(value = "开始日期: 日期格式：yyyy-MM-dd")
    @Pattern(regexp = "^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$", message = "开始日期格式错误，例：yyyy-MM-dd")
    private String startDate;

    @ApiModelProperty(value = "结束日期: 日期格式：yyyy-MM-dd")
    @Pattern(regexp = "^[1-2][0-9][0-9][0-9]-[0-1]{0,1}[0-9]-[0-3]{0,1}[0-9]$", message = "结束日期格式错误，例：yyyy-MM-dd")
    private String endDate;

    @ApiModelProperty(value = "每页显示数: 1=<pageSize <=100")
    @Pattern(regexp = "^[1-100]*$", message = "每页显示数错误")
    private String pageSize;

    @ApiModelProperty(value = "当前页")
    private String pageNo;

    @ApiModelProperty(value = "交易类型")
    private String tradeType;

    @ApiModelProperty(value = "支付类型:01-II、III类账户消费商户; 02-撤销消费商户; 03-消费退货商户; 04-领取红包商户; 05-增加账户绑定卡HW商户; 06-解除账户绑定卡HW商户; 07-充值商户; 08-设置非绑定账户入金标识商户; 09-开立银行账户OP商户; 10-上传客户影像资料商户; 11-修改客户影像资料商户; 12-升级账户HW商户; 13-生成二维码付款码; 14-扫描二维码消费;")
    private String payType;
}
