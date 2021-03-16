package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopChooseCouponDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopChooseCouponQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopChooseCouponVO;

public interface IPCMerchShopChooseCouponService {

    PageData<PCMerchShopChooseCouponVO.ListVO> pageData(PCMerchShopChooseCouponQTO.QTO qto);

    void addShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto);

    void deleteShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto);


    void editShopChooseCoupon(PCMerchShopChooseCouponDTO.ETO eto);

    PCMerchShopChooseCouponVO.DetailVO detailShopChooseCoupon(PCMerchShopChooseCouponDTO.IdDTO dto);

}