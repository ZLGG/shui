package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
public interface IGoodsSpecDictionaryItemService {

    PageData<GoodsSpecDictionaryItemVO.ListVO> listSpecItem(GoodsSpecDictionaryItemQTO.QTO qto);

    void removeById(GoodsSpecDictionaryItemDTO.IdDTO dto);
}
