package com.gs.lshly.biz.support.user.rpc.bbb.h5;

import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserFavoritesGoodsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserFavoritesGoodsQTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbbH5UserFavoritesGoodsRpc implements IBbbH5UserFavoritesGoodsRpc {

    @Autowired
    private IBbbH5UserFavoritesGoodsService bbbH5UserFavoritesGoodsService;

    @Override
    public PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO> pageData(BbbH5UserFavoritesGoodsQTO.QTO qto) {

        return bbbH5UserFavoritesGoodsService.pageData(qto);
    }

    @Override
    public void addUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.ETO eto){
        bbbH5UserFavoritesGoodsService.addUserFavoritesGoods(eto);
    }

    @Override
    public void deleteBatchUserFavoritesGoods(BbbH5UserFavoritesGoodsDTO.IdListDTO dto){
        bbbH5UserFavoritesGoodsService.deleteBatchUserFavoritesGoods(dto);
    }

    @Override
    public Integer innerFavoritesState(String goodsId, String userId) {
        return bbbH5UserFavoritesGoodsService.innerFavoritesState(goodsId, userId);
    }


}