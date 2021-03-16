package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopAdvertDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopAdvertQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopAdvertVO;

/**
*
* @author Starry
* @since 2020-10-30
*/
public interface IPCMerchShopAdvertRpc {

    /**
     * 商家广告图信息
     * @param qto
     * @return
     */
    PCMerchShopAdvertVO.DetailVO detailShopAdvert(PCMerchShopAdvertQTO.QTO qto);



    /**
     * 编辑商家广告图
     * @param eto
     */
    void editShopAdvert(PCMerchShopAdvertDTO.ETO eto);


}