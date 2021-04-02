package com.gs.lshly.biz.support.commodity.rpc.bbb.pc;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.service.bbb.IPCBbbGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsInfoService;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.commodityfupin.vo.PCBbbGoodsFupinVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class PCBbbGoodsInfoRpc implements IPCBbbGoodsInfoRpc {
    @Autowired
    private IPCBbbGoodsInfoService goodsInfoService;
    @Autowired
    private IPCBbbGoodsFupinService fupinService;


    @Override
    public PCBbbGoodsInfoVO.GoodsRecommendVO getRecommendGoodsList(PCBbbGoodsInfoQTO.QTO qto) {
        PCBbbGoodsInfoVO.GoodsRecommendVO goodsRecommendVO = goodsInfoService.getRecommendGoodsList(qto);
        //如果是推荐扶贫商品显示推荐的扶贫商品
        if (ObjectUtils.isNotEmpty(qto.getRecommendState()) && qto.getRecommendState().equals(TrueFalseEnum.是.getCode())){
            PCBbbGoodsFupinVO.GoodsIdVO goodsIdVO = fupinService.getGoodsIdVO(qto);
            if (ObjectUtils.isEmpty(goodsIdVO) || ObjectUtils.isEmpty(goodsIdVO.getGoodsId())){
                return null;
            }
            List<String> idList = goodsIdVO.getGoodsId();
            for (int i = goodsRecommendVO.getGoodsListVOS().size()-1; i>=0 ; i--){
                if (!idList.contains(goodsRecommendVO.getGoodsListVOS().get(i).getGoodsId())){
                    goodsRecommendVO.getGoodsListVOS().remove(goodsRecommendVO.getGoodsListVOS().get(i));
                }
            }
        }
        return goodsRecommendVO;
    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageGoodsDetailVO(PCBbbGoodsInfoQTO.GoodsSearchQTO qto) {
        return goodsInfoService.pageGoodsDetailVO(qto);
    }

    @Override
    public PCBbbGoodsInfoVO.GoodsDetailVO getGoodsDetailVO(PCBbbGoodsInfoDTO.IdDTO dto) {
        return goodsInfoService.getGoodsDetailVO(dto);
    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsListVO> getSearchGoods(PCBbbGoodsInfoQTO.SearchByGoodsNameQTO qto) {
        return goodsInfoService.getSearchGoods(qto);
    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageShopNavigationGoodsVO(PCBbbGoodsInfoQTO.ShopNavigationIdQTO qto) {
        return goodsInfoService.pageShopNavigationGoodsVO(qto);
    }

    //----------------内部服务---------------

    @Override
    public List<PCBbbGoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList,BaseDTO dto) {
        return goodsInfoService.innerServiceVOByIdList(skuIdList,dto);
    }

    @Override
    public PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO(String skuId,BaseDTO dto) {
        return goodsInfoService.innerServiceVO(skuId,dto);
    }

    @Override
    public PCBbbGoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId) {
        return goodsInfoService.innerSimpleServiceVO(skuId);
    }

    @Override
    public List<PCBbbGoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList,BaseDTO dto) {
        return goodsInfoService.getHomeGoodsInnerServiceVO(goodsIdList,dto);
    }

    @Override
    public List<PCBbbGoodsInfoVO.ShopInnerServiceVO> getShopGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto) {
        return goodsInfoService.getShopGoodsInnerServiceVO(goodsIdList,dto);
    }

    @Override
    public PCBbbGoodsInfoVO.GetGoodsStepPriceVO innerGetStepPrice(String skuId) {
        return goodsInfoService.innerGetStepPrice(skuId);
    }

}