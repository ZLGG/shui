package com.gs.lshly.biz.support.stock.service.common.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.gs.lshly.biz.support.stock.entity.StockLog;
import com.gs.lshly.biz.support.stock.mapper.StockLogMapper;
import com.gs.lshly.biz.support.stock.mapper.StockMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockLogView;
import com.gs.lshly.biz.support.stock.mapper.view.StockRoliceView;
import com.gs.lshly.biz.support.stock.repository.IStockLogRepository;
import com.gs.lshly.biz.support.stock.repository.IStockRepository;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockService;
import com.gs.lshly.common.enums.StockCheckStateEnum;
import com.gs.lshly.common.enums.StockDataFromTypeEnum;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-02
*/
@Component
public class CommonStockServiceImpl implements ICommonStockService {

    @Autowired
    private IStockRepository repository;

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private IStockLogRepository stockLogRepository;

    @Autowired
    private StockLogMapper stockLogMapper;


    @Override
    public ResponseData<CommonStockVO.InnerCheckStockVO> innerCheckStock(BaseDTO dto, String shopId, String skuId, Integer quantity) {

        QueryWrapper<Stock> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("sku_id",skuId);
        Stock stock =  repository.getOne(queryWrapper);
        if(null == stock){
           return  ResponseData.fail(StockCheckStateEnum.库存项失败.getRemark());
        }
        CommonStockVO.InnerCheckStockVO innerCheckStockVO = new CommonStockVO.InnerCheckStockVO();
        if(stock.getQuantity() >= quantity){
            innerCheckStockVO.setCheckState(StockCheckStateEnum.库存正常.getCode());
        }else{
            innerCheckStockVO.setCheckState(StockCheckStateEnum.库存不足.getCode());
        }
        return ResponseData.data(innerCheckStockVO);
    }

    @Override
    public ResponseData<CommonStockVO.InnerListCheckStockVO> innerListCheckStock(CommonStockDTO.InnerCheckStockDTO dto) {
        List<String> skuIdList = new ArrayList<>();
        Map<String,CommonStockDTO.InnerSkuGoodsInfoItem> dtoMap = new HashMap<>();
        for(CommonStockDTO.InnerSkuGoodsInfoItem goodsInfoItem:dto.getGoodsItemList()){
            skuIdList.add(goodsInfoItem.getSkuId());
            dtoMap.put(goodsInfoItem.getSkuId(),goodsInfoItem);
        }
        if(ObjectUtils.isEmpty(skuIdList)){
            return ResponseData.fail("sku-id不能为空");
        }
        QueryWrapper<Stock> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("sku_id",skuIdList);
        List<Stock> stockList =  repository.list(queryWrapper);
        if(ObjectUtils.isEmpty(stockList)){
            return ResponseData.fail(StockCheckStateEnum.库存项失败.getRemark());
        }
        CommonStockVO.InnerListCheckStockVO checkStockVO = new CommonStockVO.InnerListCheckStockVO();
        checkStockVO.setCheckState(StockCheckStateEnum.库存正常.getCode());
        checkStockVO.setShopId(stockList.get(0).getShopId());
        checkStockVO.setCheckItemList(new ArrayList<>());
        for(Stock stock:stockList){
            CommonStockDTO.InnerSkuGoodsInfoItem goodsInfoItem = dtoMap.get(stock.getSkuId());
            if(null == goodsInfoItem){
                checkStockVO.setCheckState(StockCheckStateEnum.库存项失败.getCode());
            }else{
                CommonStockVO.InnerCheckItem checkItem = new CommonStockVO.InnerCheckItem();
                BeanUtils.copyProperties(stock,checkItem);
                if(null==stock.getQuantity() || null == goodsInfoItem.getQuantity() || stock.getQuantity() < 1 || stock.getQuantity() < goodsInfoItem.getQuantity()){
                    checkItem.setCheckState(StockCheckStateEnum.库存不足.getCode());
                    checkStockVO.setCheckState(StockCheckStateEnum.库存不足.getCode());
                }else{
                    checkItem.setCheckState(StockCheckStateEnum.库存正常.getCode());
                }
                checkStockVO.getCheckItemList().add(checkItem);
            }
        }
        return ResponseData.data(checkStockVO);
    }

