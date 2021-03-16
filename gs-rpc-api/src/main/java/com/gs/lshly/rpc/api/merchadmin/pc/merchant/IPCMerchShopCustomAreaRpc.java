package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopCustomAreaDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopCustomAreaQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopCustomAreaVO;

/**
*
* @author Starry
* @since 2020-10-30
*/
public interface IPCMerchShopCustomAreaRpc {

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