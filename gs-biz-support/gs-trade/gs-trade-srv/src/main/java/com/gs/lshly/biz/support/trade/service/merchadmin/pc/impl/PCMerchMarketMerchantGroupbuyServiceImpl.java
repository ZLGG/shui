package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.*;
import com.gs.lshly.biz.support.trade.enums.MarketPtCutStatusEnum;
import com.gs.lshly.biz.support.trade.enums.PlatformCardCheckStatusEnum;
import com.gs.lshly.biz.support.trade.repository.*;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGroupbuyService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-10
*/
@Component
public class PCMerchMarketMerchantGroupbuyServiceImpl implements IPCMerchMarketMerchantGroupbuyService {

    @Autowired
    private IMarketMerchantGroupbuyRepository repository;
    @Autowired
    private IMarketMerchantGroupbuyGoodsRepository iMarketMerchantGroupbuyGoodsRepository;
    @Autowired
    private IMarketMerchantGroupbuyGoodsSkuRepository iMarketMerchantGroupbuyGoodsSkuRepository;
    @DubboReference
    private IPCMerchAdminGoodsInfoRpc ipcMerchAdminGoodsInfoRpc;
    @Override
    public PageData<PCMerchMarketMerchantGroupbuyVO.ViewVO> pageData(PCMerchMarketMerchantGroupbuyQTO.QTO qto) {
        QueryWrapper<MarketMerchantGroupbuy> wrapper = new QueryWrapper<>();
        wrapper.eq("merchant_id",qto.getJwtMerchantId()).
                eq("shop_id",qto.getJwtShopId());
        List<MarketMerchantGroupbuy> list = repository.list(wrapper);
        List<PCMerchMarketMerchantGroupbuyVO.ViewVO> vos=new ArrayList<>();
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (ObjectUtils.isNotEmpty(list)) {
            for (MarketMerchantGroupbuy marketMerchantGroupbuy : list) {
                Long start = marketMerchantGroupbuy.getValidStartTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                Long end = marketMerchantGroupbuy.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
                PCMerchMarketMerchantGroupbuyVO.ViewVO viewVO = new PCMerchMarketMerchantGroupbuyVO.ViewVO();
                BeanUtils.copyProperties(marketMerchantGroupbuy, viewVO);
                //MarketPtCutStatusEnum
                //10=已取消 20=已结束 30=未审核 40=待开始 50=活动中]
                if (marketMerchantGroupbuy.getIsCancel()) {
                    viewVO.setCondition(MarketPtCutStatusEnum.已取消.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())) {
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
                if (marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.待审.getCode())) {
                    viewVO.setCondition(MarketPtCutStatusEnum.未审核.getCode());
                    vos.add(viewVO);
                    continue;
                }
                if (marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.通过.getCode())) {
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
    public void addMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO dto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = new MarketMerchantGroupbuy();
        BeanUtils.copyProperties(dto, marketMerchantGroupbuy);
        marketMerchantGroupbuy.setShopId(dto.getJwtShopId());
        marketMerchantGroupbuy.setState(PlatformCardCheckStatusEnum.待审.getCode());
        marketMerchantGroupbuy.setMerchantId(dto.getJwtMerchantId());
        marketMerchantGroupbuy.setIsCancel(false).setIsCommit(false);
        repository.save(marketMerchantGroupbuy);
        saveGoodsAndSku(dto,marketMerchantGroupbuy);
    }


    @Override
    @Transactional
    public void deleteMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(dto.getId());
        if (marketMerchantGroupbuy.getIsCommit()){
            throw new BusinessException("该促销活动不能修改");
        }
        repository.removeById(dto.getId());
        QueryWrapper<MarketMerchantGroupbuyGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("groupbuy_id",dto.getId());
        iMarketMerchantGroupbuyGoodsRepository.remove(wrapper);
    }
    @Override
    public void editMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = new MarketMerchantGroupbuy();
        BeanUtils.copyProperties(eto, marketMerchantGroupbuy);
        if (ObjectUtils.isNotEmpty(marketMerchantGroupbuy.getIsCommit())) {
            if (marketMerchantGroupbuy.getIsCommit()) {
                throw new BusinessException("该促销活动不能修改");
            }
        }
        repository.updateById(marketMerchantGroupbuy);
        if (ObjectUtils.isNotEmpty(eto.getGoodsList())) {
            QueryWrapper<MarketMerchantGroupbuyGoods> wrapper = new QueryWrapper<>();
            wrapper.eq("groupbuy_id", eto.getId());
            iMarketMerchantGroupbuyGoodsRepository.remove(wrapper);
            QueryWrapper<MarketMerchantGroupbuyGoodsSku> wrapper1 = new QueryWrapper<>();
            wrapper.eq("groupbuy_id", eto.getId());
            iMarketMerchantGroupbuyGoodsSkuRepository.remove(wrapper1);
            saveGoodsAndSku(eto,marketMerchantGroupbuy);

        }
    }

