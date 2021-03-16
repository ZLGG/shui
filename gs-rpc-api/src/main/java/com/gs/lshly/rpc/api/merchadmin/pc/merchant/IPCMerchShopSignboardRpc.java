package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopSignboardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopSignboardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopSignboardVO;

/**
*
* @author Starry
* @since 2020-10-30
*/
public interface IPCMerchShopSignboardRpc {

    /**
     * 查询店铺招牌配置 和 店铺店招通栏广告图
     * @param qto
     * @return
     */
    PCMerchShopSignboardVO.DetailVO detailSignboard(PCMerchShopSignboardQTO.QTO qto);

    void editShopSignboard(PCMerchShopSignboardDTO.ETO eto);
}