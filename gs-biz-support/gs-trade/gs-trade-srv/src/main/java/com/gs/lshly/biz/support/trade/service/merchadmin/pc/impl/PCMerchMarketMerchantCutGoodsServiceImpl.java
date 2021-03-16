package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCutGoods;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCutGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantCutGoodsService;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutGoodsVO;
import com.gs.lshly.common.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-08
*/
@Component
@Slf4j
public class PCMerchMarketMerchantCutGoodsServiceImpl implements IPCMerchMarketMerchantCutGoodsService {

    @Autowired
    private IMarketMerchantCutGoodsRepository repository;

    @Override
    public PageData<PCMerchMarketMerchantCutGoodsVO.ListVO> pageData(PCMerchMarketMerchantCutGoodsQTO.QTO qto) {
        QueryWrapper<MarketMerchantCutGoods> wrapper = new QueryWrapper<>();
        IPage<MarketMerchantCutGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMarketMerchantCutGoodsVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto) {
        MarketMerchantCutGoods marketMerchantCutGoods = new MarketMerchantCutGoods();
        BeanUtils.copyProperties(eto, marketMerchantCutGoods);
        repository.save(marketMerchantCutGoods);
    }


    @Override
    public void deleteMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.ETO eto) {
        MarketMerchantCutGoods marketMerchantCutGoods = new MarketMerchantCutGoods();
        BeanUtils.copyProperties(eto, marketMerchantCutGoods);
        repository.updateById(marketMerchantCutGoods);
    }

    @Override
    public PCMerchMarketMerchantCutGoodsVO.DetailVO detailMarketMerchantCutGoods(PCMerchMarketMerchantCutGoodsDTO.IdDTO dto) {
        MarketMerchantCutGoods marketMerchantCutGoods = repository.getById(dto.getId());
        PCMerchMarketMerchantCutGoodsVO.DetailVO detailVo = new PCMerchMarketMerchantCutGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantCutGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantCutGoods, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.MarketSku activeCutPriceSku(List<CommonMarketDTO.SkuId> goods, ActivityTerminalEnum terminal) {
        //spu满减
        List<String> spuIds = ListUtil.getIdList(CommonMarketDTO.SkuId.class, goods, "spuId");
        //1,查询满减规则,(业务设置不严谨的情况下)一个spu可能存在多个
        String idsStr = CollUtil.isNotEmpty(spuIds) ? CollUtil.join(spuIds, "','") : "-1";
        List<CommonMarketDTO.SkuCutRule> cuts = repository.baseMapper().activeCutSpuRule(idsStr, terminal.getCode());

        //按spu分组合并规则
        Map<String, List<CommonMarketDTO.SkuCutRule>> allSkuCuts = CommonMarketDTO.SkuCutRule.initAllRuleToMap(cuts);

        //按spu分组商品
        Map<String, List<CommonMarketDTO.SkuId>> allSpu = CommonMarketDTO.SkuId.goodsGroupBySpuId(goods);

        //规则匹配,合并
        List<CommonMarketDTO.MarketSku> result = new ArrayList<>();
        for (Map.Entry<String, List<CommonMarketDTO.SkuId>> spuEntry : allSpu.entrySet()) {
            //spu满折价格规则
            List<CommonMarketDTO.ToCutPrice> prices = CommonMarketDTO.SkuCutRule.allCutPrice(allSkuCuts.get(spuEntry.getKey()));
            if (prices == null || prices.isEmpty()) {
                continue;
            }

            //排序所有规则,spu瞒折 - 先按 cut_price 升序, 再按 to_price 升序 需2次排序
            CommonMarketDTO.SkuDiscountRule.sort(prices);
            log.info("--spuId[" + spuEntry.getKey() + "]的所有[满减]规则, 并排序:[{}]" , CollUtil.join(prices, ","));

            //spu分组商品-合并价格并进行规则匹配
            BigDecimal totalSpuPrice = CommonMarketDTO.SkuId.calcTotalSalePrice(spuEntry.getValue());
            //匹配满减规则
            CommonMarketDTO.ToCutPrice spuCutPrice = CommonMarketDTO.ToCutPrice.match(prices, totalSpuPrice);
            if (spuCutPrice == null) {
                continue;
            }

            log.info("--spuId[" + spuEntry.getKey() + "]的[满减]优惠价格为:" + spuCutPrice);

            //将优惠价格打散到spu商品分组内的各商品中.
            CommonMarketDTO.MarketSku marketSku = CommonMarketDTO.MarketSku.calcMarketSkuPrice("满", "减", spuEntry, totalSpuPrice, spuCutPrice);

            result.add(marketSku);
        }

        return CommonMarketDTO.SkuId.calcBestMarketSku(result, goods);
    }

}
