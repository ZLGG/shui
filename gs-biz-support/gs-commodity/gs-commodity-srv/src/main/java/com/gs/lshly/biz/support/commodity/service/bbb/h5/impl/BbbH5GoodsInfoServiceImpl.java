package com.gs.lshly.biz.support.commodity.service.bbb.h5.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.GoodsSpecInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.mapper.GoodsCategoryMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsShopNavigationRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsSpecInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsLabelService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsLabelQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockAddressRpc;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserFavoritesGoodsRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-23
*/
@Component
@Slf4j
public class BbbH5GoodsInfoServiceImpl implements IBbbH5GoodsInfoService {

    @Autowired
    private IGoodsInfoRepository repository;
    @Autowired
    private IGoodsSpecInfoRepository specInfoRepository;
    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;
    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Autowired
    private IGoodsShopNavigationRepository shopNavigationRepository;

    @DubboReference
    private IBbbH5ShopRpc bbbH5ShopRpc;

    @DubboReference
    private ICommonStockRpc commonStock;

    @DubboReference
    private IBbbH5StockAddressRpc stockAddressRpc;

    @DubboReference
    private IBbbH5UserFavoritesGoodsRpc favoritesGoodsRpc;

    @DubboReference
    private IBbbUserRpc userRpc;

    @DubboReference
    private IBbbH5TradeRpc tradeRpc;

