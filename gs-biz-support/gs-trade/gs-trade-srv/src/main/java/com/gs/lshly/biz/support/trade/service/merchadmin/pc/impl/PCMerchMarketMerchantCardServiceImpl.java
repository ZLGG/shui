package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCard;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardGoods;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.gs.lshly.biz.support.trade.enums.MerchantCardOperationEnum;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCardService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
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
import java.util.UUID;

/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-04
*/
@Component
public class PCMerchMarketMerchantCardServiceImpl implements IPCMerchMarketMerchantCardService {

    @Autowired
    private IMarketMerchantCardRepository repository;
    @Autowired
    private IMarketMerchantCardGoodsRepository iMarketMerchantCardGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantCardVO.ListVO> pageData(PCMerchMarketMerchantCardQTO.QTO qto) {
        QueryWrapper<MarketMerchantCard> wrapper = new QueryWrapper<>();
        List<MarketMerchantCard> list = repository.list();
        List<PCMerchMarketMerchantCardVO.ListVO> cardList=new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        for (MarketMerchantCard card:list){
            PCMerchMarketMerchantCardVO.ListVO listVO = new PCMerchMarketMerchantCardVO.ListVO();
            BeanUtils.copyProperties(card,listVO);
            Long getStart = card.getGetStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long getEnd = card.getGetEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long validStart = card.getValidStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
            Long validEnd = card.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
           // 操作枚举类型[10=未审核 20=可领取 30=待生效 40=已取消 50=结束领取 60=生效中 70=待领取 80=已失效 90=审核拒绝]
            //MerchantCardOperationEnum

            if (card.getState()==PlatformCardCheckStatusEnum.拒审.getCode()){
                listVO.setOperation(MerchantCardOperationEnum.审核拒绝.getCode().toString());
                cardList.add(listVO);
                continue;
            }
            if (card.getState()==PlatformCardCheckStatusEnum.待审.getCode()){
                listVO.setOperation(MerchantCardOperationEnum.未审核.getCode().toString());
                cardList.add(listVO);
                continue;
            }
            if (card.getIsCancel()){
                listVO.setOperation(MerchantCardOperationEnum.已取消.getCode().toString());
                cardList.add(listVO);
                continue;
            }
            if (now<getStart){
                listVO.setOperation(MerchantCardOperationEnum.待领取.getCode()+","+MerchantCardOperationEnum.待生效.getCode());
                cardList.add(listVO);
                continue;
            }
            if (now>=getStart&&now<getEnd){
                listVO.setOperation(MerchantCardOperationEnum.可领取.getCode()+","+MerchantCardOperationEnum.待生效.getCode());
                cardList.add(listVO);
                continue;
            }
            if (now>=getEnd&&now<validStart){
                listVO.setOperation(MerchantCardOperationEnum.结束领取.getCode()+","+MerchantCardOperationEnum.待生效.getCode());
                cardList.add(listVO);
                continue;
            }
            if (now>=validStart&&now<validEnd){
                listVO.setOperation(MerchantCardOperationEnum.结束领取.getCode()+","+MerchantCardOperationEnum.生效中.getCode());
                cardList.add(listVO);
                continue;
            }
            if (now>=validEnd){
                listVO.setOperation(MerchantCardOperationEnum.结束领取.getCode()+","+MerchantCardOperationEnum.已失效.getCode());
                cardList.add(listVO);
                continue;
            }

            cardList.add(listVO);
        }
        return new PageData(cardList,qto.getPageNum(), qto.getPageSize(), cardList.size());
    }


