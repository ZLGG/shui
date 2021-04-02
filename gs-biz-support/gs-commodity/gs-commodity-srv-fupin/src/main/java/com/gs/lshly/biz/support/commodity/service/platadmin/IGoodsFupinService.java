package com.gs.lshly.biz.support.commodity.service.platadmin;

import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.qto.GoodsFupinQTO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.vo.GoodsFupinVO;

import java.util.List;

public interface IGoodsFupinService {

    /**
     * 查询扶贫商品基本信息
     * @param qto
     * @return
     */
    GoodsFupinVO.DetailVO detailGoodsFupin(GoodsFupinQTO.QTO qto);

    /**
     * 提供扶贫楼层选择的商品
     * @param qto
     * @return
     */
    List<String> listFuPinGoodsId(BaseQTO qto);

}