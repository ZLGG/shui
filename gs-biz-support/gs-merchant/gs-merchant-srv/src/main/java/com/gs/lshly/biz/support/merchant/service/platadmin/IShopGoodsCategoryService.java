package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopGoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopGoodsCategoryVO;

import java.util.List;

public interface IShopGoodsCategoryService {

    PageData<ShopGoodsCategoryVO.ListVO> pageData(ShopGoodsCategoryQTO.ShopIdQTO qto);

    List<ShopGoodsCategoryVO.GoodsCategoryOneVO> list(ShopGoodsCategoryQTO.ShopIdQTO qto);

    List<String> listShopGoodsCategoryLv1(ShopGoodsCategoryQTO.ShopIdQTO qto);

    void editShopGoodsCategoryPrice(ShopGoodsCategoryDTO.ListPriceETO eto);

    ShopGoodsCategoryVO.DetailVO detailShopGoodsCategory(ShopGoodsCategoryDTO.IdDTO dto);
}