    @Autowired
    private IBbbH5GoodsLabelService bbcGoodsLabelService;



    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> pageGoodsListVO(BbbH5GoodsInfoQTO.GoodsListByCategoryQTO qto) {
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        if (StringUtils.isNotEmpty(qto.getGoodsName())){
            boost.like("goods_name",qto.getGoodsName());
        }
        boost.ne("use_platform",GoodsUsePlatformEnums.C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotBlank(qto.getCategoryLevel())){
            boost.eq("category_id",qto.getCategoryLevel());
        }
        if (StringUtils.isNotBlank(qto.getShopId())){
            boost.eq("shop_id",qto.getShopId());
        }
        if (qto.getOrderByProperties()!=null && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode()) && qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode())){
            boost.orderByAsc("sale_price");
        }
        if (qto.getOrderByProperties()!=null && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode())  && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode())){
            boost.orderByDesc("sale_price");
        }
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.兑换积分.getCode())) {
            //如果需要积分排序，首先得是积分商品
            boost.eq("is_point_good", true);
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                //升序
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("point_price", "id");
                } else {
                    //降序
                    boost.orderByDesc("point_price", "id");
                }
            } else {
                boost.orderByAsc("point_price", "id");
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.上架时间.getCode())) {
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
        //获取2B商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page,boost);
        if (ObjectUtils.isEmpty(pageData) || ObjectUtils.isEmpty(pageData.getRecords())){
            return new PageData<>();
        }
        List<BbbH5GoodsInfoVO.GoodsListVO> goodsListVOS = getGoodsList2(pageData.getRecords(),qto,qto.getOrderByProperties(),qto.getOrderByType());
        return new PageData<>(goodsListVOS,qto.getPageNum(),qto.getPageSize(),pageData.getTotal());
    }

    @Override
    public BbbH5GoodsInfoVO.DetailVO detailGoodsInfo(BbbH5GoodsInfoDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        GoodsInfo goodsInfo = repository.getById(dto.getId());
        if(ObjectUtils.isEmpty(goodsInfo)){
            throw new BusinessException("数据异常");
        }
        BbbH5GoodsInfoVO.DetailVO detailVo = new BbbH5GoodsInfoVO.DetailVO();
        BeanUtils.copyProperties(goodsInfo,detailVo);
        detailVo.setGoodsId(goodsInfo.getId());

        BbbH5SkuGoodInfoVO.SkuVO skuVO = getSku(goodsInfo,dto);
        detailVo.setSkuId(skuVO.getSkuId());

        //若是多规格填充默认规格信息
        if (goodsInfo.getIsSingle().equals(SingleStateEnum.多规格.getCode())){
            detailVo.setSkuVO(skuVO);
            detailVo.setSalePrice(skuVO.getSkuSalePrice());
            detailVo.setOldPrice(skuVO.getSkuOldPrice());
        }
        if (goodsInfo.getIsSingle().equals(SingleStateEnum.单品.getCode())){
            detailVo.setSingleSkuStock(skuVO.getSkuStock());
        }
        //获取店铺信息
        BbbH5GoodsInfoVO.GoodsShopDetailVO shopDetailVO = shopDetailVO(goodsInfo.getShopId());

        detailVo.setGoodsShopDetailVO(shopDetailVO);

        //批发价或阶梯价处理
        if (ObjectUtils.isNotEmpty(skuVO.getSkuStepPrice())){
            detailVo.setStepPrice(skuVO.getSkuStepPrice());
        }
        if (ObjectUtils.isNotEmpty(skuVO.getWholesalePrice())){
            detailVo.setWholesalePrice(skuVO.getWholesalePrice());
        }

        //获取用户默认收货地址
        BbbH5StockAddressVO.DetailVO defaultAddresslVO = stockAddressRpc.innerGetDefault(dto,StockAddressTypeEnum.收货.getCode());
        if (defaultAddresslVO != null){
            detailVo.setUserDefaultAddress(StringUtils.isNotEmpty(defaultAddresslVO.getFullAddres())?defaultAddresslVO.getFullAddres():"");
        }

        //获取商品收藏状态
        detailVo.setFavoritesState(favoritesGoodsRpc.innerFavoritesState(dto.getId(), dto.getJwtUserId()));
        return detailVo;
    }

    @Override
    public List<BbbH5GoodsSpecInfoVO.SpecListVO> listSpecVOs(BbbH5GoodsInfoDTO.IdDTO dto) {
        QueryWrapper<SkuGoodInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("good_id",dto.getId());
        wrapper.orderByAsc("sale_price","id");
        wrapper.last("limit 0,1");
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(wrapper);
        if (ObjectUtils.isEmpty(skuGoodInfo)){
            throw new BusinessException("数据异常");
        }
        //多规格才有规格值
        if (StringUtils.isNotBlank(skuGoodInfo.getSpecsKey())) {
           String specKeys = skuGoodInfo.getSpecsKey();
            //根据规格拓展id查询拓展信息
            List<String> specKeyList = Arrays.asList(specKeys.split(","));
            List<GoodsSpecInfo> specInfos = specInfoRepository.listByIds(specKeyList);

            if (ObjectUtils.isEmpty(specInfos)){
                throw new BusinessException("规格数据异常！");
            }
            List<BbbH5GoodsSpecInfoVO.SpecListVO> specListVOS = new ArrayList<>();
            for (GoodsSpecInfo specInfo : specInfos){
                BbbH5GoodsSpecInfoVO.SpecListVO specListVO = new BbbH5GoodsSpecInfoVO.SpecListVO();
                if (StringUtils.isEmpty(specInfo.getSpecValue()) || StringUtils.isEmpty(specInfo.getSpecName())){
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
    public BbbH5SkuGoodInfoVO.SkuVO getSkuVO(BbbH5GoodsInfoQTO.GoodsSkuQTO qto) {
        if (StringUtils.isEmpty(qto.getGoodsId())){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<SkuGoodInfo> boost = MybatisPlusUtil.query();
        boost.eq("good_id",qto.getGoodsId());
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(boost);

        BbbH5SkuGoodInfoVO.SkuVO skuVO = new BbbH5SkuGoodInfoVO.SkuVO();
        //多规格
        if (StringUtils.isNotEmpty(qto.getSpecValues())){
            //传个来的参数
            List<String> specValue2 = Arrays.asList(qto.getSpecValues().split(","));
            for (SkuGoodInfo skuGoodInfo : skuGoodInfos){
                String[] specArr = skuGoodInfo.getSpecsValue().split(",");
                List<String> specValue = new ArrayList<>();
                for (int i =0;i<specArr.length;i++){
                    specValue.add(specArr[i].split(":")[1]);
                }
                //取两个规格的交集
                List<String> intersection = specValue.stream().filter(item -> specValue2.contains(item)).collect(toList());
                if (intersection.size() == specValue.size()){
                    //获取相关规格数据
                    BeanUtils.copyProperties(skuGoodInfo,skuVO);
                    skuVO.setSkuId(skuGoodInfo.getId());
                    skuVO.setSkuSalePrice(skuGoodInfo.getSalePrice());
                    skuVO.setSkuImg(skuGoodInfo.getImage());
                    skuVO.setSkuOldPrice(skuGoodInfo.getOldPrice());
                    skuVO.setSkuStock(getSkuStockNum(skuGoodInfo.getShopId(),skuGoodInfo.getId()));
                    //填充批发价或阶梯价
                    BbbH5GoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new BbbH5GoodsInfoDTO.WholesalePriceDTO();
                    wholesalePriceDTO.setJwtUserId(qto.getJwtUserId());
                    wholesalePriceDTO.setShopId(skuGoodInfo.getShopId());
                    wholesalePriceDTO.setJwtUserType(qto.getJwtUserType());
                    wholesalePriceDTO.setIsSingle(SingleStateEnum.多规格.getCode());
                    wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSalePrice());
                    wholesalePriceDTO.setGoodsId(skuGoodInfo.getGoodId());
                    wholesalePriceDTO.setSkuId(skuGoodInfo.getId());
                    wholesalePriceDTO.setSkuStepPrice(getGoodsStepPriceVOs(skuGoodInfo.getStepPrice()));
                    BbbH5GoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
                    if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())){
                        skuVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
                    }else {
                        skuVO.setSkuStepPrice(wholesalePriceVO.getStepPriceVOS());
                    }
                    break;
                }
            }
        }else {
            skuVO.setSkuId(skuGoodInfos.get(0).getId());
        }
        return skuVO;
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> getRecommendGoodsList(BbbH5GoodsInfoQTO.GoodsListQTO qto) {
        //获取推荐的商品id列表
        BbbH5GoodsLabelQTO.QTO labelQTO = new BbbH5GoodsLabelQTO.QTO();
        BeanUtils.copyProperties(qto,labelQTO);
        List<String> goodsIdList = bbcGoodsLabelService.listGoodsId(labelQTO);
        if (ObjectUtils.isEmpty(goodsIdList)){
            return new PageData<>();
        }
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.ne("use_platform",GoodsUsePlatformEnums.C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (StringUtils.isNotBlank(qto.getGoodsName())){
            boost.eq("goods_name",qto.getGoodsName());
        }
        if (qto.getOrderByProperties()!=null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.升序.getCode().intValue()){
            boost.orderByAsc("sale_price");
        }
        if (qto.getOrderByProperties()!=null && qto.getOrderByProperties().intValue() == OrderByConditionEnum.价格.getCode().intValue() && qto.getOrderByType().intValue() == OrderByTypeEnum.降序.getCode().intValue()){
            boost.orderByDesc("sale_price");
        }
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.兑换积分.getCode())) {
            //如果需要积分排序，首先得是积分商品
            boost.eq("is_point_good", true);
            if (ObjectUtils.isNotEmpty(qto.getOrderByType())) {
                //升序
                if (qto.getOrderByType().equals(10)) {
                    boost.orderByAsc("point_price", "id");
                } else {
                    //降序
                    boost.orderByDesc("point_price", "id");
                }
            } else {
                boost.orderByAsc("point_price", "id");
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.上架时间.getCode())) {
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
        boost.in("id",goodsIdList);
        //获取2B商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page,boost);
        if (ObjectUtils.isEmpty(pageData) || ObjectUtils.isEmpty(pageData.getRecords())){
            return new PageData<>();
        }
        List<BbbH5GoodsInfoVO.GoodsListVO> goodsListVOS = getGoodsList2(pageData.getRecords(),qto,qto.getOrderByProperties(),qto.getOrderByType());
        return new PageData<>(goodsListVOS,qto.getPageNum(),qto.getPageSize(),pageData.getTotal());
    }

    @Override
    public PageData<BbbH5GoodsInfoVO.GoodsListVO> getQuickOrderGoodsList(BbbH5GoodsInfoQTO.QuickOrderQTO qto) {
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.ne("use_platform",GoodsUsePlatformEnums.C商城.getCode());
        boost.eq("goods_state",GoodsStateEnum.已上架.getCode());
        if (ObjectUtils.isEmpty(qto.getLevel1CategoryId())){
            throw new BusinessException("一级类目id不能为空！");
        }
        List<String> categoryIds = getCategoryIds(qto.getLevel1CategoryId());
        if (ObjectUtils.isEmpty(categoryIds)){
            return new PageData<>();
        }
        boost.in("category_id",categoryIds);
        //获取2B商城的商品
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> pageData = repository.page(page,boost);
        if (ObjectUtils.isEmpty(pageData) || ObjectUtils.isEmpty(pageData.getRecords())){
            return new PageData<>();
        }
        List<BbbH5GoodsInfoVO.GoodsListVO> goodsListVOS = getGoodsList2(pageData.getRecords(),qto,null,null);
        return new PageData<>(goodsListVOS,qto.getPageNum(),qto.getPageSize(),pageData.getTotal());
    }


    //--------------------------内部服务--------------------

    @Override
    public List<BbbH5GoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList,BaseDTO dto) {
        if (ObjectUtils.isEmpty(skuIdList)){
            throw new BusinessException("参数不能为空！");
        }
        List<BbbH5GoodsInfoVO.InnerServiceVO> innerServiceVOS = new ArrayList<>();
        for (String skuId : skuIdList){
            BbbH5GoodsInfoVO.InnerServiceVO serviceVO = null;
            serviceVO = innerServiceVO(skuId,dto);
            if (ObjectUtils.isNotEmpty(serviceVO)){
                innerServiceVOS.add(serviceVO);
            }
        }
        return innerServiceVOS;
    }

    @Override
    public BbbH5GoodsInfoVO.InnerServiceVO innerServiceVO(String skuId,BaseDTO dto) {
        QueryWrapper<SkuGoodInfo> skuGoodInfoQueryWrapper = MybatisPlusUtil.query();
        skuGoodInfoQueryWrapper.eq("id",skuId);
        skuGoodInfoQueryWrapper.eq("state",GoodsStateEnum.已上架.getCode());
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(skuGoodInfoQueryWrapper);
        if (skuGoodInfo == null){
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",skuGoodInfo.getGoodId());
        queryWrapper.eq("goods_state",GoodsStateEnum.已上架.getCode());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (goodsInfo == null){
            return null;
        }
        BbbH5GoodsInfoVO.InnerServiceVO serviceVO = new BbbH5GoodsInfoVO.InnerServiceVO();
        BeanUtils.copyProperties(goodsInfo,serviceVO);
        serviceVO.setGoodsId(goodsInfo.getId());
        serviceVO.setSkuId(skuId);


        //判断是否是单品
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()){

            serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
            serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage()))?"":getImage(goodsInfo.getGoodsImage()));
        }else {
            serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
            serviceVO.setGoodsImage(skuGoodInfo.getImage());
            serviceVO.setSkuSpecKey(skuGoodInfo.getSpecsKey());
            serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue())?"":skuGoodInfo.getSpecsValue());
            serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
        }

        //填充批发价或者阶梯价
        BbbH5GoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new BbbH5GoodsInfoDTO.WholesalePriceDTO();
        wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
        wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
        wholesalePriceDTO.setIsSingle(goodsInfo.getIsSingle());
        wholesalePriceDTO.setShopId(goodsInfo.getShopId());
        wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
        wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSalePrice());
        wholesalePriceDTO.setGoodsId(goodsInfo.getId());
        wholesalePriceDTO.setSkuId(skuGoodInfo.getCategoryId());
        wholesalePriceDTO.setSkuStepPrice(getGoodsStepPriceVOs(skuGoodInfo.getStepPrice()));
        wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
        BbbH5GoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
        if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())){
            serviceVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
        }else {
            serviceVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
        }
        return serviceVO;
    }

    @Override
    public List<BbbH5GoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList,BaseDTO dto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_state",GoodsStateEnum.已上架.getCode());
        wrapper.in("id",goodsIdList);
        List<GoodsInfo> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        List<BbbH5GoodsInfoVO.HomeInnerServiceVO> homeInnerServiceVOS = list.stream()
                .map(e ->{
                    BbbH5GoodsInfoVO.HomeInnerServiceVO homeInnerServiceVO = new BbbH5GoodsInfoVO.HomeInnerServiceVO();
                    BeanUtils.copyProperties(e,homeInnerServiceVO);
                    homeInnerServiceVO.setGoodsId(e.getId());

                    BbbH5SkuGoodInfoVO.SkuVO skuVO = getSku(e,dto);
                    homeInnerServiceVO.setGoodsImage(getImage(e.getGoodsImage()));
                    homeInnerServiceVO.setSkuId(skuVO.getSkuId());

                    if (e.getIsSingle().equals(SingleStateEnum.单品.getCode())){
                        homeInnerServiceVO.setSingleGoodsStockNum(skuVO.getSkuStock());
                    }
                    if (e.getIsSingle().equals(SingleStateEnum.多规格.getCode())){
                        homeInnerServiceVO.setSkuVO(skuVO);
                    }
                    //填充批发价
                    if (ObjectUtils.isNotEmpty(skuVO.getWholesalePrice())){
                       homeInnerServiceVO.setWholesalePrice(skuVO.getWholesalePrice());
                    }
                    //TODO 月销量
                    return homeInnerServiceVO;
                }).collect(toList());
        return homeInnerServiceVOS;
    }

    @Override
    public BbbH5GoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId) {
        QueryWrapper<SkuGoodInfo> skuGoodInfoQueryWrapper = MybatisPlusUtil.query();
        skuGoodInfoQueryWrapper.eq("id",skuId);
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(skuGoodInfoQueryWrapper);
        if (skuGoodInfo == null){
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id",skuGoodInfo.getGoodId());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (goodsInfo == null){
            return null;
        }
        BbbH5GoodsInfoVO.InnerServiceVO serviceVO = new BbbH5GoodsInfoVO.InnerServiceVO();
        BeanUtils.copyProperties(goodsInfo,serviceVO);
        serviceVO.setGoodsId(goodsInfo.getId());
        serviceVO.setSkuId(skuId);
        //判断是否是单品
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()){

            serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
            serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage()))?"":getImage(goodsInfo.getGoodsImage()));
        }else {
            serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
            serviceVO.setGoodsImage(skuGoodInfo.getImage());
            serviceVO.setSkuSpecKey(skuGoodInfo.getSpecsKey());
            serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue())?"":skuGoodInfo.getSpecsValue());
            serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
        }
        return serviceVO;
    }


    private BbbH5GoodsInfoVO.GoodsShopDetailVO shopDetailVO(String shopId){
        BbbH5ShopQTO.InnerShopQTO qto = new BbbH5ShopQTO.InnerShopQTO();
        qto.setShopId(shopId);
        BbbH5ShopVO.InnerDetailVO innerServiceDetailVO = bbbH5ShopRpc.innerDetailShop(qto);
        BbbH5GoodsInfoVO.GoodsShopDetailVO shopDetailVO = new BbbH5GoodsInfoVO.GoodsShopDetailVO();
        if (innerServiceDetailVO != null){
            shopDetailVO.setShopId(innerServiceDetailVO.getId());
            shopDetailVO.setShopLogo(StringUtils.isEmpty(innerServiceDetailVO.getShopLogo())?"":innerServiceDetailVO.getShopLogo());
            shopDetailVO.setShopDescribe(StringUtils.isEmpty(innerServiceDetailVO.getShopDesc())?"":innerServiceDetailVO.getShopDesc());
            shopDetailVO.setShopName(StringUtils.isEmpty(innerServiceDetailVO.getShopName())?"":innerServiceDetailVO.getShopName());
            shopDetailVO.setShopScore(innerServiceDetailVO.getShopScore());
        }
        return shopDetailVO;
    }

    private List<BbbH5GoodsInfoVO.GoodsListVO> getGoodsList2(List<GoodsInfo> goodsInfos,BaseDTO dto,Integer orderType,Integer orderWay){
        List<BbbH5GoodsInfoVO.GoodsListVO> goodsListVOS = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();

        for (GoodsInfo e : goodsInfos){
            BbbH5GoodsInfoVO.GoodsListVO goodsVO = new BbbH5GoodsInfoVO.GoodsListVO();
            BeanUtils.copyProperties(e,goodsVO);
            goodsVO.setGoodsId(e.getId());
            //填充商品图片
            goodsVO.setGoodsImage(getImage(e.getGoodsImage()));

            BbbH5SkuGoodInfoVO.SkuVO skuGoodInfo = getSku(e,dto);
            goodsVO.setSkuId(skuGoodInfo.getSkuId());
            if (e.getIsSingle().equals(SingleStateEnum.单品.getCode())){
                //填充单规格库存
                goodsVO.setSingleSkuStock(skuGoodInfo.getSkuStock());
            }else {
                //填充默认的sku信息
                goodsVO.setSkuVO(skuGoodInfo);
            }
            //填充批发价
            BbbH5GoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new BbbH5GoodsInfoDTO.WholesalePriceDTO();
            wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
            wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
            wholesalePriceDTO.setShopId(e.getShopId());
            wholesalePriceDTO.setIsSingle(e.getIsSingle());
            wholesalePriceDTO.setSalePrice(e.getSalePrice());
            wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSkuSalePrice());
            wholesalePriceDTO.setGoodsId(e.getId());
            wholesalePriceDTO.setSkuId(skuGoodInfo.getSkuId());
            wholesalePriceDTO.setSkuStepPrice(skuGoodInfo.getSkuStepPrice());
            wholesalePriceDTO.setStepPrice(e.getStepPrice());
            BbbH5GoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
            if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())){
                goodsVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
            }
            //TODO 月销量

            goodsListVOS.add(goodsVO);
            goodsIdList.add(e.getId());
        }
        //如果排序类型为销量或评分
        if (ObjectUtils.isNotEmpty(orderType) && !orderType.equals(OrderByConditionEnum.价格.getCode())){
            List<BbbH5TradeListVO.InnerGoodsCompareTo> compareTos = getAfterOrderGooodsId(orderType,orderWay,goodsIdList);
            for (BbbH5GoodsInfoVO.GoodsListVO listVO : goodsListVOS){
                for (BbbH5TradeListVO.InnerGoodsCompareTo vo : compareTos){
                    if (listVO.getGoodsId().equals(vo.getId())){
                        listVO.setIdx(vo.getIdx());
                        break;
                    }
                }
            }
            goodsListVOS.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
        }
        return goodsListVOS;
    }

    private BbbH5SkuGoodInfoVO.SkuVO getSku(GoodsInfo goodsInfo,BaseDTO dto){
        QueryWrapper<SkuGoodInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("good_id",goodsInfo.getId());
        wrapper.orderByAsc("sale_price","id");
        wrapper.last("LIMIT 0,1");
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(wrapper);
        if (null == skuGoodInfo){
            throw new BusinessException("商品已下架或者商品不存在！！");
        }
        BbbH5SkuGoodInfoVO.SkuVO skuVO = new BbbH5SkuGoodInfoVO.SkuVO();
        BeanUtils.copyProperties(skuGoodInfo,skuVO);
        skuVO.setSkuId(skuGoodInfo.getId());
        //填充sku库存
        skuVO.setSkuStock(getSkuStockNum(goodsInfo.getShopId(),skuGoodInfo.getId()));

        BbbH5GoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new BbbH5GoodsInfoDTO.WholesalePriceDTO();
        wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
        wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
        wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
        wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
        wholesalePriceDTO.setIsSingle(SingleStateEnum.单品.getCode());
        wholesalePriceDTO.setGoodsId(goodsInfo.getId());
        wholesalePriceDTO.setShopId(goodsInfo.getShopId());
        wholesalePriceDTO.setSkuId(skuGoodInfo.getId());

        if (ObjectUtils.isNotEmpty(skuGoodInfo.getSpecsKey())){
            //sku售价
            skuVO.setSkuSalePrice(skuGoodInfo.getSalePrice());
            skuVO.setSkuOldPrice(skuGoodInfo.getOldPrice());
            skuVO.setSkuImg(skuGoodInfo.getImage());
            //填充批发价或阶梯价
            wholesalePriceDTO.setIsSingle(SingleStateEnum.多规格.getCode());
            wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSalePrice());
            wholesalePriceDTO.setSkuStepPrice(getGoodsStepPriceVOs(skuGoodInfo.getStepPrice()));
        }
        BbbH5GoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
        if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())){
            skuVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
        }else {
            skuVO.setSkuStepPrice(wholesalePriceVO.getStepPriceVOS());
        }
        return skuVO;
    }

    private  String getImage(String images){
        if (images !=null){
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)){
                return null;
            }
            JSONObject obj = arr.getJSONObject(0);
            String imgUrl = obj.getString("imgSrc");
            return imgUrl;
        }
        return null;
    }

    private PCBbbMerchantUserTypeVO.DetailsVO get2BUserPrivateUserInfo(String userId, String shopId){
        BbbUserVO.PrivateUserInfoVO userInfoVO = userRpc.oneShopPrivateUserInfo(shopId,userId);
        if (ObjectUtils.isEmpty(userInfoVO)){
            return null;
        }
        PCBbbMerchantUserTypeVO.DetailsVO ratioGoodsVO = userInfoVO.getDetailsVO();
        if (null == ratioGoodsVO){
            return null;
        }
        return ratioGoodsVO;
    }

    private BbbH5GoodsInfoVO.WholesalePriceVO getWholesalePriceVO(BbbH5GoodsInfoDTO.WholesalePriceDTO dto){

        BbbH5GoodsInfoVO.WholesalePriceVO wholesalePriceVO = new BbbH5GoodsInfoVO.WholesalePriceVO();

        if (ObjectUtils.isNotEmpty(dto.getJwtUserId()) && ObjectUtils.isNotEmpty(dto.getJwtUserType()) && dto.getJwtUserType().equals(UserTypeEnum._2B用户.getCode())){
            PCBbbMerchantUserTypeVO.DetailsVO detailsVO = get2BUserPrivateUserInfo(dto.getJwtUserId(),dto.getShopId());
            if (ObjectUtils.isNotEmpty(detailsVO) && ObjectUtils.isNotEmpty(detailsVO.getRatioGoodsVOS())){
                for (PCBbbMerchantUserTypeVO.RatioGoodsVO ratioGoodsVO : detailsVO.getRatioGoodsVOS()){
                    if (dto.getGoodsId().equals(ratioGoodsVO.getGoodsId()) && dto.getSkuId().equals(ratioGoodsVO.getSkuId())){
                        BigDecimal wholesalePrice = null;
                        if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())){
                            wholesalePrice = dto.getSkuSalePrice().multiply(detailsVO.getRatio());
                        }else {
                            wholesalePrice = dto.getSalePrice().multiply(detailsVO.getRatio());
                        }
                        wholesalePriceVO.setWholesalePrice(wholesalePrice);
                        break;
                    }else {
                        if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())){
                            wholesalePriceVO.setStepPriceVOS(dto.getSkuStepPrice());
                        }else {
                            wholesalePriceVO.setStepPriceVOS(getGoodsStepPriceVOs(dto.getStepPrice()));
                        }
                    }
                }

            }
            if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())){
                wholesalePriceVO.setStepPriceVOS(dto.getSkuStepPrice());
            }else {
                wholesalePriceVO.setStepPriceVOS(getGoodsStepPriceVOs(dto.getStepPrice()));
            }
        }
        return wholesalePriceVO;
    }

    private Integer getSkuStockNum(String shopId,String skuId){
        CommonStockVO.InnerStockVO stockVO = commonStock.queryStock(new BaseDTO(),shopId,skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null){
            return  stockVO.getQuantity();
        }
        return 0;
    }

    private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> getGoodsStepPriceVOs(String stepPrice){
        if (StringUtils.isNotBlank(stepPrice) && !"\"\"".equals(stepPrice)){
            JSONArray arr = JSONArray.parseArray(stepPrice);
            if (ObjectUtils.isEmpty(arr)){
                return new ArrayList<>();
            }
            List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPriceVOS = new ArrayList<>();
            for (int i =0; i<arr.size();i++){
                BbbH5GoodsInfoVO.GoodsStepPriceVO stepPriceVO = new BbbH5GoodsInfoVO.GoodsStepPriceVO();
                JSONObject jsonObject = arr.getJSONObject(i);
                BigDecimal price = jsonObject.getBigDecimal("price");
                Integer startNum = jsonObject.getInteger("startNum");
                Integer endNum = jsonObject.getInteger("endNum");
                stepPriceVO.setStartNum(startNum);
                stepPriceVO.setEndNum(endNum);
                stepPriceVO.setStepPrice(price);

                stepPriceVOS.add(stepPriceVO);
            }
            return stepPriceVOS;
        }
        return new ArrayList<>();
    }

    private  List<BbbH5TradeListVO.InnerGoodsCompareTo> getAfterOrderGooodsId(Integer orderType, Integer orderWays, List<String> goodsIdList) {
        BbbH5TradeDTO.innerCommpareTo dto = new BbbH5TradeDTO.innerCommpareTo();
        dto.setCompareToMode(orderWays);
        dto.setCompareToType(orderType);
        dto.setGoodsIds(goodsIdList);
        List<BbbH5TradeListVO.InnerGoodsCompareTo> compareTos = tradeRpc.innerCommpareTo(dto);
        return compareTos;
    }

    private List<String> getCategoryIds(String level1CategoryId){
        List<String> categoryList = new ArrayList<>();
        List<String> categories = categoryMapper.selectLevel3CategoryList(level1CategoryId);
        if (ObjectUtils.isNotEmpty(categories)){
            categoryList.addAll(categories);
        }
        return categoryList;
    }

}
