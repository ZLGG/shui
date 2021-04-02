package com.gs.lshly.biz.support.commodity.service.bbb.pc.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.entity.SkuGoodInfo;
import com.gs.lshly.biz.support.commodity.mapper.GoodsInfoMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsCategoryRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.repository.ISkuGoodInfoRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsInfoService;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsLabelService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbSkuGoodInfoVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.common.utils.QRCodeUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesGoodsRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserPrivateUserRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @Author Starry
 * @Date 18:15 2020/11/25
 */
@Component
public class PCBbbGoodsInfoServiceImpl implements IPCBbbGoodsInfoService {

    @Autowired
    private IGoodsInfoRepository repository;
    @Autowired
    private IPCBbbGoodsLabelService goodsLabelService;
    @Autowired
    private IGoodsCategoryRepository categoryRepository;
    @Autowired
    private ISkuGoodInfoRepository skuGoodInfoRepository;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @DubboReference
    private ICommonShopRpc commonShopRpc;
    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private IBbbPcStockAddressRpc bbbPcStockAddressRpc;
    @DubboReference
    private IBbbUserFavoritesGoodsRpc bbbUserFavoritesGoodsRpc;
    @DubboReference
    private IBbbShopRpc bbbShopRpc;
    @DubboReference
    private IBbbUserRpc userRpc;
    @DubboReference
    private IPCBbbUserPrivateUserRpc userPrivateUserRpc;
    @DubboReference
    private IBbbPcTradeRpc tradeRpc;
    @Value("${wx.url}")
    private String WXUrl;

