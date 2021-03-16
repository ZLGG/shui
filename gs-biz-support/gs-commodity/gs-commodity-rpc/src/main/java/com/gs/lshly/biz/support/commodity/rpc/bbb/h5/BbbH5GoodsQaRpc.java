package com.gs.lshly.biz.support.commodity.rpc.bbb.h5;
import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsQaService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsQaDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsQaQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsQaVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsQaRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-16
*/
@DubboService
public class BbbH5GoodsQaRpc implements IBbbH5GoodsQaRpc {

    @Autowired
    private IBbbH5GoodsQaService bbcGoodsQaService;

    @Override
    public PageData<BbbH5GoodsQaVO.ShowListVO> pageData(BbbH5GoodsQaQTO.QTO qto){
        return bbcGoodsQaService.pageData(qto);
    }

    @Override
    public void addGoodsQa(BbbH5GoodsQaDTO.ETO eto){
        bbcGoodsQaService.addGoodsQa(eto);
    }



}