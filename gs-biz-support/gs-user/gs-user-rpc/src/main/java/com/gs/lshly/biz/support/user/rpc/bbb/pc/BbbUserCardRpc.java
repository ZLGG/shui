package com.gs.lshly.biz.support.user.rpc.bbb.pc;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserCardService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserCardDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserCardRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-12-09
*/
@DubboService
public class BbbUserCardRpc implements IBbbUserCardRpc {

    @Autowired
    private IBbbUserCardService bbbUserCardService;

    @Override
    public PageData<PCBbbUserCardVO.ListVO> pageData(PCBbbUserCardQTO.QTO qto){
        return bbbUserCardService.pageData(qto);
    }

    @Override
    public void addUserCard(PCBbbUserCardDTO.ETO eto){
        bbbUserCardService.addUserCard(eto);
    }

    @Override
    public void deleteUserCard(PCBbbUserCardDTO.IdDTO dto){
        bbbUserCardService.deleteUserCard(dto);
    }

    @Override
    public PCBbbUserCardVO.DetailVO detailUserCard(PCBbbUserCardDTO.IdDTO dto){
        return  bbbUserCardService.detailUserCard(dto);
    }

}