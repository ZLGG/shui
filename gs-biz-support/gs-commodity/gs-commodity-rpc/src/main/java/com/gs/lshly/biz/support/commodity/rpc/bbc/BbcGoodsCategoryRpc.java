package com.gs.lshly.biz.support.commodity.rpc.bbc;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsCategoryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO.CtccDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO.QTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CategoryMenuVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CategoryTreeVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CtccHomeVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO.IdQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO.ListVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;

/**
 * @author Starry
 * @since 2020-10-23
 */
@DubboService
public class BbcGoodsCategoryRpc implements IBbcGoodsCategoryRpc {
    @Autowired
    private IBbcGoodsCategoryService bbcGoodsCategoryService;

    @Override
    public List<CategoryTreeVO> listGoodsCategory() {
        return bbcGoodsCategoryService.listGoodsCategory();
    }
    @Override
    public List<BbcGoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BbcGoodsCategoryQTO.ListQTO listQTO) {
        return bbcGoodsCategoryService.goodsCategoryTree(listQTO);
    }

    @Override
    public CategoryMenuVO getCategoryMenuVO(QTO qto) {
        return null;
    }

    @Override
    public PageData<GoodsBrandVO.ListVO> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        return bbcGoodsCategoryService.brandList(categoryIdQTO);
    }

    @Override
    public PageData<GoodsInfoVO.ListVO> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        return bbcGoodsCategoryService.goodsList(categoryIdQTO);
    }
	@Override
	public PageData<ListVO> goodsListByBrand(IdQTO idQTO) {
		return bbcGoodsCategoryService.goodsListByBrand(idQTO);
	}
	@Override
	public CtccHomeVO ctcchome(CtccDTO ctccDTO) {
		return bbcGoodsCategoryService.ctcchome(ctccDTO);
	}

}