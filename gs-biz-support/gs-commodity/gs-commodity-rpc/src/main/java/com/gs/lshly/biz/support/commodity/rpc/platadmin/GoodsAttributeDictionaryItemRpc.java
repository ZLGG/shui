package com.gs.lshly.biz.support.commodity.rpc.platadmin;


import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAttributeDictionaryItemService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryItemVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeDictionaryItemRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @Author Starry
 * @Date 14:50 2020/9/23
 */
@DubboService
public class GoodsAttributeDictionaryItemRpc implements IGoodsAttributeDictionaryItemRpc {
    @Autowired
    private IGoodsAttributeDictionaryItemService itemService;

    @Override
    public PageData<GoodsAttributeDictionaryItemVO.ListVO> listAttributeValue(GoodsAttributeDictionaryItemQTO.QTO qto) {
        return itemService.listAttributeValue(qto);
    }

    @Override
    public void deleteAttributeItem(GoodsAttributeDictionaryItemDTO.IdDTO dto) {
       itemService.removeById(dto);
    }
}
