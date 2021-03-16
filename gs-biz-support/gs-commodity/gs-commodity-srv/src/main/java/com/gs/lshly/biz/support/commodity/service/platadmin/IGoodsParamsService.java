package com.gs.lshly.biz.support.commodity.service.platadmin;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamsDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamsVO;

import java.util.List;

public interface IGoodsParamsService {

    /**
     * 查询与类目关联的参数列表
     * @param dto
     * @return
     */
    List<GoodsParamsVO.ParamsListVO> listGoodsParams(GoodsCategoryDTO.IdDTO dto);

    /**
     * 批量添加参数组
     * @param eto
     */
    void addGoodsParams(GoodsCategoryDTO.IdDTO dto,List<GoodsParamsDTO.ETO> eto);

    void deleteGoodsParams(GoodsParamsDTO.IdDTO dto);

    void editGoodsParams(GoodsParamsDTO.ETO eto);

    GoodsParamsVO.DetailVO detailGoodsParams(GoodsParamsDTO.IdDTO dto);

}