    private void saveGoodsAndSku(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto,MarketMerchantGroupbuy marketMerchantGroupbuy) {
        List<PCMerchMarketMerchantGroupbuyDTO.SignGoods> goodsAll = eto.getGoodsList();
        List<PCMerchMarketMerchantGroupbuyDTO.SignGoodsSku> goodsSPUAll = eto.getGoodsSPUAll();
        if (ObjectUtils.isNotEmpty(goodsAll) && ObjectUtils.isNotEmpty(goodsSPUAll)){
            for (PCMerchMarketMerchantGroupbuyDTO.SignGoods signGoods : goodsAll) {
                MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods = new MarketMerchantGroupbuyGoods();
                marketMerchantGroupbuyGoods.setGroupbuyId(marketMerchantGroupbuy.getId()).
                        setMerchantId(marketMerchantGroupbuy.getMerchantId()).
                        setShopId(marketMerchantGroupbuy.getShopId()).
                        setGoodsId(signGoods.getGoodsId()).setOriginalPrice(signGoods.getOriginPrice());
                iMarketMerchantGroupbuyGoodsRepository.save(marketMerchantGroupbuyGoods);
                for (PCMerchMarketMerchantGroupbuyDTO.SignGoodsSku signGoodsSku : goodsSPUAll) {
                    if (marketMerchantGroupbuyGoods.getGoodsId().equals(signGoodsSku.getGoodsId())){
                        //是这个商品的SKU
                        MarketMerchantGroupbuyGoodsSku marketMerchantGroupbuyGoodsSku = new MarketMerchantGroupbuyGoodsSku();
                        marketMerchantGroupbuyGoodsSku.setGroupbuyId(marketMerchantGroupbuy.getId()).
                                setMerchantId(marketMerchantGroupbuy.getMerchantId()).
                                setShopId(marketMerchantGroupbuy.getShopId()).
                                setGoodsSpuItemId(marketMerchantGroupbuyGoods.getId()).
                                setSkuId(signGoodsSku.getSkuId()).
                                setGoodsId(signGoodsSku.getGoodsId()).
                                setGroupbuySaleSkuPrice(signGoodsSku.getActivitySalePrice());
                        iMarketMerchantGroupbuyGoodsSkuRepository.save(marketMerchantGroupbuyGoodsSku);
                    }
                }
            }
        }
    }


    @Override
    public PCMerchMarketMerchantGroupbuyVO.View detailMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.IdDTO dto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(dto.getId());
        PCMerchMarketMerchantGroupbuyVO.View detailVo = new PCMerchMarketMerchantGroupbuyVO.View();
        if(ObjectUtils.isEmpty(marketMerchantGroupbuy)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantGroupbuy, detailVo);
        List<PCMerchMarketMerchantGroupbuyVO.ViewGoodsGift> goodsView=new ArrayList<>();
        List<MarketMerchantGroupbuyGoods> marketMerchantGroupbuyGoods1 = SelectCartGoods(marketMerchantGroupbuy.getId(), marketMerchantGroupbuy.getMerchantId(), marketMerchantGroupbuy.getShopId());
        if (ObjectUtils.isNotEmpty(marketMerchantGroupbuyGoods1)) {
            for (MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods :marketMerchantGroupbuyGoods1) {
                PCMerchMarketMerchantGroupbuyVO.ViewGoodsGift viewGoodsGift = new PCMerchMarketMerchantGroupbuyVO.ViewGoodsGift();
                ArrayList<String> ids = new ArrayList<>();
                viewGoodsGift.setGoodsPrice(marketMerchantGroupbuyGoods.getOriginalPrice()).
                        setGroupbuyPrice(marketMerchantGroupbuyGoods.getGroupbuyPrice());
                if (StringUtils.isNotBlank(marketMerchantGroupbuyGoods.getGoodsId())) {
                    ids.add(marketMerchantGroupbuyGoods.getGoodsId());
                    viewGoodsGift.setGoodsId(marketMerchantGroupbuyGoods.getGoodsId());
                }
                PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO innerServiceGoodsInfoVO = null;
                if (ObjectUtils.isNotEmpty(ids)) {
                    List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfoVOS = ipcMerchAdminGoodsInfoRpc.innerServiceGoodsInfo(new PCMerchGoodsInfoDTO.IdsInnerServiceDTO().setGoodsIdList(ids).setQueryType(10));
                    System.out.println(innerServiceGoodsInfoVO+"11111111111111111111");
                    if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVOS)){
                        innerServiceGoodsInfoVO=innerServiceGoodsInfoVOS.get(0);
                        System.out.println(innerServiceGoodsInfoVO+"222222222222222222");
                        if (ObjectUtils.isNotEmpty(innerServiceGoodsInfoVO)) {
                            viewGoodsGift.setGoodsName(innerServiceGoodsInfoVO.getGoodsName()).setImageUrl(innerServiceGoodsInfoVO.getGoodsImage());
                        }
                    }
                }
                goodsView.add(viewGoodsGift);
            }
        }
        detailVo.setGoodsList(goodsView);
        return detailVo;
    }

    @Override
    public void commitMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(eto.getId());
        if (marketMerchantGroupbuy.getIsCommit()||marketMerchantGroupbuy.getIsCancel()){
            throw new BusinessException("此促销活动无法提交");
        }
        Long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long end=marketMerchantGroupbuy.getValidEndTime().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (now>end){
            throw new BusinessException("满增活动结束了,无法审核");
        }
        marketMerchantGroupbuy.setIsCommit(true);
        repository.updateById(marketMerchantGroupbuy);
    }

    @Override
    public void cancelMarketMerchantGroupbuy(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        MarketMerchantGroupbuy marketMerchantGroupbuy = repository.getById(eto.getId());
        if (!marketMerchantGroupbuy.getIsCommit()){
            throw new BusinessException("无法取消");
        }
        if (marketMerchantGroupbuy.getIsCommit()&&(marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.拒审.getCode())||marketMerchantGroupbuy.getState().equals(PlatformCardCheckStatusEnum.待审.getCode()))){
            throw new BusinessException("无法取消");
        }
        marketMerchantGroupbuy.setIsCancel(true);
        repository.updateById(marketMerchantGroupbuy);
    }

    private List<MarketMerchantGroupbuyGoods> SelectCartGoods(String id, String jwtMerchantId, String jwtShopId) {
        QueryWrapper<MarketMerchantGroupbuyGoods> wrapper = new QueryWrapper<>();
        wrapper.eq("groupbuy_id",id);
        return iMarketMerchantGroupbuyGoodsRepository.list(wrapper);
    }
}
