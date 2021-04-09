package com.gs.lshly.rpc.api.bbb.h5.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
public interface IBbbH5GoodsInfoRpc {

    /**
     * 查询2C商城商品列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsInfoVO.GoodsListVO> pageGoodsListVO(BbbH5GoodsInfoQTO.GoodsListByCategoryQTO qto);

    /**
     * 查询2B商城商家商品列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsInfoVO.GoodsListVO> pageMerchantGoodsListVO(BbbH5GoodsInfoQTO.MerchantShopGoodsQTO qto);


    /**
     * 查询2C商城商品详情
     * @param dto
     * @return
     */
    BbbH5GoodsInfoVO.DetailVO detailGoodsInfo(BbbH5GoodsInfoDTO.IdDTO dto);


    /**
     * 根据商品id查询规格列表
     * @param dto
     * @return
     */
    List<BbbH5GoodsSpecInfoVO.SpecListVO> listSpecVOs(BbbH5GoodsInfoDTO.IdDTO dto);


    /**
     * 根据商品所选规格信息获取sku信息
     * @param qto
     * @return
     */
    BbbH5SkuGoodInfoVO.SkuVO getSkuVO(BbbH5GoodsInfoQTO.GoodsSkuQTO qto);


    /**
     * 获取推荐商品信息列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbbH5GoodsInfoQTO.GoodsListQTO qto);

    /**
     * 获取快捷下单商品列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsInfoVO.GoodsListVO> getQuickOrderGoodsList(BbbH5GoodsInfoQTO.QuickOrderQTO qto);



    //--------------------------内部服务--------------------

    /**
     * 提供通过skuId列表查询商品相关数据服务
     * @param skuIdList
     * @param dto
     * @return
     */
    List<BbbH5GoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList,BaseDTO dto);


    /**
     * 根据skuID查询商品相关数据
     * @param skuId
     * @param dto
     * @return
     */
    BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO(String skuId,BaseDTO dto);

    /**
     * 根据skuID查询商品相关数据
     * @param skuId
     * @param
     * @return    zdf
     */
    BbbH5GoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId);

    /**
     * 提供通过商品id列表查询商品信息服务
     * @param goodsIdList
     * @param dto
     * @return
     */
    List<BbbH5GoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto);


    /**
     * 获取in会员专区商品列表
     * @param qto
     * @return
     */
    PageData<BbbH5GoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(BbbH5GoodsInfoQTO.InSpecialAreaGoodsQTO qto);
}