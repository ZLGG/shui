package com.gs.lshly.biz.support.user.rpc.bbb.h5;

import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBBBInUserCouponService;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:49
 */
@DubboService
public class BbbInUserCouponRpc implements IBBBInUserCouponRpc {

    @Autowired
    private IPCBBBInUserCouponService inUserCouponService;

    @Override
    public List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto) {
        List<PCBBBInUserCouponVO> inUserCouponVOList = inUserCouponService.queryInUserCouponList(qto);
        return inUserCouponVOList;
    }
}
