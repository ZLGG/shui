package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopCustomAreaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopCustomAreaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopCustomAreaVO;

public interface IPCMerchShopCustomAreaService {

    /**
     * 编辑自定义区域内容
     * @param eto
     */
    void editShopCustomArea(PCMerchShopCustomAreaDTO.ETO eto);

    /**
     * 显示自定义区域内容
     * @param qto
     * @return
     */
    PCMerchShopCustomAreaVO.DetailVO detailShopCustomArea(PCMerchShopCustomAreaQTO.QTO qto);

}