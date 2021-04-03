package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscount;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.enums.MarketPtCutStatusEnum;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantDiscountService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-09
*/
@Component
public class PCMerchMarketMerchantDiscountServiceImpl implements IPCMerchMarketMerchantDiscountService {

    @Autowired
    private IMarketMerchantDiscountRepository repository;
    @Autowired
    private IMarketMerchantDiscountGoodsRepository iMarketMerchantDiscountGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantDiscountVO.ViewVO> pageData(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        QueryWrapper<MarketMerchantDiscount> wrapper = new QueryWrapper<>();
        wrapper.eq("merchant_id",qto.getJwtMerchantId()).
                eq("shop_id",qto.getJwtShopId());
        wrapper.orderByDesc("cdate");
        List<MarketMerchantDiscount> list = repository.list(wrapper);
        List<PCMerchMarketMerchantDiscountVO.ViewVO> vos=new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (ObjectUtils.isNotEmpty(list)) {
            for (MarketMerchantDiscount marketMerchantDiscount : list) {
                Long start = marketMerchantDiscount.getValidStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                Long end = marketMerchantDiscount.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                PCMerchMarketMerchantDiscountVO.ViewVO viewVO = new PCMerchMarketMerchantDiscountVO.ViewVO();
                BeanUtils.copyProperties(marketMerchantDiscount, viewVO);
                //MarketPtCutStatusEnum
//10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]
                if (marketMerchantDiscount.getIsCancel()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.已取消.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantDiscount.getState() == PlatformCardCheckStatusEnum.拒审.getCode()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.审核拒绝.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (now >= end) {
                    viewVO.setCondition(MarketPtCutStatusEnum.已结束.getCode());
                    vos.add(viewVO);
                    continue;
                }
                //之后的时间都是小于end
                if (marketMerchantDiscount.getState() == PlatformCardCheckStatusEnum.待审.getCode()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.未审核.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantDiscount.getState() == PlatformCardCheckStatusEnum.通过.getCode()) {
                    if (now < start) {
                        viewVO.setCondition(MarketPtCutStatusEnum.待开始.getCode());
                        vos.add(viewVO);
                        continue;
                    } else {
                        viewVO.setCondition(MarketPtCutStatusEnum.活动中.getCode());
                        vos.add(viewVO);
                        continue;
                    }

                }
                vos.add(viewVO);

            }
        }
        return new PageData(vos, qto.getPageNum(), qto.getPageSize(), vos.size());
    }

    @Override
    @Transactional
    public void addMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("请填写活动信息");
        }
        checkETO(eto);
        MarketMerchantDiscount marketMerchantDiscount = new MarketMerchantDiscount();
        BeanUtils.copyProperties(eto, marketMerchantDiscount);
        marketMerchantDiscount.setShopId(eto.getJwtShopId());
        marketMerchantDiscount.setMerchantId(eto.getJwtMerchantId());
        marketMerchantDiscount.setIsCancel(false).setIsCommit(false);
        repository.save(marketMerchantDiscount);
        List<PCMerchMarketMerchantDiscountGoodsDTO.ETO> goodsList = eto.getGoodsList();
        if (ObjectUtils.isEmpty(goodsList)){
            throw new BusinessException("没有数据");
        }
        for (PCMerchMarketMerchantDiscountGoodsDTO.ETO goods:goodsList){
            MarketMerchantDiscountGoods marketMerchantDiscountGoods = new MarketMerchantDiscountGoods();
            BeanUtils.copyProperties(goods,marketMerchantDiscountGoods);
            List<String> strings = new ArrayList<>();
            PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO =null;
            if (ObjectUtils.isNotEmpty(goods.getGoodsId())){
                strings.add(goods.getGoodsId());
            }
            if (ObjectUtils.isNotEmpty(strings)) {
                List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO>  innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceAllGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO(strings, null, 10));
                if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)){
                    innerServiceGoodsInfoVO= innerServiceGoodsInfoVOS.get(0);
                }
            }
            if (ObjectUtils.isEmpty(goods.getSkuIds()) && ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)){
                StringBuffer stringBuffer = new StringBuffer();
                List<PCMerchSkuGoodInfoVO.ListVO> skuList = innerServiceGoodsInfoVO.getSkuList();
                if (ObjectUtils.isNotEmpty(skuList)){
                    for (int i = 0; i < skuList.size(); i++) {
                        if (i==skuList.size()-1){
                            stringBuffer.append(skuList.get(i).getId());
                        }else {
                            stringBuffer.append(skuList.get(i).getId()+",");
                        }

                    }
                }
                marketMerchantDiscountGoods.setSkuIds(stringBuffer.toString());
            }else {
                marketMerchantDiscountGoods.setSkuIds(goods.getSkuIds());
            }
            marketMerchantDiscountGoods.setDiscountId(marketMerchantDiscount.getId()).
                    setMerchantId(eto.getJwtMerchantId()).
                    setShopId(eto.getJwtShopId());
            iMarketMerchantDiscountGoodsRepository.save(marketMerchantDiscountGoods);
        }
    }
    private void checkETO(PCMerchMarketMerchantDiscountDTO.AddDTO eto) {
        if (ObjectUtils.isEmpty(eto.getScountName())){
            throw new BusinessException("请填写名字");
        }
        if (ObjectUtils.isEmpty(eto.getScountDescribe())){
            throw new BusinessException("请填写描述");
        }
        if (ObjectUtils.isEmpty(eto.getScountRule())){
            throw new BusinessException("请填写规则");
        }
        if (ObjectUtils.isEmpty(eto.getValidEndTime())){
            throw new BusinessException("请填写结束时间");
        }
        if (ObjectUtils.isEmpty(eto.getValidStartTime())){
            throw new BusinessException("请填写优惠卷有效开始时间");
        }
        if (ObjectUtils.isEmpty(eto.getOnUserLeve())){
            throw new BusinessException("请填写优惠卷适用会员等级(1,2,3,4,5,6)");
        }
        if (ObjectUtils.isEmpty(eto.getUserDoNumber())){
            throw new BusinessException("请填写可参与次数");
        }
        if (ObjectUtils.isEmpty(eto.getTerminal())){
            throw new BusinessException("请填写适用平台[10=pc端 20=wap端 30=app端]\"");
        }
        if (LocalDateTime.now().isAfter(eto.getValidEndTime())){
            throw new BusinessException("请检查时间");
        }
        if (LocalDateTime.now().isAfter(eto.getValidStartTime())){
            throw new BusinessException("请检查时间");
        }
    }

    @Override
    @Transactional
    public void deleteMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto) {
        MarketMerchantDiscount marketMerchantDiscount = repository.getById(dto.getId());
        if (marketMerchantDiscount.getState()!=20){
            throw new BusinessException("该促销活动不能修改");
        }
        repository.removeById(dto.getId());
        QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id",dto.getId());
        iMarketMerchantDiscountGoodsRepository.remove(wrapper);
    }
    @Override
    @Transactional
    public void editMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.AddDTO eto) {
        MarketMerchantDiscount marketMerchantDiscount = new MarketMerchantDiscount();
        BeanUtils.copyProperties(eto, marketMerchantDiscount);
        marketMerchantDiscount.setMerchantId(eto.getJwtMerchantId());
        marketMerchantDiscount.setShopId(eto.getJwtShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantDiscount.getIsCommit())) {
            if (marketMerchantDiscount.getIsCommit()) {
                throw new BusinessException("该促销活动不能修改");
            }
        }
        repository.updateById(marketMerchantDiscount);
        if (ObjectUtils.isNotEmpty(eto.getGoodsList())) {
            QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
            wrapper.eq("discount_id", eto.getId());
            iMarketMerchantDiscountGoodsRepository.remove(wrapper);
            List<PCMerchMarketMerchantDiscountGoodsDTO.ETO> goodsList = eto.getGoodsList();
            for (PCMerchMarketMerchantDiscountGoodsDTO.ETO goods : goodsList) {
                MarketMerchantDiscountGoods marketMerchantDiscountGoods = new MarketMerchantDiscountGoods();
                BeanUtils.copyProperties(goods, marketMerchantDiscountGoods);
                marketMerchantDiscountGoods.setDiscountId(marketMerchantDiscount.getId()).
                        setShopId(eto.getJwtShopId()).
                        setMerchantId(eto.getJwtMerchantId());
                iMarketMerchantDiscountGoodsRepository.save(marketMerchantDiscountGoods);
            }
        }
    }

    @Override
    public PCMerchMarketMerchantDiscountVO.View detailMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO dto) {
        MarketMerchantDiscount marketMerchantDiscount = repository.getById(dto.getId());
        PCMerchMarketMerchantDiscountVO.View detailVo = new PCMerchMarketMerchantDiscountVO.View();
        if(ObjectUtils.isEmpty(marketMerchantDiscount)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantDiscount, detailVo);
        List<PCMerchMarketMerchantDiscountVO.ViewGoods> views=new ArrayList<>();
        //遍历商品
        for (MarketMerchantDiscountGoods goods:SelectCartGoods(dto.getId(), dto.getJwtMerchantId(),dto.getJwtShopId())){
            PCMerchMarketMerchantDiscountVO.ViewGoods view1 = new PCMerchMarketMerchantDiscountVO.ViewGoods();
            ArrayList<String> ids = new ArrayList<>();
            ids.add(goods.getGoodsId());
            view1.setGoodsId(goods.getGoodsId());
            List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
            List<PCMerchSkuGoodInfoVO.ListVO> skuList=null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)){
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                view1.setGoodsName(ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO.getGoodsName())?innerServiceGoodsInfoVO.getGoodsName():"").
                        setGoodsPrice(ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO.getSalePrice())?innerServiceGoodsInfoVO.getSalePrice():null).
                        setImageUrl(ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO.getGoodsImage())?innerServiceGoodsInfoVO.getGoodsImage():null);
                skuList= innerServiceGoodsInfoVO.getSkuList();
            }
            List<String> arrSkuIdList =null;
            if (ObjectUtils.isNotEmpty(goods.getSkuIds())) {
                arrSkuIdList = Arrays.asList(goods.getSkuIds().split(","));
            }
            if (ObjectUtils.isNotEmpty(skuList) && ObjectUtils.isNotEmpty(arrSkuIdList)) {
                for (int i = 0; i < skuList.size(); i++) {
                    if (!arrSkuIdList.contains(skuList.get(i).getId())) {
                        skuList.remove(i);
                    }
                }
            }
            if (ObjectUtils.isNotEmpty(skuList)){
                view1.setViewSKU(skuList);
            }
            views.add(view1);
        }
        detailVo.setGoodsList(views);
        return detailVo;
    }

    @Override
    public void commitMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        MarketMerchantDiscount marketMerchantDiscount = repository.getById(eto.getId());
        if (marketMerchantDiscount.getIsCommit()||marketMerchantDiscount.getIsCancel()){
            throw new BusinessException("此促销活动已提交");
        }
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=marketMerchantDiscount.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end){
            throw new BusinessException("满减活动结束了,无法审核");
        }
        marketMerchantDiscount.setIsCommit(true);
        repository.updateById(marketMerchantDiscount);
    }

    @Override
    public void cancelMarketMerchantDiscount(PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        MarketMerchantDiscount marketMerchantDiscount = repository.getById(eto.getId());
        if (!marketMerchantDiscount.getIsCommit()){
            throw new BusinessException("无法取消");
        }
        if (marketMerchantDiscount.getIsCommit()&&(marketMerchantDiscount.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())||marketMerchantDiscount.getState().equals(PlatformCardCheckStatusEnum.待审.getCode()))){
            throw new BusinessException("无法取消");
        }
        marketMerchantDiscount.setIsCancel(true);
        repository.updateById(marketMerchantDiscount);
    }

    private List<MarketMerchantDiscountGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("discount_id",id);
        return iMarketMerchantDiscountGoodsRepository.list(wrapper);

    }

}
