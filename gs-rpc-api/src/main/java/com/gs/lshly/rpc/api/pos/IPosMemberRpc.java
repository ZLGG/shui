package com.gs.lshly.rpc.api.pos;

import com.gs.lshly.common.struct.pos.dto.PosMemberRequestDTO;

/**
 * @Author Starry
 * @Date 13:27 2021/2/25
 */
public interface IPosMemberRpc {

    /**
     * 同步POS的会员信息
     * @param dto
     */
    void pushPosMember(PosMemberRequestDTO.DTO dto);
}
