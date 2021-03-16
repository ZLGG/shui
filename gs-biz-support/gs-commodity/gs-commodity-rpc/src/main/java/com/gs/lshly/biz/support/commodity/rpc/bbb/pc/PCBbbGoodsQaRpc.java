package com.gs.lshly.biz.support.commodity.rpc.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.biz.support.commodity.service.bbb.pc.IPCBbbGoodsQaService;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsQaRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
*
* @author Starry
* @since 2020-10-16
*/
@DubboService
public class PCBbbGoodsQaRpc implements IPCBbbGoodsQaRpc {

    @Autowired
    private IPCBbbGoodsQaService bbbGoodsQaService;

    @Override
    public PageData<PCBbbGoodsQaVO.ShowListVO> pageData(PCBbbGoodsQaQTO.QTO qto){
        return bbbGoodsQaService.pageData(qto);
    }

    @Override
    public PageData<PCBbbGoodsQaVO.UserGoodsQaListVO> pageUserGoodsQa(PCBbbGoodsQaQTO.UserQTO qto) {
        return bbbGoodsQaService.pageUserGoodsQa(qto);
    }

    @Override
    public void addGoodsQa(PCBbbGoodsQaDTO.ETO eto){
        bbbGoodsQaService.addGoodsQa(eto);
    }

    @Override
    public void deleteGoodsQa(PCBbbGoodsQaDTO.IdListDTO dto){
        bbbGoodsQaService.deleteGoodsQa(dto);
    }
    @Override
    public PCBbbGoodsQaVO.CountQuizVO countQuiz(PCBbbGoodsQaQTO.GoodsIdQTO qto) {
        return bbbGoodsQaService.countQuiz(qto);
    }


}