    @Override
    @Transactional
    public void addMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto) {
        MarketMerchantCard marketMerchantCard = new MarketMerchantCard();
        BeanUtils.copyProperties(eto, marketMerchantCard);
        String cardPrefix = UUID.randomUUID().toString().replace("-","").toUpperCase().substring(0, 5);
        marketMerchantCard.setCardPrefix(cardPrefix).
                            setState(PlatformCardCheckStatusEnum.待审.getCode()).
                            setIsCommit(false).
                            setIsCancel(false).setReceivedTotal(0).setUsedTotal(0).
                            setMerchantId(eto.getJwtMerchantId()).setShopId(eto.getJwtShopId());
        repository.save(marketMerchantCard);
        List<PCMerchMarketMerchantCardGoodsDTO.GoodsInfo> cardGoodsInfo = eto.getGoodsList();
        if (ObjectUtils.isNotEmpty(cardGoodsInfo)) {
            for (PCMerchMarketMerchantCardGoodsDTO.GoodsInfo goodsInfo : cardGoodsInfo) {
                MarketMerchantCardGoods marketMerchantCardGoods = new MarketMerchantCardGoods();
                marketMerchantCardGoods.setCardId(marketMerchantCard.getId()).
                        setGoodsId(goodsInfo.getGoodsId()).
                        setSkuId(goodsInfo.getSkuId()).
                        setMerchantId(eto.getJwtMerchantId()).
                        setShopId(eto.getJwtShopId());
                List<String> strings = new ArrayList<>();
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO = null;
                if (ObjectUtils.isNotEmpty(goodsInfo.getGoodsId())) {
                    strings.add(goodsInfo.getGoodsId());
                }
                if (ObjectUtils.isNotEmpty(strings)) {
                    List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceAllGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO(strings, null, 10));
                    if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)) {
                        innerServiceGoodsInfoVO = innerServiceGoodsInfoVOS.get(0);
                    }
                }
                if (ObjectUtils.isEmpty(goodsInfo.getSkuId()) && ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)) {
                    StringBuffer stringBuffer = new StringBuffer();
                    List<PCMerchSkuGoodInfoVO.ListVO> skuList = innerServiceGoodsInfoVO.getSkuList();
                    if (ObjectUtils.isNotEmpty(skuList)) {
                        for (int i = 0; i < skuList.size(); i++) {
                            if (i == skuList.size() - 1) {
                                stringBuffer.append(skuList.get(i).getId());
                            } else {
                                stringBuffer.append(skuList.get(i).getId() + ",");
                            }
                        }
                    }
                    marketMerchantCardGoods.setSkuId(stringBuffer.toString());
                } else {
                    marketMerchantCardGoods.setSkuId(goodsInfo.getSkuId());
                }
                iMarketMerchantCardGoodsRepository.save(marketMerchantCardGoods);
            }
        }

    }

    @Override
    public void deleteMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantCard(PCMerchMarketMerchantCardDTO.ETO eto) {
        MarketMerchantCard marketMerchantCard = new MarketMerchantCard();
        BeanUtils.copyProperties(eto, marketMerchantCard);
        repository.updateById(marketMerchantCard);
        if (ObjectUtils.isNotEmpty(eto.getGoodsList())) {
            QueryWrapper<MarketMerchantCardGoods> wrapper = new QueryWrapper<>();
            wrapper.eq("card_id", eto.getId());
            iMarketMerchantCardGoodsRepository.remove(wrapper);
            List<PCMerchMarketMerchantCardGoodsDTO.GoodsInfo> cardGoodsInfo = eto.getGoodsList();
            for (PCMerchMarketMerchantCardGoodsDTO.GoodsInfo goods : cardGoodsInfo) {
                MarketMerchantCardGoods marketMerchantCardGoods = new MarketMerchantCardGoods();
                BeanUtils.copyProperties(goods, marketMerchantCardGoods);
                marketMerchantCardGoods.setCardId(marketMerchantCard.getId()).setShopId(marketMerchantCard.getShopId()).
                        setMerchantId(marketMerchantCard.getMerchantId());
                iMarketMerchantCardGoodsRepository.save(marketMerchantCardGoods);
            }
        }
    }

    @Override
    public PCMerchMarketMerchantCardVO.DetailVO detailMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO dto) {
        MarketMerchantCard marketMerchantCard = repository.getById(dto.getId());
        PCMerchMarketMerchantCardVO.DetailVO detailVo = new PCMerchMarketMerchantCardVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantCard)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantCard, detailVo);
        return detailVo;
    }

    @Override
    public void cancelMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        MarketMerchantCard marketMerchantCard = repository.getById(eto.getId());
        if (!marketMerchantCard.getIsCommit()){
            throw new BusinessException("无法取消");
        }
        if (marketMerchantCard.getIsCommit()&&(marketMerchantCard.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())||marketMerchantCard.getState().equals(PlatformCardCheckStatusEnum.待审.getCode()))){
            throw new BusinessException("无法取消");
        }
        marketMerchantCard.setIsCancel(true);
        repository.updateById(marketMerchantCard);
    }

    @Override
    public void commitMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        MarketMerchantCard marketMerchantCard = new MarketMerchantCard();
        BeanUtils.copyProperties(eto, marketMerchantCard);
        marketMerchantCard.setIsCommit(true);
        repository.updateById(marketMerchantCard);
    }

    @Override
    public PCMerchMarketMerchantCardVO.View viewMarketMerchantCard(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        MarketMerchantCard marketMerchantCard = repository.getById(eto.getId());
        PCMerchMarketMerchantCardVO.View view = new PCMerchMarketMerchantCardVO.View();
        BeanUtils.copyProperties(marketMerchantCard,view);
        List<PCMerchMarketMerchantCardGoodsVO.View> views = new ArrayList<>();
        //遍历商品
        for (MarketMerchantCardGoods goods:SelectCartGoods(marketMerchantCard.getId(), marketMerchantCard.getMerchantId(), marketMerchantCard.getShopId())){
            PCMerchMarketMerchantCardGoodsVO.View view1 = new PCMerchMarketMerchantCardGoodsVO.View();
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
            if (ObjectUtils.isNotEmpty(goods.getSkuId())) {
                arrSkuIdList = Arrays.asList(goods.getSkuId().split(","));
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
        view.setGoodsList(views);
        return view;
    }
    /**
     * 通过优惠卷ID，店铺ID，商家ID，查询关联商品
     *
     * @return*/
    private List<MarketMerchantCardGoods> SelectCartGoods(String id, String merchantId, String shopId) {
        QueryWrapper<MarketMerchantCardGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("card_id",id);
        return iMarketMerchantCardGoodsRepository.list(wrapper);
    }


}
