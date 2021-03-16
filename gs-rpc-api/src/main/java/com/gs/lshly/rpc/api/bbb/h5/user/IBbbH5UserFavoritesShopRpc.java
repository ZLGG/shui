package com.gs.lshly.rpc.api.bbb.h5.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesShopVO;


/**
*
* @author 陈奇
* @since 2020-10-24
*/
public interface IBbbH5UserFavoritesShopRpc {

    PageData<BbbH5UserFavoritesShopVO.ListVO> pageData(BbbH5UserFavoritesShopQTO.QTO qto);

    void addUserFavoritesShop(BbbH5UserFavoritesShopDTO.ETO eto);

    void deleteBatchUserFavoritesShop(BbbH5UserFavoritesShopDTO.IdListDTO dto);



    /**
     * 店铺收藏状态
     * @param dto
     * @param shopId
     * @param userId
     * @return
     */
    Integer innerFavoritesState(BaseDTO dto, String shopId, String userId);

}