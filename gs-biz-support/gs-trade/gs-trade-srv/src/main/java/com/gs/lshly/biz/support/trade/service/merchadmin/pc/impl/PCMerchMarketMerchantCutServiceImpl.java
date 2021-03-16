package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtCutStatusEnum;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCutService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardGoodsVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import io.swagger.annotations.ApiModelProperty;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
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
* @since 2020-12-08
*/
@Component
public class PCMerchMarketMerchantCutServiceImpl implements IPCMerchMarketMerchantCutService {

    @Autowired
    private IMarketMerchantCutRepository repository;
    @Autowired
    private IMarketMerchantCutGoodsRepository iMarketMerchantCutGoodsRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantCutVO.ViewVO> pageData(PCMerchMarketMerchantCutQTO.QTO qto) {
        QueryWrapper<MarketMerchantCut> wrapper = new QueryWrapper<>();
        IPage<MarketMerchantCut> pager = MybatisPlusUtil.pager(qto);
        wrapper.eq("merchant_id",qto.getJwtMerchantId()).
                eq("shop_id",qto.getJwtShopId());
        repository.page(pager, wrapper);
        if (ObjectUtils.isEmpty(wrapper) || ObjectUtils.isEmpty(pager)){
            throw new BusinessException("没有数据");
        }
        List<PCMerchMarketMerchantCutVO.ViewVO> vos=new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (ObjectUtils.isNotEmpty(pager.getRecords())) {
            for (MarketMerchantCut marketMerchantCut : pager.getRecords()) {
                Long start = marketMerchantCut.getValidStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                Long end = marketMerchantCut.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                PCMerchMarketMerchantCutVO.ViewVO viewVO = new PCMerchMarketMerchantCutVO.ViewVO();
                BeanUtils.copyProperties(marketMerchantCut, viewVO);
                //MarketPtCutStatusEnum
//10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]
                if (marketMerchantCut.getIsCancel()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.已取消.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantCut.getState() == PlatformCardCheckStatusEnum.拒审.getCode()) {
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
                if (marketMerchantCut.getState() == PlatformCardCheckStatusEnum.待审.getCode()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.未审核.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantCut.getState() == PlatformCardCheckStatusEnum.通过.getCode()) {
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
        return new PageData(vos, qto.getPageNum(), qto.getPageSize(), pager.getTotal());
    }

    @Override
    @Transactional
    public void addMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto) {
        MarketMerchantCut marketMerchantCut = new MarketMerchantCut();
        BeanUtils.copyProperties(eto, marketMerchantCut);
        marketMerchantCut.setShopId(eto.getJwtShopId());
        marketMerchantCut.setMerchantId(eto.getJwtMerchantId());
        marketMerchantCut.setIsCancel(false).setIsCommit(false);
        repository.save(marketMerchantCut);
        List<PCMerchMarketMerchantCutGoodsDTO.ETO> goodsList = eto.getGoodsList();
        if (ObjectUtils.isEmpty(goodsList)){
            throw new BusinessException("没有数据");
        }
        for (PCMerchMarketMerchantCutGoodsDTO.ETO goods:goodsList){
            MarketMerchantCutGoods marketMerchantCutGoods = new MarketMerchantCutGoods();
            BeanUtils.copyProperties(goods,marketMerchantCutGoods);
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
                marketMerchantCutGoods.setSkuIds(stringBuffer.toString());
            }
            else {
                marketMerchantCutGoods.setSkuIds(goods.getSkuIds());
            }
            marketMerchantCutGoods.setCutId(marketMerchantCut.getId()).
                    setMerchantId(eto.getJwtMerchantId()).
                    setShopId(eto.getJwtShopId());
            iMarketMerchantCutGoodsRepository.save(marketMerchantCutGoods);
        }
    }


    @Override
    @Transactional
    public void deleteMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto) {
        MarketMerchantCut marketMerchantCut = repository.getById(dto.getId());
        if (marketMerchantCut.getIsCommit()){
            throw new BusinessException("该促销不能删除");
        }
        repository.removeById(dto.getId());
        QueryWrapper<MarketMerchantCutGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("cut_id",dto.getId());
        iMarketMerchantCutGoodsRepository.remove(wrapper);
    }
    @Override
    @Transactional
    public void editMarketMerchantCut(PCMerchMarketMerchantCutDTO.AddDTO eto) {
        MarketMerchantCut marketMerchantCut = new MarketMerchantCut();
        BeanUtils.copyProperties(eto, marketMerchantCut);
        marketMerchantCut.setId(eto.getCutId());
        marketMerchantCut.setMerchantId(eto.getJwtMerchantId());
        marketMerchantCut.setShopId(eto.getJwtShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantCut.getIsCommit())) {
            if (marketMerchantCut.getIsCommit()) {
                throw new BusinessException("该促销不能编辑");
            }
        }
        repository.updateById(marketMerchantCut);
        if (ObjectUtils.isNotEmpty(eto.getGoodsList())) {
            QueryWrapper<MarketMerchantCutGoods> wrapper = new QueryWrapper<>();
            wrapper.eq("cut_id", marketMerchantCut.getId());
            iMarketMerchantCutGoodsRepository.remove(wrapper);
            List<PCMerchMarketMerchantCutGoodsDTO.ETO> goodsList = eto.getGoodsList();
            for (PCMerchMarketMerchantCutGoodsDTO.ETO goods : goodsList) {
                MarketMerchantCutGoods marketMerchantCutGoods = new MarketMerchantCutGoods();
                BeanUtils.copyProperties(goods, marketMerchantCutGoods);
                marketMerchantCutGoods.setCutId(marketMerchantCut.getId()).setShopId(eto.getJwtShopId()).
                        setMerchantId(eto.getJwtMerchantId());
                iMarketMerchantCutGoodsRepository.save(marketMerchantCutGoods);
            }
        }
    }

