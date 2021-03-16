package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserLogDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserPrivateUserRpc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserPrivateUserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-12-24
*/
@DubboService
public class PCBbbUserPrivateUserRpc implements IPCBbbUserPrivateUserRpc{

    @Autowired
    private IPCBbbUserPrivateUserService  pCBbbUserPrivateUserService;

    @Override
    public PageData<PCBbbUserPrivateUserVO.ListVO> pageData(PCBbbUserPrivateUserQTO.QTO qto){
        return pCBbbUserPrivateUserService.pageData(qto);
    }

    @Override
    public void deleteBatch(PCBbbUserPrivateUserDTO.IdListDTO dto) {
        pCBbbUserPrivateUserService.deleteBatch(dto);
    }

    @Override
    public PageData<PCBbbUserPrivateUserLogVO.ListVO> pageLogData(PCBbbUserPrivateUserQTO.QTO qto) {
        return pCBbbUserPrivateUserService.pageLogData(qto);
    }

    @Override
    public void addPrivateUserLog(PCBbbUserPrivateUserLogDTO.ETO eto) {
        pCBbbUserPrivateUserService.addPrivateUserLog(eto);
    }

    @Override
    public List<String> list(String userId) {
        return pCBbbUserPrivateUserService.list(userId);
    }



}