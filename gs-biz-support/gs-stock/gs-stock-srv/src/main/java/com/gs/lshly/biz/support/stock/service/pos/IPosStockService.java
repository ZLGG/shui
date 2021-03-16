package com.gs.lshly.biz.support.stock.service.pos;


import com.gs.lshly.common.struct.pos.dto.PosStockRequestDTO;

public interface IPosStockService {

    void addAndFlush(PosStockRequestDTO.DTO posStockRequestDTO);
}
