package com.gs.lshly.biz.support.commodity.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.InMemberGoodsQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;

import java.util.List;


public interface IBbcGoodsInfoService {


    /**
     * 查询2C商城商品列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsListVO(BbcGoodsInfoQTO.GoodsListByCategoryQTO qto);

    /**
     * 查询2C商家商品列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> pageMerchantGoods(BbcGoodsInfoQTO.MerchantGoodsQTO qto);

    /**
     * 查询2C商城商品详情
     * @param dto
     * @return
     */
    BbcGoodsInfoVO.DetailVO detailGoodsInfo(BbcGoodsInfoDTO.IdDTO dto);


    /**
     * 搜索2C商城首页商品信息
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsData(BbcGoodsInfoQTO.GoodsListQTO qto);


    /**
     * 根据商品所选规格信息获取sku信息
     * @param qto
     * @return
     */
    BbcSkuGoodInfoVO.SkuVO getSkuVO(BbcGoodsInfoQTO.GoodsSkuQTO qto);


    /**
     * 获取推荐商品信息列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbcGoodsInfoQTO.OrderGoodsListQTO qto);


    /**
     * 返回加密解析数据
     * @param qto
     * @return
     */
    BbcGoodsInfoVO.GoodsSharingVO getGoodsSharingVO(BbcGoodsInfoQTO.GoodsSharingQTO qto);




    //--------------------------内部服务--------------------

    /**
     * 提供通过skuId列表查询商品相关数据服务
     * @param qto
     * @return
     */
    List<BbcGoodsInfoVO.InnerServiceVO> innerServicePageShopGoods(BbcGoodsInfoQTO.SkuIdListQTO qto);


    /**
     * 提供通过商品id列表查询这些店铺下所有商品信息服务
     * @param qto
     * @return
     */
    List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getGoodsInnerServiceVO(BbcGoodsInfoQTO.GoodsIdListQTO qto);


    List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerSimpleServiceVO(List<String> goodsIds);

    BbcGoodsInfoVO.InnerServiceVO innerSimpleServiceGoodsVO(String skuID);
    
    /**
     * 获取IN会员专区商品
     * @param qto
     * @return
     */
    BbcGoodsInfoVO.InMemberGoodsVO pageInMemberGoods(InMemberGoodsQTO qto);

    /**
     * 获取in会员专区商品列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(BbcGoodsInfoQTO.InSpecialAreaGoodsQTO qto);
}