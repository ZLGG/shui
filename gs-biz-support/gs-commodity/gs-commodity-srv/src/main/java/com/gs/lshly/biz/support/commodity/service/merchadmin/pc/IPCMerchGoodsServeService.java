package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;

import java.util.List;

/**
 * @author hanly
 */
public interface IPCMerchGoodsServeService {
    PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto);

    List<GoodsServeVO.ListVO> getGoodsServeDetail(GoodsServeDTO.IdDTO dto);
}
