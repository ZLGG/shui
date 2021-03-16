package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopTextNavigationLinkVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopTextNavigationLinkDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("文本导航name")
        private String textNavigationName;

        @ApiModelProperty("文本导航链接")
        private String textNavigationLink;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

    }

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkDTO.EditMenuETO")
    public static class EditMenuETO extends BaseDTO {

        @ApiModelProperty(value = "店铺分类列表")
        List<PCMerchShopNavigationVO.MenuListVO> shopNavigations;

        @ApiModelProperty(value = "自定义导航分类列表")
        List<PCMerchShopTextNavigationLinkVO.ListVO> listVOS;
    }

    @Data
    @ApiModel("PCMerchShopTextNavigationLinkDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
