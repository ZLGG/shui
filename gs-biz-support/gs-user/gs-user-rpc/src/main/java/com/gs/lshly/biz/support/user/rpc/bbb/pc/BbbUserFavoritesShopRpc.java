package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserFavoritesShopService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesShopRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-24
*/
@DubboService
public class BbbUserFavoritesShopRpc implements IBbbUserFavoritesShopRpc {

    @Autowired
    private IBbbUserFavoritesShopService bbbUserFavoritesShopService;


    @Override
    public PageData<BbbUserFavoritesShopVO.ListVO> pageData(BbbUserFavoritesShopQTO.QTO qto) {
        return bbbUserFavoritesShopService.pageData(qto);
    }

    @Override
    public void addUserFavoritesShop(BbbUserFavoritesShopDTO.ETO eto){
        bbbUserFavoritesShopService.addUserFavoritesShop(eto);
    }

    @Override
    public void deleteBatchUserFavoritesShop(BbbUserFavoritesShopDTO.IdListDTO dto){
        bbbUserFavoritesShopService.deleteBatchUserFavoritesShop(dto);
    }

    @Override
    public Integer innerFavoritesState(BaseDTO dto, String shopId,String userId) {

        return bbbUserFavoritesShopService.innerFavoritesState(dto,shopId, userId);
    }

    @Override
    public List<String> innerFavoritesListState(BaseDTO dto, List<String> shopIdList, String userId) {
        return bbbUserFavoritesShopService.innerFavoritesListState(dto,shopIdList,userId);
    }

}