package com.gs.lshly.biz.support.foundation.rpc.platadmin;

import com.gs.lshly.biz.support.foundation.service.platadmin.ISysDataMessageService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SysDataMessageDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SysDataMessageQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysDataMessageVO;
import com.gs.lshly.rpc.api.platadmin.foundation.ISysDataMessageRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * RPC里面不允许写业务代码：
 * 只允许做三件事情：
 * 1，面向Service做数据准备；
 * 2，调用Service；
 * 3，面向前端解析VO数据；
 *
 * @author lxus
 * @since 2020/09/14
 */
@DubboService
public class SysDataMessageRpc implements ISysDataMessageRpc {

    @Autowired
    private ISysDataMessageService sysDataMessageService;

    @Override
    public PageData<SysDataMessageVO.ListVO> list(SysDataMessageQTO.QTO qoDTO) {
        return sysDataMessageService.pageData(qoDTO);
    }

    @Override
    public void addSysDataMessage(SysDataMessageDTO.ETO dto) {
        sysDataMessageService.saveSysDataMessage(dto);
    }

    @Override
    public void deleteSysDataMessage(SysDataMessageDTO.IdDTO dto) {
        sysDataMessageService.delete(dto);
    }

    @Override
    public SysDataMessageVO.DetailVO getSysDataMessage(SysDataMessageDTO.IdDTO dto) {
        return sysDataMessageService.detail(dto);
    }


}
