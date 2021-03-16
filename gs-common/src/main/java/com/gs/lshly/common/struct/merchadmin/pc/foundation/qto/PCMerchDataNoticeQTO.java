package com.gs.lshly.common.struct.merchadmin.pc.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-11-16
*/
public abstract class PCMerchDataNoticeQTO implements Serializable {

    @Data
    @ApiModel("PCMerchDataNoticeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("通知类型ID")
        private String noticeTypeId;

    }

    @Data
    @ApiModel("PCMerchDataNoticeQTO.NoticeTypeQTO")
    @Accessors(chain = true)
    public static class NoticeTypeQTO extends BaseDTO {


    }
}