    @Override
    public List<String> innerListGoodsNotEmpytStock(List<String> goodsIdList) {
        List<String> idList = new ArrayList<>();
        if(ObjectUtils.isNotEmpty(goodsIdList)){
            QueryWrapper<StockRoliceView> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.in("goods_id",goodsIdList);
            queryWrapper.gt("quantity",0);
            queryWrapper.groupBy("goods_id");
            List<StockRoliceView> viewList = stockMapper.mapperListGoodsNotEmpytStock(queryWrapper);
            if(ObjectUtils.isNotEmpty(viewList)){
                for(StockRoliceView view:viewList){
                    idList.add(view.getGoodsId());
                }
            }
        }
        return idList;
    }

    @Override
    public ResponseData<List<CommonStockVO.InnerCountSalesVO>> innerListSalesVolume(BaseDTO dto, List<String> goodsIdList) {
        if(ObjectUtils.isEmpty(goodsIdList)){
            return ResponseData.fail("商品项数组不能为空");
        }
        QueryWrapper<StockLog> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("location",StockLocationEnum.减少.getCode());
        queryWrapper.eq("data_from_type", StockDataFromTypeEnum.销售订单.getCode());
        queryWrapper.in("goods_id",goodsIdList);
        List<StockLogView> viewList = stockLogMapper.sumSalesVolume(queryWrapper);
        List<CommonStockVO.InnerCountSalesVO> voList = new ArrayList<>();
        for(String goodsId:goodsIdList){
            StockLogView viewItem = null;
            CommonStockVO.InnerCountSalesVO  vo = new  CommonStockVO.InnerCountSalesVO();
            if(ObjectUtils.isEmpty(viewList)){
                for(StockLogView view:viewList){
                    if(null != view){
                        if(view.getGoods_id().equals(goodsId)){
                            viewItem = view;
                            break;
                        }
                    }
                }
            }
            if(null != viewItem){
                vo.setGoodsId(viewItem.getGoods_id());
                vo.setSalesVolume(viewItem.getSales_volume());
            }else{
                vo.setGoodsId(goodsId);
                vo.setSalesVolume(0);
            }
        }
        return ResponseData.data(voList);
    }

    @Override
    public ResponseData<CommonStockVO.InnerCountSalesVO> innerSalesVolume(BaseDTO dto, String goodsId) {
        QueryWrapper<StockLog> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("location",StockLocationEnum.减少.getCode());
        queryWrapper.eq("data_from_type", StockDataFromTypeEnum.销售订单.getCode());
        queryWrapper.eq("goods_id",goodsId);
        List<StockLogView> viewList = stockLogMapper.sumSalesVolume(queryWrapper);
        CommonStockVO.InnerCountSalesVO countSalesVO = new CommonStockVO.InnerCountSalesVO();
        if(ObjectUtils.isEmpty(viewList)){
            countSalesVO.setGoodsId(goodsId);
            countSalesVO.setSalesVolume(0);
        }else {
            countSalesVO.setGoodsId(goodsId);
            countSalesVO.setSalesVolume(viewList.get(0)!=null?viewList.get(0).getSales_volume():0);
        }
           return ResponseData.data(countSalesVO);
    }

