package com.gs.lshly.biz.support.user.rpc.bbc;

import com.gs.lshly.biz.support.user.service.bbc.IBbcUserFavoritesShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbcUserFavoritesShopRpc implements IBbcUserFavoritesShopRpc {

    @Autowired
    private IBbcUserFavoritesShopService bbcUserFavoritesShopService;


    @Override
    public PageData<BbcUserFavoritesShopVO.ListVO> pageData(BbcUserFavoritesShopQTO.QTO qto) {
        return bbcUserFavoritesShopService.pageData(qto);
    }

    @Override
    public void addUserFavoritesShop(BbcUserFavoritesShopDTO.ETO eto){
        bbcUserFavoritesShopService.addUserFavoritesShop(eto);
    }

    @Override
    public void deleteBatchUserFavoritesShop(BbcUserFavoritesShopDTO.IdListDTO dto){
        bbcUserFavoritesShopService.deleteBatchUserFavoritesShop(dto);
    }

    @Override
    public Integer innerFavoritesState(BaseDTO dto, String shopId,String userId) {

        return bbcUserFavoritesShopService.innerFavoritesState(dto,shopId, userId);
    }

}