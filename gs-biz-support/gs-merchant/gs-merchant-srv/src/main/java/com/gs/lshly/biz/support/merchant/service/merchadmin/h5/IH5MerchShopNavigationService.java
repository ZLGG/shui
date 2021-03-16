package com.gs.lshly.biz.support.merchant.service.merchadmin.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopNavigationVO;

import java.util.List;

public interface IH5MerchShopNavigationService {

    List<H5MerchShopNavigationVO.ListVO> list(H5MerchShopNavigationQTO.QTO qto);

    List<H5MerchShopNavigationVO.NavigationVO> listLevel001(BaseDTO dto);

    H5MerchMerchantAccountVO.checkShopByShopId checkShopByShopId(H5MerchShopQTO.CutShopQTO qto);

    H5MerchMerchantAccountVO.CheckShopVO checkShop(BaseDTO dto);
}