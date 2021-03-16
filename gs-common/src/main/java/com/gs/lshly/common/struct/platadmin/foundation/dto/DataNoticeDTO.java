package com.gs.lshly.common.struct.platadmin.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.MerchantShopDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataNoticeDTO implements Serializable {

    @Data
    @ApiModel("DataNoticeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "通知ID", hidden = true)
        private String id;

        @ApiModelProperty(value = "通知标题",position = 1)
        private String title;

        @ApiModelProperty(value = "通知内容",position = 2)
        private String content;

        @ApiModelProperty(value = "通知类型ID",position = 3)
        private String noticeTypeId;

        @ApiModelProperty(value = "通知类型名称",position = 4)
        private String noticeTypeName;

        @ApiModelProperty(value = "接收者范围类型[10=全部 20=店铺ID数组]",position = 5)
        private Integer scopeType;

        @ApiModelProperty(value = "商家店铺数组(接受范围=20时有效)",position = 6)
        private List<MerchantShopDTO.ETO> merchantShopList;

    }

    @Data
    @ApiModel("DataNoticeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty("通知ID")
        private String id;
    }


    @Data
    @ApiModel("DataNoticeDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty("通知ID数组")
        private List<String> idList;

    }


}
