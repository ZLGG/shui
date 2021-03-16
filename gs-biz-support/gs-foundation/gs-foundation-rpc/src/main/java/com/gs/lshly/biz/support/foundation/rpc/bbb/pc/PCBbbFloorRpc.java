package com.gs.lshly.biz.support.foundation.rpc.bbb.pc;
import com.gs.lshly.biz.support.foundation.service.bbb.pc.IPCBbbFloorService;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-14
*/
@DubboService
public class PCBbbFloorRpc implements IPCBbbFloorRpc {

    @Autowired
    private IPCBbbFloorService pcBbbFloorService;

    @Override
    public PCBbbHomeVO.FloorOrAdvertVO pageList(PCBbbHomeQTO.QTO qto) {
        return pcBbbFloorService.pageList(qto);
    }

    @Override
    public List<PCBbbHomeVO.PCFloorMenuGoods> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto) {
        return pcBbbFloorService.floorMenuGoodsQuery(qto);
    }

}