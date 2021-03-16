package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchSettingsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchSettingsVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-13
*/
public interface IPCMerchShopRpc {

    List<PCMerchShopVO.ListVO> list(PCMerchShopQTO.QTO qto);

    void editShop(PCMerchShopDTO.ETO eto);

    PCMerchShopVO.DetailVO detailShop(BaseDTO dto);

    PCMerchSettingsVO.DeliveryStyleVO getDeliveryStyle(BaseDTO baseDTO);

    void setDeliveryStyle(PCMerchSettingsDTO.DeliveryStyleDTO dto);

    /**查店铺状态 店铺状态[10=开通 20=关闭]**/
    Integer innerShopState(String shopId);

    PCMerchShopVO.ShopSimpleVO innerShopSimple(String shopId);
}