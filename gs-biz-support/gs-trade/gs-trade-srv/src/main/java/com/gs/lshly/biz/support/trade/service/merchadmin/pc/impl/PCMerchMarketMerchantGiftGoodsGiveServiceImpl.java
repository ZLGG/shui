package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGiftGoodsGive;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGiftGoodsGiveRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGiftGoodsGiveService;
import com.gs.lshly.common.enums.*;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeGoodsDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeGoodsDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftGoodsGiveDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftGoodsGiveQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftGoodsGiveVO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.math.BigDecimal;
import java.util.*;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-09
*/
@Component
@Slf4j
public class PCMerchMarketMerchantGiftGoodsGiveServiceImpl implements IPCMerchMarketMerchantGiftGoodsGiveService {

    @Autowired
    private IMarketMerchantGiftGoodsGiveRepository repository;

    @DubboReference
    private ICommonStockRpc commonStockRpc;
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    @DubboReference
    private IPCBbbGoodsInfoRpc bbbGoodsInfoRpc;

    @DubboReference
    private IBbbH5GoodsInfoRpc bbbH5GoodsInfoRpc;

    @Override
    public PageData<PCMerchMarketMerchantGiftGoodsGiveVO.ListVO> pageData(PCMerchMarketMerchantGiftGoodsGiveQTO.QTO qto) {
        QueryWrapper<MarketMerchantGiftGoodsGive> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<MarketMerchantGiftGoodsGive> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMarketMerchantGiftGoodsGiveVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto) {
        MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive = new MarketMerchantGiftGoodsGive();
        BeanUtils.copyProperties(eto, marketMerchantGiftGoodsGive);
        repository.save(marketMerchantGiftGoodsGive);
    }


