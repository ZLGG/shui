package com.gs.lshly.biz.support.commodity.service.bbc;
import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO.CategoryIdCountDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.InMemberGoodsQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.SpecInfoByGoodsQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsSpecInfoVO.SpecListVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;


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
     * 秒杀活动详情页
     * @param dto
     * @return
     */
    BbcGoodsInfoVO.DetailVO detailSeckillGoodsInfo(BbcGoodsInfoDTO.SeckillIdDTO dto);


    /**
     * 搜索2C商城首页商品信息
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsData(BbcGoodsInfoQTO.GoodsSearchListQTO qto);


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
     * 购物车商品
     * @param qto
     * @return
     */
    List<BbcGoodsInfoVO.InnerServiceVO> innerServiceShopGoods(BbcGoodsInfoQTO.SkuIdListQTO qto);
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
    BbcGoodsInfoVO.InMemberGoodsVO inMemberGoodsHome(InMemberGoodsQTO qto);

    /**
     * 获取in会员抵扣专区商品列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(BbcGoodsInfoQTO.InSpecialAreaGoodsQTO qto);

    /**
     * 获取积分商城商品信息列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.IntegralGoodsInfo> queryIntegralGoodsInfo(BbcGoodsInfoQTO.IntegralGoodsQTO qto);

    /**
     * 我能兑换的积分商品列表
     * @return
     */
    List<BbcGoodsInfoVO.MyIntegrationExchangeVO> myIntegrationExchange();

    /**
     * 查询搜索历史记录
     * @param qto
     * @return
     */
    List<String> getSearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto);

    /**
     * 清空历史搜索记录
     */
    void emptySearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto);
    
    /**
     * 分页查询IN会员商品数据
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.InVipListVO> pageInMemberGoodsInfo(BbcGoodsInfoQTO.InMemberGoodsQTO qto);
    
    /**
     * 获取指定分类下的指定产品数
     * @param dto
     * @return
     */
    List<BbcGoodsInfoVO.SimpleListVO> listGoodsInfoByCategory(CategoryIdCountDTO dto);
    
    /**
     * 获取简单数据内容
     * @param dto
     * @return
     */
    BbcGoodsInfoVO.SimpleListVO simpleListVO(BbcGoodsInfoDTO.IdDTO dto);
    
    /**
     * 获取IN会员首页信息
     * @return
     */
    BbcGoodsInfoVO.InMemberHomeVO inMemberHome();

    /**
     * 获取店铺推荐商品列表
     * @param qto
     * @return
     */
    List<BbcGoodsInfoVO.GoodsListVO> getShopRecommendGoods(BbcGoodsInfoQTO.ShopGoodsIdQTO qto);

    /**
     * 根据商品名称模糊匹配类别id
     * @param goodsName
     * @return
     */
    List<String> getCategoryIdsByName(String goodsName);

    /**
     * 查询通用商品信息
     * @return
     */
    List<BbcGoodsInfoVO.GoodsListVO> getGeneralGoodsInfo();

    /**
     * 根据商品名称模糊匹配商品id
     * @param goodsName
     * @return
     */
    List<String> getGoodsIdsByName(String goodsName);

    /**
     * 跟据商品ID查询SKU
     * @param qto
     * @return
     */
	List<SpecListVO> listSpecInfoByGoods(SpecInfoByGoodsQTO qto);

    /**
     * 是否收藏过商品
     * @param qto
     * @return
     */
    BbcGoodsInfoVO.isCollectionGoodsVO isCollectGoods(BbcGoodsInfoQTO.GoodsIdQTO qto);
    
    /**
     * 跟据商品ID获取优惠券
     * @param qto
     * @return
     */
    List<BbcGoodsInfoVO.ListCouponVO> listCoupon(BbcGoodsInfoQTO.GoodsIdQTO qto);

    /**
     * 获取类目下所有商品列表
     * @param qto
     * @return
     */
    PageData<BbcGoodsInfoVO.GoodsListVO> allFirstCategoryGoods(GoodsInfoQTO.CategoryGoodsQTO qto);

    BbcTradeRightsVO.GoodsInfo selectOne(String tradeGoodsId);
    
    /**
     * 跟据商品ID获取第三方API
     * @param goodId
     * @return
     */
    BbcGoodsInfoVO.GoodsCtccApiVO getCtccApiByGoodId(String goodId);
}