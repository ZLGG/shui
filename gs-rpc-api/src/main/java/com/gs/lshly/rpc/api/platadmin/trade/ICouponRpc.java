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
     * @param saveCouponDTO
     * @return
     */
    Boolean saveCoupon(CouponDTO.SaveCouponDTO saveCouponDTO);

    /**
     * 更新优惠券
     * @param updateCouponDTO
     * @return
     */
    Boolean updateCoupon(CouponDTO.UpdateCouponDTO updateCouponDTO);

    /**
     * 删除优惠券
     * @param id
     * @return
     */
    Boolean deleteCoupon(Long id);

    /**
     * 优惠券详情
     * @param id
     * @return
     */
    CouponVO.CouponDetailVO getDetail(Long id);

    /**
     * 优惠券分页列表
     * @return
     */
    PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO);
}