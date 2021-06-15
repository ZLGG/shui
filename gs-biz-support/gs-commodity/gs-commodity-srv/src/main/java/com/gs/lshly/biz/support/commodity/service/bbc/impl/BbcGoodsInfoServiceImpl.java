package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAttributeInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.entity.GoodsCtccApi;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsRelationLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsSearchHistory;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsSearchHistoryMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAttributeInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCtccApiRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsLabelRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsRelationLabelRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsCategoryService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsLabelService;
import com.gs.lshly.common.enums.GoodsBuyRemarkEnum;
import com.gs.lshly.common.enums.GoodsCategoryLevelEnum;
import com.gs.lshly.common.enums.GoodsStateEnum;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.enums.InUserCouponPriceEnum;
import com.gs.lshly.common.enums.MallCategoryEnum;
import com.gs.lshly.common.enums.OrderByConditionEnum;
import com.gs.lshly.common.enums.OrderByTypeEnum;
import com.gs.lshly.common.enums.QueryIntegralGoodsEnum;
import com.gs.lshly.common.enums.SingleStateEnum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO.CategoryIdCountDTO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO.IdDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.GoodsIdQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.InMemberGoodsQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO.SkuIdListQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsLabelQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.AttributeVOS;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.GoodsCtccApiVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.InMemberHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.InnerServiceVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.ListCouponVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.PromiseVOS;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO.SimpleListVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.utils.AesCbcUtil;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.StringManageUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsServeRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteTopicRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockAddressRpc;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserCtccPointRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserFavoritesGoodsRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Starry
 * @since 2020-10-23
 */
@Component
@Slf4j
@SuppressWarnings({"unchecked", "null", "unused"})
public class BbcGoodsInfoServiceImpl implements IBbcGoodsInfoService {

    @Autowired
    private IGoodsInfoRepository repository;
    @Autowired
    private IGoodsSpecInfoRepository specInfoRepository;
    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private IBbcGoodsLabelService bbcGoodsLabelService;
    @Autowired
    private IGoodsLabelRepository goodsLabelRepository;
    @Autowired
    private IGoodsRelationLabelRepository relationLabelRepository;
    @Autowired
    private IGoodsCategoryRepository categoryRepository;
    @Autowired
    private GoodsCategoryMapper categoryMapper;
    @Autowired
    private GoodsSearchHistoryMapper searchHistoryMapper;
    @Autowired
    private IGoodsAttributeInfoRepository goodsAttributeInfoRepository;
    @Autowired
    private IBbcGoodsCategoryService bbcGoodsCategoryService;
    @Autowired
    private IGoodsCtccApiRepository goodsCtccApiRepository;
    


    @DubboReference
    private IBbcShopRpc bbcShopRpc;

    @DubboReference
    private ICommonStockRpc commonStock;

    @DubboReference
    private IBbcStockAddressRpc stockAddressRpc;

    @DubboReference
    private IBbcUserFavoritesGoodsRpc favoritesGoodsRpc;

    @DubboReference
    private IBbcUserRpc userRpc;

    @DubboReference
    private IBbcTradeRpc tradeRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @DubboReference
    private IBbcGoodsCategoryRpc goodsCategoryRpc;


    @DubboReference
    private IBbcSiteTopicRpc bbcSiteTopicRpc;

    @DubboReference
    private IBbcUserCtccPointRpc bbcUserCtccPointRpc;

    @DubboReference
    private IBbcSiteAdvertRpc bbcSiteAdvertRpc;

    @DubboReference
    private IBbcMarketActivityRpc bbcMarketActivityRpc;

    @DubboReference
    private IBbcStockRpc bbcStockRpc;

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsListVO(BbcGoodsInfoQTO.GoodsListByCategoryQTO qto) {
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (StringUtils.isNotEmpty(qto.getGoodsName())) {
            boost.like("goods_name", qto.getGoodsName());
        }
        boost.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        boost.eq("goods_state", GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotEmpty(qto.getCategoryLevel())) {
            List<String> categoryList = listCategoryId(qto.getCategoryLevel());
            if (ObjectUtils.isEmpty(categoryList)) {
                return new PageData<>();
            }
            boost.in("category_id", categoryList);
        }
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.升序.getCode().intValue()) {
            boost.orderByAsc("sale_price");
        }
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.降序.getCode().intValue()) {
            boost.orderByDesc("sale_price");
        }
        //如果是积分查询
        else if (qto.getOrderByProperties() != null && (qto.getOrderByProperties().equals(OrderByConditionEnum.兑换积分.getCode()))) {
            boost.eq("is_point_good", true);
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("is_point_good", "id");
                } else {
                    boost.orderByDesc("is_point_good", "id");
                }
            } else {
                boost.orderByAsc("is_point_good", "id");
            }
        }//按照发布时间排序
        else if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.上架时间.getCode())) {
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("publish_time", "id");
                } else {
                    boost.orderByDesc("publish_time", "id");
                }
            } else {
                boost.orderByAsc("publish_time", "id");
            }
        }

        //获取2C商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page, boost);
        List<GoodsInfo> goodsInfos = pageData.getRecords();
        //声明商品数据的储存容器
        List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();

        //按销售或者评价排序
        if (qto.getOrderByProperties() != null && (qto.getOrderByProperties().equals(OrderByConditionEnum.销售.getCode()) ||
                qto.getOrderByProperties().equals(OrderByConditionEnum.评价.getCode()))) {

            goodsListVOS = getGoodsList2(goodsInfos, qto.getOrderByProperties(), qto.getOrderByType());

        } else {
            //按价格排序
            for (GoodsInfo info : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(info, goodsListVO);
                goodsListVO.setGoodsId(info.getId());

                List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS = getSpecInfoVO(info);
                if (ObjectUtils.isNotEmpty(specListVOS)) {
                    goodsListVO.setSpecListVOS(specListVOS);
                }

                if (info.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(info).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(info.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(info.getId()));
                goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(info.getGoodsImage())) ? "" : getImage(info.getGoodsImage()));
                goodsListVOS.add(goodsListVO);
            }
        }
        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageMerchantGoods(BbcGoodsInfoQTO.MerchantGoodsQTO qto) {
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        
        BbcUserVO.DetailVO detailVO = userRpc.getUserInfoNoLogin(qto);
        
        if (StringUtils.isNotEmpty(qto.getGoodsName())) {
            boost.like("gs.goods_name", qto.getGoodsName());
        }
        if (StringUtils.isNotBlank(qto.getShopId())) {
            boost.eq("gs.shop_id", qto.getShopId());
        }
        boost.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        boost.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        if (qto.getIsPointGood() != null && qto.getIsPointGood().equals(TrueFalseEnum.是.getCode())) {
            boost.eq("is_point_good", TrueFalseEnum.是.getCode());
        }
        if (StringUtils.isNotEmpty(qto.getShopNavigationId())) {
            boost.eq("gsn.shop_navigation", qto.getShopNavigationId());
        }
        /**
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.升序.getCode().intValue()) {
            boost.orderByAsc("gs.sale_price");
        }
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.降序.getCode().intValue()) {
            boost.orderByDesc("gs.sale_price");
        }
         **/

        //如果是积分查询
        else if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
        	if(detailVO.getIsInUser().equals(1)){
        		boost.orderByAsc("in_member_point_price");
        	}else{
        		boost.orderByAsc("point_price");
        	}
        }
        if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
        	if(detailVO.getIsInUser().equals(1)){
            	boost.orderByDesc("in_member_point_price");
            }else{
            	boost.orderByDesc("point_price");
            }
        }//按照发布时间排序
        else if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.上架时间.getCode())) {
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("publish_time", "id");
                } else {
                    boost.orderByDesc("publish_time", "id");
                }
            } else {
                boost.orderByAsc("publish_time", "id");
            }
        }
        //获取2C商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = goodsInfoMapper.getGoodsPageInfo(page, boost);
        List<GoodsInfo> goodsInfos = pageData.getRecords();

        //声明商品数据的储存容器
        List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();

        //按销售或者评价排序
        if (qto.getOrderByProperties() != null && (qto.getOrderByProperties().equals(OrderByConditionEnum.销售.getCode()) || qto.getOrderByProperties().equals(OrderByConditionEnum.评价.getCode()))) {
            goodsListVOS = getGoodsList2(goodsInfos, qto.getOrderByProperties(), qto.getOrderByType());
        } else {
            //按价格排序
            for (GoodsInfo info : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(info, goodsListVO);
                goodsListVO.setGoodsId(info.getId());
                if (ObjectUtils.isNotEmpty(getSpecInfoVO(info))) {
                    goodsListVO.setSpecListVOS(getSpecInfoVO(info));
                }
                if (info.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(info).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(info.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(info.getId()));
                goodsListVO.setGoodsImage(StringUtils.isEmpty(getImage(info.getGoodsImage())) ? "" : getImage(info.getGoodsImage()));
                goodsListVOS.add(goodsListVO);
            }
        }
        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

    @Override
    public BbcGoodsInfoVO.DetailVO detailGoodsInfo(BbcGoodsInfoDTO.IdDTO dto) {

        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(goodsInfo)) {
            throw new BusinessException("数据异常");
        }
//        if(!goodsInfo.getGoodsState().equals(20)){
//        	throw new BusinessException("商品未上架");
//        }
        BbcGoodsInfoVO.DetailVO detailVo = new BbcGoodsInfoVO.DetailVO();
        fillUserType(detailVo, dto);

        //判断当前商品参于了哪个活动
        BbcGoodsInfoVO.ActivityVOS activityVO = bbcMarketActivityRpc.getActivityByGoodsId(goodsInfo.getId());

        if (activityVO != null) {//有参于活动
//        	GoodsActivityVO goodsactivityvo = new GoodsActivityVO();
            List<BbcGoodsInfoVO.ActivityVOS> activityVOS = new ArrayList<BbcGoodsInfoVO.ActivityVOS>();
            activityVOS.add(activityVO);
            detailVo.setActivityVOS(activityVOS);
            ;
        }

        BeanUtils.copyProperties(goodsInfo, detailVo);
        detailVo.setGoodsId(goodsInfo.getId());

        BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(goodsInfo).get(0);
        //若是多规格填充默认规格信息
        //获取默认规格商品信息
        detailVo.setSkuVO(skuVO);

        detailVo.setSkuId(skuVO.getSkuId());

        detailVo.setSalePrice(skuVO.getSkuSalePrice());
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
            if (detailVo.getIsBuy().equals(0)) {
                detailVo.setIsBuy(skuVO.getIsBuy());
                detailVo.setBuyRemark(skuVO.getBuyRemark());
            }
        }

        //填充spec规格列表信息
        if (ObjectUtils.isNotEmpty(getSpecInfoVO(goodsInfo))) {
            detailVo.setSpecListVOS(getSpecInfoVO(goodsInfo));
        }

        //获取店铺信息
        BbcGoodsInfoVO.GoodsShopDetailVO shopDetailVO = shopDetailVO(goodsInfo.getShopId());

        detailVo.setGoodsShopDetailVO(shopDetailVO);

        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
            detailVo.setSkuId(skuVO.getSkuId());
            detailVo.setSingleSkuStock(getSkuStockNum(goodsInfo.getShopId(), skuVO.getSkuId()));
        }

        //填充spu月销量
        int spuStockNum = tradeRpc.innerMonthSaleNum(goodsInfo.getId());
        detailVo.setSpuStockNum(spuStockNum);

        //获取商品销售数量
        Integer saleQuantity = tradeRpc.getSaleQuantity(dto.getId());
        detailVo.setSaleQuantity(saleQuantity);
