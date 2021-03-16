package com.gs.lshly.rpc.api.platadmin.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;

/**
 * @Author Starry
 * @Date 12:02 2020/9/25
 */
public interface IGoodsSpecDictionaryItemRpc {
    /**
     * 查询规格值列表
     * @param qto
     * @return
     */
    PageData<GoodsSpecDictionaryItemVO.ListVO> listSpecItem(GoodsSpecDictionaryItemQTO.QTO qto);

    /**
     * 删除规格值
     * @param dto
     */
    void deleteSpecItem(GoodsSpecDictionaryItemDTO.IdDTO dto);
}
