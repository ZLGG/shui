package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsAttributeInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeInfoVO;

import java.util.List;

public interface IGoodsAttributeInfoService {

    PageData<PCMerchGoodsAttributeInfoVO.ListVO> pageData(PCMerchGoodsAttributeInfoQTO.QTO qto);

    /**
     * 添加商品拓展属性信息
     * @param dto
     * @return
     */
    String addGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO dto);

    void deleteGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto);
    void editGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.ETO eto);

    PCMerchGoodsAttributeInfoVO.DetailVO detailGoodsAttributeInfo(PCMerchGoodsAttributeInfoDTO.IdDTO dto);

    /**
     * 根据商品id获取商品拓展属性列表
     * @param dto
     * @return
     */
    List<PCMerchGoodsAttributeInfoVO.ListVO> getListVO(PCMerchGoodsAttributeInfoDTO.GoodIdDTO dto);
}