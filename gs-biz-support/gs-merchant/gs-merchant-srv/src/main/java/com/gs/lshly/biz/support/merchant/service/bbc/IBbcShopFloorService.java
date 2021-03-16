package com.gs.lshly.biz.support.merchant.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopFloorQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;

public interface IBbcShopFloorService {

    PageData<BbcShopFloorVO.ListVO> pageData(BbcShopFloorQTO.QTO qto);

}