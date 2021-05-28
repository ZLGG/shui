package com.gs.lshly.rpc.api.platadmin.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;

/**
 * 优惠券 rpc
 * @author chenyang
 */
public interface ICouponRpc {

    /**
     * 新增优惠券
     * @param qto
     * @return
     */
    Boolean saveCoupon(CouponQTO.SaveCouponQTO qto);

    /**
     * 更新优惠券
     * @param qto
     * @return
     */
    Boolean updateCoupon(CouponQTO.UpdateCouponQTO qto);

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    Boolean deleteCoupon(String id);

    /**
     * 优惠券详情
     * @param id
     * @return
     */
    CouponVO.CouponDetailVO getDetail(String id);

    /**
     * 优惠券分页列表
     * @return
     */
    PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO);

    /**
     * 停止发放
     * @return
     */
    Boolean stopSend(String id);

    /**
     * 修改库存
     * @return
     */
    Boolean updateStockNum(CouponQTO.CouponStockQTO qto);

    /**
     * 审核优惠券
     * @return
     */
    Boolean dealAduitCoupon(CouponQTO.CouponAduitQTO qto);
}
