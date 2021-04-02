package com.gs.lshly.biz.support.commodity.rpc.bbb.h5;

import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsInfoService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class BbbH5GoodsInfoRpc implements IBbbH5GoodsInfoRpc {
    @Autowired
    private IBbbH5GoodsInfoService bbbH5GoodsInfoService;




    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> pageGoodsListVO(BbbH5GoodsInfoQTO.GoodsListByCategoryQTO qto) {
        return bbbH5GoodsInfoService.pageGoodsListVO(qto);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> pageMerchantGoodsListVO(BbbH5GoodsInfoQTO.MerchantShopGoodsQTO qto) {
        return bbbH5GoodsInfoService.pageMerchantGoodsListVO(qto);
    }


    @Override
    public BbbH5GoodsInfoVO.DetailVO detailGoodsInfo(BbbH5GoodsInfoDTO.IdDTO dto){
        return  bbbH5GoodsInfoService.detailGoodsInfo(dto);
    }


    @Override
    public List<BbbH5GoodsSpecInfoVO.SpecListVO> listSpecVOs(BbbH5GoodsInfoDTO.IdDTO dto) {
        return bbbH5GoodsInfoService.listSpecVOs(dto);
    }

    @Override
    public BbbH5SkuGoodInfoVO.SkuVO getSkuVO(BbbH5GoodsInfoQTO.GoodsSkuQTO qto) {
        return bbbH5GoodsInfoService.getSkuVO(qto);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbbH5GoodsInfoQTO.GoodsListQTO qto) {
        return bbbH5GoodsInfoService.getRecommendGoodsList(qto);
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> getQuickOrderGoodsList(BbbH5GoodsInfoQTO.QuickOrderQTO qto) {
        return bbbH5GoodsInfoService.getQuickOrderGoodsList(qto);
    }

    //-------------------------内部服务---------------------------------

    @Override
    public List<BbbH5GoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList,BaseDTO dto) {
        return bbbH5GoodsInfoService.innerServiceVOByIdList(skuIdList,dto);
    }

    @Override
    public BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO(String skuId,BaseDTO dto) {
        return bbbH5GoodsInfoService.innerServiceVO(skuId,dto);
    }

    @Override
    public BbbH5GoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId) {
        return bbbH5GoodsInfoService.innerSimpleServiceVO(skuId);
    }

    @Override
    public List<BbbH5GoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto) {
        return bbbH5GoodsInfoService.getHomeGoodsInnerServiceVO(goodsIdList,dto);
    }



}