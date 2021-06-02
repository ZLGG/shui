package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityGoodsService;
import com.gs.lshly.biz.support.trade.service.platadmin.ICtccPtActivityService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityGoodsRpc;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author hanly
 * @create 2021/6/2 9:53
 */
@DubboService
public class CtccPtActivityGoodsRpc implements ICtccPtActivityGoodsRpc {
    @Autowired
    private ICtccPtActivityGoodsService ctccPtActivityGoodsService;

    @Override
    public List<String> getList() {
        return ctccPtActivityGoodsService.getList();
    }
}
