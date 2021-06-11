package com.gs.lshly.rpc.api.platadmin.commodity;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bb.commodity.qto.BbGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMarketPtSeckillQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMarketPtSeckillVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

/**
 * @Author Starry
 * @Date 17:11 2020/10/14
 */
public interface IGoodsInfoRpc {

    /**
     * 分页查询商品列表
     *
     * @param qto
     * @return
     */
    PageData<GoodsInfoVO.SpuListVO> pageGoodsData(GoodsInfoQTO.QTO qto);

    /**
     * 查询商品详情
     *
     * @param dto
     * @return
     */
    GoodsInfoVO.DetailVO getGoodsDetail(GoodsInfoDTO.IdDTO dto);

    /**
     * 批量上架商品
     *
     * @param dto
     */
    void upCarriageGoods(GoodsInfoDTO.IdListDTO dto);

    /**
     * 批量下架商品
     *
     * @param dto
     */
    void underCarriageGoods(GoodsInfoDTO.IdListDTO dto);

    /**
     * 批量删除商品
     *
     * @param dto
     */
    void deleteGoodsBatches(GoodsInfoDTO.IdListDTO dto);

    /**
     * 审核商品
     *
     * @param dto
     */
    void checkGoods(GoodsInfoDTO.CheckGoodsDTO dto);

    /**
     * 批量审核商品
     *
     * @param dto
     */
    void checkGoodsBatches(GoodsInfoDTO.CheckGoodsBatchesDTO dto);


    /**
     * 提供店铺楼层选择的商品列表
     *
     * @param qto
     * @return
     */
    PageData<GoodsInfoVO.ShopFloorCommodityVO> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto);

    /**
     * 提供扶贫楼层选择的商品列表
     *
     * @param qto
     * @return
     */
    PageData<GoodsInfoVO.FupinFloorCommodityVO> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto);

    /**
     * 查看三级类目下绑定的商品列表
     *
     * @param qto
     * @return
     */
    PageData<GoodsInfoVO.BindCategoryGoodsVO> getBindCategoryGoodsVO(GoodsInfoQTO.CategoryIdQTO qto);


    //-------------内部服务--------------------

    /**
     * 通过商品id列表查询商品信息(id+name)
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto);

    /**
     * 通过商品id列表查询spu商品信息
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.ListVO> innerServiceSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(spu+sku)
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(id+name)
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.InnerServiceGoodsVO> innerServiceAllGoodsVO(GoodsInfoDTO.IdsInnerServiceDTO dto);

    /**
     * 通过商品id列表查询spu商品信息
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.ListVO> innerServiceAllSpuGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 通过商品id列表查询商品信息(spu+sku)
     *
     * @param dto
     * @return
     */
    List<GoodsInfoVO.InnerServiceGoodsInfoVO> innerServiceAllGoodsInfo(GoodsInfoDTO.IdsInnerServiceDTO dto);


    /**
     * 提供下架商品服务
     *
     * @param shopId
     */
    void innerServiceUnderShelfGoods(List<String> shopId);

    /**
     * 获取所有商品列表
     *
     * @return
     */
    List<GoodsInfoVO.ListVO> listGoodsData();


    /**
     * 获取IN会员商品
     *
     * @return
     */
    PageData<GoodsInfoVO.ListVO> pageInGoods(BbGoodsInfoQTO.QTO qto);

}

