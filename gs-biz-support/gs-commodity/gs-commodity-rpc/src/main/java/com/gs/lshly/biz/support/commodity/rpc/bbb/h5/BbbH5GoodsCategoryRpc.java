package com.gs.lshly.biz.support.commodity.rpc.bbb.h5;

import com.gs.lshly.biz.support.commodity.service.bbb.h5.IBbbH5GoodsCategoryService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsCategoryVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author Starry
* @since 2020-10-23
*/
@DubboService
public class BbbH5GoodsCategoryRpc implements IBbbH5GoodsCategoryRpc {
    @Autowired
    private IBbbH5GoodsCategoryService bbcGoodsCategoryService;


    @Override
    public List<BbbH5GoodsCategoryVO.CategoryTreeVO> listGoodsCategory(BaseDTO dto) {
        return bbcGoodsCategoryService.listGoodsCategory(dto);
    }


}