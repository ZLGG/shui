package com.gs.lshly.biz.support.commodity.rpc.platadmin;

import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsSpecDictionaryItemService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecDictionaryItemRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 13:48 2020/9/25
 */
@DubboService
public class GoodsSpecDictionaryItemRpc implements IGoodsSpecDictionaryItemRpc {
   @Autowired
   private IGoodsSpecDictionaryItemService itemService;

    @Override
    public PageData<GoodsSpecDictionaryItemVO.ListVO> listSpecItem(GoodsSpecDictionaryItemQTO.QTO qto) {
        return itemService.listSpecItem(qto);
    }

    @Override
    public void deleteSpecItem(GoodsSpecDictionaryItemDTO.IdDTO dto) {
        itemService.removeById(dto);
    }
}
