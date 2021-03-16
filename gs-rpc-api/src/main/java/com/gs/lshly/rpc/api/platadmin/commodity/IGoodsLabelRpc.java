package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-15
*/
public interface IGoodsLabelRpc {

    PageData<GoodsLabelVO.ListVO> pageData(GoodsLabelQTO.QTO qto);

    void addGoodsLabel(GoodsLabelDTO.ETO eto);

    void deleteGoodsLabel(GoodsLabelDTO.IdListDTO dto);


    void editGoodsLabel(GoodsLabelDTO.ETO eto);

    GoodsLabelVO.DetailVO detailGoodsLabel(GoodsLabelDTO.IdDTO dto);

    /**
     * 获取商品标签列表
     * @return
     */
    List<GoodsLabelVO.ListVO> listGoodsLabel();

}