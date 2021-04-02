package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchSettingsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchSettingsVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import java.util.List;

public interface IPCMerchShopService {

    List<PCMerchShopVO.ListVO> list(PCMerchShopQTO.QTO qto);

    void editShop(PCMerchShopDTO.ETO eto);

    PCMerchShopVO.DetailVO detailShop(BaseDTO dto);

    PCMerchSettingsVO.DeliveryStyleVO fetch(BaseDTO dto);

    void set(PCMerchSettingsDTO.DeliveryStyleDTO dto);

    Integer innerShopState(String shopId);

    PCMerchShopVO.ShopSimpleVO innerShopSimple(String shopId);

    Integer innPV(String shopId);

    Integer innUV(String shopId);

    PCMerchShopVO.ShopIdVO innerLikeSimple(String shopId);
}