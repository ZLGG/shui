package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsParamInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;

public interface IGoodsParamInfoService {

    PageData<GoodsParamInfoVO.ListVO> pageData(GoodsParamInfoQTO.QTO qto);

    void addGoodsParamInfo(GoodsParamInfoDTO.ETO eto);

    void deleteGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto);

    void editGoodsParamInfo(GoodsParamInfoDTO.ETO eto);

    GoodsParamInfoVO.DetailVO detailGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto);

}