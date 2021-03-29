package com.gs.lshly.rpc.api.bbb.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBBBInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:14
 */
public interface IBBBInUserCouponRpc {
    List<PCBBBInUserCouponVO> queryInUserCouponList(BbbInUserCouponQTO.QTO qto);
}
