package com.gs.lshly.rpc.api.platadmin.commodity;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamsDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamsVO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-09-29
*/
public interface IGoodsParamsRpc {

    /**
     * 查询参数列表
     * @param dto
     * @return
     */
    List<GoodsParamsVO.ParamsListVO> listGoodsParams(GoodsCategoryDTO.IdDTO dto);

    void addGoodsParams(GoodsCategoryDTO.IdDTO dto,List<GoodsParamsDTO.ETO> eto);

    void deleteGoodsParams(GoodsParamsDTO.IdDTO dto);

    void editGoodsParams(GoodsParamsDTO.ETO eto);

    GoodsParamsVO.DetailVO detailGoodsParams(GoodsParamsDTO.IdDTO dto);

}