package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;

/**
 * @author hanly
 */
public interface IGoodsServeService {
    PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto);

    GoodsServeVO.ListVO getGoodsServeDetail(GoodsServeDTO.IdDTO dto);

    void addGoodsServe(GoodsServeDTO.ETO eto);

    void editGoodsServe(GoodsServeDTO.ETO eto);

    void deleteGoodsServe(GoodsServeDTO.IdListDTO dto);
}
