package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantDiscountGoods;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantDiscountGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantDiscountGoodsService;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountGoodsVO;
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
* @since 2020-12-09
*/
@Component
@Slf4j
public class PCMerchMarketMerchantDiscountGoodsServiceImpl implements IPCMerchMarketMerchantDiscountGoodsService {

    @Autowired
    private IMarketMerchantDiscountGoodsRepository repository;

    @Override
    public PageData<PCMerchMarketMerchantDiscountGoodsVO.ListVO> pageData(PCMerchMarketMerchantDiscountGoodsQTO.QTO qto) {
        QueryWrapper<MarketMerchantDiscountGoods> wrapper = new QueryWrapper<>();
        IPage<MarketMerchantDiscountGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMarketMerchantDiscountGoodsVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto) {
        MarketMerchantDiscountGoods marketMerchantDiscountGoods = new MarketMerchantDiscountGoods();
        BeanUtils.copyProperties(eto, marketMerchantDiscountGoods);
        repository.save(marketMerchantDiscountGoods);
    }


    @Override
    public void deleteMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.ETO eto) {
        MarketMerchantDiscountGoods marketMerchantDiscountGoods = new MarketMerchantDiscountGoods();
        BeanUtils.copyProperties(eto, marketMerchantDiscountGoods);
        repository.updateById(marketMerchantDiscountGoods);
    }

    @Override
    public PCMerchMarketMerchantDiscountGoodsVO.DetailVO detailMarketMerchantDiscountGoods(PCMerchMarketMerchantDiscountGoodsDTO.IdDTO dto) {
        MarketMerchantDiscountGoods marketMerchantDiscountGoods = repository.getById(dto.getId());
        PCMerchMarketMerchantDiscountGoodsVO.DetailVO detailVo = new PCMerchMarketMerchantDiscountGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantDiscountGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantDiscountGoods, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.MarketSku activeDiscountPriceSku(List<CommonMarketDTO.SkuId> goods, ActivityTerminalEnum terminal) {
        //spu满折
        List<String> spuIds = ListUtil.getIdList(CommonMarketDTO.SkuId.class, goods, "spuId");
        //1,查询满折规则,(业务设置不严谨的情况下)一个spu会出现多个规则
        String idsStr = CollUtil.isNotEmpty(spuIds) ? CollUtil.join(spuIds, "','") : "-1";
        List<CommonMarketDTO.SkuDiscountRule> discounts = repository.baseMapper().activeDiscountSpuRule(idsStr, terminal.getCode());
        //按spu分组合并规则
        Map<String, List<CommonMarketDTO.SkuDiscountRule>> allSkuDiscount = CommonMarketDTO.SkuDiscountRule.initAllRuleToMap(discounts);
        //按spu分组商品
        Map<String, List<CommonMarketDTO.SkuId>> allSpu = CommonMarketDTO.SkuId.goodsGroupBySpuId(goods);
        //规则匹配,合并
        List<CommonMarketDTO.MarketSku> result = new ArrayList<>();
        for (Map.Entry<String, List<CommonMarketDTO.SkuId>> spuEntry : allSpu.entrySet()) {

            //spu满折价格规则
            List<CommonMarketDTO.ToCutPrice> prices = CommonMarketDTO.SkuDiscountRule.allCutPrice(allSkuDiscount.get(spuEntry.getKey()));
            if (prices == null || prices.isEmpty()) {
                continue;
            }

            //排序所有规则,spu瞒折 - 先按 cut_price 升序, 再按 to_price 升序 需2次排序
            CommonMarketDTO.SkuDiscountRule.sort(prices);
            log.info("--spuId[" + spuEntry.getKey() + "]的所有[满折]规则, 并排序:[{}]" , CollUtil.join(prices, ","));

            //spu分组商品-合并价格并进行规则匹配
            BigDecimal totalSpuPrice = CommonMarketDTO.SkuId.calcTotalSalePrice(spuEntry.getValue());
            //匹配满减规则
            CommonMarketDTO.ToCutPrice spuCutPrice = CommonMarketDTO.ToCutPrice.match(prices, totalSpuPrice);
            if (spuCutPrice == null) {
                continue;
            }

            log.info("--spuId[" + spuEntry.getKey() + "]的[满折]优惠价格为:" + spuCutPrice);

            //将优惠价格打散到spu商品分组内的各商品中.
            CommonMarketDTO.MarketSku marketSku = CommonMarketDTO.MarketSku.calcMarketSkuPrice("满", "折", spuEntry, totalSpuPrice, spuCutPrice);
            result.add(marketSku);
        }

        return CommonMarketDTO.SkuId.calcBestMarketSku(result, goods);
    }

}
