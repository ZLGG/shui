package com.gs.lshly.biz.support.stock.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.mapper.StockMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockRoliceView;
import com.gs.lshly.biz.support.stock.repository.IStockRepository;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCMerchStockService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.stock.vo.PCMerchStockVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* <p>
*  服务实现类
* </p>
* @author zhaozhigang
* @since 2020-10-22
*/
@Component
public class PCMerchStockServiceImpl implements IPCMerchStockService {

    @Autowired
    private IStockRepository repository;

    @Autowired
    private StockMapper  stockMapper;

    @Override
    public PCMerchStockVO.StockAlarmGoodsIdListVO storeCall(String shopId, Integer changeQuantity) {
        QueryWrapper<Stock> stockWrap = MybatisPlusUtil.query();
        stockWrap.eq("shop_id",shopId);
        List<Stock> listStock = repository.list(stockWrap);

        PCMerchStockVO.StockAlarmGoodsIdListVO   ListShopVoList = new PCMerchStockVO.StockAlarmGoodsIdListVO();


        List<String>  goodsIdList = new ArrayList<>();
        List<String> goodsId = listStock.stream().map(Stock::getGoodsId)
                .distinct().collect(Collectors.toList());
        for (String id:goodsId ){
            //获取spu商品的总库存数
            int spuStockNum =  stockMapper.selectSum(id);
            if (spuStockNum <= changeQuantity){
                goodsIdList.add(id);
            }
        }
        ListShopVoList.setGoodsIdList(goodsIdList);

        return ListShopVoList;
    }

    @Override
    public List<PCMerchStockVO.InnerRoliceVO> innerStockRolice(BaseDTO baseDTO, Integer changeQuantity, String shopId) {
       List<StockRoliceView> viewList = stockMapper.mapperStockRoliceQuery(changeQuantity,shopId);
       if(ObjectUtils.isNotEmpty(viewList)){
          return  ListUtil.listCover(PCMerchStockVO.InnerRoliceVO.class,viewList);
       }
       return new ArrayList<>();
    }


}
