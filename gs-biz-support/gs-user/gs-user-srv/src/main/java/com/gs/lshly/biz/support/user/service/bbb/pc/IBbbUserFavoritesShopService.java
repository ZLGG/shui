package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;

import java.util.List;

public interface IBbbUserFavoritesShopService {

    PageData<BbbUserFavoritesShopVO.ListVO> pageData(BbbUserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbbUserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbbUserFavoritesShopDTO.IdListDTO dto);

    Integer innerFavoritesState(BaseDTO dto, String shopId, String userId);

    List<String> innerFavoritesListState(BaseDTO dto, List<String> shopIdList, String userId);

}