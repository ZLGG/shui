package com.gs.lshly.biz.support.commodity.service.bbc;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;

public interface IBbcGoodsCategoryService {

    List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory();
    /**
     * 查询所有2C商城分类列表
     *
     * @return
     */
    List<BbcGoodsCategoryVO.CategoryTreeVO> goodsCategoryTree(BbcGoodsCategoryQTO.ListQTO listQTO);

    PageData<GoodsBrandVO.ListVO> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);

    PageData<GoodsInfoVO.ListVO> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO);
    
    /**
     * 跟据品牌查询商品列表
     * @param idQTO
     * @return
     */
    PageData<GoodsInfoVO.ListVO> goodsListByBrand(GoodsBrandQTO.IdQTO idQTO);
    
    /**
     * 电信产品
     * @param ctccDTO
     * @return
     */
    BbcGoodsCategoryVO.CtccHomeVO ctcchome(BbcGoodsCategoryDTO.CtccDTO ctccDTO);
    
    /**
     * 查询第三级分类列表
     * @param dto
     * @return
     */
    List<BbcGoodsCategoryVO.ListVO> listThirdGoodsCategory(BbcGoodsCategoryDTO.ThirdListDTO dto);
    
    /**
     * 跟据父Id查询所有子ID
     * @param dto
     * @return
     */
    List<String> listGoodsCategoryByParentId(BbcGoodsCategoryDTO.ParentIdDTO dto);
    
}