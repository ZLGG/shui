package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;

/**
 *
 * @author chenyang
 */
public interface ICouponService {

    Boolean saveCoupon(CouponQTO.SaveCouponQTO qto);

    Boolean updateCoupon(CouponQTO.UpdateCouponQTO qto);

    Boolean deleteCoupon(Long id);

    CouponVO.CouponDetailVO getDetail(Long id);

    PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO);

    Boolean updateCouponByCondition(CouponDTO.UpdateCouponByConDTO updateCouponByConDTO);
}
