package com.gs.lshly.biz.support.commodity.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsLabelService;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsLabelRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author Starry
* @since 2020-10-15
*/
@DubboService
public class GoodsLabelRpc implements IGoodsLabelRpc{

    @Autowired
    private IGoodsLabelService goodsLabelService;

    @Override
    public PageData<GoodsLabelVO.ListVO> pageData(GoodsLabelQTO.QTO qto){
        return goodsLabelService.pageData(qto);
    }

    @Override
    public void addGoodsLabel(GoodsLabelDTO.ETO eto){
        goodsLabelService.addGoodsLabel(eto);
    }

    @Override
    public void deleteGoodsLabel(GoodsLabelDTO.IdListDTO dto){
        goodsLabelService.deleteGoodsLabel(dto);
    }


    @Override
    public void editGoodsLabel(GoodsLabelDTO.ETO eto){
        goodsLabelService.editGoodsLabel(eto);
    }

    @Override
    public GoodsLabelVO.DetailVO detailGoodsLabel(GoodsLabelDTO.IdDTO dto){
        return goodsLabelService.detailGoodsLabel(dto);
    }

    @Override
    public List<GoodsLabelVO.ListVO> listGoodsLabel() {
        return goodsLabelService.listGoodsLabel();
    }

}