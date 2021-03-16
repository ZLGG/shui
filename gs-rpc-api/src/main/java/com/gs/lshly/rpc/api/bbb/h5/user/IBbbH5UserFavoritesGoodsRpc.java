package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesGoodsVO;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbbH5UserFavoritesGoodsRpc {

    PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData(BbbH5UserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.IdListDTO dto);


    /**
     * 商品收藏状态
     * @param goodsId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(String goodsId, String userId);

}