package com.gs.lshly.rpc.api.bbc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbcUserFavoritesGoodsRpc {

    PageData<BbcUserFavoritesGoodsVO.ListVO> pageData(BbcUserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbcUserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbcUserFavoritesGoodsDTO.IdListDTO dto);


    /**
     * 商品收藏状态
     * @param goodsId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(String goodsId, String userId);

}