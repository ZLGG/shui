package com.gs.lshly.biz.support.commodity.rpc.bbc;

import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsQaService;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsQaService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsQaDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsQaQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsQaVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsQaRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsQaRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author Starry
* @since 2020-10-16
*/
@DubboService
public class BbcGoodsQaRpc implements IBbcGoodsQaRpc {

    @Autowired
    private IBbcGoodsQaService bbcGoodsQaService;

    @Override
    public PageData<BbcGoodsQaVO.ShowListVO> pageData(BbcGoodsQaQTO.QTO qto){
        return bbcGoodsQaService.pageData(qto);
    }

    @Override
    public void addGoodsQa(BbcGoodsQaDTO.ETO eto){
        bbcGoodsQaService.addGoodsQa(eto);
    }




}