package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopAdvertDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopAdvertQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopAdvertVO;

public interface IPCMerchShopAdvertService {


    /**
     * 编辑商家广告图
     * @param eto
     */
    void editShopAdvert(PCMerchShopAdvertDTO.ETO eto);

    /**
     * 展示商家广告图信息
     * @param qto
     * @return
     */
    PCMerchShopAdvertVO.DetailVO detailShopAdvert(PCMerchShopAdvertQTO.QTO qto);

}