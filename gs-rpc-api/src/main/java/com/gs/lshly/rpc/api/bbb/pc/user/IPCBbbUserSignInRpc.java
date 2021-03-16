package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserSignInDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserSignInQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserSignInVO;

/**
*
* @author zdf
* @since 2021-01-13
*/
public interface IPCBbbUserSignInRpc {

    PageData<PCBbbUserSignInVO.ListVO> pageData(PCBbbUserSignInQTO.QTO qto);

    void addUserSignIn(PCBbbUserSignInDTO.ETO eto);

    void deleteUserSignIn(PCBbbUserSignInDTO.IdDTO dto);


    void editUserSignIn(PCBbbUserSignInDTO.ETO eto);

    PCBbbUserSignInVO.DetailVO detailUserSignIn(PCBbbUserSignInDTO.IdDTO dto);

}