//        //获取积分兑换数量
//        Integer exchangeQuantity = tradeRpc.getSaleQuantity(dto.getId(),GoodsSourceTypeEnum.积分商品.getCode());
//        detailVo.setExchangeQuantity(exchangeQuantity);

        /**获取用户默认收货地址
         BbcStockAddressVO.DetailVO defaultAddresslVO = stockAddressRpc.innerGetDefault(new BaseDTO(), StockAddressTypeEnum.收货.getCode());
         if (defaultAddresslVO != null) {
         detailVo.setUserDefaultAddress((StringUtils.isNotEmpty(defaultAddresslVO.getProvince()) ? defaultAddresslVO.getProvince() : "") + (StringUtils.isNotEmpty(defaultAddresslVO.getCity()) ? defaultAddresslVO.getCity() : "") + (StringUtils.isNotEmpty(defaultAddresslVO.getCounty()) ? defaultAddresslVO.getCity() : ""));
         }
         **/
        //店铺客服信息
        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setJwtShopId(goodsInfo.getShopId());
        CommonShopVO.ShopServiceVO shopServiceVO = commonShopRpc.getShopServiceVO(baseDTO);
        if (ObjectUtils.isNotEmpty(shopServiceVO)) {
            detailVo.setShopServiceVO(shopServiceVO);
        }


        //获取商品收藏状态
        detailVo.setFavoritesState(favoritesGoodsRpc.innerFavoritesState(dto.getId(), dto.getJwtUserId()));

        //查询标签
        detailVo.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(goodsInfo.getId()));

        /**
         * 人工匹配服务承诺
         */
        fillPromiseVOS(detailVo);

        /**
         * 匹配商品属性
         */
        fillAttributeVOS(detailVo);

        if (!(goodsInfo.getGoodsState()).equals(GoodsStateEnum.已上架.getCode())) {
            detailVo.setIsBuy(0);
            detailVo.setBuyRemark(GoodsBuyRemarkEnum.getRemark(goodsInfo.getGoodsState()));
        }

        return detailVo;
    }

    /**
     * 填充当前用户类型信息
     */
    private void fillAttributeVOS(BbcGoodsInfoVO.DetailVO detailVo) {
        QueryWrapper<GoodsAttributeInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("good_id", detailVo.getId());
        List<GoodsAttributeInfo> list = goodsAttributeInfoRepository.list(queryWrapper);
        List<AttributeVOS> attributeVOS = new ArrayList<AttributeVOS>();
        if (CollectionUtils.isNotEmpty(list)) {
            AttributeVOS attributeVO = null;
            for (GoodsAttributeInfo info : list) {
                attributeVO = new AttributeVOS();
                BeanCopyUtils.copyProperties(info, attributeVO);
                attributeVO.setName(info.getAttributeName());
                attributeVO.setValue(info.getAttributeValue());
                attributeVOS.add(attributeVO);
            }
        }
        detailVo.setAttributeVOS(attributeVOS);
    }

    @DubboReference
    private IBbcGoodsServeRpc goodsServeRpc;

    /**
     * 填充当前用户类型信息
     */
    private void fillPromiseVOS(BbcGoodsInfoVO.DetailVO detailVo) {
        List<PromiseVOS> promiseVOS = new ArrayList<PromiseVOS>();
        BbcGoodsServeQTO.GoodsInfoQTO qto = new BbcGoodsServeQTO.GoodsInfoQTO();
        qto.setId(detailVo.getGoodsId());
        List<BbcGoodsServeVO.ListVO> goodsServeList = goodsServeRpc.getGoodsServeDetailByGoodsId(qto);
        if (CollUtil.isNotEmpty(goodsServeList)) {
            for (BbcGoodsServeVO.ListVO listVO : goodsServeList) {
                PromiseVOS promiseVO = new PromiseVOS();
                promiseVO.setId(listVO.getId());
                promiseVO.setName(listVO.getServeName());
                promiseVO.setContant(listVO.getServeContext());
                promiseVO.setImageUrl(listVO.getImageUrl());
                promiseVO.setJumpUrl(listVO.getJumpUrl());
                promiseVOS.add(promiseVO);
            }
            detailVo.setPromiseVOS(promiseVOS);
        }
    }

    /**
     * 填充当前用户类型信息
     */
    private void fillUserType(BbcGoodsInfoVO.DetailVO detailVo, BaseDTO dto) {
        //获取当前登录用户的基本信息
        BbcUserVO.UserTypeVO userTypeVO = userRpc.getUserType(dto);
        detailVo.setUserId(userTypeVO.getId());
        detailVo.setIsInUser(userTypeVO.getIsInUser());
        detailVo.setMemberType(userTypeVO.getMemberType());
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> pageGoodsData(BbcGoodsInfoQTO.GoodsSearchListQTO qto) {
    	
    	BbcUserVO.DetailVO detailVO = userRpc.getUserInfoNoLogin(qto);
    	
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        // 1. 通用查询条件
        if (StringUtils.isNotBlank(qto.getGoodsName())) {
            boost.like("goods_name", qto.getGoodsName());
        }
        boost.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        boost.eq("goods_state", GoodsStateEnum.已上架.getCode());
        // 按点击量排序（默认排序）
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties() == 10) {
            boost.orderByDesc("click_volume");
        }
        // 按价格排序
        if (MallCategoryEnum.电信商城.getCode().equals(qto.getSearchEntry())) {
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
                boost.orderByAsc("sale_price");
            }
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
                boost.orderByDesc("sale_price");
            }
        } else {
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
                //判断当前用户是不是in_user
            	if(detailVO.getIsInUser().equals(1)){
            		boost.orderByAsc("in_member_point_price");
            	}else{
            		boost.orderByAsc("point_price");
            	}
            }
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
                if(detailVO.getIsInUser().equals(1)){
                	boost.orderByDesc("in_member_point_price");
                }else{
                	boost.orderByDesc("point_price");
                }
            }
        }
        // 按上架时间降序排序
        if (null != qto.getOrderByProperties() && qto.getOrderByProperties() == 40) {
            boost.orderByDesc("publish_time", "id");
        }

        // 2.积分商城
        if (MallCategoryEnum.积分商城.getCode().equals(qto.getSearchEntry())) {
            boost.eq("is_point_good", true);
        }

        // 3.积分商城-我能兑换
        if (MallCategoryEnum.我能兑换.getCode().equals(qto.getSearchEntry())) {
//            System.out.println(qto.getUserId());
            if (StringUtils.isBlank(qto.getJwtUserId())) {
                throw new BusinessException("请登录后查询我能兑换的积分商品");
            }
            boost.eq("is_point_good", true);
            /** in会员
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties() == 50) {
                boost.eq("is_in_member_gift", true);
            }**/
            // 按价格排序
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
            	if(detailVO.getIsInUser().equals(1)){
            		boost.orderByAsc("in_member_point_price");
            	}else{
            		boost.orderByAsc("point_price");
            	}
            }
            if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
            	if(detailVO.getIsInUser().equals(1)){
                	boost.orderByDesc("in_member_point_price");
                }else{
                	boost.orderByDesc("point_price");
                }
            }
        }

        // 4.获取2C商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page, boost);
        List<GoodsInfo> goodsInfos = pageData.getRecords();
        // 声明商品数据的储存容器
        List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();

        // 按销量排序
        if (qto.getOrderByProperties() != null && (qto.getOrderByProperties() == 20)) {
            goodsListVOS = getGoodsList2(goodsInfos, qto.getOrderByProperties(), qto.getOrderByType());
        } else {
            for (GoodsInfo info : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(info, goodsListVO);
                goodsListVO.setGoodsId(info.getId());
                goodsListVO.setShopName(shopDetailVO(info.getShopId()).getShopName());
                if (ObjectUtils.isNotEmpty(getSpecInfoVO(info))) {
                    goodsListVO.setSpecListVOS(getSpecInfoVO(info));
                }
                if (info.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(info).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(info.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(info.getId()));
                goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(info.getGoodsImage())) ? "" : getImage(info.getGoodsImage()));
                goodsListVO.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(info.getId()));
                goodsListVOS.add(goodsListVO);
            }
        }
        // 5.保存搜索记录
        if (StringUtils.isNotBlank(qto.getGoodsName()) && StringUtils.isNotBlank(qto.getUserId())) {
            if (MallCategoryEnum.我能兑换.getCode() != qto.getSearchEntry()) {
                GoodsSearchHistory goodsSearchHistory = new GoodsSearchHistory()
                        .setFlag(false)
                        .setCdate(new Date())
                        .setKeyword(qto.getGoodsName())
                        .setUdate(new Date())
                        .setUserId(qto.getUserId())
                        .setSearchEntry(qto.getSearchEntry() == MallCategoryEnum.电信商城.getCode() ? MallCategoryEnum.电信商城.getCode() : MallCategoryEnum.积分商城.getCode());
                searchHistoryMapper.insert(goodsSearchHistory);
            }
        }
        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

    @Override
    public BbcSkuGoodInfoVO.SkuVO getSkuVO(BbcGoodsInfoQTO.GoodsSkuQTO qto) {
        if (StringUtils.isEmpty(qto.getGoodsId())) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id", qto.getGoodsId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);

        BbcSkuGoodInfoVO.SkuVO skuVO = new BbcSkuGoodInfoVO.SkuVO();
        //多规格
        if (StringUtils.isNotEmpty(qto.getSpecValues())) {

            //传个来的参数
            List<String> specValue2 = Arrays.asList(qto.getSpecValues().split(","));

            for (SkuGoodInfo skuGoodInfo : skuGoodInfos) {
                String[] specArr = skuGoodInfo.getSpecsValue().split(",");
                List<String> specValue = new ArrayList<>();
                for (int i = 0; i < specArr.length; i++) {
                    specValue.add(specArr[i].split(":")[1]);
                }
                //取两个规格的交集
                List<String> intersection = specValue.stream().filter(item -> specValue2.contains(item)).collect(toList());
                if (intersection.size() == specValue.size()) {
                    //获取相关规格数据
                    BeanUtils.copyProperties(skuGoodInfo, skuVO);
                    skuVO.setSkuId(skuGoodInfo.getId());
                    skuVO.setSkuSalePrice(skuGoodInfo.getSalePrice());
                    skuVO.setSpecValue(skuGoodInfo.getSpecsValue());
                    skuVO.setSkuStock(getSkuStockNum(skuGoodInfo.getShopId(), skuGoodInfo.getId()));

                    //获取库存数
                    Integer quantity = bbcStockRpc.getQuantity(skuGoodInfo.getId());
                    if (quantity == null || quantity.equals(0)) {
                        skuVO.setIsBuy(0);
                        skuVO.setBuyRemark(GoodsBuyRemarkEnum.库存不足.getRemark());
                    }

                    if (!(skuGoodInfo.getState()).equals(GoodsStateEnum.已上架.getCode())) {
                        skuVO.setIsBuy(0);
                        skuVO.setBuyRemark(GoodsBuyRemarkEnum.getRemark(skuGoodInfo.getState()));
                    }
                    break;
                }
            }
        } else {
        	SkuGoodInfo skuGoodInfo = skuGoodInfos.get(0);
        	BeanUtils.copyProperties(skuGoodInfo, skuVO);
//            skuVO.setSkuId(skuGoodInfos.get(0).getId());
            
        }
        return skuVO;
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbcGoodsInfoQTO.OrderGoodsListQTO qto) {
        //获取推荐的商品id列表
        BbcGoodsLabelQTO.QTO labelQTO = new BbcGoodsLabelQTO.QTO();
        BeanUtils.copyProperties(qto, labelQTO);
        List<String> goodsIdList = bbcGoodsLabelService.listGoodsId(labelQTO);
        if (ObjectUtils.isEmpty(goodsIdList)) {
            return new PageData<>();
        }
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        boost.eq("goods_state", GoodsStateEnum.已上架.getCode());
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.升序.getCode().intValue()) {
            boost.orderByAsc("sale_price");
        }
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
            boost.orderByDesc("sale_price");
        }
        //如果是积分查询
        else if (qto.getOrderByProperties() != null && (qto.getOrderByProperties().equals(OrderByConditionEnum.兑换积分.getCode()))) {
            boost.eq("is_point_good", true);
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("is_point_good", "id");
                } else {
                    boost.orderByDesc("is_point_good", "id");
                }
            } else {
                boost.orderByAsc("is_point_good", "id");
            }
        }//按照发布时间排序
        else if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.上架时间.getCode())) {
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("publish_time", "id");
                } else {
                    boost.orderByDesc("publish_time", "id");
                }
            } else {
                boost.orderByAsc("publish_time", "id");
            }
        }
        boost.in("id", goodsIdList);
        //获取2C商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page, boost);
        List<GoodsInfo> goodsInfos = pageData.getRecords();
        //声明商品数据的储存容器
        List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();

        //按销售或者评价排序
        if (qto.getOrderByProperties() != null && (qto.getOrderByProperties().intValue() == OrderByConditionEnum.销售.getCode().intValue() || qto.getOrderByProperties().intValue() == OrderByConditionEnum.评价.getCode().intValue())) {
            goodsListVOS = getGoodsList2(goodsInfos, qto.getOrderByProperties(), qto.getOrderByType());
        } else {
            //按价格排序
            for (GoodsInfo info : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(info, goodsListVO);
                goodsListVO.setGoodsId(info.getId());
                if (ObjectUtils.isNotEmpty(getSpecInfoVO(info))) {
                    goodsListVO.setSpecListVOS(getSpecInfoVO(info));
                }
                if (info.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(info).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(info.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(info.getId()));
                goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(info.getGoodsImage())) ? "" : getImage(info.getGoodsImage()));
                goodsListVOS.add(goodsListVO);
            }
        }

        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

    @Override
    public BbcGoodsInfoVO.GoodsSharingVO getGoodsSharingVO(BbcGoodsInfoQTO.GoodsSharingQTO qto) {
        BbcGoodsInfoVO.GoodsSharingVO sharingVO = new BbcGoodsInfoVO.GoodsSharingVO();
        // 登录凭证不能为空
        if (qto.getCode() == null || qto.getCode().length() == 0) {
            sharingVO.setState(0);
            return sharingVO;
        }
        // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wx7e4d87c442e47530";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "fbffdd94538024a7851d5e9475c6f7fe";
        // 授权（必填）
        String grant_type = "authorization_code";

        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid

        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + qto.getCode() + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpUtil
                .createGet("https://api.weixin.qq.com/sns/jscode2session?" + params)
                .execute()
                .charset("UTF-8")
                .body();
        // 解析相应内容（转换成json对象）
        JSONObject json = JSON.parseObject(sr);
        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
//        String openid = (String) json.get("openid");

        //2、对encryptedData加密数据进行AES解密
        try {
            String result = AesCbcUtil.decrypt(qto.getEncryptedData(), session_key, qto.getIv(), "UTF-8");
            if (null != result && result.length() > 0) {
                sharingVO.setState(1);
                JSONObject jsonObject = JSON.parseObject(result);
                sharingVO.setOpenId(jsonObject.getString("openId"));
                sharingVO.setNickName(jsonObject.getString("nickName"));
                sharingVO.setGender(jsonObject.getInteger("gender"));
                sharingVO.setCity(jsonObject.getString("city"));
                sharingVO.setProvince(jsonObject.getString("province"));
                sharingVO.setAvatarUrl(jsonObject.getString("avatarUrl"));

                // 解密unionId & openId;
                if (ObjectUtils.isNotEmpty(jsonObject.getString("unionId"))) {
                    sharingVO.setUnionId(jsonObject.getString("unionId"));
                }
            } else {
                sharingVO.setState(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sharingVO;
    }


    //--------------------------内部服务--------------------

    @Override
    public List<BbcGoodsInfoVO.InnerServiceVO> innerServicePageShopGoods(BbcGoodsInfoQTO.SkuIdListQTO qto) {
        if (qto == null) {
            throw new BusinessException("参数不能为空！");
        }
        List<BbcGoodsInfoVO.InnerServiceVO> innerServiceVOS = new ArrayList<>();
        for (String skuId : qto.getSkuIdList()) {

            BbcGoodsInfoVO.InnerServiceVO serviceVO = new BbcGoodsInfoVO.InnerServiceVO();

            QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
            boost.eq("id", skuId);
            boost.eq("state", GoodsStateEnum.已上架.getCode());
            SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(boost);
            if (skuGoodInfo == null) {
                continue;
            }

            //获取spu的id
            String goodsId = skuGoodInfo.getGoodId();
            GoodsInfo goodsInfo = repository.getById(goodsId);
            if (goodsInfo == null) {
                continue;
            }
            //判断是否是单品
            if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                BeanUtils.copyProperties(goodsInfo, serviceVO);
                serviceVO.setShopId(goodsInfo.getShopId());
                serviceVO.setGoodsId(goodsInfo.getId());
                serviceVO.setSkuId(skuId);
                serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
                serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
            } else {
                serviceVO.setShopId(goodsInfo.getShopId());
                serviceVO.setSkuId(skuId);
                serviceVO.setGoodsId(goodsInfo.getId());
                serviceVO.setGoodsName(goodsInfo.getGoodsName());
                serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
                serviceVO.setPointPrice(skuGoodInfo.getPointPrice());
                serviceVO.setGoodsImage(skuGoodInfo.getImage());
                serviceVO.setGoodsState(skuGoodInfo.getState());
                serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue()) ? "" : skuGoodInfo.getSpecsValue());
                serviceVO.setGoodsTitle(goodsInfo.getGoodsTitle());
                serviceVO.setGoodsNo(goodsInfo.getGoodsNo());
                serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
                serviceVO.setIsPointGood(skuGoodInfo.getIsPointGood());
                serviceVO.setExchangeType(goodsInfo.getExchangeType());
                serviceVO.setIsInMemberGift(skuGoodInfo.getIsInMemberGift());


                serviceVO.setGoodsAmount(skuGoodInfo.getSalePrice());
                serviceVO.setGoodsPointAmount(skuGoodInfo.getPointPrice());
                serviceVO.setInMemberPointPrice(skuGoodInfo.getInMemberPointPrice());

            }
            innerServiceVOS.add(serviceVO);
        }
        return innerServiceVOS;
    }


    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getGoodsInnerServiceVO(BbcGoodsInfoQTO.GoodsIdListQTO qto) {
        if (qto == null && ObjectUtils.isEmpty(qto.getIdList())) {
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", qto.getIdList());
        queryWrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        List<GoodsInfo> goodsInfos = repository.list(queryWrapper);

        if (ObjectUtils.isEmpty(goodsInfos)) {
            return new ArrayList<>();
        }
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> shopInnerServiceVOS = goodsInfos
                .stream().map(e -> {
                    BbcGoodsInfoVO.HomeAndShopInnerServiceVO homeAndShopInnerServiceVO = new BbcGoodsInfoVO.HomeAndShopInnerServiceVO();
                    BeanUtils.copyProperties(e, homeAndShopInnerServiceVO);
                    homeAndShopInnerServiceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(e.getGoodsImage())) ? "" : getImage(e.getGoodsImage()));
                    homeAndShopInnerServiceVO.setSpecListVOS(ObjectUtils.isEmpty(getSpecInfoVO(e)) ? new ArrayList<>() : getSpecInfoVO(e));
                    if (e.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                        BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(e).get(0);
                        homeAndShopInnerServiceVO.setSkuId(skuVO.getSkuId());
                        homeAndShopInnerServiceVO.setSingleSkuStock(getSkuStockNum(e.getShopId(), skuVO.getSkuId()));
                    }
                    homeAndShopInnerServiceVO.setLabelVOS(getGoodsLabelVO(e.getId()));
                    homeAndShopInnerServiceVO.setOldPrice(e.getOldPrice());
                    homeAndShopInnerServiceVO.setInMemberPointPrice(e.getInMemberPointPrice());
                    homeAndShopInnerServiceVO.setIsInMemberGift(e.getIsInMemberGift());
                    homeAndShopInnerServiceVO.setIsPointGood(e.getIsPointGood());
                    homeAndShopInnerServiceVO.setOldPointPrice(e.getOldPointPrice());
                    homeAndShopInnerServiceVO.setPointPrice(e.getPointPrice());
                    return homeAndShopInnerServiceVO;
                }).collect(toList());
        return shopInnerServiceVOS;
    }

    @Override
    public List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> getInnerSimpleServiceVO(List<String> goodsIds) {
        if (goodsIds == null && ObjectUtils.isEmpty(goodsIds)) {
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", goodsIds);
        List<GoodsInfo> goodsInfos = repository.list(queryWrapper);

        if (ObjectUtils.isEmpty(goodsInfos)) {
            return new ArrayList<>();
        }
        List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> shopInnerServiceVOS = goodsInfos
                .stream().map(e -> {
                    BbcGoodsInfoVO.HomeAndShopInnerServiceVO homeAndShopInnerServiceVO = new BbcGoodsInfoVO.HomeAndShopInnerServiceVO();
                    BeanUtils.copyProperties(e, homeAndShopInnerServiceVO);
                    homeAndShopInnerServiceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(e.getGoodsImage())) ? "" : getImage(e.getGoodsImage()));
                    homeAndShopInnerServiceVO.setSpecListVOS(ObjectUtils.isEmpty(getSpecInfoVO(e)) ? new ArrayList<>() : getSpecInfoVO(e));
                    if (e.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                        BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(e).get(0);
                        homeAndShopInnerServiceVO.setSkuId(skuVO.getSkuId());
                        homeAndShopInnerServiceVO.setSingleSkuStock(getSkuStockNum(e.getShopId(), skuVO.getSkuId()));
                    }
                    homeAndShopInnerServiceVO.setLabelVOS(getGoodsLabelVO(e.getId()));
                    return homeAndShopInnerServiceVO;
                }).collect(toList());
        return shopInnerServiceVOS;
    }

    @Override
    public BbcGoodsInfoVO.InnerServiceVO innerSimpleServiceGoodsVO(String skuID) {
        if (skuID == null) {
            throw new BusinessException("参数不能为空！");
        }

        BbcGoodsInfoVO.InnerServiceVO serviceVO = new BbcGoodsInfoVO.InnerServiceVO();

        QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
        boost.eq("id", skuID);
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(boost);

        if (skuGoodInfo == null) {
            return new BbcGoodsInfoVO.InnerServiceVO();
        }
        //获取spu的id
        String goodsId = skuGoodInfo.getGoodId();
        GoodsInfo goodsInfo = repository.getById(goodsId);
        if (goodsInfo == null) {
            return new BbcGoodsInfoVO.InnerServiceVO();
        }
        skuGoodInfo.setPosSpuId(goodsInfo.getPosSpuId());
        //判断是否是单品
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
            BeanUtils.copyProperties(goodsInfo, serviceVO);
            serviceVO.setShopId(goodsInfo.getShopId());
            serviceVO.setGoodsId(goodsInfo.getId());
            serviceVO.setSkuId(skuID);
            serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
            serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
            
            serviceVO.setGoodsPointAmount(goodsInfo.getPointPrice());
            serviceVO.setSalePrice(goodsInfo.getSalePrice());
            serviceVO.setInMemberPointPrice(skuGoodInfo.getInMemberPointPrice());
            serviceVO.setIsInMemberGift(skuGoodInfo.getIsInMemberGift());
            serviceVO.setIsPointGood(skuGoodInfo.getIsPointGood());
        } else {
            serviceVO.setShopId(goodsInfo.getShopId());
            serviceVO.setSkuId(skuID);
            serviceVO.setGoodsId(goodsInfo.getId());
            serviceVO.setGoodsName(goodsInfo.getGoodsName());
            serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
            serviceVO.setGoodsImage(skuGoodInfo.getImage());
            serviceVO.setGoodsState(skuGoodInfo.getState());
            serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue()) ? "" : skuGoodInfo.getSpecsValue());
            serviceVO.setGoodsTitle(goodsInfo.getGoodsTitle());
            serviceVO.setGoodsNo(goodsInfo.getGoodsNo());
            serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());

            serviceVO.setGoodsPointAmount(skuGoodInfo.getPointPrice());
            serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
            serviceVO.setInMemberPointPrice(skuGoodInfo.getInMemberPointPrice());
            serviceVO.setIsInMemberGift(skuGoodInfo.getIsInMemberGift());
            serviceVO.setIsPointGood(skuGoodInfo.getIsPointGood());
        }
        return serviceVO;

    }

    private List<BbcGoodsInfoVO.GoodsListVO> getGoodsList2(List<GoodsInfo> goodsInfos, Integer orderType, Integer orderWay) {
        if (ObjectUtils.isNotEmpty(goodsInfos)) {
            List<String> goodsIds = new ArrayList<>();
            List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();
            for (GoodsInfo e : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(e, goodsListVO);
                goodsListVO.setGoodsId(e.getId());
                goodsListVO.setShopName(shopDetailVO(e.getShopId()).getShopName());
                List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS = getSpecInfoVO(e);
                if (ObjectUtils.isNotEmpty(specListVOS)) {
                    goodsListVO.setSpecListVOS(specListVOS);
                }
                if (e.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(e).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(e.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(e.getId()));
                goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(e.getGoodsImage())) ? "" : getImage(e.getGoodsImage()));

                goodsIds.add(e.getId());
                goodsListVOS.add(goodsListVO);
            }
            List<BbcTradeListVO.InnerGoodsCompareTo> voList = getAfterOrderGooodsId(orderType, orderWay, goodsIds);
            for (BbcGoodsInfoVO.GoodsListVO goodsListVO : goodsListVOS) {
                for (BbcTradeListVO.InnerGoodsCompareTo vo : voList) {
                    if (goodsListVO.getGoodsId().equals(vo.getId())) {
                        goodsListVO.setIdx(vo.getIdx());
                        break;
                    }
                }
            }
            goodsListVOS.sort((o1, o2) -> ((Integer) o1.getIdx()) != null && ((Integer) o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
            return goodsListVOS;
        }
        return new ArrayList<>();
    }

    private List<BbcGoodsInfoVO.GoodsLabelVO> getGoodsLabelVO(String goodsId) {
        QueryWrapper<GoodsRelationLabel> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id", goodsId);
        List<GoodsRelationLabel> relationLabels = relationLabelRepository.list(wrapper);
        if (ObjectUtils.isEmpty(relationLabels)) {
            return new ArrayList<>();
        }
        List<String> strings = ListUtil.getIdList(GoodsRelationLabel.class, relationLabels, "labelId");
        QueryWrapper<GoodsLabel> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("id", strings);
        List<GoodsLabel> labels = goodsLabelRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(labels)) {
            throw new BusinessException("商品标签数据异常");
        }
        List<BbcGoodsInfoVO.GoodsLabelVO> labelVOS = ListUtil.listCover(BbcGoodsInfoVO.GoodsLabelVO.class, labels);
        return labelVOS;
    }

    private List<BbcSkuGoodInfoVO.SkuVO> getSkuList(GoodsInfo goodsInfo) {
        //商品id查询sku列表
        QueryWrapper<SkuGoodInfo> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("good_id", goodsInfo.getId());
        wrapperBoost.orderByAsc("sale_price", "id");
        wrapperBoost.last("LIMIT 0,1");
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(wrapperBoost);
        if (ObjectUtils.isEmpty(skuGoodInfos)) {
            throw new BusinessException("数据异常");
        }
        List<BbcSkuGoodInfoVO.SkuVO> skuVOS = new ArrayList<>();
        for (SkuGoodInfo skuGoodInfo : skuGoodInfos) {
            BbcSkuGoodInfoVO.SkuVO skuVO = new BbcSkuGoodInfoVO.SkuVO();
            skuVO.setSkuId(skuGoodInfo.getId());
            if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.多规格.getCode().intValue()) {
                skuVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
                skuVO.setSkuStock(getSkuStockNum(goodsInfo.getShopId(), skuGoodInfo.getId()));
                skuVO.setSkuSalePrice(skuGoodInfo.getSalePrice());
                skuVO.setSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue()) ? "" : skuGoodInfo.getSpecsValue());
                skuVO.setImage(StringUtils.isBlank(skuGoodInfo.getImage()) ? "" : skuGoodInfo.getImage());

                Integer quantity = bbcStockRpc.getQuantity(skuGoodInfo.getId());
                if (quantity == null || quantity.equals(0)) {
                    skuVO.setIsBuy(0);
                    skuVO.setBuyRemark(GoodsBuyRemarkEnum.库存不足.getRemark());
                }

                if (!(skuGoodInfo.getState()).equals(GoodsStateEnum.已上架.getCode())) {
                    skuVO.setIsBuy(0);
                    skuVO.setBuyRemark(GoodsBuyRemarkEnum.getRemark(skuGoodInfo.getState()));
                }
            }else{
            	BeanCopyUtils.copyProperties(skuGoodInfo, skuVO);
            }
            skuVOS.add(skuVO);
        }
        return skuVOS;
    }

    private List<BbcGoodsSpecInfoVO.SpecListVO> getSpecInfoVO(GoodsInfo goodsInfo) {
        QueryWrapper<SkuGoodInfo> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("good_id", goodsInfo.getId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(queryWrapperBoost);

        if (ObjectUtils.isEmpty(skuGoodInfos)) {
            throw new BusinessException("数据异常");
        }
        //多规格才有规格值
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.多规格.getCode().intValue()) {
            String specKeys = skuGoodInfos.get(0).getSpecsKey();
            if (StringUtils.isEmpty(specKeys)) {
                throw new BusinessException("sku数据异常");
            }

            //根据规格拓展id查询拓展信息
            List<String> specKeyList = Arrays.asList(specKeys.split(","));
            List<GoodsSpecInfo> specInfos = specInfoRepository.listByIds(specKeyList);

            if (ObjectUtils.isEmpty(specInfos)) {
                throw new BusinessException("规格数据异常！");
            }
            List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS = new ArrayList<>();
            for (GoodsSpecInfo specInfo : specInfos) {
                BbcGoodsSpecInfoVO.SpecListVO specListVO = new BbcGoodsSpecInfoVO.SpecListVO();
                if (StringUtils.isEmpty(specInfo.getSpecValue()) || StringUtils.isEmpty(specInfo.getSpecName())) {
                    throw new BusinessException("规格数据异常");
                }
                specListVO.setSpecName(specInfo.getSpecName());
                specListVO.setSpecValues(Arrays.asList(specInfo.getSpecValue().split(",")));
                specListVOS.add(specListVO);
            }
            return specListVOS;
        }
        return new ArrayList<>();
    }

    @Override
    public PageData<BbcGoodsInfoVO.GoodsListVO> allFirstCategoryGoods(GoodsInfoQTO.CategoryGoodsQTO qto) {
        List<String> categoryIds = new ArrayList<>();
        if (qto.getLevel()!=null &&qto.getLevel().equals(1)) {
            // 获取所有子类目
            BbcGoodsCategoryDTO.ThirdListDTO thirdListDTO = new BbcGoodsCategoryDTO.ThirdListDTO();
            thirdListDTO.setParentId(qto.getCategoryId());
            List<BbcGoodsCategoryVO.ListVO> categoryList = goodsCategoryRpc.listThirdGoodsCategory(thirdListDTO);
            categoryList.forEach(m -> categoryIds.add(m.getId()));
        } else {
            categoryIds.add(qto.getCategoryId());
        }
        // 获取类目下商品
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        // 1. 通用查询条件
        if (ObjectUtils.isNotEmpty(categoryIds)) {
            boost.in("category_id", categoryIds);
        }
        boost.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        boost.eq("goods_state", GoodsStateEnum.已上架.getCode());
        boost.eq("is_point_good", true);
        // 按点击量排序（默认排序）
        if (qto.getOrderByProperties() != null && qto.getOrderByProperties() == 10) {
            boost.orderByDesc("click_volume");
        }
        // 按积分价格排序
        if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
            boost.orderByAsc("point_price");
        }
        if (null != qto.getOrderByProperties() && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())) {
            boost.orderByDesc("point_price");
        }
        // 按上架时间排序
        if (null != qto.getOrderByProperties() && qto.getOrderByProperties() == 40) {
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                if (qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())) {
                    boost.orderByAsc("publish_time", "id");
                } else {
                    boost.orderByDesc("publish_time", "id");
                }
            } else {
                boost.orderByDesc("publish_time", "id");
            }
        }

        // 2.获取2C商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page, boost);
        List<GoodsInfo> goodsInfos = pageData.getRecords();
        // 声明商品数据的储存容器
        List<BbcGoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();

        // 按销量排序
        if (qto.getOrderByProperties() != null && (qto.getOrderByProperties() == 20)) {
            goodsListVOS = getGoodsList2(goodsInfos, qto.getOrderByProperties(), qto.getOrderByType());
        } else {
            for (GoodsInfo info : goodsInfos) {
                BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
                BeanUtils.copyProperties(info, goodsListVO);
                goodsListVO.setGoodsId(info.getId());
                goodsListVO.setShopName(shopDetailVO(info.getShopId()).getShopName());
                if (ObjectUtils.isNotEmpty(getSpecInfoVO(info))) {
                    goodsListVO.setSpecListVOS(getSpecInfoVO(info));
                }
                if (info.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                    BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(info).get(0);
                    goodsListVO.setSkuId(skuVO.getSkuId());
                    goodsListVO.setSingleSkuStock(getSkuStockNum(info.getShopId(), skuVO.getSkuId()));
                }
                goodsListVO.setLabelVOS(getGoodsLabelVO(info.getId()));
                goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(info.getGoodsImage())) ? "" : getImage(info.getGoodsImage()));
                goodsListVOS.add(goodsListVO);
            }
        }
        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), pageData.getTotal());
    }

    @Override
    public BbcTradeRightsVO.GoodsInfo selectOne(String tradeGoodsId) {
        if (StrUtil.isEmpty(tradeGoodsId)) {
            return null;
        }
        GoodsInfo goodsInfo = this.repository.getById(tradeGoodsId);
        if (ObjectUtil.isEmpty(goodsInfo)) {
            return null;
        }
        BbcTradeRightsVO.GoodsInfo tradeGoodsInfo = new BbcTradeRightsVO.GoodsInfo();
        tradeGoodsInfo.setGoodsTitle(goodsInfo.getGoodsTitle());
        tradeGoodsInfo.setGoodsPriceUnit(goodsInfo.getGoodsPriceUnit());
        tradeGoodsInfo.setGoodsImage(goodsInfo.getGoodsImage());
        return tradeGoodsInfo;
    }

    private String getImage(String images) {
        if (images != null) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }

    private BbcGoodsInfoVO.GoodsShopDetailVO shopDetailVO(String shopId) {
        BbcShopQTO.InnerShopQTO qto = new BbcShopQTO.InnerShopQTO();
        qto.setShopId(shopId);
        BbcShopVO.InnerDetailVO innerServiceDetailVO = bbcShopRpc.innerDetailShop(qto);
        BbcGoodsInfoVO.GoodsShopDetailVO shopDetailVO = new BbcGoodsInfoVO.GoodsShopDetailVO();
        if (innerServiceDetailVO != null) {
            shopDetailVO.setShopId(innerServiceDetailVO.getId());
            shopDetailVO.setShopLogo(StringUtils.isEmpty(innerServiceDetailVO.getShopLogo()) ? "" : innerServiceDetailVO.getShopLogo());
            shopDetailVO.setShopDescribe(StringUtils.isEmpty(innerServiceDetailVO.getShopDesc()) ? "" : innerServiceDetailVO.getShopDesc());
            shopDetailVO.setShopName(StringUtils.isEmpty(innerServiceDetailVO.getShopName()) ? "" : innerServiceDetailVO.getShopName());
            shopDetailVO.setShopScore(innerServiceDetailVO.getShopScore());
        }
        return shopDetailVO;
    }

    private Integer getSkuStockNum(String shopId, String skuId) {
        CommonStockVO.InnerStockVO stockVO = commonStock.queryStock(new BaseDTO(), shopId, skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null) {
            return stockVO.getQuantity();
        }
        return 0;
    }

    private List<BbcTradeListVO.InnerGoodsCompareTo> getAfterOrderGooodsId(Integer orderType, Integer orderWays, List<String> goodsIdList) {
        BbcTradeDTO.innerCommpareTo dto = new BbcTradeDTO.innerCommpareTo();
        dto.setCompareToMode(orderWays);
        dto.setCompareToType(orderType);
        dto.setGoodsIds(goodsIdList);
        List<BbcTradeListVO.InnerGoodsCompareTo> compareTos = tradeRpc.innerCommpareTo(dto);
        return compareTos;
    }

    private List<String> listCategoryId(String categoryId) {
        QueryWrapper<GoodsCategory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id", categoryId);
        GoodsCategory category = categoryRepository.getOne(wrapper);
        List<String> categoryList = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(category)) {
            if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.ONE.getCode())) {
                List<String> categories = categoryMapper.selectLevel3CategoryList(categoryId);
                if (ObjectUtils.isNotEmpty(categories)) {
                    categoryList.addAll(categories);
                }
            }
            if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.TWO.getCode())) {
                QueryWrapper<GoodsCategory> queryWrapper = MybatisPlusUtil.query();
                queryWrapper.eq("parent_id", categoryId);
                List<GoodsCategory> categories = categoryRepository.list(queryWrapper);
                if (ObjectUtils.isNotEmpty(categories)) {
                    List<String> idList = ListUtil.getIdList(GoodsCategory.class, categories);
                    categoryList.addAll(idList);
                }
            }
            if (category.getGsCategoryLevel().equals(GoodsCategoryLevelEnum.THREE.getCode())) {
                categoryList.add(categoryId);
            }
        }
        return categoryList;
    }

    @Override
    public void emptySearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        QueryWrapper<GoodsSearchHistory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("flag", false);
        wrapper.eq("search_entry", qto.getSearchEntry());
        wrapper.eq("user_id", qto.getUserId());
        searchHistoryMapper.emptySearchHistory(wrapper);
    }

    @Override
    public List<BbcGoodsInfoVO.SearchHistory> getSearchHistory(BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        QueryWrapper<GoodsSearchHistory> wrapper = MybatisPlusUtil.query();
        wrapper.eq("search_entry", qto.getSearchEntry());
        wrapper.eq("user_id", qto.getUserId());
        wrapper.eq("flag", false);
        wrapper.orderByDesc("cdate");
        wrapper.last("limit 10");
        List<BbcGoodsInfoVO.SearchHistory> searchHistoryList = searchHistoryMapper.getSearchHistory(wrapper);
        return searchHistoryList;
    }

    @Override
    public List<BbcGoodsInfoVO.MyIntegrationExchangeVO> myIntegrationExchange() {
        // 随机查询四个我能兑换的商品
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.is_point_good", true);
        wrapper.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        List<String> goodsIds = goodsInfoMapper.getAllIds(wrapper);
        List<String> ranIds = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {

            String ranNum = goodsIds.get(rand.nextInt(goodsIds.size()));
            goodsIds.remove(ranNum);
            ranIds.add(ranNum);
        }
        wrapper.in("gs.id", ranIds);
        List<BbcGoodsInfoVO.MyIntegrationExchangeVO> integralGoodsInfos = goodsInfoMapper.myIntegrationExchange(wrapper);
        return integralGoodsInfos;
    }

    @Override
    public PageData<BbcGoodsInfoVO.IntegralGoodsInfo> queryIntegralGoodsInfo(BbcGoodsInfoQTO.IntegralGoodsQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getGoodsName())) {
            wrapper.like("gs.goods_name", qto.getGoodsName());
        }
        if (StringUtils.isNotBlank(qto.getCategoryId())) {
            wrapper.eq("gs.category_id", qto.getCategoryId());
        }
        wrapper.lt("gs.point_price", qto.getOkIntegral());
        wrapper.eq("gs.is_point_good", true);
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.eq("gs.flag", false);
//        // 1. 我能兑换积分商品
//        if (QueryIntegralGoodsEnum.我能兑换.getCode() == qto.getOrderByProperties()) {
//            Optional.ofNullable(qto.getUserId()).orElseThrow(() -> new BusinessException("请登录后查看我能兑换的积分商品"));
//            // 查询用户可用积分
//            Integer okIntegral = goodsInfoMapper.getUserOkIntegral(qto.getUserId());
//            wrapper.lt("gs.point_price",okIntegral);
//        }
        // 2. in会员积分商品
        if (QueryIntegralGoodsEnum.IN会员.getCode() == qto.getOrderByProperties()) {
            wrapper.eq("gs.is_in_member_gift", 1);
        }
        // 3. 销量查询积分商品
        if (QueryIntegralGoodsEnum.销量.getCode() == qto.getOrderByProperties()) {
            wrapper.orderByDesc("gs.sale_quantity");
        }
        // 4. 价格查询积分商品
        if (QueryIntegralGoodsEnum.价格.getCode() == qto.getOrderByProperties()) {
            if (10 == qto.getOrderByType()) {
                wrapper.orderByAsc("gs.point_price");
            } else {
                wrapper.orderByDesc("gs.point_price");
            }
        }
        // 5. 上新查询积分商品
        if (QueryIntegralGoodsEnum.上新.getCode() == qto.getOrderByProperties()) {
            wrapper.orderByDesc("gs.cdate");
        }

        IPage<BbcGoodsInfoVO.IntegralGoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<BbcGoodsInfoVO.IntegralGoodsInfo> pageData = goodsInfoMapper.queryIntegralGoodsInfo(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, BbcGoodsInfoVO.IntegralGoodsInfo.class, pageData);
    }

    @Override
    public PageData<BbcGoodsInfoVO.InVIPSpecialAreaVO> queryInVIPSpecialAreaList(BbcGoodsInfoQTO.InSpecialAreaGoodsQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.in_coupon_type", qto.getInCouponType());
        wrapper.eq("gs.is_in_member_gift", true);
        wrapper.eq("gs.flag", false);
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        // 排序条件字段 10=综合 20=销量 30=价格 40=上新
        if (20 == qto.getOrderByProperties()) {
            if (10 == qto.getOrderByType()) {
                wrapper.orderByAsc("gs.sale_quantity");
            } else {
                wrapper.orderByDesc("gs.sale_quantity");
            }
        }
        if (30 == qto.getOrderByProperties()) {
            if (10 == qto.getOrderByType()) {
                wrapper.orderByAsc("gs.sale_price");
            } else {
                wrapper.orderByDesc("gs.sale_price");
            }
        }
        if (40 == qto.getOrderByProperties()) {
            wrapper.orderByDesc("gs.cdate");
        }
        IPage<BbcGoodsInfoVO.InVIPSpecialAreaVO> page = MybatisPlusUtil.pager(qto);
        IPage<BbcGoodsInfoVO.InVIPSpecialAreaVO> pageData = goodsInfoMapper.queryInVIPSpecialAreaList(page, wrapper);
        // 计算商品折后价格
        pageData.getRecords().parallelStream().forEach(m -> {
            m.setInDiscountedPrice(m.getSalePrice().subtract(new BigDecimal(m.getInCouponType())));
        });
        return MybatisPlusUtil.toPageData(qto, BbcGoodsInfoVO.InVIPSpecialAreaVO.class, pageData);
    }

    @Override
    public BbcGoodsInfoVO.InMemberGoodsVO inMemberGoodsHome(InMemberGoodsQTO qto) {
        BbcGoodsInfoVO.InMemberGoodsVO ret = new BbcGoodsInfoVO.InMemberGoodsVO();
        ret.setId("inmembergoods");
        ret.setName("IN会员精口专区");
        ret.setImageUrl("https://lingang-app-bete.oss-cn-shanghai.aliyuncs.com/admin/2021/03/30/1617118074782.png");
        ret.setTelecomsIntegral(0);
        String userId = qto.getJwtUserId();
        if (StringUtils.isNotEmpty(userId)) {
            BbcUserCtccPointVO.DetailVO detailvo = bbcUserCtccPointRpc.getCtccPointByUserId(userId);
            if (detailvo != null && StringUtils.isNotEmpty(detailvo.getId())) {
                ret.setTelecomsIntegral(detailvo.getPointBalance());
            }
        }

        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("is_in_member_gift", TrueFalseEnum.是.getCode());
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page, wrapper);
        if (ObjectUtils.isEmpty(goodsInfoIPage) || ObjectUtils.isEmpty(goodsInfoIPage.getRecords())) {
            return ret;
        }
        List<BbcGoodsInfoVO.DetailVO> categoryGoodsVOS = ListUtil.listCover(BbcGoodsInfoVO.DetailVO.class, goodsInfoIPage.getRecords());
        if (CollectionUtils.isNotEmpty(categoryGoodsVOS)) {
            for (BbcGoodsInfoVO.DetailVO detailVO : categoryGoodsVOS) {
                detailVO.setGoodsId(detailVO.getId());
                String goodsId = detailVO.getGoodsId();
                //查询标签
                detailVO.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(goodsId));
            }
        }
        ret.setList(new PageData<>(categoryGoodsVOS, qto.getPageNum(), qto.getPageSize(), goodsInfoIPage.getTotal()));

        return ret;
    }

    private List<String> listShopNavigationIds(String navigationId) {
        List<String> shopNavigationIds = bbcShopRpc.innerGetNavigationList(navigationId);
        return shopNavigationIds;
    }

    @Override
    public PageData<BbcGoodsInfoVO.InVipListVO> pageInMemberGoodsInfo(InMemberGoodsQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("is_in_member_gift", TrueFalseEnum.是.getCode());
        wrapper.eq("flag", false);
        wrapper.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        if (qto.getInCouponType() != null && qto.getInCouponType() > 0) {
            wrapper.eq("in_coupon_type", qto.getInCouponType());
        }
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page, wrapper);

        List<BbcGoodsInfoVO.InVipListVO> categoryGoodsVOS = ListUtil.listCover(BbcGoodsInfoVO.InVipListVO.class, goodsInfoIPage.getRecords());
        if (CollectionUtils.isNotEmpty(categoryGoodsVOS)) {
            for (BbcGoodsInfoVO.InVipListVO detailVO : categoryGoodsVOS) {
                BigDecimal inMemberPointPrice = detailVO.getInMemberPointPrice();
            	
            	detailVO.setGoodsImage(ObjectUtils.isEmpty(getImage(detailVO.getGoodsImage())) ? "" : getImage(detailVO.getGoodsImage()));
                detailVO.setInMemberPointPrice(inMemberPointPrice);
            
                detailVO.setOldPrice(inMemberPointPrice.divide(new BigDecimal("100")));
                inMemberPointPrice = inMemberPointPrice.divide(new BigDecimal("100"));
                BigDecimal salePrice = inMemberPointPrice.compareTo(new BigDecimal(qto.getInCouponType())) > 0 ? inMemberPointPrice.subtract(new BigDecimal(qto.getInCouponType())) : new BigDecimal(0);
                
                detailVO.setSalePrice(salePrice);
            }
        }
        return new PageData<>(categoryGoodsVOS, qto.getPageNum(), qto.getPageSize(), goodsInfoIPage.getTotal());
    }

    @Override
    public List<SimpleListVO> listGoodsInfoByCategory(CategoryIdCountDTO dto) {

        List<SimpleListVO> retList = new ArrayList<SimpleListVO>();
        BbcGoodsCategoryDTO.ParentIdDTO parentIdDTO = new BbcGoodsCategoryDTO.ParentIdDTO();
        parentIdDTO.setParentId(dto.getCategoryId());
        List<String> categoryIds = bbcGoodsCategoryService.listGoodsCategoryByParentId(parentIdDTO);

        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.in("category_id", categoryIds);
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        List<GoodsInfo> goodsInfoList = repository.list(wrapper);

        List<Integer> counts = StringManageUtil.randomdBetween(0, goodsInfoList.size(), 4);
        for (int i = 0; i < counts.size(); i++) {
            GoodsInfo goodsInfo = goodsInfoList.get(counts.get(i));
            SimpleListVO listVO = new SimpleListVO();
            BeanCopyUtils.copyProperties(goodsInfo, listVO);
            listVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
            retList.add(listVO);
        }
        return retList;
    }

    @Override
    public SimpleListVO simpleListVO(IdDTO dto) {
        if (dto == null) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if (ObjectUtils.isEmpty(goodsInfo)) {
            throw new BusinessException("数据异常");
        }

        BbcGoodsInfoVO.SimpleListVO detailVo = new BbcGoodsInfoVO.SimpleListVO();
        BeanUtils.copyProperties(goodsInfo, detailVo);
        detailVo.setGoodsId(goodsInfo.getId());

        //查询标签
        detailVo.setTags(bbcGoodsLabelService.listGoodsLabelByGoodsId(goodsInfo.getId()));
        return detailVo;
    }

    @Override
    public List<String> getGoodsIdsByName(String goodsName) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.like("goods_name", goodsName);
        wrapper.eq("flag", false);
        List<String> goodsIds = goodsInfoMapper.getGoodsIdsByName(wrapper);
        return goodsIds;
    }

    @Override
    public BbcGoodsInfoVO.isCollectionGoodsVO isCollectGoods(BbcGoodsInfoQTO.GoodsIdQTO qto) {
        Integer count = goodsInfoMapper.isCollectGoods(qto.getGoodsId(), qto.getJwtUserId());
        BbcGoodsInfoVO.isCollectionGoodsVO goodsVO = new BbcGoodsInfoVO.isCollectionGoodsVO();
        // 0-未收藏 1-已收藏
        if (count != 0) {
            goodsVO.setIsCollect(1);
        } else {
            goodsVO.setIsCollect(0);
        }
        return goodsVO;
    }

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> getGeneralGoodsInfo() {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.is_point_good", true);
        wrapper.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.eq("gs.flag", false);
        List<String> goodsIds = goodsInfoMapper.getAllIds(wrapper);
        List<String> ranIds = new ArrayList<>();
        QueryWrapper<GoodsInfo> boost = new QueryWrapper<>();
        if (goodsIds.size() > 10) {
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                String ranNum = goodsIds.get(rand.nextInt(goodsIds.size()));
                goodsIds.remove(ranNum);
                ranIds.add(ranNum);
            }
            boost.in("id", ranIds);
        } else {
            boost.in("id", goodsIds);
        }

        // 获取商品信息
        List<GoodsInfo> goodsInfoList = repository.list(boost);

        // 获取商品规格
        List<BbcGoodsInfoVO.GoodsListVO> resultList = new ArrayList<>();
        goodsInfoList.forEach(goods -> {
            BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
            BeanUtils.copyProperties(goods, goodsListVO);
            goodsListVO.setGoodsId(goods.getId());
            goodsListVO.setShopName(shopDetailVO(goods.getShopId()).getShopName());
            if (ObjectUtils.isNotEmpty(getSpecInfoVO(goods))) {
                goodsListVO.setSpecListVOS(getSpecInfoVO(goods));
            }
            if (goods.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(goods).get(0);
                goodsListVO.setSkuId(skuVO.getSkuId());
                goodsListVO.setSingleSkuStock(getSkuStockNum(goods.getShopId(), skuVO.getSkuId()));
            }
            goodsListVO.setLabelVOS(getGoodsLabelVO(goods.getId()));
            goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goods.getGoodsImage())) ? "" : getImage(goods.getGoodsImage()));
            resultList.add(goodsListVO);
        });
        return resultList;
    }

    @Override
    public List<String> getCategoryIdsByName(String goodsName) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.like("goods_name", goodsName);
        wrapper.eq("flag", false);
        List<String> categoryIds = goodsInfoMapper.getCategoryIdsByName(wrapper);
        return categoryIds;
    }

    @Override
    public List<BbcGoodsInfoVO.GoodsListVO> getShopRecommendGoods(BbcGoodsInfoQTO.ShopGoodsIdQTO qto) {
        // 随机查询四个店铺推荐商品
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("gs.is_point_good", true);
        wrapper.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.B商城.getCode());
        wrapper.eq("gs.shop_id", qto.getShopId());
        wrapper.eq("gs.flag", false);
        List<String> goodsIds = goodsInfoMapper.getAllIds(wrapper);
        List<String> ranIds = new ArrayList<>();
        QueryWrapper<GoodsInfo> boost = new QueryWrapper<>();
        if (goodsIds.size() > 4) {
            Random rand = new Random();
            for (int i = 0; i < 4; i++) {
                String ranNum = goodsIds.get(rand.nextInt(goodsIds.size()));
                goodsIds.remove(ranNum);
                ranIds.add(ranNum);
            }
            boost.in("id", ranIds);
        } else {
            boost.in("id", goodsIds);
        }

        // 获取商品信息
        List<GoodsInfo> goodsInfoList = repository.list(boost);

        // 获取商品规格
        List<BbcGoodsInfoVO.GoodsListVO> resultList = new ArrayList<>();
        goodsInfoList.forEach(goods -> {
            BbcGoodsInfoVO.GoodsListVO goodsListVO = new BbcGoodsInfoVO.GoodsListVO();
            BeanUtils.copyProperties(goods, goodsListVO);
            goodsListVO.setGoodsId(goods.getId());
            goodsListVO.setShopName(shopDetailVO(goods.getShopId()).getShopName());
            if (ObjectUtils.isNotEmpty(getSpecInfoVO(goods))) {
                goodsListVO.setSpecListVOS(getSpecInfoVO(goods));
            }
            if (goods.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                BbcSkuGoodInfoVO.SkuVO skuVO = getSkuList(goods).get(0);
                goodsListVO.setSkuId(skuVO.getSkuId());
                goodsListVO.setSingleSkuStock(getSkuStockNum(goods.getShopId(), skuVO.getSkuId()));
            }
            goodsListVO.setLabelVOS(getGoodsLabelVO(goods.getId()));
            goodsListVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goods.getGoodsImage())) ? "" : getImage(goods.getGoodsImage()));
            resultList.add(goodsListVO);
        });
        return resultList;
    }

    @Override
    public InMemberHomeVO inMemberHome() {
        InMemberHomeVO vo = new InMemberHomeVO();
        BbcSiteAdvertQTO.SubjectQTO qto = new BbcSiteAdvertQTO.SubjectQTO();
        qto.setSubject(SubjectEnum.IN会员专区.getCode());
        List<BbcSiteAdvertVO.AdvertDetailVO> adverts = bbcSiteAdvertRpc.listBySubject(qto);

        vo.setAdverts(adverts);
        vo.setCouponTypes(InUserCouponPriceEnum.getAll());
        return vo;
    }

    @Override
    public List<InnerServiceVO> innerServiceShopGoods(SkuIdListQTO qto) {
        if (qto == null) {
            throw new BusinessException("参数不能为空！");
        }
        List<BbcGoodsInfoVO.InnerServiceVO> innerServiceVOS = new ArrayList<>();
        for (String skuId : qto.getSkuIdList()) {

            BbcGoodsInfoVO.InnerServiceVO serviceVO = new BbcGoodsInfoVO.InnerServiceVO();

            QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
            boost.eq("id", skuId);
            SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(boost);
            if (skuGoodInfo == null) {
                continue;
            }

            //获取spu的id
            String goodsId = skuGoodInfo.getGoodId();
            GoodsInfo goodsInfo = repository.getById(goodsId);
            if (goodsInfo == null) {
                continue;
            }
            //判断是否是单品
            if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
                BeanUtils.copyProperties(goodsInfo, serviceVO);
                serviceVO.setShopId(goodsInfo.getShopId());
                serviceVO.setGoodsId(goodsInfo.getId());
                serviceVO.setSkuId(skuId);
                serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
                serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
                serviceVO.setIsSingle(goodsInfo.getIsSingle());
            } else {
                serviceVO.setShopId(goodsInfo.getShopId());
                serviceVO.setSkuId(skuId);
                serviceVO.setGoodsId(goodsInfo.getId());
                serviceVO.setGoodsName(goodsInfo.getGoodsName());
                serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
                serviceVO.setPointPrice(skuGoodInfo.getPointPrice());
                serviceVO.setGoodsImage(skuGoodInfo.getImage());
                serviceVO.setGoodsState(skuGoodInfo.getState());
                serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue()) ? "" : skuGoodInfo.getSpecsValue());
                serviceVO.setGoodsTitle(goodsInfo.getGoodsTitle());
                serviceVO.setGoodsNo(goodsInfo.getGoodsNo());
                serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
                serviceVO.setIsPointGood(skuGoodInfo.getIsPointGood());
                serviceVO.setExchangeType(goodsInfo.getExchangeType());
                serviceVO.setIsInMemberGift(skuGoodInfo.getIsInMemberGift());


                serviceVO.setGoodsAmount(skuGoodInfo.getSalePrice());
                serviceVO.setGoodsPointAmount(skuGoodInfo.getPointPrice());
                serviceVO.setInMemberPointPrice(skuGoodInfo.getInMemberPointPrice());
                serviceVO.setIsSingle(goodsInfo.getIsSingle());

            }
            innerServiceVOS.add(serviceVO);
        }
        return innerServiceVOS;
    }

    @Override
    public List<BbcGoodsSpecInfoVO.SpecListVO> listSpecInfoByGoods(BbcGoodsInfoQTO.SpecInfoByGoodsQTO qto) {
        String id = qto.getGoodsId();
        GoodsInfo goodsInfo = repository.getById(id);
        if (goodsInfo == null)
            throw new BusinessException("数据异常");

        QueryWrapper<SkuGoodInfo> queryWrapperBoost = MybatisPlusUtil.query();
        queryWrapperBoost.eq("good_id", goodsInfo.getId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(queryWrapperBoost);

        if (ObjectUtils.isEmpty(skuGoodInfos)) {
            throw new BusinessException("数据异常");
        }
        //多规格才有规格值
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.多规格.getCode().intValue()) {
            String specKeys = skuGoodInfos.get(0).getSpecsKey();
            if (StringUtils.isEmpty(specKeys)) {
                throw new BusinessException("sku数据异常");
            }

            //根据规格拓展id查询拓展信息
            List<String> specKeyList = Arrays.asList(specKeys.split(","));
            List<GoodsSpecInfo> specInfos = specInfoRepository.listByIds(specKeyList);

            if (ObjectUtils.isEmpty(specInfos)) {
                throw new BusinessException("规格数据异常！");
            }
            List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS = new ArrayList<>();
            for (GoodsSpecInfo specInfo : specInfos) {
                BbcGoodsSpecInfoVO.SpecListVO specListVO = new BbcGoodsSpecInfoVO.SpecListVO();
                if (StringUtils.isEmpty(specInfo.getSpecValue()) || StringUtils.isEmpty(specInfo.getSpecName())) {
                    throw new BusinessException("规格数据异常");
                }
                specListVO.setSpecName(specInfo.getSpecName());
                specListVO.setSpecValues(Arrays.asList(specInfo.getSpecValue().split(",")));
                specListVOS.add(specListVO);
            }
            return specListVOS;
        }
        return new ArrayList<>();
    }

    @Override
    public List<ListCouponVO> listCoupon(GoodsIdQTO qto) {

        List<ListCouponVO> retList = new ArrayList<ListCouponVO>();
        ListCouponVO listCouponVO = new ListCouponVO();
        listCouponVO.setCouponType(1);
        listCouponVO.setUseTime("2021/01/01 2021/08/01");
        listCouponVO.setCouponName("仅购买IN会员商品可以使用");
        listCouponVO.setCouponStatus(2);
        listCouponVO.setDeduction(new BigDecimal("20.00"));
        listCouponVO.setUseThreshold(new BigDecimal("40.00"));
        listCouponVO.setDeductionType(Integer.valueOf(1));
        listCouponVO.setId("4ecef3ea3d6c421f9fd7f4c82bfcab5b");
        retList.add(listCouponVO);
        return retList;
    }

	@Override
	public GoodsCtccApiVO getCtccApiByGoodId(String goodId) {
		GoodsCtccApiVO goodsCtccApiVO = new GoodsCtccApiVO();
		QueryWrapper<GoodsCtccApi> wrapper = MybatisPlusUtil.query();
        wrapper.eq("good_id", goodId);
        GoodsCtccApi goodsCtccApi = goodsCtccApiRepository.getOne(wrapper);
        if(goodsCtccApi!=null){
        	BeanCopyUtils.copyProperties(goodsCtccApi, goodsCtccApiVO);
        	return goodsCtccApiVO;
        }
		return null;
	}

}
