package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsServeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;

import java.util.List;

/**
 * @author hanly
 */
public interface IPCMerchGoodsServeService {
    PageData<PCMerchGoodsServeVO.ListVO> pageGoodsServeData(PCMerchGoodsServeQTO.QTO qto);

    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetail(PCMerchGoodsServeDTO.IdDTO dto);

    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailTemp(PCMerchGoodsServeDTO.IdDTO dto);
}
