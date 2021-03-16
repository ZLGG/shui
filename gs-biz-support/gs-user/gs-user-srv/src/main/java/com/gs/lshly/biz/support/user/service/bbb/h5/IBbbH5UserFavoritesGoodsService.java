package com.gs.lshly.biz.support.user.service.bbb.h5;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesGoodsVO;

public interface IBbbH5UserFavoritesGoodsService {

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData(BbbH5UserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.IdListDTO dto);


    Integer innerFavoritesState(String goodsId, String userId);

}