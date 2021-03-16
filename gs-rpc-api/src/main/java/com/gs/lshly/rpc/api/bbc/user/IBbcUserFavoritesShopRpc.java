package com.gs.lshly.rpc.api.bbc.user;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbcUserFavoritesShopRpc {

    PageData<BbcUserFavoritesShopVO.ListVO> pageData(BbcUserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbcUserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbcUserFavoritesShopDTO.IdListDTO dto);



    /**
     * 店铺收藏状态
     * @param dto
     * @param shopId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(BaseDTO dto,String shopId,String userId);

}