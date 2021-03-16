package com.gs.lshly.biz.support.user.rpc.bbb.h5;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserFavoritesShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbbH5UserFavoritesShopRpc implements IBbbH5UserFavoritesShopRpc {

    @Autowired
    private IBbbH5UserFavoritesShopService bbbH5UserFavoritesShopService;


    @Override
    public PageData<BbbH5UserFavoritesShopVO.ListVO> pageData(BbbH5UserFavoritesShopQTO.QTO qto) {
        return bbbH5UserFavoritesShopService.pageData(qto);
    }

    @Override
    public void addUserFavoritesShop(BbbH5UserFavoritesShopDTO.ETO eto){
        bbbH5UserFavoritesShopService.addUserFavoritesShop(eto);
    }

    @Override
    public void deleteBatchUserFavoritesShop(BbbH5UserFavoritesShopDTO.IdListDTO dto){
        bbbH5UserFavoritesShopService.deleteBatchUserFavoritesShop(dto);
    }

    @Override
    public Integer innerFavoritesState(BaseDTO dto, String shopId,String userId) {

        return bbbH5UserFavoritesShopService.innerFavoritesState(dto,shopId, userId);
    }

}