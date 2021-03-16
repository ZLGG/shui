package com.gs.lshly.biz.support.user.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserFavoritesGoodsDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserFavoritesGoodsQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserFavoritesGoodsVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesGoodsRpc;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserFavoritesGoodsService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-28
*/
@DubboService
public class BbcUserFavoritesGoodsRpc implements IBbcUserFavoritesGoodsRpc{

    @Autowired
    private IBbcUserFavoritesGoodsService  bbcUserFavoritesGoodsService;

    @Override
    public PageData<BbcUserFavoritesGoodsVO.ListVO> pageData(BbcUserFavoritesGoodsQTO.QTO qto) {

        return bbcUserFavoritesGoodsService.pageData(qto);
    }

    @Override
    public void addUserFavoritesGoods(BbcUserFavoritesGoodsDTO.ETO eto){
        bbcUserFavoritesGoodsService.addUserFavoritesGoods(eto);
    }

    @Override
    public void deleteBatchUserFavoritesGoods(BbcUserFavoritesGoodsDTO.IdListDTO dto){
        bbcUserFavoritesGoodsService.deleteBatchUserFavoritesGoods(dto);
    }

    @Override
    public Integer innerFavoritesState(String goodsId, String userId) {
        return bbcUserFavoritesGoodsService.innerFavoritesState(goodsId, userId);
    }


}