package com.gs.lshly.common.struct.fy.wallet.dto;

import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/22 下午3:54
 */
@Data
@Accessors(chain = true)
public class SearchBankInfoRespDTO extends FyBaseRespDTO.BaseRespDTO implements Serializable {

    @ApiModelProperty(value = "银行信息")
    private List<BankInfoDTO> bankInfo;

    @Data
    @ApiModel("SearchBankInfoRespDTO.BankInfoDTO")
    @Accessors(chain = true)
    public static class BankInfoDTO implements Serializable {

        @ApiModelProperty(value = "银行代码")
        private String bankCode;

        @ApiModelProperty(value = "银行名称")
        private String bankName;

        @ApiModelProperty(value = "单笔最高限额")
        private String singleLimit;

        @ApiModelProperty(value = "日累计最高限额")
        private String daysLimit;
    }


}
