package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserSignInDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserSignInQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserSignInVO;

public interface IPCBbbUserSignInService {

    PageData<PCBbbUserSignInVO.ListVO> pageData(PCBbbUserSignInQTO.QTO qto);

    void addUserSignIn(PCBbbUserSignInDTO.ETO eto);

    void deleteUserSignIn(PCBbbUserSignInDTO.IdDTO dto);


    void editUserSignIn(PCBbbUserSignInDTO.ETO eto);

    PCBbbUserSignInVO.DetailVO detailUserSignIn(PCBbbUserSignInDTO.IdDTO dto);

}