    @Override
    public ResponseData<CommonStockVO.InnerComplexCountSalesVO> innerComplexSalesVolume(CommonStockDTO.InnerCountSalesDTO dto) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResponseData<CommonStockVO.InnerChangeStockVO> innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto) {
        if(ObjectUtils.isNotEmpty(dto.getGoodsItemList())){
            ResponseData.fail("库存变动商品项不能为空");
        }
        List<String> skuIdList = ListUtil.getIdList(CommonStockDTO.InnerChangeStockItem.class,dto.getGoodsItemList(),"skuId");
        QueryWrapper<Stock> stockQueryWrapper = MybatisPlusUtil.query();
        stockQueryWrapper.in("sku_id",skuIdList);
        stockQueryWrapper.eq("shop_id",dto.getShopId());
        List<Stock> stockList =  repository.list(stockQueryWrapper);
        Map<String,Stock> skuStockMap = new HashMap();
        for(Stock stock:stockList){
            skuStockMap.put(stock.getSkuId(),stock);
        }

        for(CommonStockDTO.InnerChangeStockItem changeStockItem:dto.getGoodsItemList()){
            Stock stock = skuStockMap.get(changeStockItem.getSkuId());
            if(null == stock){
                stock = new Stock();
                BeanUtils.copyProperties(changeStockItem,stock);
                stock.setShopId(dto.getShopId());
                stock.setMerchantId(dto.getMerchantId());
                skuStockMap.put(changeStockItem.getSkuId(),stock);
            }
            if(StockLocationEnum.初始化.getCode().equals(dto.getLocation())){
                stock.setQuantity(changeStockItem.getQuantity());
            }else{
                if(StockLocationEnum.减少.getCode().equals(dto.getLocation())){
                    stock.setQuantity(stock.getQuantity() - changeStockItem.getQuantity());
                    if(stock.getQuantity() < 0){
                        stock.setQuantity(0);
                    }
                }else if(StockLocationEnum.增加.getCode().equals(dto.getLocation())){
                    stock.setQuantity(stock.getQuantity() + changeStockItem.getQuantity());
                }
            }
        }
        List<Stock> stockBatchList = new ArrayList<>(skuStockMap.values());
        repository.saveOrUpdateBatch(stockBatchList);
        List<StockLog> stockLogList = new ArrayList<>();
        CommonStockVO.InnerChangeStockVO changeStockVO = new CommonStockVO.InnerChangeStockVO();
        changeStockVO.setGoodsInfoItemList(new ArrayList<>());
        for(Stock stock:stockBatchList){
            //生成当前库存返回
            CommonStockVO.InnerGoodsInfoItem goodsInfoItem = new CommonStockVO.InnerGoodsInfoItem();
            BeanUtils.copyProperties(stock,goodsInfoItem);
            changeStockVO.getGoodsInfoItemList().add(goodsInfoItem);
            //生成日志
            StockLog stockLog   = new StockLog();
            stockLog.setStockId(stock.getId());
            stockLog.setShopId(dto.getShopId());
            stockLog.setDataFromType(dto.getDataFromType());
            stockLog.setGoodsId(stock.getGoodsId());
            stockLog.setSkuId(stock.getSkuId());
            stockLog.setLocation(dto.getLocation());
            stockLogList.add(stockLog);
        }
        stockLogRepository.saveBatch(stockLogList);
        return ResponseData.data(changeStockVO);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean innerSubtractStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        return this.subtractStock(goodsItemList,StockDataFromTypeEnum.销售订单.getCode());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean innerPlusStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        return this.plusStock(goodsItemList,StockDataFromTypeEnum.取消订单.getCode());
    }


    public boolean subtractStock(List<CommonStockDTO.InnerChangeStockItem> goodsItemList,Integer dataFromType) {

        if(ObjectUtils.isNotEmpty(goodsItemList)){
            List<String> skuIdList = new ArrayList<>();
            Map<String,CommonStockDTO.InnerChangeStockItem> dtoMap = new HashMap<>();
            for(CommonStockDTO.InnerChangeStockItem changeStockItem:goodsItemList){
                dtoMap.put(changeStockItem.getSkuId(),changeStockItem);
                skuIdList.add(changeStockItem.getSkuId());
            }
            QueryWrapper<Stock> stockQueryWrapper = MybatisPlusUtil.query();
            stockQueryWrapper.in("sku_id",skuIdList);
            List<Stock> stockList =  repository.list(stockQueryWrapper);
            List<StockLog> stockLogList = new ArrayList<>();
            for(Stock stock: stockList){
                CommonStockDTO.InnerChangeStockItem dtoItem =   dtoMap.get(stock.getSkuId());
                stock.setQuantity(stock.getQuantity() - dtoItem.getQuantity());
                if(stock.getQuantity() < 0 ){
                    stock.setQuantity(0);
                }
                //生成日志
                StockLog stockLog   = new StockLog();
                stockLog.setStockId(stock.getId());
                stockLog.setShopId(stock.getShopId());
                stockLog.setMerchantId(stock.getMerchantId());
                stockLog.setDataFromType(dataFromType);
                stockLog.setGoodsId(stock.getGoodsId());
                stockLog.setSkuId(stock.getSkuId());
                stockLog.setLocation(StockLocationEnum.减少.getCode());
                stockLogList.add(stockLog);
            }
            repository.updateBatchById(stockList);
            stockLogRepository.saveBatch(stockLogList);
            return true;
        }
        return false;
    }


    private boolean plusStock(List<CommonStockDTO.InnerChangeStockItem> goodsItemList,Integer dataFromType) {
        if(ObjectUtils.isNotEmpty(goodsItemList)){
            List<String> skuIdList = new ArrayList<>();
            Map<String,CommonStockDTO.InnerChangeStockItem> dtoMap = new HashMap<>();
            for(CommonStockDTO.InnerChangeStockItem changeStockItem:goodsItemList){
                dtoMap.put(changeStockItem.getSkuId(),changeStockItem);
                skuIdList.add(changeStockItem.getSkuId());
            }
            QueryWrapper<Stock> stockQueryWrapper = MybatisPlusUtil.query();
            stockQueryWrapper.in("sku_id",skuIdList);
            List<Stock> stockList =  repository.list(stockQueryWrapper);
            List<StockLog> stockLogList = new ArrayList<>();
            for(Stock stock: stockList){
                CommonStockDTO.InnerChangeStockItem dtoItem =   dtoMap.get(stock.getSkuId());
                stock.setQuantity(stock.getQuantity() + dtoItem.getQuantity());
                //生成日志
                StockLog stockLog   = new StockLog();
                stockLog.setStockId(stock.getId());
                stockLog.setShopId(stock.getShopId());
                stockLog.setMerchantId(stock.getMerchantId());
                stockLog.setDataFromType(dataFromType);
                stockLog.setGoodsId(stock.getGoodsId());
                stockLog.setSkuId(stock.getSkuId());
                stockLog.setLocation(StockLocationEnum.增加.getCode());
                stockLogList.add(stockLog);
            }
            repository.updateBatchById(stockList);
            stockLogRepository.saveBatch(stockLogList);
            return true;
        }
        return false;
    }

    @Override
    public ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId) {

        QueryWrapper<Stock> stockQueryWrapper = MybatisPlusUtil.query();
        stockQueryWrapper.eq("shop_id",shopId);
        stockQueryWrapper.eq("sku_id",skuId);
        Stock stock =  repository.getOne(stockQueryWrapper);
        if(null == stock){
          return  ResponseData.fail("仓库项不存在");
        }
        CommonStockVO.InnerStockVO innerStockVO = new CommonStockVO.InnerStockVO();
        innerStockVO.setGoodsId(stock.getGoodsId());
        innerStockVO.setSkuId(stock.getSkuId());
        innerStockVO.setQuantity(stock.getQuantity());
        return ResponseData.data(innerStockVO);
    }

    @Override
    public List<CommonStockVO.InnerStockVO> queryListStock(List<String> skuIdList) {
        if(ObjectUtils.isEmpty(skuIdList)){
            return new ArrayList<>();
        }
        QueryWrapper<Stock> stockQueryWrapper = MybatisPlusUtil.query();
        stockQueryWrapper.in("sku_id",skuIdList);
        List<Stock> stockList =  repository.list(stockQueryWrapper);
        if(ObjectUtils.isEmpty(stockList)){
            return new ArrayList<>();
        }
        return ListUtil.listCover(CommonStockVO.InnerStockVO.class,stockList);
    }


}
