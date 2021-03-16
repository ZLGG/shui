package com.gs.lshly.common.struct.merchadmin.pc.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-20
*/
public abstract class PCMerchUserQTO implements Serializable {

    @Data
    @ApiModel("PCMerchUserQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询条件类型[10=用户名 20=手机号 30=真实姓名 40=标签 50=邮箱]")
        private Integer conditionType;

        @ApiModelProperty("查询条件值")
        private String conditionValue;

    }

    @Data
    @ApiModel("PCMerchUserQTO.ApplyListQTO")
    @Accessors(chain = true)
    public static class ApplyListQTO extends BaseQTO {

        @ApiModelProperty("审核状态[10=待审 20=通过 30=驳回 40= 全部]]")
        private Integer applyState;


    }
}
