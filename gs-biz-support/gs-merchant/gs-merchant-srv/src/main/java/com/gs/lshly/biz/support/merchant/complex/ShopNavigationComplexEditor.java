package com.gs.lshly.biz.support.merchant.complex;

import com.gs.lshly.biz.support.merchant.entity.ShopNavigation;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import lombok.Data;

@Data
public class ShopNavigationComplexEditor {

    private PCMerchShopNavigationDTO.ItemDTO itemDTO;


    private ShopNavigation shopNavigation;

}
