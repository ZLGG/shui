package com.gs.lshly.biz.support.user.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserCardDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;

public interface IBbbUserCardService {

    PageData<PCBbbUserCardVO.ListVO> pageData(PCBbbUserCardQTO.QTO qto);

    void addUserCard(PCBbbUserCardDTO.ETO eto);

    void deleteUserCard(PCBbbUserCardDTO.IdDTO dto);

    PCBbbUserCardVO.DetailVO detailUserCard(PCBbbUserCardDTO.IdDTO dto);

}