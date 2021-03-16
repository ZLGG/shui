package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;

import java.util.List;

public interface IGoodsLabelService {

    PageData<GoodsLabelVO.ListVO> pageData(GoodsLabelQTO.QTO qto);

    void addGoodsLabel(GoodsLabelDTO.ETO eto);


    /**
     * 批量删除商品标签
     * @param dto
     */
    void deleteGoodsLabel(GoodsLabelDTO.IdListDTO dto);


    void editGoodsLabel(GoodsLabelDTO.ETO eto);



    GoodsLabelVO.DetailVO detailGoodsLabel(GoodsLabelDTO.IdDTO dto);

    /**
     * 获取商品标签列表
     * @return
     */
    List<GoodsLabelVO.ListVO> listGoodsLabel();



}