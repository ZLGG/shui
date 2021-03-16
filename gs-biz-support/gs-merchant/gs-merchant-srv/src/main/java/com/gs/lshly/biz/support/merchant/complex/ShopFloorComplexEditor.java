package com.gs.lshly.biz.support.merchant.complex;

import com.gs.lshly.biz.support.merchant.entity.ShopFloor;
import com.gs.lshly.biz.support.merchant.entity.ShopNavigation;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopFloorDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopNavigationDTO;
import lombok.Data;

@Data
public class ShopFloorComplexEditor {

    private PCMerchShopFloorDTO.H5ItemETO itemDTO;


    private ShopFloor shopFloor;

}
