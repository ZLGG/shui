package com.gs.lshly.biz.support.user.rpc.bbb.pc;

import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBBBInUserCouponService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:49
 */
@DubboService
public class PCBBBInUserCouponRpc implements IBBBInUserCouponRpc {

    @Autowired
    private IPCBBBInUserCouponService inUserCouponService;

    @Override
    public List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto) {
        List<PCBBBInUserCouponVO> inUserCouponVOList = inUserCouponService.queryInUserCouponList(qto);
        return inUserCouponVOList;
    }

    @Override
    public void getCouponByBuy(BbbInUserCouponQTO.BuyCouponQTO qto) {
        inUserCouponService.getCouponByBuy(qto);
    }

    @Override
    public void getCouponByShare(BbbInUserCouponQTO.ShareCouponQTO qto) {
        inUserCouponService.getCouponByShare(qto);
    }
}
