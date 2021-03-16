package com.gs.lshly.biz.support.user.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;

public interface IBbcUserFavoritesGoodsService {

    PageData<BbcUserFavoritesGoodsVO.ListVO> pageData(BbcUserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbcUserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbcUserFavoritesGoodsDTO.IdListDTO dto);


    Integer innerFavoritesState(String goodsId, String userId);

}