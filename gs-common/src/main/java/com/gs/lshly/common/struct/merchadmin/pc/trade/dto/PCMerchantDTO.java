package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月31日 下午2:02:06
 */
public abstract class PCMerchantDTO implements Serializable {

    @Data
    @ApiModel("PCMerchantDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO {

    }
    
    
    @Data
    @ApiModel("PCMerchantDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {
    	@ApiModelProperty("联系人")
        private String linkMan;
    	@ApiModelProperty("联系手机号码")
        private String linkPhone;
    	@ApiModelProperty("联系地址")
        private String address;
    	@ApiModelProperty("EMAIL")
        private String email;
    }

}
