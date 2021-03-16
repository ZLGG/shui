package com.gs.lshly.rpc.api.bbb.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesGoodsVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
public interface IBbbUserFavoritesGoodsRpc {

    PageData<BbbUserFavoritesGoodsVO.ListVO> pageData(BbbUserFavoritesGoodsQTO.QTO qto);

    void addUserFavoritesGoods(BbbUserFavoritesGoodsDTO.ETO eto);

    void deleteBatchUserFavoritesGoods(BbbUserFavoritesGoodsDTO.IdListDTO dto);


    /**
     * 商品收藏状态
     * @param goodsId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(String goodsId, String userId);

    /**
     * 一组商品收藏状态,返回已收藏的商品ID
     * @param goodsIdList
     * @param userId
     * @return
     */
    List<String> innerFavoritesListState(List<String> goodsIdList, String userId);

}