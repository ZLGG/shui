package com.gs.lshly.biz.support.user.rpc.bbc;

import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBBBInUserCouponService;
import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBBBInUserCouponRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcInUserCouponRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:49
 */
@DubboService
public class BbcInUserCouponRpc implements IBbcInUserCouponRpc {

    @Autowired
    private IBbcInUserCouponService inUserCouponService;

    @Override
    public List<BbcInUserCouponVO.ListVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto) {
        List<BbcInUserCouponVO.ListVO> inUserCouponVOList = inUserCouponService.queryInUserCouponList(qto);
        return inUserCouponVOList;
    }

    @Override
    public List<BbcInUserCouponVO.MyCouponListVO> getMyCouponToUse(BbcInUserCouponQTO.MyCouponQTO qto) {
        return inUserCouponService.getMyCouponToUse(qto);
    }

    @Override
    public List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.QTO qto) {
        return inUserCouponService.getCardList(qto);
    }

    @Override
    public void getCouponByShare(BbcInUserCouponQTO.ShareCouponQTO qto) {
        inUserCouponService.getCouponByShare(qto);
    }

    @Override
    public void getCouponByBuy(BbcInUserCouponQTO.BuyCouponQTO qto) {
        inUserCouponService.getCouponByBuy(qto);
    }
}
