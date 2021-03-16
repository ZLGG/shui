package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopSignboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopSignboardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopSignboardVO;

public interface IPCMerchShopSignboardService {

    /**
     * 查询店铺招牌配置
     * @param qto
     * @return
     */
   PCMerchShopSignboardVO.DetailVO detailSignboard(PCMerchShopSignboardQTO.QTO qto);

    /**
     * 编辑店铺招牌配置
     * @param eto
     */
    void editShopSignboard(PCMerchShopSignboardDTO.ETO eto);

}