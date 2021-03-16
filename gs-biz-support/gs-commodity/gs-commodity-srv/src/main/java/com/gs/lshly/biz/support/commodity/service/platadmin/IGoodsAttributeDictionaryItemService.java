package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-09-23
 */
public interface IGoodsAttributeDictionaryItemService {
    /**
     * 查询属性值列表
     * @param qto
     * @return
     */
    PageData<GoodsAttributeDictionaryItemVO.ListVO > listAttributeValue(GoodsAttributeDictionaryItemQTO.QTO qto);

    void removeById(GoodsAttributeDictionaryItemDTO.IdDTO dto);
}
