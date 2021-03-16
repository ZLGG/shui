package com.gs.lshly.rpc.api.pos;

import com.gs.lshly.common.struct.pos.dto.*;


public interface IPosStockRpc {

    void addAndFlush(PosStockRequestDTO.DTO posStockRequestDTO);
}
