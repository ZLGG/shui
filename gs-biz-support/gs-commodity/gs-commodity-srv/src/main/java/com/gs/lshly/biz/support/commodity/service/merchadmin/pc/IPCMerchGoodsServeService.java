package com.gs.lshly.biz.support.commodity.service.merchadmin.pc;

import java.util.List;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO.IdDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsServeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsServeVO;

/**
 * @author hanly
 */
public interface IPCMerchGoodsServeService {
    PageData<PCMerchGoodsServeVO.ListVO> pageGoodsServeData(PCMerchGoodsServeQTO.QTO qto);

    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetail(PCMerchGoodsServeDTO.IdDTO dto);

    List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailTemp(PCMerchGoodsServeDTO.IdDTO dto);
    
    List<String> getServeTempIdByGoodsId(IdDTO dto);
}
