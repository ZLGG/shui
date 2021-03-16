package com.gs.lshly.rpc.api.bbb.h5.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopFloorVO;


/**
*
* @author xxfc
* @since 2020-11-05
*/
public interface IBbbH5ShopFloorRpc {

    PageData<BbbH5ShopFloorVO.ListVO> pageData(BbbH5ShopFloorQTO.QTO qto);

}