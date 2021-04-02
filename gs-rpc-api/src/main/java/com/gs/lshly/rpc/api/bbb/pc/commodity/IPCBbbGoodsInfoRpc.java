package com.gs.lshly.rpc.api.bbb.pc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;

import java.util.List;

/**
 * @Author Starry
 * @Date 18:38 2020/11/25
 */
public interface IPCBbbGoodsInfoRpc {

    /**
     * 获取推荐商品信息
     * @param qto
     * @return
     */
    PCBbbGoodsInfoVO.GoodsRecommendVO getRecommendGoodsList(PCBbbGoodsInfoQTO.QTO qto);


    /**
     * 查询商品列表
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageGoodsDetailVO(PCBbbGoodsInfoQTO.GoodsSearchQTO qto);

    /**
     * 查询商品详情信息
     * @param dto
     * @return
     */
    PCBbbGoodsInfoVO.GoodsDetailVO getGoodsDetailVO(PCBbbGoodsInfoDTO.IdDTO dto);

    /**
     * 获取搜索商品信息
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsInfoVO.GoodsListVO> getSearchGoods(PCBbbGoodsInfoQTO.SearchByGoodsNameQTO qto);


    /**
     * 查询店铺自定义分类下面的商品
     * @param qto
     * @return
     */
    PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageShopNavigationGoodsVO(PCBbbGoodsInfoQTO.ShopNavigationIdQTO qto);


    //----------------内部服务-----------------

    /**
     * 提供通过skuId列表查询商品相关数据服务
     * @param skuIdList
     * @param dto
     * @return
     */
    List<PCBbbGoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList,BaseDTO dto);

    /**
     * 根据skuID查询商品相关数据
     * @param skuId
     * @param dto
     * @return
     */
    PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO(String skuId,BaseDTO dto);
    /**
     * 根据skuID查询商品简单数据
     * @param skuId
     * @param
     * @return     zdf
     */
    PCBbbGoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId);

    /**
     * 提供通过商品id列表查询商品信息(基本的商品信息)服务
     * @param goodsIdList
     * @param dto
     * @return
     */
    List<PCBbbGoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList,BaseDTO dto);


    /**
     * 提供通过商品id列表查询商品信息(复杂的商品信息)服务
     * @param goodsIdList
     * @param dto
     * @return
     */
    List<PCBbbGoodsInfoVO.ShopInnerServiceVO> getShopGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto);

    /**
     * 获取阶梯价
     * @param skuId
     * @return
     */
    PCBbbGoodsInfoVO.GetGoodsStepPriceVO innerGetStepPrice(String skuId);
}
