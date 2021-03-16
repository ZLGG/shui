package com.gs.lshly.biz.support.commodity.service.bbb;


import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.pc.commodityfupin.vo.PCBbbGoodsFupinVO;

public interface IPCBbbGoodsFupinService {


    /**
     * 查询扶贫商品的id
     * @param qto
     * @return
     */
    PCBbbGoodsFupinVO.GoodsIdVO getGoodsIdVO(BaseQTO qto);

}