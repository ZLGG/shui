package com.gs.lshly.biz.support.user.rpc.bbb.pc;

import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserFavoritesGoodsService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesGoodsVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesGoodsRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbbUserFavoritesGoodsRpc implements IBbbUserFavoritesGoodsRpc {

    @Autowired
    private IBbbUserFavoritesGoodsService bbbUserFavoritesGoodsService;

    @Override
    public PageData<BbbUserFavoritesGoodsVO.ListVO> pageData(BbbUserFavoritesGoodsQTO.QTO qto) {

        return bbbUserFavoritesGoodsService.pageData(qto);
    }

    @Override
    public void addUserFavoritesGoods(BbbUserFavoritesGoodsDTO.ETO eto){
        bbbUserFavoritesGoodsService.addUserFavoritesGoods(eto);
    }

    @Override
    public void deleteBatchUserFavoritesGoods(BbbUserFavoritesGoodsDTO.IdListDTO dto){
        bbbUserFavoritesGoodsService.deleteBatchUserFavoritesGoods(dto);
    }

    @Override
    public Integer innerFavoritesState(String goodsId, String userId) {
        return bbbUserFavoritesGoodsService.innerFavoritesState(goodsId, userId);
    }

    @Override
    public List<String> innerFavoritesListState(List<String> goodsIdList, String userId) {
        return bbbUserFavoritesGoodsService.innerFavoritesListState(goodsIdList,userId);
    }


}