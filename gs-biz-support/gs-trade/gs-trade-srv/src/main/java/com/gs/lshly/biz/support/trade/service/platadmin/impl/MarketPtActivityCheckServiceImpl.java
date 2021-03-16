package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import com.alibaba.druid.sql.ast.statement.SQLForeignKeyImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSku;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsSpu;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityMerchant;
import com.gs.lshly.biz.support.trade.enums.MarketPtActivityCheckStatusEnum;
import com.gs.lshly.biz.support.trade.entity.MarketPtActivityGoodsCategory;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.platadmin.IMarketPtActivityCheckService;
import com.gs.lshly.common.enums.ActivitySignEnum;
import com.gs.lshly.common.enums.ActivitySignOperationEnum;
import com.gs.lshly.common.enums.ShopTypeEnum;
import com.gs.lshly.common.enums.SkuActivityPriceEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMarketPtActivityMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtActivityMerchantVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Component
public class MarketPtActivityCheckServiceImpl implements IMarketPtActivityCheckService {
    //活动记录表
    @Autowired
    private IMarketPtActivityMerchantRepository iMarketPtActivityMerchantRepository;
    //活动表
    @Autowired
    private IMarketPtActivityRepository iMarketPtActivityRepository;
    //活动类目表
    @Autowired
    private IMarketPtActivityGoodsCategoryRepository iMarketPtActivityGoodsCategoryRepository;
    //商家报名商品
    @Autowired
    private IMarketPtActivityGoodsSpuRepository iMarketPtActivityGoodsSpuRepository;
    //商品sku报名表
    @Autowired
    private IMarketPtActivityGoodsSkuRepository iMarketPtActivityGoodsSkuRepository;
    //商品表
    @DubboReference
    private IGoodsInfoRpc iGoodsInfoRpc;
    //店铺表
    @DubboReference
    private IPCMerchShopRpc shopRpc;
    @DubboReference
    private IGoodsCategoryRpc iGoodsCategoryRpc;
    @Override
    public PageData<PCMerchMarketPtActivityMerchantVO.platformCheck> noChcekList(MarketPtActivityQTO.QTO qto) {
        List<MarketPtActivityMerchant> list = iMarketPtActivityMerchantRepository.list();
        List<PCMerchMarketPtActivityMerchantVO.platformCheck> platformChecks = new ArrayList<>();
        for (MarketPtActivityMerchant marketPtActivityMerchant:list){
            PCMerchMarketPtActivityMerchantVO.platformCheck platformCheck = new PCMerchMarketPtActivityMerchantVO.platformCheck();
            MarketPtActivity marketPtActivity = iMarketPtActivityRepository.getById(marketPtActivityMerchant.getActivityId());
            if (ObjectUtils.isNotEmpty(marketPtActivity)){
                platformCheck.setSignEndTime(marketPtActivity.getSignEndTime()).setSignStartTime(marketPtActivity.getSignStartTime());
            }
            Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long endTime=marketPtActivityMerchant.getActivityEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
             //获取店铺信息

            PCMerchShopVO.DetailVO detailVO = shopRpc.detailShop(new BaseDTO().setJwtShopId(marketPtActivityMerchant.getShopId()));
            if (ObjectUtils.isNotEmpty(detailVO)){
                platformCheck. setShopName(detailVO.getShopName()+"("+ ShopTypeEnum.findRemark(detailVO.getShopType())+")");
            }
            platformCheck.setId(marketPtActivityMerchant.getId()).
                    setActivityName(marketPtActivityMerchant.getName()).
                    setCheck(marketPtActivityMerchant.getState()).
                    setUdata(marketPtActivityMerchant.getUdate()).
                    setOperation(now > endTime ? marketPtActivityMerchant.getState(): ActivitySignOperationEnum.无法审核.getCode().toString()).
                    setState("10");
            switch (qto.getCheck()){
                case "10":
                    System.out.println("10");if (marketPtActivityMerchant.getState().equals(MarketPtActivityCheckStatusEnum.未审核.getCode().toString())){platformChecks.add(platformCheck);}break;
                case "20":
                    System.out.println("20");if (marketPtActivityMerchant.getState().equals(MarketPtActivityCheckStatusEnum.审核通过.getCode().toString())){platformChecks.add(platformCheck);}break;
                case "30":
                    System.out.println("30");if (marketPtActivityMerchant.getState().equals(MarketPtActivityCheckStatusEnum.审核未通过.getCode().toString())){platformChecks.add(platformCheck);}break;
                default:
                    System.out.println("其它");platformChecks.add(platformCheck);
            }
        }
        return new PageData(platformChecks, qto.getPageNum(), qto.getPageSize(), platformChecks.size());
    }

