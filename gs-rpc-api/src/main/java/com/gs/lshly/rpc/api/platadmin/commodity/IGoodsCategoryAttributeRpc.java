package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryAttributeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryAttributeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryAttributeVO;

/**
*
* @author Starry
* @since 2020-10-27
*/
public interface IGoodsCategoryAttributeRpc {

    PageData<GoodsCategoryAttributeVO.ListVO> pageData(GoodsCategoryAttributeQTO.QTO qto);

    void addGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto);

    void deleteGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto);


    void editGoodsCategoryAttribute(GoodsCategoryAttributeDTO.ETO eto);

    GoodsCategoryAttributeVO.DetailVO detailGoodsCategoryAttribute(GoodsCategoryAttributeDTO.IdDTO dto);

}