package com.gs.lshly.biz.support.user.service.bbc;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;

public interface IBbcUserFavoritesShopService {

    PageData<BbcUserFavoritesShopVO.ListVO> pageData(BbcUserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbcUserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbcUserFavoritesShopDTO.IdListDTO dto);

    Integer innerFavoritesState(BaseDTO dto, String shopId,String userId);
}