package com.gs.lshly.rpc.api.bbb.pc.foundation;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-14
*/
public interface IPCBbbFloorRpc {

    PCBbbHomeVO.FloorOrAdvertVO pageList(PCBbbHomeQTO.QTO qto);

    List<PCBbbHomeVO.PCFloorMenuGoods> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto);

}