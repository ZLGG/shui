package com.gs.lshly.biz.support.trade.rpc.platadmin;

import com.gs.lshly.biz.support.trade.service.platadmin.ICouponService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;
import com.gs.lshly.rpc.api.platadmin.trade.ICouponRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author chenyang
 */
@DubboService
public class CouponRpc implements ICouponRpc {

    @Autowired
    private ICouponService iCouponService;

    @Override
    public Boolean saveCoupon(CouponDTO.SaveCouponDTO saveCouponDTO) {
        return iCouponService.saveCoupon(saveCouponDTO);
    }

    @Override
    public Boolean updateCoupon(CouponDTO.UpdateCouponDTO updateCouponDTO) {
        return iCouponService.updateCoupon(updateCouponDTO);
    }

    @Override
    public Boolean deleteCoupon(Long id) {
        return iCouponService.deleteCoupon(id);
    }

    @Override
    public CouponVO.CouponDetailVO getDetail(Long id) {
        return iCouponService.getDetail(id);
    }

    @Override
    public PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO) {
        return iCouponService.queryCouponList(couponListQTO);
    }
}