    @Override
    public PCMerchMarketMerchantCutVO.View detailMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO dto) {
        MarketMerchantCut marketMerchantCut = repository.getById(dto.getId());
        PCMerchMarketMerchantCutVO.View detailVo = new PCMerchMarketMerchantCutVO.View();
        if(ObjectUtils.isEmpty(marketMerchantCut)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantCut, detailVo);
        detailVo.setCutRule(marketMerchantCut.getCutRule()+"("+marketMerchantCut.getIsNotMax()+")");
        List<PCMerchMarketMerchantCutVO.ViewGoods> views=new ArrayList<>();
        //遍历商品
        for (MarketMerchantCutGoods goods:SelectCartGoods(dto.getId(), dto.getJwtMerchantId(),dto.getJwtShopId())){
            PCMerchMarketMerchantCutVO.ViewGoods view1 = new PCMerchMarketMerchantCutVO.ViewGoods();
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
    public void commitMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto) {
        MarketMerchantCut marketMerchantCut = repository.getById(eto.getId());
        if (marketMerchantCut.getIsCommit()||marketMerchantCut.getIsCancel()){
            throw new BusinessException("此促销活动已提交");
        }
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=marketMerchantCut.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end){
            throw new BusinessException("满减活动结束了,无法审核");
        }
        marketMerchantCut.setIsCommit(true);
        repository.updateById(marketMerchantCut);

    }

    @Override
    public void cancelMarketMerchantCut(PCMerchMarketMerchantCutDTO.IdDTO eto) {
        MarketMerchantCut marketMerchantCut = repository.getById(eto.getId());
        if (!marketMerchantCut.getIsCommit()){
            throw new BusinessException("无法取消");
        }
        if (marketMerchantCut.getIsCommit()&&(marketMerchantCut.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())||marketMerchantCut.getState().equals(PlatformCardCheckStatusEnum.待审.getCode()))){
            throw new BusinessException("无法取消");
        }
        marketMerchantCut.setIsCancel(true);
        repository.updateById(marketMerchantCut);
    }

    private List<MarketMerchantCutGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantCutGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("cut_id",id);
        return iMarketMerchantCutGoodsRepository.list(wrapper);

    }

}
