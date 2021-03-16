package com.gs.lshly.rpc.api.bbb.pc.user;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserCardDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;

/**
*
* @author xxfc
* @since 2020-12-09
*/
public interface IBbbUserCardRpc {

    PageData<PCBbbUserCardVO.ListVO> pageData(PCBbbUserCardQTO.QTO qto);

    void addUserCard(PCBbbUserCardDTO.ETO eto);

    void deleteUserCard(PCBbbUserCardDTO.IdDTO dto);


    PCBbbUserCardVO.DetailVO detailUserCard(PCBbbUserCardDTO.IdDTO dto);

}