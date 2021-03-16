package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class SiteBroadDTO implements Serializable {

    @Data
    @ApiModel("SiteBroadDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "公告文章列表")
        private List<ItemETO> itemList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;
    }

    @Data
    @ApiModel("SiteBroadDTO.ItemETO")
    @Accessors(chain = true)
    public static class ItemETO implements Serializable {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty(value = "是否新增")
        private Integer isNew;

        @ApiModelProperty("文本内容")
        private String text;

        @ApiModelProperty("跳转地址")
        private String linkUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteBroadDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
