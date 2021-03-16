package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsParamInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;

/**
*
* @author Starry
* @since 2020-09-29
*/
public interface IGoodsParamInfoRpc {

    PageData<GoodsParamInfoVO.ListVO> pageData(GoodsParamInfoQTO.QTO qto);

    void addGoodsParamInfo(GoodsParamInfoDTO.ETO eto);

    void deleteGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto);

    void editGoodsParamInfo(GoodsParamInfoDTO.ETO eto);

    GoodsParamInfoVO.DetailVO detailGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto);

}