package com.gs.lshly.biz.support.user.service.pos;

import com.gs.lshly.common.struct.pos.dto.PosMemberRequestDTO;

/**
 * @Author Starry
 * @Date 13:18 2021/2/25
 */
public interface IPosMemberService {

    /**
     * 同步POS的会员信息
     * @param dto
     */
    void pushPosMember(PosMemberRequestDTO.DTO dto);
}
