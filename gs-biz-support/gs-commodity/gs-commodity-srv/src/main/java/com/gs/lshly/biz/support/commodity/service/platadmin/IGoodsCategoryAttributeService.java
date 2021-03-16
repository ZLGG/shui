package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryAttributeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryAttributeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryAttributeVO;

public interface IGoodsCategoryAttributeService {

    PageData<GoodsCategoryAttributeVO.ListVO> pageData(GoodsCategoryAttributeQTO.QTO qto);

    void addGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto);

    void deleteGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto);


    void editGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto);

    GoodsCategoryAttributeVO.DetailVO detailGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto);

}