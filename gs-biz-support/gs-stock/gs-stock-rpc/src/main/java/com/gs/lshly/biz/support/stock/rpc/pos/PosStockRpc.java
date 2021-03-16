package com.gs.lshly.biz.support.stock.rpc.pos;


import com.gs.lshly.biz.support.stock.service.pos.IPosStockService;
import com.gs.lshly.common.struct.pos.dto.PosStockRequestDTO;
import com.gs.lshly.rpc.api.pos.IPosStockRpc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PosStockRpc implements IPosStockRpc {

    @Autowired
    private IPosStockService iPosTradeService;

    @Override
    public void addAndFlush(PosStockRequestDTO.DTO posStockRequestDTO) {
        iPosTradeService.addAndFlush(posStockRequestDTO);
    }
}
