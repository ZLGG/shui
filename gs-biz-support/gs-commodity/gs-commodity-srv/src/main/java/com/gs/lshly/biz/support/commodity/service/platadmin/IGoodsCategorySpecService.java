package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategorySpecDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategorySpecQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategorySpecVO;

public interface IGoodsCategorySpecService {

    PageData<GoodsCategorySpecVO.ListVO> pageData(GoodsCategorySpecQTO.QTO qto);

    void addGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto);

    void deleteGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto);


    void editGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto);

    GoodsCategorySpecVO.DetailVO detailGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto);

}