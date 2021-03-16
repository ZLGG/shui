package com.gs.lshly.biz.support.foundation.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;

import java.util.List;

public interface IPCBbbFloorService {

    PCBbbHomeVO.FloorOrAdvertVO pageList(PCBbbHomeQTO.QTO qto);

    List<PCBbbHomeVO.PCFloorMenuGoods> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto);
}