    @Override
    public PCMerchMarketPtActivityMerchantVO.platformCheckView checkView(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        //通过活动记录Id获取活动记录
        MarketPtActivityMerchant activityMerchant = iMarketPtActivityMerchantRepository.getById(dto.getId());
        //通过活动ID获取活动
        MarketPtActivity activity = iMarketPtActivityRepository.getById(activityMerchant.getActivityId());
        //活动规则
        MarketPtActivityVO.CheckActivity listVO = new MarketPtActivityVO.CheckActivity();
        BeanUtils.copyProperties(activity,listVO);
        QueryWrapper<MarketPtActivityGoodsCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("activity_id",activityMerchant.getActivityId());
        List<MarketPtActivityGoodsCategory> list = iMarketPtActivityGoodsCategoryRepository.list(wrapper);
        StringBuffer categoryString = new StringBuffer();
        if (ObjectUtils.isNotEmpty(list)){
            List<String> category = new ArrayList<>();
            for (int i = 0; i <list.size(); i++) {
                category.add(list.get(i).getCategoryId());
                if (i==list.size()-1){
                    categoryString.append(list.get(i).getCategoryId());
                }else {
                    categoryString.append(list.get(i).getCategoryId()).append(",");
                }
            }
            List<GoodsCategoryVO.CategoryJoinSearchVO> categoryJoinSearchVOS = iGoodsCategoryRpc.innerGetIdAndName(category);
            if (ObjectUtils.isNotEmpty(categoryJoinSearchVOS)){
                List<MarketPtActivityVO.CategoryJoinSearchVO> CategoryName=new ArrayList<>();
                for (GoodsCategoryVO.CategoryJoinSearchVO categoryJoinSearchVO : categoryJoinSearchVOS) {
                    MarketPtActivityVO.CategoryJoinSearchVO categoryJoinSearchVO1 = new MarketPtActivityVO.CategoryJoinSearchVO();
                    BeanUtils.copyProperties(categoryJoinSearchVO,categoryJoinSearchVO1);
                    CategoryName.add(categoryJoinSearchVO1);
                }
                listVO.setActivityCategoryName(CategoryName);
            }
        }
        //设置活动类目
        listVO.setActivityCategory(categoryString.toString());
        //店铺信息
        PCMerchMarketPtActivityMerchantVO.platformCheck platformCheck = new PCMerchMarketPtActivityMerchantVO.platformCheck();
        PCMerchShopVO.DetailVO detailVO = shopRpc.detailShop(new BaseDTO().setJwtShopId(activityMerchant.getShopId()));
        if (ObjectUtils.isNotEmpty(detailVO)){
            platformCheck.setShopName(detailVO.getShopName()+ShopTypeEnum.findRemark(detailVO.getShopType()));
        }
        platformCheck.setCheck(activityMerchant.getState()).
                setUdata(activityMerchant.getUdate());
        //商品信息
        List<PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo> goodsInfoList=new ArrayList<>();
        QueryWrapper<MarketPtActivityGoodsSpu> spuQueryWrapper = new QueryWrapper<>();
        spuQueryWrapper.eq("activity_id",activityMerchant.getActivityId()).
                eq("shop_id",activityMerchant.getShopId()).
                eq("merchant_id",activityMerchant.getMerchantId());
        List<MarketPtActivityGoodsSpu> activityGoodsSpuList = iMarketPtActivityGoodsSpuRepository.list(spuQueryWrapper);
        if (ObjectUtils.isNotEmpty(activityGoodsSpuList)) {
            List<String> goodsList = new ArrayList<>();
            for (MarketPtActivityGoodsSpu activityGoodsSpu : activityGoodsSpuList) {
                PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo platformCheckGoodsInfo = new PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo();
                platformCheckGoodsInfo.setGoodsId(activityGoodsSpu.getGoodsId());
                goodsList.add(activityGoodsSpu.getGoodsId());
                QueryWrapper<MarketPtActivityGoodsSku> skuQueryWrapper = new QueryWrapper<>();
                skuQueryWrapper.eq("activity_id", activityMerchant.getActivityId()).
                        eq("shop_id", activityMerchant.getShopId()).
                        eq("merchant_id", activityMerchant.getMerchantId()).
                        eq("goods_id", activityGoodsSpu.getGoodsId()).
                        eq("goods_spu_item_id", activityGoodsSpu.getId());
                List<MarketPtActivityGoodsSku> skuList = iMarketPtActivityGoodsSkuRepository.list(skuQueryWrapper);
                PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo skuInfo = new PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo();
                ArrayList<String> ids = new ArrayList<>();
                for (MarketPtActivityGoodsSku sku : skuList) {
                    ids.add(sku.getId());
                }
                platformCheckGoodsInfo.setActivityPrice(activityGoodsSpu.getActivitySalePrice()).//商品活动价
                        setCheckState(Integer.parseInt(activityMerchant.getState())).
                        //把sku的id设置到一起
                                setSkuActivityPriceInfos(skuList.size() > 0 ? new PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo(SkuActivityPriceEnum.SKU促销价.getCode(), ids) : new PCMerchMarketPtActivityMerchantVO.skuActivityPriceInfo(SkuActivityPriceEnum.此商品无sku值.getCode(), ids));//Sku活动价枚举类型[10=SKU促销价 20=此商品无sku值]

                goodsInfoList.add(platformCheckGoodsInfo);
            }
            if (ObjectUtils.isNotEmpty(goodsList)){
                List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS = iGoodsInfoRpc.innerServiceAllGoodsInfo(new GoodsInfoDTO.IdsInnerServiceDTO(goodsList, null, 10));
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)) {
                    SetGoodsInfo(innerServiceGoodsInfoVOS, goodsInfoList);
                }
            }
        }
        return new PCMerchMarketPtActivityMerchantVO.platformCheckView(listVO,platformCheck, goodsInfoList);
    }

    private void SetGoodsInfo(List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS, List<PCMerchMarketPtActivityMerchantVO.platformCheckGoodsInfo> goodsInfoList) {
        for (int i = 0; i < goodsInfoList.size(); i++) {
            for (GoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO : innerServiceGoodsInfoVOS) {
                if (goodsInfoList.get(i).getGoodsId().equals(innerServiceGoodsInfoVO.getId())){
                    goodsInfoList.get(i).
                            setGoodsName(innerServiceGoodsInfoVO.getGoodsName()).
                            setGoodsNo(innerServiceGoodsInfoVO.getGoodsNo()) .
                            setSalePrice(innerServiceGoodsInfoVO.getSalePrice());
                }
            }

        }
    }

    @Override
    public void checkSuccess(PCMerchMarketPtActivityMerchantDTO.idRecordDTO dto) {
        if (iMarketPtActivityMerchantRepository.getById(dto).getState().equals(ActivitySignEnum.已审核.getCode().toString())){
            throw new BusinessException("已审核回");
        }
        iMarketPtActivityMerchantRepository.updateById(new MarketPtActivityMerchant().
                setId(dto.getId()).
                setState(ActivitySignEnum.已审核.getCode().toString()));
    }

    @Override
    public void CheckFail(PCMerchMarketPtActivityMerchantDTO.idRecordRejectionDTO dto) {
        if (StringUtils.isBlank(dto.getReasonsForRejection())){
            throw new BusinessException("请输入驳回原因");
        }
        if (iMarketPtActivityMerchantRepository.getById(dto).getState().equals(ActivitySignEnum.审核驳回.getCode().toString())){
            throw new BusinessException("已驳回");
        }
        iMarketPtActivityMerchantRepository.updateById(new MarketPtActivityMerchant().
                setId(dto.getId()).
                setState(ActivitySignEnum.审核驳回.getCode().toString()).
                setReasonsForRejection(dto.getReasonsForRejection()));
    }
}
