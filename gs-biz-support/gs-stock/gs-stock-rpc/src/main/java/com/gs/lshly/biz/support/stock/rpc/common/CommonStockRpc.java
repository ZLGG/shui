package com.gs.lshly.biz.support.stock.rpc.common;

import com.gs.lshly.biz.support.stock.service.common.ICommonStockService;
import com.gs.lshly.biz.support.stock.service.common.ICommonStockTempService;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author xxfc
 * @since 2020-11-02
 */
@DubboService
public class CommonStockRpc implements ICommonStockRpc {

    @Autowired
    private ICommonStockService commonStockService;

    @Autowired
    private ICommonStockTempService commonStockTempService;


    @Override
    public ResponseData<CommonStockVO.InnerCheckStockVO> innerCheckStock(BaseDTO dto, String shopId, String skuId, Integer quantity) {
        return commonStockService.innerCheckStock(dto, shopId, skuId, quantity);
    }

    @Override
    public ResponseData<CommonStockVO.InnerListCheckStockVO> innerListCheckStock(CommonStockDTO.InnerCheckStockDTO dto) {
        return commonStockService.innerListCheckStock(dto);
    }

    @Override
    public List<String> innerListGoodsNotEmpytStock(List<String> goodsIdList) {
        return commonStockService.innerListGoodsNotEmpytStock(goodsIdList);
    }

    @Override
    public ResponseData<List<CommonStockVO.InnerCountSalesVO>> innerListSalesVolume(BaseDTO dto, List<String> goodsIdList) {
        return commonStockService.innerListSalesVolume(dto, goodsIdList);
    }

    @Override
    public ResponseData<CommonStockVO.InnerComplexCountSalesVO> innerComplexSalesVolume(CommonStockDTO.InnerCountSalesDTO dto) {

        return commonStockService.innerComplexSalesVolume(dto);
    }

    @Override
    public ResponseData<CommonStockVO.InnerCountSalesVO> innerSalesVolume(BaseDTO dto, String goodsId) {
        return commonStockService.innerSalesVolume(dto, goodsId);
    }

    @Override
    public ResponseData<CommonStockVO.InnerChangeStockVO> innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto) {
        return commonStockService.innerChangeStock(dto);
    }

    @Override
    public boolean innerSubtractStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        return commonStockService.innerSubtractStockForTrade(goodsItemList);
    }

    @Override
    public boolean innerPlusStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList) {
        return commonStockService.innerPlusStockForTrade(goodsItemList);
    }

    @Override
    public ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId) {

        return commonStockService.queryStock(dto, shopId, skuId);
    }

    @Override
    public List<CommonStockVO.InnerStockVO> queryListStock(List<String> skuIdList) {
        return commonStockService.queryListStock(skuIdList);
    }

    @Override
    public Integer getGoodsQuantity(String goodsId) {
        return commonStockService.getGoodsQuantity(goodsId);
    }

    @Override
    public Boolean innerChangeStockTemp(CommonStockDTO.InnerChangeStockDTO dto) {
        return commonStockTempService.innerChangeStock(dto);
    }

    @Override
    public ResponseData<CommonStockVO.InnerStockVO> queryStockTemp(BaseDTO dto, String shopId, String skuId) {
        return commonStockTempService.queryStock(dto, shopId, skuId);
    }

}