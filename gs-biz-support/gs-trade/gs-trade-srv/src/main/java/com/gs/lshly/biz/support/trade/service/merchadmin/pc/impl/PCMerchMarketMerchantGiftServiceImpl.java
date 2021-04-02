package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtCutStatusEnum;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsGiveDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-09
*/
@Component
public class PCMerchMarketMerchantGiftServiceImpl implements IPCMerchMarketMerchantGiftService {

    @Autowired
    private IMarketMerchantGiftRepository repository;
    @Autowired
    private IMarketMerchantGiftGoodsRepository iMarketMerchantGiftGoodsRepository;
    @Autowired
    private IMarketMerchantGiftGoodsGiveRepository iMarketMerchantGiftGoodsGiveRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantGiftVO.ViewVO> pageData(PCMerchMarketMerchantGiftQTO.QTO qto) {
        QueryWrapper<MarketMerchantGift> wrapper = new QueryWrapper<>();
        wrapper.eq("merchant_id",qto.getJwtMerchantId()).
                eq("shop_id",qto.getJwtShopId());
        wrapper.orderByDesc("cdate");
        List<MarketMerchantGift> list = repository.list(wrapper);
        List<PCMerchMarketMerchantGiftVO.ViewVO> vos=new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (ObjectUtils.isNotEmpty(list)) {

            for (MarketMerchantGift marketMerchantGift : list) {
                Long start = marketMerchantGift.getValidStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                Long end = marketMerchantGift.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                PCMerchMarketMerchantGiftVO.ViewVO viewVO = new PCMerchMarketMerchantGiftVO.ViewVO();
                BeanUtils.copyProperties(marketMerchantGift, viewVO);
                //MarketPtCutStatusEnum
                //10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]
                if (marketMerchantGift.getIsCancel()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.已取消.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())) {
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
                if (marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.待审.getCode())) {
                    viewVO.setCondition(MarketPtCutStatusEnum.未审核.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())) {
                    if (now < start) {
                        viewVO.setCondition(MarketPtCutStatusEnum.待开始.getCode());
                        vos.add(viewVO);
                    } else {
                        viewVO.setCondition(MarketPtCutStatusEnum.活动中.getCode());
                        vos.add(viewVO);
                    }

                }
                vos.add(viewVO);

            }
        }
        return new PageData(vos, qto.getPageNum(), qto.getPageSize(), vos.size());
    }
    @Override
    @Transactional
    public void addMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("请填写活动信息");
        }
        checkETO(eto);
        MarketMerchantGift marketMerchantGift = new MarketMerchantGift();
        BeanUtils.copyProperties(eto, marketMerchantGift);
        marketMerchantGift.setShopId(eto.getJwtShopId());
        marketMerchantGift.setMerchantId(eto.getJwtMerchantId());
        marketMerchantGift.setIsCancel(false).setIsCommit(false);
        repository.save(marketMerchantGift);
        List<PCMerchMarketMerchantGiftGoodsDTO.ETO> goodsList = eto.getGoodsList();
        if (ObjectUtils.isEmpty(goodsList)){
            throw new BusinessException("没有数据");
        }
        for (PCMerchMarketMerchantGiftGoodsDTO.ETO goods:goodsList){
            MarketMerchantGiftGoods marketMerchantGiftGoods = new MarketMerchantGiftGoods();
            BeanUtils.copyProperties(goods,marketMerchantGiftGoods);
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
                marketMerchantGiftGoods.setSkuIds(stringBuffer.toString());
            }else {
                marketMerchantGiftGoods.setSkuIds(goods.getSkuIds());
            }
            marketMerchantGiftGoods.setGiftId(marketMerchantGift.getId()).
                    setMerchantId(eto.getJwtMerchantId()).
                    setShopId(eto.getJwtShopId());
            iMarketMerchantGiftGoodsRepository.save(marketMerchantGiftGoods);
        }
        List<PCMerchMarketMerchantGiftGoodsGiveDTO.ETO> goodsGiveList = eto.getGoodsGiveList();
        if (ObjectUtils.isEmpty(goodsGiveList)){
            throw new BusinessException("没有数据");
        }
        for (PCMerchMarketMerchantGiftGoodsGiveDTO.ETO goodsGive:goodsGiveList){
            MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive = new MarketMerchantGiftGoodsGive();
            BeanUtils.copyProperties(goodsGive,marketMerchantGiftGoodsGive);
            marketMerchantGiftGoodsGive.setGiftId(marketMerchantGift.getId()).
                    setMerchantId(eto.getJwtMerchantId()).
                    setShopId(eto.getJwtShopId());
            iMarketMerchantGiftGoodsGiveRepository.save(marketMerchantGiftGoodsGive);
        }
    }
    private void checkETO(PCMerchMarketMerchantGiftDTO.AddDTO eto) {
        if (ObjectUtils.isEmpty(eto.getGiftName())){
            throw new BusinessException("请填写名字");
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
        if (ObjectUtils.isEmpty(eto.getTerminal())){
            throw new BusinessException("请填写适用平台[10=pc端 20=wap端 30=app端]");
        }
        if (ObjectUtils.isEmpty(eto.getReturnGift())){
            throw new BusinessException("请填写退回赠品[10=退回 20=不退回]");
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
    public void deleteMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto) {
        MarketMerchantGift marketMerchantGift = repository.getById(dto.getId());
        if (marketMerchantGift.getIsCommit()){
            throw new BusinessException("该促销活动不能修改");
        }
        repository.removeById(dto.getId());
        QueryWrapper<MarketMerchantGiftGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("gift_id",dto.getId());
        iMarketMerchantGiftGoodsRepository.remove(wrapper);
        QueryWrapper<MarketMerchantGiftGoodsGive> wrapperGive = new QueryWrapper<>();
        wrapper.eq("gift_id",dto.getId());
        iMarketMerchantGiftGoodsGiveRepository.remove(wrapperGive);
    }
    @Override
    @Transactional
    public void editMarketMerchantGift(PCMerchMarketMerchantGiftDTO.AddDTO eto) {
        MarketMerchantGift marketMerchantGift = new MarketMerchantGift();
        BeanUtils.copyProperties(eto, marketMerchantGift);
        marketMerchantGift.setShopId(eto.getJwtShopId());
        marketMerchantGift.setMerchantId(eto.getJwtMerchantId());
        if (marketMerchantGift.getIsCommit()) {
            if (marketMerchantGift.getIsCommit()) {
                throw new BusinessException("该促销活动不能修改");
            }
        }
        repository.updateById(marketMerchantGift);
        List<PCMerchMarketMerchantGiftGoodsDTO.ETO> goodsList = eto.getGoodsList();
        if (ObjectUtils.isNotEmpty(goodsList)){
            QueryWrapper<MarketMerchantGiftGoods> wrapper = new QueryWrapper<>();
            wrapper.eq("gift_id",eto.getId());
            iMarketMerchantGiftGoodsRepository.remove(wrapper);
            for (PCMerchMarketMerchantGiftGoodsDTO.ETO goods:goodsList){
                MarketMerchantGiftGoods marketMerchantGiftGoods = new MarketMerchantGiftGoods();
                BeanUtils.copyProperties(goods,marketMerchantGiftGoods);
                marketMerchantGiftGoods.setGiftId(marketMerchantGift.getId()).
                        setShopId(eto.getJwtShopId()).
                        setMerchantId(eto.getJwtMerchantId());
                iMarketMerchantGiftGoodsRepository.save(marketMerchantGiftGoods);
            }
        }
        List<PCMerchMarketMerchantGiftGoodsGiveDTO.ETO> goodsGiveList = eto.getGoodsGiveList();
        if (ObjectUtils.isNotEmpty(goodsGiveList)){
            QueryWrapper<MarketMerchantGiftGoodsGive> wrapperGive = new QueryWrapper<>();
            wrapperGive.eq("gift_id",eto.getId());
            iMarketMerchantGiftGoodsGiveRepository.remove(wrapperGive);
            for (PCMerchMarketMerchantGiftGoodsDTO.ETO goods:goodsList){
                MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive = new MarketMerchantGiftGoodsGive();
                BeanUtils.copyProperties(goods,marketMerchantGiftGoodsGive);
                marketMerchantGiftGoodsGive.setGiftId(marketMerchantGift.getId()).
                        setShopId(eto.getJwtShopId()).
                        setMerchantId(eto.getJwtMerchantId());
                iMarketMerchantGiftGoodsGiveRepository.save(marketMerchantGiftGoodsGive);
            }
        }

    }

    @Override
    public PCMerchMarketMerchantGiftVO.View detailMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO dto) {
        MarketMerchantGift marketMerchantGift = repository.getById(dto.getId());
        PCMerchMarketMerchantGiftVO.View detailVo = new PCMerchMarketMerchantGiftVO.View();
        if(ObjectUtils.isEmpty(marketMerchantGift)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantGift, detailVo);
        List<PCMerchMarketMerchantGiftVO.ViewGoods> views=new ArrayList<>();
        List<PCMerchMarketMerchantGiftVO.ViewGoodsGift> viewGifts=new ArrayList<>();
        //遍历商品
        for (MarketMerchantGiftGoods goods:SelectCartGoods(dto.getId(), dto.getJwtMerchantId(),dto.getJwtShopId())){
            PCMerchMarketMerchantGiftVO.ViewGoods view1 = new PCMerchMarketMerchantGiftVO.ViewGoods();
            view1.setGoodsId(goods.getGoodsId());
            ArrayList<String> ids = new ArrayList<>();
            ids.add(goods.getGoodsId());
            List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
            PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO=null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)) {
                innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                view1.setGoodsName(innerServiceGoodsInfoVO.getGoodsName()).
                        setGoodsPrice(innerServiceGoodsInfoVO.getSalePrice()).
                        setImageUrl(innerServiceGoodsInfoVO.getGoodsImage());
            }
            List<PCMerchSkuGoodInfoVO.ListVO> skuList=null;
            if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)) {
                skuList = innerServiceGoodsInfoVO.getSkuList();
            }
            List<String> arrSkuIdList=null;
            if (ObjectUtils.isNotEmpty(goods.getSkuIds())) {
                arrSkuIdList= Arrays.asList(goods.getSkuIds().split(","));
            }
            if (ObjectUtils.isNotEmpty(skuList) && ObjectUtils.isNotEmpty(arrSkuIdList)) {
                for (int i = 0; i < skuList.size(); i++) {
                    if (!arrSkuIdList.contains(skuList.get(i).getId())) {
                        skuList.remove(i);
                    }
                }
            }

            view1.setViewSKU(skuList);
            views.add(view1);
        }
        //获取赠品
        QueryWrapper<MarketMerchantGiftGoodsGive> wrapper = new QueryWrapper<>();
        wrapper.eq("gift_id",dto.getId()).
                eq("merchant_id",dto.getJwtMerchantId()).
                eq("shop_id",dto.getJwtShopId());
        List<MarketMerchantGiftGoodsGive> list = iMarketMerchantGiftGoodsGiveRepository.list(wrapper);
        ArrayList<String> skuIds = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)) {

            for (MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive : list) {

                skuIds.add(marketMerchantGiftGoodsGive.getSkuId());
            }
        }
        //通过商品的SKU ID数组 查询 sku信息
        if (ObjectUtils.isNotEmpty(skuIds)) {
            List<PCMerchSkuGoodInfoVO.ListVO> skuListInfo = ipcMerchAdminGoodsInfoRpc.innerServiceSkuGoodsList(skuIds);
            if (ObjectUtils.isNotEmpty(skuListInfo)) {
                for (PCMerchSkuGoodInfoVO.ListVO skuInfo : skuListInfo) {
                    PCMerchMarketMerchantGiftVO.ViewGoodsGift view2 = new PCMerchMarketMerchantGiftVO.ViewGoodsGift();
                    //通过商品ID查询商品信息
                    view2.setGoodsPrice(skuInfo.getSalePrice()).//规格价格
                            setImageUrl(skuInfo.getImage()).//规格图片
                            setSpecsValue(skuInfo.getSpecsValue());//规格明
                    ArrayList<String> goodsIds = new ArrayList<>();
                    goodsIds.add(skuInfo.getGoodId());
                    List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO =  ipcMerchAdminGoodsInfoRpc.innerServiceGoodsVO(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(goodsIds).setQueryType(10));
                    if (ObjectUtils.isNotEmpty(innerServiceGoodsVO)) {
                        view2.setGoodsName(innerServiceGoodsVO.get(0).getGoodsName());//商品的名字
                    }
                    viewGifts.add(view2);
                }
            }
        }

        detailVo.setGoodsList(views).setGiftView(viewGifts);
        return detailVo;
    }

    @Override
    public void commitMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        MarketMerchantGift marketMerchantGift = repository.getById(eto.getId());
        if (marketMerchantGift.getIsCommit()||marketMerchantGift.getIsCancel()){
            throw new BusinessException("此促销活动无法提交");
        }
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=marketMerchantGift.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end){
            throw new BusinessException("满增活动结束了,无法审核");
        }
        marketMerchantGift.setIsCommit(true);
        repository.updateById(marketMerchantGift);
    }

    @Override
    public void cancelMarketMerchantGift(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        MarketMerchantGift marketMerchantGift = repository.getById(eto.getId());
        if (!marketMerchantGift.getIsCommit()){
            throw new BusinessException("无法取消");
        }
        if (marketMerchantGift.getIsCommit()&&(marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())||marketMerchantGift.getState().equals(PlatformCardCheckStatusEnum.待审.getCode()))){
            throw new BusinessException("无法取消");
        }
        marketMerchantGift.setIsCancel(true);
        repository.updateById(marketMerchantGift);
    }

    private List<MarketMerchantGiftGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantGiftGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("gift_id",id);
        return iMarketMerchantGiftGoodsRepository.list(wrapper);
    }

}
