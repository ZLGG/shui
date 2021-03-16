package com.gs.lshly.rpc.api.bbc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopFloorQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopFloorVO;

/**
*
* @author xxfc
* @since 2020-11-05
*/
public interface IBbcShopFloorRpc {

    PageData<BbcShopFloorVO.ListVO> pageData(BbcShopFloorQTO.QTO qto);

}