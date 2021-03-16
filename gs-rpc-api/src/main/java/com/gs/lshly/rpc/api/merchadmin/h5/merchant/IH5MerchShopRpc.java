package com.gs.lshly.rpc.api.merchadmin.h5.merchant;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;

import java.util.List;

/**
*
* @author zst
* @since 2021-01-20
*/
public interface IH5MerchShopRpc {

    List<H5MerchShopVO.ListVO> list(H5MerchShopQTO.QTO qto);

    void editShop(H5MerchShopDTO.ETO eto);

    H5MerchShopVO.DetailVO detailShop(BaseDTO dto);

    H5MerchShopVO.ShopSimpleVO innerShopSimple(String shopId);
}