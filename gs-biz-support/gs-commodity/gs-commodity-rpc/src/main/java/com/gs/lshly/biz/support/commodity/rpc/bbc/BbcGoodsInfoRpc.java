package com.gs.lshly.biz.support.commodity.rpc.bbc;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class BbcGoodsInfoRpc implements IBbcGoodsInfoRpc{
    @Autowired
    private IBbcGoodsInfoService  bbcGoodsInfoService;




    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsListVO(BbcGoodsInfoQTO.GoodsListByCategoryQTO qto) {
        return bbcGoodsInfoService.pageGoodsListVO(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageMerchantGoods(BbcGoodsInfoQTO.MerchantGoodsQTO qto) {
        return bbcGoodsInfoService.pageMerchantGoods(qto);
    }

    @Override
    public BbcGoodsInfoVO.DetailVO detailGoodsInfo(BbcGoodsInfoDTO.IdDTO dto){
        return  bbcGoodsInfoService.detailGoodsInfo(dto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsData(BbcGoodsInfoQTO.GoodsListQTO qto) {
        return bbcGoodsInfoService.pageGoodsData(qto);
    }

    @Override
    public BbcSkuGoodInfoVO.SkuVO getSkuVO(BbcGoodsInfoQTO.GoodsSkuQTO qto) {
        return bbcGoodsInfoService.getSkuVO(qto);
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbcGoodsInfoQTO.OrderGoodsListQTO qto) {
        return bbcGoodsInfoService.getRecommendGoodsList(qto);
    }

    @Override
    public BbcGoodsInfoVO.GoodsSharingVO getGoodsSharingVO(BbcGoodsInfoQTO.GoodsSharingQTO qto) {
        return bbcGoodsInfoService.getGoodsSharingVO(qto);
    }


    //-------------------------内部服务---------------------------------

    @Override
    public List<BbcGoodsInfoVO.InnerServiceVO> innerServicePageShopGoods(BbcGoodsInfoQTO.SkuIdListQTO qto) {
        return bbcGoodsInfoService.innerServicePageShopGoods(qto);
    }

    @Override
    public BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO(String skuID) {

        BbcGoodsInfoQTO.SkuIdListQTO skuIdListQTO = new BbcGoodsInfoQTO.SkuIdListQTO();
        skuIdListQTO.setSkuIdList(new ArrayList<>());
        skuIdListQTO.getSkuIdList().add(skuID);
        List<BbcGoodsInfoVO.InnerServiceVO> innerServiceDataList = bbcGoodsInfoService.innerServicePageShopGoods(skuIdListQTO);

        return ObjectUtils.isNotEmpty(innerServiceDataList) ? innerServiceDataList.get(0) : null;
    }

    @Override
    public BbcGoodsInfoVO.InnerServiceVO innerSimpleServiceGoodsVO(String skuID) {
        return bbcGoodsInfoService.innerSimpleServiceGoodsVO(skuID);
    }


    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerServiceVO(BbcGoodsInfoQTO.GoodsIdListQTO qto) {
        return bbcGoodsInfoService.getGoodsInnerServiceVO(qto);
    }

    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerSimpleServiceVO(List<String> goodsIds) {
        return bbcGoodsInfoService.getInnerSimpleServiceVO(goodsIds);
    }


}