    @Override
    public void deleteMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.ETO eto) {
        MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive = new MarketMerchantGiftGoodsGive();
        BeanUtils.copyProperties(eto, marketMerchantGiftGoodsGive);
        repository.updateById(marketMerchantGiftGoodsGive);
    }

    @Override
    public PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO detailMarketMerchantGiftGoodsGive(PCMerchMarketMerchantGiftGoodsGiveDTO.IdDTO dto) {
        MarketMerchantGiftGoodsGive marketMerchantGiftGoodsGive = repository.getById(dto.getId());
        PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO detailVo = new PCMerchMarketMerchantGiftGoodsGiveVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantGiftGoodsGive)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantGiftGoodsGive, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.SkuId activeGiveSku(List<CommonMarketDTO.SkuId> goods, ActivityTerminalEnum terminal) {
        //spu满赠
        List<String> spuIds = ListUtil.getIdList(CommonMarketDTO.SkuId.class, goods, "spuId");
        //1,查询满赠规则,(业务设置不严谨的情况下)一个spu会出现多个赠品规则
        String idsStr = CollUtil.isNotEmpty(spuIds) ? CollUtil.join(spuIds, "','") : "-1";
        List<CommonMarketDTO.SkuGive> gifts = repository.baseMapper().activeSpuGift(idsStr, terminal.getCode());

        //按spu分组合并规则
        Map<String, List<CommonMarketDTO.SkuGive>> allSkuCuts = CommonMarketDTO.SkuGive.initAllGiftToMap(gifts);
        //按spu分组商品
        Map<String, List<CommonMarketDTO.SkuId>> allSpu = CommonMarketDTO.SkuId.goodsGroupBySpuId(goods);
        //规则匹配,合并
        for (Map.Entry<String, List<CommonMarketDTO.SkuId>> spuEntry : allSpu.entrySet()) {
            //spu优惠券
            List<CommonMarketDTO.SkuGive> skuGiveRules = allSkuCuts.get(spuEntry.getKey());
            if (skuGiveRules == null || skuGiveRules.isEmpty()) {
                continue;
            }

            //排序所有规则,spu满赠 - 按 toCount 降序
            CommonMarketDTO.SkuGive.sort(skuGiveRules);
            log.info("对所有规则进行排序:" + CollUtil.join(skuGiveRules, ","));
            //spu分组商品-合并数量并进行规则匹配
            Integer totalSpuCount = 0;
            for (CommonMarketDTO.SkuId good : spuEntry.getValue()) {
                //累加spu分组价格
                totalSpuCount += good.getQuantity();
            }
            //匹配满减规则
            for (CommonMarketDTO.SkuGive rule : skuGiveRules) {
                //spu匹配上后,立即返回一个赠品
                if (totalSpuCount >= (rule.getToCount())) {
                    log.info("spuId[" + spuEntry.getKey() + "]匹配到规则:" + rule + ": 赠送SKU:" + rule.getGiveSkuId());
                    return new CommonMarketDTO.SkuId().setSkuId(rule.getGiveSkuId()).setQuantity(rule.getGiveQuantity());
                }
            }

        }

        return null;
    }

    /**
     * 库存检查
     * @param giveSkuId
     * @return
     */
    private boolean hasStock(CommonMarketDTO.SkuId giveSkuId) {
        List<CommonStockDTO.InnerSkuGoodsInfoItem> goodsItemList = new ArrayList<>();
        goodsItemList.add(new CommonStockDTO.InnerSkuGoodsInfoItem(giveSkuId.getSpuId(), giveSkuId.getSkuId(), giveSkuId.getQuantity()));
        CommonStockDTO.InnerCheckStockDTO innerListCheckStockDTO = new CommonStockDTO.InnerCheckStockDTO();
        innerListCheckStockDTO.setGoodsItemList(goodsItemList);
        ResponseData<CommonStockVO.InnerListCheckStockVO> checkStockVO = commonStockRpc.innerListCheckStock(innerListCheckStockDTO);
        if (checkStockVO==null || ResponseCodeEnum.失败.getCode().equals(checkStockVO.getCode())) {
            log.error("库存检查失败:" + JsonUtils.toJson(checkStockVO));
            return false;
        } else {
            if (checkStockVO.getData() == null) {
                return false;
            }
            if (checkStockVO.getData().getCheckItemList() == null || checkStockVO.getData().getCheckItemList().isEmpty()) {
                return false;
            }
            for (CommonStockVO.InnerCheckItem item : checkStockVO.getData().getCheckItemList()) {
                if (StockCheckStateEnum.库存正常.getCode().equals(item.getCheckState())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public BbcTradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVO(CommonMarketDTO.SkuId giveSkuId) {
        if (giveSkuId == null) {
            return null;
        }
        BbcGoodsInfoVO.InnerServiceVO innerServiceVO = giftGoodsInfoVO(giveSkuId);
        if (innerServiceVO == null) {
                return null;
        }
        return fillGoodsInfoVO(null, giveSkuId.getQuantity(), innerServiceVO);
    }

    @Override
    public BbcTradeGoodsDTO.ETO fillBbcGoodsInfoOrderVO(CommonMarketDTO.SkuId giveSkuId, BbcTradeBuildDTO.DTO dto) {
        if (giveSkuId == null) {
            return null;
        }
        BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = giftGoodsInfoVO(giveSkuId);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        return fillTradeGoodsDTO(dto.getJwtUserId(), dto.getShopId(), giveSkuId.getQuantity(), innerServiceGoodsVO);
    }

    private BbcGoodsInfoVO.InnerServiceVO giftGoodsInfoVO(CommonMarketDTO.SkuId giveSkuId) {
        if (!hasStock(giveSkuId)) {
            log.info("赠品已无库存.");
            return null;
        }
        BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbcGoodsInfoRpc.innerServiceGoodsVO(giveSkuId.getSkuId());
        // 无商品数据或已下架
        if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
            return null;
        }
        //商品已下架
        if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
            return null;
        }
        innerServiceGoodsVO.setSalePrice(BigDecimal.ZERO);
        innerServiceGoodsVO.setGoodsName("赠品:" + innerServiceGoodsVO.getGoodsName());
        innerServiceGoodsVO.setGoodsTitle("赠品:" + innerServiceGoodsVO.getGoodsTitle());
        return innerServiceGoodsVO;
    }

    /**
     * 组装订单商品表信息 - bbc
     * @param userId
     * @param shopId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbcTradeGoodsDTO.ETO fillTradeGoodsDTO(String userId,String shopId,Integer quantity, BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbcTradeGoodsDTO.ETO tradeGoodsDTO = new BbcTradeGoodsDTO.ETO();
        tradeGoodsDTO.setUserId(userId);
        tradeGoodsDTO.setShopId(shopId);
        tradeGoodsDTO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        tradeGoodsDTO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        tradeGoodsDTO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        tradeGoodsDTO.setGoodsNo(innerServiceGoodsVO.getGoodsNo());
        tradeGoodsDTO.setSkuId(innerServiceGoodsVO.getSkuId());
        tradeGoodsDTO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        tradeGoodsDTO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        tradeGoodsDTO.setSkuGoodsNo(innerServiceGoodsVO.getSkuGoodsNo());
        tradeGoodsDTO.setQuantity(quantity);
        tradeGoodsDTO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setPayAmount(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        return tradeGoodsDTO;
    }

    /**
     * 组装商品信息 - bbc
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbcTradeSettlementVO.ListVO.goodsInfoVO fillGoodsInfoVO(String carId,Integer quantity, BbcGoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbcTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = new BbcTradeSettlementVO.ListVO.goodsInfoVO();
        goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
        goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id
        goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        return goodsInfoVO;
    }


    @Override
    public BbbTradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVOBBB(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto) {
        if (giveSkuId == null) {
            return null;
        }
        PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = giftGoodsInfoVOBBB(giveSkuId, dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        return fillGoodsInfoVOBBB(null, giveSkuId.getQuantity(), innerServiceGoodsVO);
    }

    @Override
    public BbbTradeGoodsDTO.ETO fillBbcGoodsInfoOrderVOBBB(CommonMarketDTO.SkuId giveSkuId, BbbTradeBuildDTO.DTO dto) {
        if (giveSkuId == null) {
            return null;
        }
        PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = giftGoodsInfoVOBBB(giveSkuId, dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        return fillTradeGoodsDTOBBB(dto.getJwtUserId(), dto.getShopId(), giveSkuId.getQuantity(), innerServiceGoodsVO, null);
    }

    private PCBbbGoodsInfoVO.InnerServiceVO giftGoodsInfoVOBBB(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto) {
        if (!hasStock(giveSkuId)) {
            log.info("赠品已无库存.");
            return null;
        }
        PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbbGoodsInfoRpc.innerServiceVO(giveSkuId.getSkuId(), dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        // 无商品数据或已下架
        if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
            return null;
        }
        //商品已下架
        if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
            return null;
        }
        //检查库存

        innerServiceGoodsVO.setSalePrice(BigDecimal.ZERO);
        innerServiceGoodsVO.setGoodsName("赠品:" + innerServiceGoodsVO.getGoodsName());
        innerServiceGoodsVO.setGoodsTitle("赠品:" + innerServiceGoodsVO.getGoodsTitle());
        return innerServiceGoodsVO;
    }
    /**
     * 组装商品信息 - bbb - pc
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbTradeSettlementVO.ListVO.goodsInfoVO fillGoodsInfoVOBBB(String carId,Integer quantity, PCBbbGoodsInfoVO.InnerServiceVO  innerServiceGoodsVO) {
        BbbTradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = new BbbTradeSettlementVO.ListVO.goodsInfoVO();
        goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
        goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id
        goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        goodsInfoVO.setSockStatus(StockCheckStateEnum.库存正常.getCode());
        return goodsInfoVO;
    }

    /**
     * 组装订单商品表信息 - bbb - pc
     * @param userId
     * @param shopId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbTradeGoodsDTO.ETO fillTradeGoodsDTOBBB(String userId, String shopId, Integer quantity, PCBbbGoodsInfoVO.InnerServiceVO innerServiceGoodsVO,BigDecimal stepPrice) {
        BbbTradeGoodsDTO.ETO tradeGoodsDTO = new BbbTradeGoodsDTO.ETO();
        tradeGoodsDTO.setUserId(userId);
        tradeGoodsDTO.setShopId(shopId);
        tradeGoodsDTO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        tradeGoodsDTO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        tradeGoodsDTO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        tradeGoodsDTO.setGoodsNo(innerServiceGoodsVO.getGoodsNo());
        tradeGoodsDTO.setSkuId(innerServiceGoodsVO.getSkuId());
        tradeGoodsDTO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        tradeGoodsDTO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        tradeGoodsDTO.setSkuGoodsNo(innerServiceGoodsVO.getSkuGoodsNo());
        tradeGoodsDTO.setQuantity(quantity);
        tradeGoodsDTO.setSalePrice(ObjectUtils.isNotEmpty(stepPrice)?stepPrice:BigDecimal.ZERO);
        tradeGoodsDTO.setPayAmount(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        return tradeGoodsDTO;
    }


    @Override
    public BbbH5TradeSettlementVO.ListVO.goodsInfoVO fillBbcGoodsInfoSettlementVOBBBH5(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto) {
        if (giveSkuId == null) {
            return null;
        }
        BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO = giftGoodsInfoVOBBBH5(giveSkuId, dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        return fillGoodsInfoVOBBBH5(null, giveSkuId.getQuantity(), innerServiceGoodsVO);
    }

    @Override
    public BbbH5TradeGoodsDTO.ETO fillBbcGoodsInfoOrderVOBBBH5(CommonMarketDTO.SkuId giveSkuId, BbbH5TradeBuildDTO.DTO dto) {
        if (giveSkuId == null) {
            return null;
        }
        BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO = giftGoodsInfoVOBBBH5(giveSkuId, dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        return fillTradeGoodsDTOBBBH5(dto.getJwtUserId(), dto.getShopId(), giveSkuId.getQuantity(), innerServiceGoodsVO);
    }

    private BbbH5GoodsInfoVO.InnerServiceVO giftGoodsInfoVOBBBH5(CommonMarketDTO.SkuId giveSkuId, BaseDTO dto) {
        if (!hasStock(giveSkuId)) {
            log.info("赠品已无库存.");
            return null;
        }
        BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO = bbbH5GoodsInfoRpc.innerServiceVO(giveSkuId.getSkuId(), dto);
        if (innerServiceGoodsVO == null) {
            return null;
        }
        // 无商品数据或已下架
        if(ObjectUtils.isEmpty(innerServiceGoodsVO) || innerServiceGoodsVO.getGoodsId() == null){
            return null;
        }
        //商品已下架
        if(!innerServiceGoodsVO.getGoodsState().equals(GoodsStateEnum.已上架.getCode())){
            return null;
        }
        //检查库存
        innerServiceGoodsVO.setSalePrice(BigDecimal.ZERO);
        innerServiceGoodsVO.setGoodsName("赠品:" + innerServiceGoodsVO.getGoodsName());
        innerServiceGoodsVO.setGoodsTitle("赠品:" + innerServiceGoodsVO.getGoodsTitle());
        return innerServiceGoodsVO;
    }

    /**
     * 组装商品信息 bbb - h5
     * @param carId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbH5TradeSettlementVO.ListVO.goodsInfoVO fillGoodsInfoVOBBBH5(String carId,Integer quantity, BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbbH5TradeSettlementVO.ListVO.goodsInfoVO goodsInfoVO = new BbbH5TradeSettlementVO.ListVO.goodsInfoVO();
        if (ObjectUtils.isNotEmpty(innerServiceGoodsVO)){
            goodsInfoVO.setGoodsId(innerServiceGoodsVO.getGoodsId());
            goodsInfoVO.setGoodsName(innerServiceGoodsVO.getGoodsName());
            goodsInfoVO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
            goodsInfoVO.setSkuId(innerServiceGoodsVO.getSkuId());
            goodsInfoVO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
            goodsInfoVO.setSalePrice(innerServiceGoodsVO.getSalePrice());
            goodsInfoVO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        }
        goodsInfoVO.setQuantity(quantity);//购买数量
        goodsInfoVO.setCartId(carId);//购物车id

        return goodsInfoVO;
    }

    /**
     * 组装订单商品表信息 bbb - h5
     * @param userId
     * @param shopId
     * @param quantity
     * @param innerServiceGoodsVO
     * @return
     */
    private BbbH5TradeGoodsDTO.ETO fillTradeGoodsDTOBBBH5(String userId,String shopId,Integer quantity, BbbH5GoodsInfoVO.InnerServiceVO innerServiceGoodsVO) {
        BbbH5TradeGoodsDTO.ETO tradeGoodsDTO = new BbbH5TradeGoodsDTO.ETO();
        tradeGoodsDTO.setUserId(userId);
        tradeGoodsDTO.setShopId(shopId);
        tradeGoodsDTO.setGoodsId(innerServiceGoodsVO.getGoodsId());
        tradeGoodsDTO.setGoodsName(innerServiceGoodsVO.getGoodsName());
        tradeGoodsDTO.setGoodsTitle(innerServiceGoodsVO.getGoodsTitle());
        tradeGoodsDTO.setGoodsNo(innerServiceGoodsVO.getGoodsNo());
        tradeGoodsDTO.setSkuId(innerServiceGoodsVO.getSkuId());
        tradeGoodsDTO.setSkuSpecValue(StringUtils.isEmpty(innerServiceGoodsVO.getSkuSpecValue()) ? "" : innerServiceGoodsVO.getSkuSpecValue());
        tradeGoodsDTO.setSkuImg(innerServiceGoodsVO.getGoodsImage());
        tradeGoodsDTO.setSkuGoodsNo(innerServiceGoodsVO.getSkuGoodsNo());
        tradeGoodsDTO.setQuantity(quantity);
        tradeGoodsDTO.setSalePrice(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setPayAmount(innerServiceGoodsVO.getSalePrice());
        tradeGoodsDTO.setCommentFlag(TradeTrueFalseEnum.是.getCode());
        return tradeGoodsDTO;
    }
}
