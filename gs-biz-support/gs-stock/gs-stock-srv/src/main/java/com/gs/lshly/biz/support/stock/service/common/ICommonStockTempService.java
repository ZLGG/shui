package com.gs.lshly.biz.support.stock.service.common;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;

public interface ICommonStockTempService {

    Boolean innerChangeStock(CommonStockDTO.InnerChangeStockDTO dto);

    ResponseData<CommonStockVO.InnerStockVO> queryStock(BaseDTO dto, String shopId, String skuId);
}
