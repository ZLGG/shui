package com.gs.lshly.biz.support.trade.service.bbb.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.gs.lshly.biz.support.trade.enums.MarketPtCardStatusEnum;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.biz.support.trade.service.bbb.pc.IPCBbbMarketMerchantCardUsersService;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbMarketMerchantCardUsersDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-01-08
*/
@Component
@Slf4j
public class PCBbbMarketMerchantCardUsersServiceImpl implements IPCBbbMarketMerchantCardUsersService {

    @Autowired
    private IMarketMerchantCardUsersRepository repository;

    @Override
    public PageData<PCBbbMarketMerchantCardUsersVO.PageData> pageData(PCBbbMarketMerchantCardUsersQTO.QTO qto) {
        QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> query = MybatisPlusUtil.query();
        IPage<PCBbbMarketMerchantCardUsersVO.PageData> pager = MybatisPlusUtil.pager(qto);
        query.and(i->i.eq("td.`user_id`",qto.getJwtUserId()));
        if (ObjectUtils.isNotEmpty(qto.getState())){
            switch (qto.getState()){
                case 10:query.and(i->i.eq("td.`state`",MarketPtCardStatusEnum.未使用.getCode()));break;
                case 20:query.and(i->i.eq("td.`state`",MarketPtCardStatusEnum.已使用.getCode()));break;
                case 30:query.and(i->i.lt("td.`valid_end_time`",LocalDateTime.now()));break;
            }
        }
        if (ObjectUtils.isNotEmpty(qto.getExpirationTime())){
            switch (qto.getExpirationTime()){
                case 10:query.orderByAsc("td.`valid_end_time`");
                case 20:query.orderByDesc("td.`valid_end_time`");
            }
        }else if (ObjectUtils.isNotEmpty(qto.getPreferentialAmount())){
            switch (qto.getPreferentialAmount()){
                case 10:query.orderByAsc("td.`cut_price`");
                case 20:query.orderByDesc("td.`cut_price`");
            }
        }
        repository.selectListPage(pager,query);
        if (ObjectUtils.isNotEmpty(query) || ObjectUtils.isNotEmpty(pager)){
            return MybatisPlusUtil.toPageData(qto, PCBbbMarketMerchantCardUsersVO.PageData.class, pager);
        }

        return new PageData<>();
    }

    @Override
    public void addMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto) {
        MarketMerchantCardUsers marketMerchantCardUsers = new MarketMerchantCardUsers();
        BeanUtils.copyProperties(eto, marketMerchantCardUsers);
        repository.save(marketMerchantCardUsers);
    }


    @Override
    public void deleteMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.ETO eto) {
        MarketMerchantCardUsers marketMerchantCardUsers = new MarketMerchantCardUsers();
        BeanUtils.copyProperties(eto, marketMerchantCardUsers);
        repository.updateById(marketMerchantCardUsers);
    }

    @Override
    public PCBbbMarketMerchantCardUsersVO.DetailVO detailMarketMerchantCardUsers(PCBbbMarketMerchantCardUsersDTO.IdDTO dto) {
        MarketMerchantCardUsers marketMerchantCardUsers = repository.getById(dto.getId());
        PCBbbMarketMerchantCardUsersVO.DetailVO detailVo = new PCBbbMarketMerchantCardUsersVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantCardUsers)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantCardUsers, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.MarketSku activeCardSku(String jwtUserId, List<CommonMarketDTO.SkuId> goods, ActivityTerminalEnum terminal) {
        //spu满折
        List<String> spuIds = ListUtil.getIdList(CommonMarketDTO.SkuId.class, goods, "spuId");
        //1,查询用户优惠券,(业务设置不严谨的情况下)一个spu会出现多个规则
        String idsStr = CollUtil.isNotEmpty(spuIds) ? CollUtil.join(spuIds, "','") : "-1";
        //用户所持有的可用sku优惠券
        List<CommonMarketDTO.SkuCard> cardUsers = repository.baseMapper().activeSkuCard(idsStr, terminal.getCode(), jwtUserId);

        //规则合并
        Map<String, List<CommonMarketDTO.SkuCard>> allUserCards = CommonMarketDTO.SkuCard.initAllRuleToMap(cardUsers);

        //按spu分组商品
        Map<String, List<CommonMarketDTO.SkuId>> allSpu = CommonMarketDTO.SkuId.goodsGroupBySpuId(goods);

        //规则匹配,合并
        List<CommonMarketDTO.MarketSku> result = new ArrayList<>();
        for (Map.Entry<String, List<CommonMarketDTO.SkuId>> spuEntry : allSpu.entrySet()) {
            //spu优惠券
            List<CommonMarketDTO.SkuCard> spuCards = allUserCards.get(spuEntry.getKey());
            if (spuCards == null || spuCards.isEmpty()) {
                continue;
            }
            //spu优惠券排序,先按 cut_price 降序; 在按 to_price 升序
            spuCards.sort((o1, o2) -> o1.getCutPrice().compareTo(o2.getCutPrice()) > 0 ? -1 : 1);
            spuCards.sort((o1, o2) -> (o1.getCutPrice().compareTo(o2.getCutPrice()) == 0 && o2.getToPrice().compareTo(o1.getToPrice()) > 0) ? -1 : 1);

            //spu分组商品-合并价格并进行规则匹配
            BigDecimal totalSpuPrice = CommonMarketDTO.SkuId.calcTotalSalePrice(spuEntry.getValue());
            //匹配满减规则
            CommonMarketDTO.ToCutPrice spuCutPrice = CommonMarketDTO.SkuCard.match(spuCards, totalSpuPrice);
            if (spuCutPrice == null) {
                continue;
            }

            log.info("--spuId[" + spuEntry.getKey() + "]的[使用优惠券]优惠价格为:" + spuCutPrice);

            //将优惠价格打散到spu商品分组内的各商品中.
            CommonMarketDTO.MarketSku marketSku = CommonMarketDTO.MarketSku.calcMarketSkuPrice("优惠券:满", "减", spuEntry, totalSpuPrice, spuCutPrice);
            result.add(marketSku);
        }

        return CommonMarketDTO.SkuId.calcBestMarketSku(result, goods, false);
    }

}
