package com.gs.lshly.rpc.api.bbb.pc.user;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbbUserFavoritesShopRpc {

    PageData<BbbUserFavoritesShopVO.ListVO> pageData(BbbUserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbbUserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbbUserFavoritesShopDTO.IdListDTO dto);

    /**
     * 店铺收藏状态
     * @param dto
     * @param shopId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(BaseDTO dto, String shopId, String userId);

    /**
     * 一组店铺收藏状态
     * @param dto
     * @param shopIdList
     * @param userId
     * @return
     */
    List<String> innerFavoritesListState(BaseDTO dto, List<String> shopIdList, String userId);

}