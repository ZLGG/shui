package com.gs.lshly.rpc.api.platadmin.commodity;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;


/**
 * @Author Starry
 * @Date 14:44 2020/9/23
 */
public interface IGoodsAttributeDictionaryItemRpc {
    /**
     * 查询属性值列表
     * @param qto
     * @return
     */
    PageData<GoodsAttributeDictionaryItemVO.ListVO > listAttributeValue(GoodsAttributeDictionaryItemQTO.QTO qto);

    /**
     * 删除属性值
     * @param dto
     */
    void deleteAttributeItem(GoodsAttributeDictionaryItemDTO.IdDTO dto);
}
