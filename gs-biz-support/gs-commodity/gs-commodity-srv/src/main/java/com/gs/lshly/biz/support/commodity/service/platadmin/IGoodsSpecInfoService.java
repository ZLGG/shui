package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsSpecInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecInfoVO;

import java.util.List;

public interface IGoodsSpecInfoService {

    PageData<PCMerchGoodsSpecInfoVO.ListVO> pageData(PCMerchGoodsSpecInfoQTO.QTO qto);

    String addGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto);

    void deleteGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto);
    void editGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.ETO eto);

    PCMerchGoodsSpecInfoVO.DetailVO detailGoodsSpecInfo(PCMerchGoodsSpecInfoDTO.IdDTO dto);

    /**
     * 根据商品id获取商品拓展规格
     * @param dto
     * @return
     */
    List<PCMerchGoodsSpecInfoVO.ListVO> specInfoListVO (PCMerchGoodsSpecInfoDTO.GoodIdDTO dto);
}