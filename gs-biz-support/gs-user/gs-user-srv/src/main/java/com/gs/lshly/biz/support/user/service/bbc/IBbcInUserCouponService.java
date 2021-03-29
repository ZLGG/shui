package com.gs.lshly.biz.support.user.service.bbc;

import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
public interface IBbcInUserCouponService {
    /**
     * 查询in会员优惠券列表
     * @param qto
     * @return
     */
    List<BbcInUserCouponVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto);
}
