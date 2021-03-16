package com.gs.lshly.biz.support.user.service.bbb.pc;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesGoodsVO;

import java.util.List;

public interface IBbbUserFavoritesGoodsService {

    PageData<BbbUserFavoritesGoodsVO.ListVO> pageData(BbbUserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbbUserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbbUserFavoritesGoodsDTO.IdListDTO dto);


    Integer innerFavoritesState(String goodsId, String userId);

    List<String> innerFavoritesListState(List<String> goodsIdList, String userId);

}