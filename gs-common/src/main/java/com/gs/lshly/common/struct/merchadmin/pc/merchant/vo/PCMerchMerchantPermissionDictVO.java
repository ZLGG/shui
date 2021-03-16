package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-26
*/
public abstract class PCMerchMerchantPermissionDictVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantPermissionDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("组名称")
        private String groupName;

        @ApiModelProperty("权限数组")
        private List<ItemVO> permissionList;
    }

    @Data
    @ApiModel("PCMerchMerchantPermissionDictVO.ItemVO")
    public static class ItemVO implements  Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("权限名")
        private String permissionName;
    }

}
