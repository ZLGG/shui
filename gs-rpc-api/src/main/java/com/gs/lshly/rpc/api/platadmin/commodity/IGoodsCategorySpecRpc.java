package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategorySpecDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategorySpecQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategorySpecVO;

/**
*
* @author Starry
* @since 2020-10-27
*/
public interface IGoodsCategorySpecRpc {

    PageData<GoodsCategorySpecVO.ListVO> pageData(GoodsCategorySpecQTO.QTO qto);

    void addGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto);

    void deleteGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto);


    void editGoodsCategorySpec(GoodsCategorySpecDTO.ETO eto);

    GoodsCategorySpecVO.DetailVO detailGoodsCategorySpec(GoodsCategorySpecDTO.IdDTO dto);

}