    @Override
    public PCBbbGoodsInfoVO.GoodsRecommendVO getRecommendGoodsList(PCBbbGoodsInfoQTO.QTO qto) {
        //获取推荐的商品id列表
        List<String> goodsIdList = goodsLabelService.listGoodsId();
        if (ObjectUtils.isEmpty(goodsIdList)) {
            return null;
        }
        QueryWrapper<GoodsInfo> boost = MybatisPlusUtil.query();
        boost.ne("use_platform", GoodsUsePlatformEnums.C商城.getCode());
        boost.eq("goods_state", GoodsStateEnum.已上架.getCode());
        boost.in("id", goodsIdList);
        //获取2B商城的商品
        List<GoodsInfo> goodsInfos = repository.list(boost);
        BaseDTO dto = new BaseDTO();
        BeanUtils.copyProperties(qto, dto);
        List<PCBbbGoodsInfoVO.GoodsListVO> goodsListVOS = getGoodsListVOS(goodsInfos, dto);

        PCBbbGoodsInfoVO.GoodsRecommendVO goodsRecommendVO = new PCBbbGoodsInfoVO.GoodsRecommendVO();
        goodsRecommendVO.setGoodsListVOS(goodsListVOS);
        return goodsRecommendVO;

    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageGoodsDetailVO(PCBbbGoodsInfoQTO.GoodsSearchQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getLevel2CategoryId())) {
            //获取该类目下的三级类目
            List<String> idList = getLevel3CategoryIdList(qto.getLevel2CategoryId());
            if (ObjectUtils.isEmpty(idList)) {
                return new PageData<>();
            }
            wrapper.in("category_id", idList);
        }
        if (ObjectUtils.isNotEmpty(qto.getLevel3CategoryId())) {
            wrapper.in("category_id", qto.getLevel3CategoryId());
        }
        if (ObjectUtils.isNotEmpty(qto.getBrandId())) {
            wrapper.in("brand_id", qto.getBrandId());
        }
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties()) && qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode())) {
            wrapper.orderByAsc("sale_price", "id");
        }
        wrapper.ne("use_platform", GoodsUsePlatformEnums.C商城.getCode());
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page, wrapper);
        if (ObjectUtils.isNotEmpty(qto.getHasStock()) && qto.getHasStock().equals(10)) {
            List<String> checkIdList = ListUtil.getIdList(GoodsInfo.class, page.getRecords());
            checkIdList = commonStockRpc.innerListGoodsNotEmpytStock(checkIdList);
            for (GoodsInfo goodsInfo : goodsInfoIPage.getRecords()) {
                if (!checkIdList.contains(goodsInfo.getId())) {
                    goodsInfoIPage.getRecords().remove(goodsInfo);
                }
            }
        }
        if (ObjectUtils.isEmpty(goodsInfoIPage)) {
            return new PageData<>();
        }
        List<PCBbbGoodsInfoVO.GoodsDetailListVO> listVOList = getPageInfo(goodsInfoIPage, qto,qto.getOrderByProperties(),OrderByTypeEnum.降序.getCode());
        return new PageData<>(listVOList, qto.getPageNum(), qto.getPageSize(), goodsInfoIPage.getTotal());
    }

    @Override
    public PCBbbGoodsInfoVO.GoodsDetailVO getGoodsDetailVO(PCBbbGoodsInfoDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto)) {
            throw new BusinessException("参数异常，商品id为空！");
        }
        //查询商品信息
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("id", dto.getId());
        GoodsInfo goodsInfo = repository.getOne(wrapper);
        if (ObjectUtils.isEmpty(goodsInfo)) {
            throw new BusinessException("商品查询数据异常！！");
        }
        PCBbbGoodsInfoVO.GoodsDetailVO goodsDetailVO = new PCBbbGoodsInfoVO.GoodsDetailVO();
        BeanUtils.copyProperties(goodsInfo, goodsDetailVO);
        goodsDetailVO.setGoodsId(goodsInfo.getId());
        //TODO 填充商品评分
        BbbTradeListVO.InnerGoodsScore innerGoodsScore = tradeRpc.innerShopScore(goodsInfo.getShopId(), goodsInfo.getId());
        //填充店铺信息
        BbbShopVO.ShopScoreDetailVO shopScoreDetailVO = bbbShopRpc.innerShopScoreDetailVO(goodsInfo.getShopId(), dto);
        shopScoreDetailVO.setGoodsScore(innerGoodsScore.getGoodsGrade()).
                setDeliveryScore(innerGoodsScore.getDeliveryGrade()).
                setServiceScore(innerGoodsScore.getServiceGrade()).setGoodsScoreNum(innerGoodsScore.getCommentCount());
        goodsDetailVO.setShopInfo(shopScoreDetailVO);
        //商品收藏状态
        if (StringUtils.isNotBlank(dto.getJwtUserId())) {
            Integer favoritesState = bbbUserFavoritesGoodsRpc.innerFavoritesState(goodsInfo.getId(), dto.getJwtUserId());
            if (null != favoritesState) {
                goodsDetailVO.setFavoriteState(favoritesState);
                ;
            }
        }

        List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = getSkuList(goodsInfo, dto);
        goodsDetailVO.setSkuId(skuDetailListVOS.get(0).getId());
        List<String> imagesList = new ArrayList<>();
        if (goodsInfo.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
            //填充sku规格列表
            goodsDetailVO.setSkuDetailListVOS(skuDetailListVOS);
            //填充默认的sku规格数据
            goodsDetailVO.setDefaultSkuInfo(skuDetailListVOS.get(0));

            goodsDetailVO.setSalePrice(skuDetailListVOS.get(0).getSalePrice());

            //获取所有的规格图片
            List<String> skuImgs = skuDetailListVOS.stream()
                    .filter(e -> StringUtils.isNotBlank(e.getImage()))
                    .map(PCBbbSkuGoodInfoVO.SkuDetailListVO::getImage)
                    .collect(Collectors.toList());

            imagesList.addAll(skuImgs);
        }

        //填充默认批发价
        PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
        wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
        wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
        wholesalePriceDTO.setShopId(goodsInfo.getShopId());
        wholesalePriceDTO.setIsSingle(goodsInfo.getIsSingle());
        wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
        wholesalePriceDTO.setSkuSalePrice(skuDetailListVOS.get(0).getSalePrice());
        wholesalePriceDTO.setGoodsId(goodsInfo.getId());
        wholesalePriceDTO.setSkuId(skuDetailListVOS.get(0).getId());
        wholesalePriceDTO.setSkuStepPrice(skuDetailListVOS.get(0).getSkuStepPrice());
        wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
        PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
        if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
            goodsDetailVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
        } else {
            goodsDetailVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
        }

        //填充商品图片
        List<String> goodsImgList = getImageList(goodsInfo.getGoodsImage());
        imagesList.addAll(goodsImgList);
        goodsDetailVO.setImagesList(imagesList);

        //填充单规格库存
        if (goodsInfo.getIsSingle().equals(SingleStateEnum.单品.getCode())) {
            goodsDetailVO.setSingleGoodsStock(getSkuStockNum(goodsInfo.getShopId(), skuDetailListVOS.get(0).getId()));
        }
        String tmpFileName = StrUtil.uuid();
        try {
            QRCodeUtil.genAndSaveFile(WXUrl+"?id="+dto.getId(), 300, 300, tmpFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        goodsDetailVO.setCode(tmpFileName);
        return goodsDetailVO;
    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsListVO> getSearchGoods(PCBbbGoodsInfoQTO.SearchByGoodsNameQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getGoodsName())) {
            wrapper.like("goods_name", qto.getGoodsName());
        }
        if (StringUtils.isNotBlank(qto.getMerchantId())) {
            wrapper.eq("merchant_id", qto.getMerchantId());
        }
        if (StringUtils.isNotBlank(qto.getShopId())) {
            wrapper.eq("shop_id", qto.getShopId());
        }
        wrapper.eq("goods_state",GoodsStateEnum.已上架.getCode());
        wrapper.ne("use_platform", GoodsUsePlatformEnums.C商城.getCode());
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = repository.page(page, wrapper);
        if (ObjectUtils.isEmpty(goodsInfoIPage)) {
            return new PageData<>();
        }
        BaseDTO dto = new BaseDTO();
        BeanUtils.copyProperties(qto, dto);
        List<PCBbbGoodsInfoVO.GoodsListVO> goodsListVOS = getGoodsListVOS(goodsInfoIPage.getRecords(), dto);
        return new PageData<>(goodsListVOS, qto.getPageNum(), qto.getPageSize(), goodsInfoIPage.getTotal());
    }

    @Override
    public PageData<PCBbbGoodsInfoVO.GoodsDetailListVO> pageShopNavigationGoodsVO(PCBbbGoodsInfoQTO.ShopNavigationIdQTO qto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        if (StringUtils.isNotBlank(qto.getShopNavigationId())) {
            List<String> navigationIds = listShopNavigationIds(qto.getShopNavigationId());
            if (ObjectUtils.isEmpty(navigationIds)){
                return new PageData<>();
            }
            wrapper.in("gsn.shop_navigation", navigationIds);
        }
        if (StringUtils.isNotBlank(qto.getShopId())) {
            wrapper.eq("gs.shop_id", qto.getShopId());
        }
        if (StringUtils.isNotEmpty(qto.getGoodsName())) {
            wrapper.like("gs.goods_name", qto.getGoodsName());
        }
        wrapper.eq("gs.goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.ne("gs.use_platform", GoodsUsePlatformEnums.C商城.getCode());
        if (ObjectUtils.isNotEmpty(qto.getOrderByProperties())) {
            if (qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode())
                    && (ObjectUtils.isEmpty(qto.getOrderByType()) || qto.getOrderByType().equals(OrderByTypeEnum.升序.getCode()))) {
                wrapper.orderByAsc("gs.sale_price", "gs.id");
            }
            if (qto.getOrderByProperties().equals(OrderByConditionEnum.价格.getCode())
                    && (ObjectUtils.isNotEmpty(qto.getOrderByType()) && qto.getOrderByType().equals(OrderByTypeEnum.降序.getCode()))) {
                wrapper.orderByDesc("gs.sale_price", "gs.id");
            }
        }
        IPage<GoodsInfo> page = MybatisPlusUtil.pager(qto);
        IPage<GoodsInfo> goodsInfoIPage = goodsInfoMapper.getGoodsPageInfo(page, wrapper);
        if (ObjectUtils.isEmpty(goodsInfoIPage)) {
            return new PageData<>();
        }
        List<PCBbbGoodsInfoVO.GoodsDetailListVO> listVOList = getPageInfo(goodsInfoIPage, qto,qto.getOrderByProperties(),OrderByTypeEnum.降序.getCode());

        return new PageData<>(listVOList, qto.getPageNum(), qto.getPageSize(), goodsInfoIPage.getTotal());
    }

    //--------------------------内部服务--------------------

    @Override
    public List<PCBbbGoodsInfoVO.InnerServiceVO> innerServiceVOByIdList(List<String> skuIdList, BaseDTO dto) {
        if (ObjectUtils.isEmpty(skuIdList)) {
            throw new BusinessException("参数不能为空！");
        }
        List<PCBbbGoodsInfoVO.InnerServiceVO> innerServiceVOS = new ArrayList<>();
        for (String skuId : skuIdList) {
            PCBbbGoodsInfoVO.InnerServiceVO serviceVO = null;
            serviceVO = innerServiceVO(skuId, dto);
            if (ObjectUtils.isNotEmpty(serviceVO)) {
                innerServiceVOS.add(serviceVO);
            }
        }
        return innerServiceVOS;
    }

    @Override
    public PCBbbGoodsInfoVO.InnerServiceVO innerServiceVO(String skuId, BaseDTO dto) {
        QueryWrapper<SkuGoodInfo> skuGoodInfoQueryWrapper = MybatisPlusUtil.query();
        skuGoodInfoQueryWrapper.eq("id", skuId);
        skuGoodInfoQueryWrapper.eq("state", GoodsStateEnum.已上架.getCode());
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(skuGoodInfoQueryWrapper);
        if (skuGoodInfo == null) {
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id", skuGoodInfo.getGoodId());
        queryWrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (goodsInfo == null) {
            return null;
        }
        PCBbbGoodsInfoVO.InnerServiceVO serviceVO = new PCBbbGoodsInfoVO.InnerServiceVO();
        BeanUtils.copyProperties(goodsInfo, serviceVO);
        serviceVO.setGoodsId(goodsInfo.getId());
        serviceVO.setSkuId(skuId);
        serviceVO.setGoodsWeight(goodsInfo.getGoodsWeight());

        //判断是否是单品
        if (goodsInfo.getIsSingle().intValue() == SingleStateEnum.单品.getCode().intValue()) {
            serviceVO.setSkuGoodsNo(goodsInfo.getGoodsNo());
            serviceVO.setGoodsImage(ObjectUtils.isEmpty(getImage(goodsInfo.getGoodsImage())) ? "" : getImage(goodsInfo.getGoodsImage()));
        } else {
            serviceVO.setSalePrice(skuGoodInfo.getSalePrice());
            serviceVO.setGoodsImage(skuGoodInfo.getImage());
            serviceVO.setSkuSpecKey(skuGoodInfo.getSpecsKey());
            serviceVO.setSkuSpecValue(StringUtils.isEmpty(skuGoodInfo.getSpecsValue()) ? "" : skuGoodInfo.getSpecsValue());
            serviceVO.setSkuGoodsNo(skuGoodInfo.getSkuGoodsNo());
        }

        //填充批发价或者阶梯价
        PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
        wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
        wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
        wholesalePriceDTO.setIsSingle(goodsInfo.getIsSingle());
        wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
        wholesalePriceDTO.setShopId(goodsInfo.getShopId());
        if (goodsInfo.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
            wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSalePrice());
            wholesalePriceDTO.setSkuStepPrice(getGoodsStepPriceVOs(skuGoodInfo.getStepPrice()));
        }
        wholesalePriceDTO.setGoodsId(goodsInfo.getId());
        wholesalePriceDTO.setSkuId(skuId);
        wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
        PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
        if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
            serviceVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
        } else {
            serviceVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
        }
        return serviceVO;
    }

    @Override
    public PCBbbGoodsInfoVO.GetGoodsStepPriceVO innerGetStepPrice(String skuId) {
        QueryWrapper<SkuGoodInfo> skuGoodInfoQueryWrapper = MybatisPlusUtil.query();
        skuGoodInfoQueryWrapper.eq("id", skuId);
        skuGoodInfoQueryWrapper.eq("state", GoodsStateEnum.已上架.getCode());
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(skuGoodInfoQueryWrapper);
        if (skuGoodInfo == null) {
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id", skuGoodInfo.getGoodId());
        queryWrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (goodsInfo == null) {
            return null;
        }
        PCBbbGoodsInfoVO.GetGoodsStepPriceVO getGoodsStepPriceVO = new PCBbbGoodsInfoVO.GetGoodsStepPriceVO();
        getGoodsStepPriceVO.setGoodsId(goodsInfo.getId());
        getGoodsStepPriceVO.setGoodsName(goodsInfo.getGoodsName());
        getGoodsStepPriceVO.setSkuId(skuGoodInfo.getId());
        getGoodsStepPriceVO.setGoodsStepPriceList(this.getGoodsStepPriceVOs(goodsInfo.getStepPrice()));
        return getGoodsStepPriceVO;
    }

    @Override
    public PCBbbGoodsInfoVO.InnerServiceVO innerSimpleServiceVO(String skuId) {
        QueryWrapper<SkuGoodInfo> skuGoodInfoQueryWrapper = MybatisPlusUtil.query();
        skuGoodInfoQueryWrapper.eq("id", skuId);
        SkuGoodInfo skuGoodInfo = skuGoodInfoRepository.getOne(skuGoodInfoQueryWrapper);
        if (skuGoodInfo == null) {
            return null;
        }
        QueryWrapper<GoodsInfo> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("id", skuGoodInfo.getGoodId());
        GoodsInfo goodsInfo = repository.getOne(queryWrapper);
        if (goodsInfo == null) {
            return null;
        }
        PCBbbGoodsInfoVO.InnerServiceVO serviceVO = new PCBbbGoodsInfoVO.InnerServiceVO();
        BeanUtils.copyProperties(goodsInfo, serviceVO);
        serviceVO.setGoodsId(goodsInfo.getId());
        serviceVO.setSkuId(skuId);
        serviceVO.setPosSpuId(ObjectUtils.isNotEmpty(goodsInfo.getPosSpuId())?goodsInfo.getPosSpuId():"");
        serviceVO.setSQuantity(getSkuStockNum(goodsInfo.getShopId(),skuId));
        return serviceVO;
    }

    @Override
    public List<PCBbbGoodsInfoVO.HomeInnerServiceVO> getHomeGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.in("id", goodsIdList);
        List<GoodsInfo> list = repository.list(wrapper);
        if (ObjectUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<PCBbbGoodsInfoVO.HomeInnerServiceVO> homeInnerServiceVOS = list.stream()
                .map(e -> {
                    PCBbbGoodsInfoVO.HomeInnerServiceVO homeInnerServiceVO = new PCBbbGoodsInfoVO.HomeInnerServiceVO();
                    BeanUtils.copyProperties(e, homeInnerServiceVO);
                    //填充批发价
                    List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = getSkuList(e, dto);
                    PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
                    wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
                    wholesalePriceDTO.setShopId(e.getShopId());
                    wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
                    wholesalePriceDTO.setIsSingle(e.getIsSingle());
                    wholesalePriceDTO.setSalePrice(e.getSalePrice());
                    wholesalePriceDTO.setSkuSalePrice(skuDetailListVOS.get(0).getSalePrice());
                    wholesalePriceDTO.setGoodsId(e.getId());
                    wholesalePriceDTO.setSkuId(skuDetailListVOS.get(0).getId());
                    wholesalePriceDTO.setSkuStepPrice(skuDetailListVOS.get(0).getSkuStepPrice());
                    wholesalePriceDTO.setStepPrice(e.getStepPrice());
                    PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
                    if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
                        homeInnerServiceVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
                    } else {
                        homeInnerServiceVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
                    }
                    homeInnerServiceVO.setGoodsImage(getImage(e.getGoodsImage()));
                    return homeInnerServiceVO;
                }).collect(toList());
        return homeInnerServiceVOS;
    }

    @Override
    public List<PCBbbGoodsInfoVO.ShopInnerServiceVO> getShopGoodsInnerServiceVO(List<String> goodsIdList, BaseDTO dto) {
        QueryWrapper<GoodsInfo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_state", GoodsStateEnum.已上架.getCode());
        wrapper.in("id", goodsIdList);
        List<GoodsInfo> goodsInfos = repository.list(wrapper);
        if (ObjectUtils.isEmpty(goodsInfos)) {
            return new ArrayList<>();
        }
        //要检查是否被用户收藏的商品ID
        List<String> checkFavoritesIdList = new ArrayList<>();
        List<PCBbbGoodsInfoVO.ShopInnerServiceVO> serviceVOS = goodsInfos.parallelStream()
                .map(e -> {
                    checkFavoritesIdList.add(e.getId());
                    PCBbbGoodsInfoVO.ShopInnerServiceVO serviceVO = new PCBbbGoodsInfoVO.ShopInnerServiceVO();
                    BeanUtils.copyProperties(e, serviceVO);
                    //sku列表
                    List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = getSkuList(e, dto);
                    PCBbbSkuGoodInfoVO.SkuDetailListVO skuDetailListVO = skuDetailListVOS.get(0);
                    if (ObjectUtils.isNotEmpty(dto.getJwtUserId()) && dto.getJwtUserType().equals(UserTypeEnum._2B用户.getCode())) {
                        if (ObjectUtils.isNotEmpty(skuDetailListVO.getWholesalePrice())) {
                            serviceVO.setWholesalePrice(skuDetailListVO.getWholesalePrice());
                        }
                        serviceVO.setStepPrice(ObjectUtils.isEmpty(skuDetailListVO.getSkuStepPrice()) ? new ArrayList<>() : skuDetailListVO.getSkuStepPrice());
                    }
                    if (ObjectUtils.isEmpty(dto.getJwtUserId())) {
                        serviceVO.setStepPrice(ObjectUtils.isEmpty(skuDetailListVO.getSkuStepPrice()) ? new ArrayList<>() : skuDetailListVO.getSkuStepPrice());
                    }
                    //单规格商品库存数量
                    if (e.getIsSingle().equals(SingleStateEnum.单品.getCode())) {
                        serviceVO.setSingleGoodsStockNum(getSkuStockNum(e.getShopId(), skuDetailListVO.getId()));
                        serviceVO.setSkuId(skuDetailListVO.getId());
                        serviceVO.setGoodsImage(getImage(e.getGoodsImage()));
                    }
                    if (e.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
                        serviceVO.setSkuDetailListVOS(skuDetailListVOS);
                        serviceVO.setSkuId(skuDetailListVO.getId());
                        serviceVO.setGoodsImage(skuDetailListVO.getImage());
                        serviceVO.setSalePrice(skuDetailListVO.getSalePrice());
                    }
                    return serviceVO;
                }).collect(toList());
        //商品收藏状态
        if (StringUtils.isNotBlank(dto.getJwtUserId())) {
            if (ObjectUtils.isNotEmpty(checkFavoritesIdList)) {
                List<String> favoritesIdList = bbbUserFavoritesGoodsRpc.innerFavoritesListState(checkFavoritesIdList, dto.getJwtUserId());
                for (PCBbbGoodsInfoVO.ShopInnerServiceVO serviceVO : serviceVOS) {
                    if (favoritesIdList.contains(serviceVO.getId())) {
                        serviceVO.setFavoriteState(TrueFalseEnum.是.getCode());
                    }
                }
            }
        }

        return serviceVOS;
    }


    private List<PCBbbGoodsInfoVO.GoodsDetailListVO> getPageInfo(IPage<GoodsInfo> goodsInfoIPage, BaseDTO dto,Integer orderType,Integer orderWay) {
        List<PCBbbGoodsInfoVO.GoodsDetailListVO> listVOList = new ArrayList<>();
        //要检查是否被收藏的商品ID
        List<String> checkFavoritesIdList = new ArrayList<>();
        List<String> goodsIdList = new ArrayList<>();
        for (GoodsInfo goodsInfo : goodsInfoIPage.getRecords()) {
            checkFavoritesIdList.add(goodsInfo.getId());
            PCBbbGoodsInfoVO.GoodsDetailListVO listVO = new PCBbbGoodsInfoVO.GoodsDetailListVO();
            BeanUtils.copyProperties(goodsInfo, listVO);
            listVO.setGoodsId(goodsInfo.getId());
            //填充商品图片
            listVO.setGoodsImage(getImage(goodsInfo.getGoodsImage()));

            //填充店铺名称
            CommonShopVO.SimpleVO simpleVO = shopDetails(goodsInfo.getShopId());
            listVO.setShopName(StringUtils.isEmpty(simpleVO.getShopName()) ? "" : simpleVO.getShopName());

            List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuList = getSkuList(goodsInfo, dto);
            if (goodsInfo.getIsSingle().equals(SingleStateEnum.单品.getCode())) {
                //填充skuId
                listVO.setSkuId(skuList.get(0).getId());
                //填充单规格商品库存
                listVO.setSingleGoodsStock(skuList.get(0).getStockNum());
            }
            if (goodsInfo.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
                //填充sku信息
                listVO.setSkuDetailListVOS(skuList);
            }
            //填充批发价
            PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
            wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
            wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
            wholesalePriceDTO.setIsSingle(goodsInfo.getIsSingle());
            wholesalePriceDTO.setShopId(goodsInfo.getShopId());
            wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
            wholesalePriceDTO.setSkuSalePrice(skuList.get(0).getSalePrice());
            wholesalePriceDTO.setGoodsId(goodsInfo.getId());
            wholesalePriceDTO.setSkuId(skuList.get(0).getId());
            wholesalePriceDTO.setSkuStepPrice(skuList.get(0).getSkuStepPrice());
            wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
            PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
            if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
                listVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
            } else {
                listVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
            }
            goodsIdList.add(goodsInfo.getId());
            listVOList.add(listVO);
        }
        //商品收藏状态
        if (StringUtils.isNotBlank(dto.getJwtUserId())) {
            if (ObjectUtils.isNotEmpty(checkFavoritesIdList)) {
                List<String> favoritesIdList = bbbUserFavoritesGoodsRpc.innerFavoritesListState(checkFavoritesIdList, dto.getJwtUserId());
                for (PCBbbGoodsInfoVO.GoodsDetailListVO goodsDetailListVO : listVOList) {
                    if (favoritesIdList.contains(goodsDetailListVO.getGoodsId())) {
                        goodsDetailListVO.setFavoriteState(TrueFalseEnum.是.getCode());
                    }
                }
            }
        }
        //如果排序类型为销量或评分
        if (ObjectUtils.isNotEmpty(orderType) && !orderType.equals(OrderByConditionEnum.价格.getCode())){
            List<BbbTradeListVO.InnerGoodsCompareTo> compareTos = getAfterOrderGooodsId(orderType,orderWay,goodsIdList);
            for (PCBbbGoodsInfoVO.GoodsDetailListVO listVO : listVOList){
                for (BbbTradeListVO.InnerGoodsCompareTo vo : compareTos){
                    if (listVO.getGoodsId().equals(vo.getId())){
                        listVO.setIdx(vo.getIdx());
                        break;
                    }
                }
            }
            listVOList.sort((o1, o2) -> ((Integer)o1.getIdx()) != null && ((Integer)o2.getIdx()) != null ? (o1.getIdx() > o2.getIdx() ? 1 : -1) : -1);
        }
        return listVOList;
    }

    private String getImage(String images) {
        if (StringUtils.isNotBlank(images)) {
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

    private List<String> getImageList(String images) {
        List<String> list = new ArrayList<>();
        if (StringUtils.isNotBlank(images)) {
            JSONArray arr = JSONArray.parseArray(images);
            if (ObjectUtils.isEmpty(arr)) {
                return list;
            }
            for (int i = 0; i<arr.size();i++){
                JSONObject obj = arr.getJSONObject(i);
                String imgUrl = obj.getString("imgSrc");
                list.add(imgUrl);
            }
        }
        return list;
    }

    private List<String> getLevel3CategoryIdList(String level2CategoryId) {
        QueryWrapper<GoodsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", level2CategoryId);
        List<GoodsCategory> categories = categoryRepository.list(wrapper);
        if (ObjectUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        return ListUtil.getIdList(GoodsCategory.class, categories);
    }

    private CommonShopVO.SimpleVO shopDetails(String shopId) {
        CommonShopVO.SimpleVO simpleVO = commonShopRpc.shopDetails(shopId);
        if (ObjectUtils.isEmpty(simpleVO)) {
            throw new BusinessException("店铺数据异常");
        }
        return simpleVO;
    }

    private Integer getSkuStockNum(String shopId, String skuId) {
        CommonStockVO.InnerStockVO stockVO = commonStockRpc.queryStock(new BaseDTO(), shopId, skuId).getData();
        if (stockVO != null && stockVO.getQuantity() != null) {
            return stockVO.getQuantity();
        }
        return 0;
    }

    private List<PCBbbSkuGoodInfoVO.SkuDetailListVO> getSkuList(GoodsInfo goodsInfo, BaseDTO dto) {
        //商品id查询sku列表
        QueryWrapper<SkuGoodInfo> wrapperBoost = MybatisPlusUtil.query();
        wrapperBoost.eq("good_id", goodsInfo.getId());
        wrapperBoost.orderByAsc("sale_price", "id");
        List<SkuGoodInfo> skuGoodInfos = skuGoodInfoRepository.list(wrapperBoost);
        if (ObjectUtils.isEmpty(skuGoodInfos)) {
            throw new BusinessException("数据异常");
        }
        List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuVOS = new ArrayList<>();
        for (SkuGoodInfo skuGoodInfo : skuGoodInfos) {
            PCBbbSkuGoodInfoVO.SkuDetailListVO skuVO = new PCBbbSkuGoodInfoVO.SkuDetailListVO();
            BeanUtils.copyProperties(skuGoodInfo, skuVO);
            //填充sku库存
            skuVO.setStockNum(getSkuStockNum(goodsInfo.getShopId(), skuGoodInfo.getId()));
            //填充sku批发价或者阶梯价
            PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
            wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
            wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
            wholesalePriceDTO.setShopId(goodsInfo.getShopId());
            wholesalePriceDTO.setIsSingle(goodsInfo.getIsSingle());
            wholesalePriceDTO.setSalePrice(goodsInfo.getSalePrice());
            wholesalePriceDTO.setSkuSalePrice(skuGoodInfo.getSalePrice());
            wholesalePriceDTO.setGoodsId(goodsInfo.getId());
            wholesalePriceDTO.setSkuId(skuGoodInfo.getId());
            wholesalePriceDTO.setSkuStepPrice(getGoodsStepPriceVOs(skuGoodInfo.getStepPrice()));
            wholesalePriceDTO.setStepPrice(goodsInfo.getStepPrice());
            PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
            if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
                skuVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
            } else {
                skuVO.setSkuStepPrice(wholesalePriceVO.getStepPriceVOS());
            }

            skuVOS.add(skuVO);
        }
        return skuVOS;
    }

    private PCBbbMerchantUserTypeVO.DetailsVO get2BUserPrivateUserInfo(String userId, String shopId) {
        BbbUserVO.PrivateUserInfoVO userInfoVO = userRpc.oneShopPrivateUserInfo(shopId, userId);
        if (ObjectUtils.isEmpty(userInfoVO)) {
            return null;
        }
        PCBbbMerchantUserTypeVO.DetailsVO ratioGoodsVO = userInfoVO.getDetailsVO();
        if (null == ratioGoodsVO) {
            return null;
        }
        return ratioGoodsVO;
    }

    private List<PCBbbGoodsInfoVO.GoodsListVO> getGoodsListVOS(List<GoodsInfo> goodsInfos, BaseDTO dto) {
        List<PCBbbGoodsInfoVO.GoodsListVO> goodsListVOS = goodsInfos
                .parallelStream().map(e -> {
                    PCBbbGoodsInfoVO.GoodsListVO goodsListVO = new PCBbbGoodsInfoVO.GoodsListVO();
                    BeanUtils.copyProperties(e, goodsListVO);
                    goodsListVO.setGoodsId(e.getId());
                    goodsListVO.setGoodsImage(getImage(e.getGoodsImage()));
                    //批发价与阶梯价的处理
                    List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = getSkuList(e, dto);
                    PCBbbGoodsInfoDTO.WholesalePriceDTO wholesalePriceDTO = new PCBbbGoodsInfoDTO.WholesalePriceDTO();
                    wholesalePriceDTO.setJwtUserId(dto.getJwtUserId());
                    wholesalePriceDTO.setJwtUserType(dto.getJwtUserType());
                    wholesalePriceDTO.setShopId(e.getShopId());
                    wholesalePriceDTO.setIsSingle(e.getIsSingle());
                    wholesalePriceDTO.setSalePrice(e.getSalePrice());
                    wholesalePriceDTO.setSkuSalePrice(skuDetailListVOS.get(0).getSalePrice());
                    wholesalePriceDTO.setGoodsId(e.getId());
                    wholesalePriceDTO.setSkuId(skuDetailListVOS.get(0).getId());
                    wholesalePriceDTO.setSkuStepPrice(skuDetailListVOS.get(0).getSkuStepPrice());
                    wholesalePriceDTO.setStepPrice(e.getStepPrice());
                    PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = getWholesalePriceVO(wholesalePriceDTO);
                    if (ObjectUtils.isNotEmpty(wholesalePriceVO.getWholesalePrice())) {
                        goodsListVO.setWholesalePrice(wholesalePriceVO.getWholesalePrice());
                    } else {
                        goodsListVO.setStepPrice(wholesalePriceVO.getStepPriceVOS());
                    }
                    return goodsListVO;
                }).collect(Collectors.toList());
        return goodsListVOS;
    }

    private List<PCBbbGoodsInfoVO.GoodsStepPriceVO> getGoodsStepPriceVOs(String stepPrice) {
        if (StringUtils.isNotBlank(stepPrice) && !"\"\"".equals(stepPrice)) {
            JSONArray arr = JSONArray.parseArray(stepPrice);
            if (ObjectUtils.isEmpty(arr)) {
                return new ArrayList<>();
            }
            List<PCBbbGoodsInfoVO.GoodsStepPriceVO> stepPriceVOS = new ArrayList<>();
            for (int i = 0; i < arr.size(); i++) {
                PCBbbGoodsInfoVO.GoodsStepPriceVO stepPriceVO = new PCBbbGoodsInfoVO.GoodsStepPriceVO();
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

    private PCBbbGoodsInfoVO.WholesalePriceVO getWholesalePriceVO(PCBbbGoodsInfoDTO.WholesalePriceDTO dto) {

        PCBbbGoodsInfoVO.WholesalePriceVO wholesalePriceVO = new PCBbbGoodsInfoVO.WholesalePriceVO();

        //获取用户类型
        BbbUserVO.InnerUserInfoVO userInfoVO = userRpc.innerUserVo(dto);
        if (null == userInfoVO){
            wholesalePriceVO.setStepPriceVOS(new ArrayList<>());
            return wholesalePriceVO;
        }
        Integer userType = userInfoVO.getType();

        if (ObjectUtils.isNotEmpty(dto.getJwtUserId()) && ObjectUtils.isNotEmpty(userType) && userType.equals(UserTypeEnum._2B用户.getCode())) {
            PCBbbMerchantUserTypeVO.DetailsVO detailsVO = get2BUserPrivateUserInfo(dto.getJwtUserId(), dto.getShopId());
            if (ObjectUtils.isNotEmpty(detailsVO) && ObjectUtils.isNotEmpty(detailsVO.getRatioGoodsVOS())) {
                for (PCBbbMerchantUserTypeVO.RatioGoodsVO ratioGoodsVO : detailsVO.getRatioGoodsVOS()) {
                    if (dto.getGoodsId().equals(ratioGoodsVO.getGoodsId()) && dto.getSkuId().equals(ratioGoodsVO.getSkuId())) {
                        BigDecimal wholesalePrice = null;
                        if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
                            wholesalePrice = dto.getSkuSalePrice().multiply(detailsVO.getRatio());
                        } else {
                            wholesalePrice = dto.getSalePrice().multiply(detailsVO.getRatio());
                        }
                        wholesalePriceVO.setWholesalePrice(wholesalePrice);
                        break;
                    } else {
                        if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
                            wholesalePriceVO.setStepPriceVOS(dto.getSkuStepPrice());
                        } else {
                            wholesalePriceVO.setStepPriceVOS(getGoodsStepPriceVOs(dto.getStepPrice()));
                        }
                    }
                }

            }
            if (dto.getIsSingle().equals(SingleStateEnum.多规格.getCode())) {
                wholesalePriceVO.setStepPriceVOS(dto.getSkuStepPrice());
            } else {
                wholesalePriceVO.setStepPriceVOS(getGoodsStepPriceVOs(dto.getStepPrice()));
            }
        }
        return wholesalePriceVO;
    }

    private  List<BbbTradeListVO.InnerGoodsCompareTo> getAfterOrderGooodsId(Integer orderType, Integer orderWays, List<String> goodsIdList) {
        BbbOrderDTO.innerCommpareTo dto = new BbbOrderDTO.innerCommpareTo();
        dto.setCompareToMode(orderWays);
        dto.setCompareToType(orderType);
        dto.setGoodsIds(goodsIdList);
        List<BbbTradeListVO.InnerGoodsCompareTo> compareTos = tradeRpc.innerCommpareTo(dto);
        return compareTos;
    }

    private List<String> listShopNavigationIds(String navigationId){
        List<String> shopNavigationIds = bbbShopRpc.innerGetNavigationList(navigationId);
        return shopNavigationIds;
    }
}
