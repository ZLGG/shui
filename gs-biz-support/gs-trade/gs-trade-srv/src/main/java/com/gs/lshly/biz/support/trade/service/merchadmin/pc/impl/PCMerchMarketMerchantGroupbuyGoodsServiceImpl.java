package com.gs.lshly.biz.support.trade.service.merchadmin.pc.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantGroupbuyGoods;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantGroupbuyGoodsRepository;
import com.gs.lshly.biz.support.trade.service.merchadmin.pc.IPCMerchMarketMerchantGroupbuyGoodsService;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMarketDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyGoodsDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyGoodsQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyGoodsVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2020-12-10
*/
@Component
public class PCMerchMarketMerchantGroupbuyGoodsServiceImpl implements IPCMerchMarketMerchantGroupbuyGoodsService {

    @Autowired
    private IMarketMerchantGroupbuyGoodsRepository repository;

    @Override
    public PageData<PCMerchMarketMerchantGroupbuyGoodsVO.ListVO> pageData(PCMerchMarketMerchantGroupbuyGoodsQTO.QTO qto) {
        QueryWrapper<MarketMerchantGroupbuyGoods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<MarketMerchantGroupbuyGoods> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchMarketMerchantGroupbuyGoodsVO.ListVO.class, page);
    }

    @Override
    public void addMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto) {
        MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods = new MarketMerchantGroupbuyGoods();
        BeanUtils.copyProperties(eto, marketMerchantGroupbuyGoods);
        repository.save(marketMerchantGroupbuyGoods);
    }


    @Override
    public void deleteMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.ETO eto) {
        MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods = new MarketMerchantGroupbuyGoods();
        BeanUtils.copyProperties(eto, marketMerchantGroupbuyGoods);
        repository.updateById(marketMerchantGroupbuyGoods);
    }

    @Override
    public PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO detailMarketMerchantGroupbuyGoods(PCMerchMarketMerchantGroupbuyGoodsDTO.IdDTO dto) {
        MarketMerchantGroupbuyGoods marketMerchantGroupbuyGoods = repository.getById(dto.getId());
        PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO detailVo = new PCMerchMarketMerchantGroupbuyGoodsVO.DetailVO();
        if(ObjectUtils.isEmpty(marketMerchantGroupbuyGoods)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(marketMerchantGroupbuyGoods, detailVo);
        return detailVo;
    }

    @Override
    public CommonMarketDTO.MarketSku activeGroupbuySku(List<CommonMarketDTO.SkuId> skuIds, ActivityTerminalEnum terminal) {
        //团购spu的活动价
        List<String> spuIds = ListUtil.getIdList(CommonMarketDTO.SkuId.class, skuIds, "spuId");
        String idsStr = CollUtil.isNotEmpty(spuIds) ? CollUtil.join(spuIds, "','") : "-1";
        List<CommonMarketDTO.MarketSku> list = repository.baseMapper().activeSpuPrice(idsStr, terminal.getCode());
        CommonMarketDTO.MarketSku marketSku = CommonMarketDTO.MarketSku.pickBest(list, skuIds);
        return marketSku != null ? marketSku.setMarketName("商家团购") : null;
    }

}
