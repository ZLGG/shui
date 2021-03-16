package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsRelationLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsRelationLabelVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsRelationLabelService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsRelationLabelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-15
*/
@DubboService
public class GoodsRelationLabelRpc implements IGoodsRelationLabelRpc{

    @Autowired
    private IGoodsRelationLabelService goodsRelationLabelService;


    @Override
    public void addGoodsRelationLabel(GoodsRelationLabelDTO.ETO eto){
        goodsRelationLabelService.addGoodsRelationLabel(eto);
    }


}