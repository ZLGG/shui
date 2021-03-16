package com.gs.lshly.biz.support.commodity.rpc.bbc;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO.QTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CategoryMenuVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO.CategoryTreeVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class BbcGoodsCategoryRpc implements IBbcGoodsCategoryRpc{
    @Autowired
    private IBbcGoodsCategoryService  bbcGoodsCategoryService;

	@Override
	public List<CategoryTreeVO> listGoodsCategory() {
		// TODO Auto-generated method stub
		return bbcGoodsCategoryService.listGoodsCategory();
	}

	@Override
	public CategoryMenuVO getCategoryMenuVO(QTO qto) {
		// TODO Auto-generated method stub
		return null;
	}



}