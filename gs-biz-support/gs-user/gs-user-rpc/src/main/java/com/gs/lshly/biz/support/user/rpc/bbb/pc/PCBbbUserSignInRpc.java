package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserSignInDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserSignInQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserSignInVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserSignInRpc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserSignInService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zdf
* @since 2021-01-13
*/
@DubboService
public class PCBbbUserSignInRpc implements IPCBbbUserSignInRpc{
    @Autowired
    private IPCBbbUserSignInService  pCBbbUserSignInService;

    @Override
    public PageData<PCBbbUserSignInVO.ListVO> pageData(PCBbbUserSignInQTO.QTO qto){
        return pCBbbUserSignInService.pageData(qto);
    }

    @Override
    public void addUserSignIn(PCBbbUserSignInDTO.ETO eto){
        pCBbbUserSignInService.addUserSignIn(eto);
    }

    @Override
    public void deleteUserSignIn(PCBbbUserSignInDTO.IdDTO dto){
        pCBbbUserSignInService.deleteUserSignIn(dto);
    }


    @Override
    public void editUserSignIn(PCBbbUserSignInDTO.ETO eto){
        pCBbbUserSignInService.editUserSignIn(eto);
    }

    @Override
    public PCBbbUserSignInVO.DetailVO detailUserSignIn(PCBbbUserSignInDTO.IdDTO dto){
        return  pCBbbUserSignInService.detailUserSignIn(dto);
    }

}