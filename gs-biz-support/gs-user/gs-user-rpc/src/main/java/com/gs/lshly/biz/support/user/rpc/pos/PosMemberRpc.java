package com.gs.lshly.biz.support.user.rpc.pos;

import com.gs.lshly.biz.support.user.service.pos.IPosMemberService;
import com.gs.lshly.common.struct.pos.dto.PosMemberRequestDTO;
import com.gs.lshly.rpc.api.pos.IPosMemberRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 13:29 2021/2/25
 */
@DubboService
public class PosMemberRpc implements IPosMemberRpc {

    @Autowired
    private IPosMemberService posMemberService;

    @Override
    public void pushPosMember(PosMemberRequestDTO.DTO dto) {
        posMemberService.pushPosMember(dto);
    }
}
