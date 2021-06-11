package com.gs.lshly.biz.support.stock.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.entity.StockTemp;
import com.gs.lshly.biz.support.stock.repository.IStockTempRepository;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTempService;
import com.gs.lshly.common.enums.StockLocationEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommonStockTempServiceImpl implements ICommonStockTempService {

    @Autowired
    private IStockTempRepository repository;

    @Override
    public Boolean innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto) {
        if (ObjectUtils.isEmpty(dto.getGoodsItemList())) {
            ResponseData.fail("库存变动商品项不能为空");
        }
        List<String> skuIdList = ListUtil.getIdList(CommonStockDTO.InnerChangeStockItem.class, dto.getGoodsItemList(), "skuId");
        QueryWrapper<StockTemp> stockQueryWrapper = MybatisPlusUtil.query();
        stockQueryWrapper.in("sku_id", skuIdList);
        stockQueryWrapper.eq("shop_id", dto.getShopId());
        List<StockTemp> stockList = repository.list(stockQueryWrapper);
        Map<String, StockTemp> skuStockMap = new HashMap();
        for (StockTemp stock : stockList) {
            skuStockMap.put(stock.getSkuId(), stock);
        }

        for (CommonStockDTO.InnerChangeStockItem changeStockItem : dto.getGoodsItemList()) {
            StockTemp stock = skuStockMap.get(changeStockItem.getSkuId());
            if (null == stock) {
                stock = new StockTemp();
                BeanUtils.copyProperties(changeStockItem, stock);
                stock.setShopId(dto.getShopId());
                stock.setMerchantId(dto.getMerchantId());
                skuStockMap.put(changeStockItem.getSkuId(), stock);
            }
            if (StockLocationEnum.初始化.getCode().equals(dto.getLocation())) {
                stock.setQuantity(changeStockItem.getQuantity());
            }
        }
        List<StockTemp> stockBatchList = new ArrayList<>(skuStockMap.values());
        return repository.saveOrUpdateBatch(stockBatchList);
    }

    @Override
    public ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId) {
        QueryWrapper<StockTemp> stockQueryWrapper = MybatisPlusUtil.query();
        stockQueryWrapper.eq("shop_id", shopId);
        stockQueryWrapper.eq("sku_id", skuId);
        StockTemp stock = repository.getOne(stockQueryWrapper);
        if (null == stock) {
            return ResponseData.fail("仓库项不存在");
        }
        CommonStockVO.InnerStockVO innerStockVO = new CommonStockVO.InnerStockVO();
        innerStockVO.setGoodsId(stock.getGoodsId());
        innerStockVO.setSkuId(stock.getSkuId());
        innerStockVO.setQuantity(stock.getQuantity());
        return ResponseData.data(innerStockVO);
    }
}
