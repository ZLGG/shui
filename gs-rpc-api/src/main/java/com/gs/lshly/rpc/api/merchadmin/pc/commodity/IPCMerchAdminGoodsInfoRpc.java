package com.gs.lshly.rpc.api.merchadmin.pc.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-08
*/
public interface IPCMerchAdminGoodsInfoRpc {

    /**
     * 查询商品列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.SpuListVO> pageData(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto);

    /**
     * 查询库存报警相关联的商品信息列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.StockAlarmGoodsVO> pageStockAlarmGoods(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto);

    /**
     * 修改商品信息
     * @param eto
     */
    void editGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto);
    /**
     * 根据店铺id与商品id查看商品信息
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.DetailVO detailGoodsInfo(PCMerchGoodsInfoDTO.EditIdsDTO dto);

    /**
     *发布商品
     * @param eto
     */
    void addGoodsInfo(PCMerchGoodsInfoDTO.AddGoodsETO eto);

    /**
     * 修改库存
     * @param dto
     */
    void updateStock(PCMerchGoodsInfoDTO.UpdateStockDTO dto);
    /**
     * 删除商品信息
     * @param dto
     * @return
     */
    void deleteGoodsInfo(PCMerchGoodsInfoDTO.IdDTO dto);

    /**
     * 批量上架商品
     * @param dto
     */
    void groundingGoods(PCMerchGoodsInfoDTO.IdListDTO dto);

    /**
     * 批量下架商品
     * @param dto
     */
    void underCarriageGoods(PCMerchGoodsInfoDTO.IdListDTO dto);

    /**
     * 批量删除商品
     * @param dto
     */
    void deleteGoodsBatches(PCMerchGoodsInfoDTO.IdListDTO dto);

    /**
     * 导出数据
     * @param qto
     * @return
     * @throws Exception
     */
    ExportDataDTO export(PCMerchGoodsInfoQTO.IdListQTO qto) throws Exception;

    /**
     * 下载模板
     * @return
     * @throws Exception
     */
    ExportDataDTO downExcelMode() throws Exception;

    /**
     * 获取编辑商品信息详情-上架、待上架状态
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.EditDetailVO getEditDetailEto(PCMerchGoodsInfoDTO.IdDTO dto);

    /**
     * 获取编辑商品信息详情-审核中、已审核、草稿箱状态
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.EditDetailVO getEditDetailTempEto(PCMerchGoodsInfoDTO.IdDTO dto);

    /**
     * 提供店铺楼层选择的商品列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(PCMerchGoodsInfoQTO.ShopFloorQTO qto);

    /**
     * 查询与店铺自定义关联的商品列表以及提供供类目选择的商品列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.ShopNavigationCommodityVO> pageShopNavigationCommodityVO(PCMerchGoodsInfoQTO.ShopNavigationQTO qto);

    /**
     * 为活动提供商品列表选择
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.GoodsActiveVO> pageGoodsActiveVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto);

    /**
     * 为活动提供sku赠品列表
     * @param qto
     * @return
     */
    PageData<PCMerchGoodsInfoVO.SkuActivePageVO> pageSkuActivePageVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto);


    /**
     * 通过商品id查询sku信息列表
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.SkuActiveVO> listSkuActiveVO(PCMerchGoodsInfoDTO.IdDTO dto);

    /**
     * 设置运费模板
     * @param dto
     */
    void setGoodsTemplate(PCMerchGoodsInfoDTO.SettingGoodsTemplateDTO dto);

    /**
     * 批量添加数据
     * @param list
     */
    void  addGoodsBatch(List<PCMerchGoodsInfoDTO.ExcelGoodsDataETO> list, BaseDTO dto);


    /**
     * 获取店铺中2b分类下的sku商品列表
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO> getShopCategoryGoodsVO(BaseDTO dto);


    //------------------内部服务---------------

    /**
     * 通过商品id列表查询商品信息(id+name)
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);

    /**
     * 通过商品id列表查询spu商品信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(spu+sku)
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(id+name)
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);

    /**
     * 通过商品id列表查询spu商品信息
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(spu+sku)
     * @param dto
     * @return
     */
    List<PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(PCMerchGoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 商品是否关联了店铺分类
     * @param shopNavigationIds
     * @return
     */
    boolean innerServiceShopNavigation(List<String> shopNavigationIds);


    /**
     * 根据skuId查询sku商品信息
     * @param skuIds
     * @return
     */
    List<PCMerchSkuGoodInfoVO.ListVO> innerServiceSkuGoodsList(List<String> skuIds);


    /**
     * 统计店铺首页商品相关数量
     * @param dto
     * @return
     */
    PCMerchGoodsInfoVO.HomeCountGoodsVO innerHomeCountGoodsVO(BaseDTO dto);

    /**
     * 未POS提供通过商品货号查询ｓｋｕＩＤ的服务
     * */
    PCMerchGoodsInfoVO.SkuIdByGoodsNoVO innerSkuIdByGoodsNo(String goodsNo);


    /**
     * 为Pos同步库存提供支持
     * */
    PCMerchGoodsInfoVO.SkuIdByGoodsNoVO innerByNoSkuId(String posSku69);

    String selectGoodsNo(String tradeGoodsId);

    PCMerchMarketPtSeckillVO.SpuVO selectAllWithOutSeckill(PCMerchMarketPtSeckillQTO.AllSpuQTO qto);

    PCMerchMarketPtSeckillVO.BrandAndCategry selectBrandAndCategory(String goodsId);

    String selectGoodsImage(String goodsId);

    Boolean deleteBatchesTemp(PCMerchGoodsInfoDTO.IdsDTO idsDTO);

    PCMerchGoodsInfoVO.GoodsStateCountVO getCountByGoodsState(PCMerchGoodsInfoDTO.MerchantDTO merchantDTO);
}