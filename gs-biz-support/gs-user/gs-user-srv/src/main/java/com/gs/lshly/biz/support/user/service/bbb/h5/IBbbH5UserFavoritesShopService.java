package com.gs.lshly.biz.support.user.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesShopVO;

public interface IBbbH5UserFavoritesShopService {

    PageData<BbbH5UserFavoritesShopVO.ListVO> pageData(BbbH5UserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbbH5UserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbbH5UserFavoritesShopDTO.IdListDTO dto);

    Integer innerFavoritesState(BaseDTO dto, String shopId, String userId);
}