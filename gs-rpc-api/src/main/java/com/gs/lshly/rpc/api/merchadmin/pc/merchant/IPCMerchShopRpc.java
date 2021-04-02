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

    Integer innerShopState(String shopId);

    PCMerchShopVO.ShopSimpleVO innerShopSimple(String shopId);

    PCMerchShopVO.ShopIdVO innerLikeSimple(String shopId);

    //店铺访问量
    Integer innPV(String shopId);

    //店铺UV
    Integer innUV(String shopId);
}