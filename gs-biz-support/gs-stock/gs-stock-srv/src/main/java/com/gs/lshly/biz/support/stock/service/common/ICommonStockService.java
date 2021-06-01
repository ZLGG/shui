package com.gs.lshly.biz.support.stock.service.common;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import java.util.List;

public interface ICommonStockService {

    ResponseData<CommonStockVO.InnerCheckStockVO> innerCheckStock(BaseDTO dto, String shopId, String skuId, Integer quantity);

    ResponseData<CommonStockVO.InnerListCheckStockVO> innerListCheckStock(CommonStockDTO.InnerCheckStockDTO dto);

    List<String> innerListGoodsNotEmpytStock(List<String> goodsIdList);

    ResponseData<List<CommonStockVO.InnerCountSalesVO>> innerListSalesVolume(BaseDTO dto, List<String> goodsIdList);

    ResponseData<CommonStockVO.InnerCountSalesVO> innerSalesVolume(BaseDTO dto, String goodsId);

    ResponseData<CommonStockVO.InnerComplexCountSalesVO> innerComplexSalesVolume(CommonStockDTO.InnerCountSalesDTO dto);

    ResponseData<CommonStockVO.InnerChangeStockVO> innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto);


    boolean innerSubtractStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList);

    boolean innerPlusStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList);

    ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId);

    List<CommonStockVO.InnerStockVO> queryListStock(List<String> skuIdList);

    Integer getGoodsQuantity(String goodsId);
}