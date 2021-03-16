package com.gs.lshly.rpc.api.bbb.h5.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsQaDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsQaQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsQaVO;

/**
*
* @author Starry
* @since 2020-10-16
*/
public interface IBbbH5GoodsQaRpc {

    PageData<BbbH5GoodsQaVO.ShowListVO> pageData(BbbH5GoodsQaQTO.QTO qto);

    void addGoodsQa(BbbH5GoodsQaDTO.ETO eto);



}