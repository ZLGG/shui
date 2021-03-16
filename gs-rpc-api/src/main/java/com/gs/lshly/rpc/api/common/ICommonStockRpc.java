package com.gs.lshly.rpc.api.common;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-11-02
*/
public interface ICommonStockRpc {

    /**
     * 检查库存(一个SKU-ID)
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerCheckStockVO> innerCheckStock(BaseDTO dto, String shopId, String skuId, Integer quantity);

    /**
     * 检查库存(多个SKU-ID)
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerListCheckStockVO> innerListCheckStock(CommonStockDTO.InnerCheckStockDTO dto);

    /**
     * 取有存存的SPU-ID
     * @param goodsIdList
     * @return
     */
    List<String> innerListGoodsNotEmpytStock(List<String> goodsIdList);

    /**
     * 销量列表(多个商品)
     * @param dto
     * @return
     */
    ResponseData<List<CommonStockVO.InnerCountSalesVO>> innerListSalesVolume(BaseDTO dto, List<String> goodsIdList);

    /**
     * 销量列表(多个商品)
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerComplexCountSalesVO> innerComplexSalesVolume(CommonStockDTO.InnerCountSalesDTO dto);

    /**
     * 销量(一个商品)
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerCountSalesVO> innerSalesVolume(BaseDTO dto, String goodsId);

    /**
     * 库存变动,返回变动后库存信息
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerChangeStockVO> innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto);

    /**
     * SKU减库存
     *
     * @return
     */
    boolean innerSubtractStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList);

    /**
     * 取消订单回库存
     *
     * @return
     */
    boolean innerPlusStockForTrade(List<CommonStockDTO.InnerChangeStockItem> goodsItemList);

    /**
     * 获取SKU库存
     * @param dto
     * @return
     */
    ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId);

    /**
     * 获取SKU库存
     * @return
     */
    List<CommonStockVO.InnerStockVO> queryListStock(List<String> skuIdList);
}