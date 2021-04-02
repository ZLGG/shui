package com.gs.lshly.biz.support.merchant.service.merchadmin.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;

import java.util.List;

public interface IH5MerchShopService {

    List<H5MerchShopVO.ListVO> list(H5MerchShopQTO.QTO qto);

    void editShop(H5MerchShopDTO.ETO eto);

    H5MerchShopVO.DetailVO detailShop(H5MerchShopDTO.IdDTO dto);

    H5MerchShopVO.ShopSimpleVO innerShopSimple(String shopId);
}