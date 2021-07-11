package com.gs.lshly.biz.support.user.rpc.bbc;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.struct.bbc.user.dto.BbcInUserCouponDTO.CreateDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO.DetailVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO.ListVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcInUserCouponRpc;

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
    public List<BbcInUserCouponVO.GoodsCouponListVO> getGoodsCoupon(BbcInUserCouponQTO.GoodsCouponQTO qto) {
        return inUserCouponService.getGoodsCoupon(qto);
    }

    @Override
    public List<BbcInUserCouponVO.MyCouponListVO> getMyCouponToUse(BbcInUserCouponQTO.MyCouponQTO qto) {
        return inUserCouponService.getMyCouponToUse(qto);
    }

    @Override
    public List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.CardQTO qto) {
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

	@Override
	public List<ListVO> listByCouponId(BbcUserCouponQTO.ListByCouponIdQTO qto) {
		return inUserCouponService.listByCouponId(qto);
	}

	@Override
	public void createInUserCoupon(CreateDTO dto) {
		inUserCouponService.createInUserCoupon(dto);
		
	}

	@Override
	public List<String> modifyUserCoupon(List<String> couponIds, String userId, Integer status) {
		return inUserCouponService.modifyUserCoupon(couponIds,userId,status);
		
	}

	@Override
	public DetailVO detailCoupon(String couponId) {
		// TODO Auto-generated method stub
		return inUserCouponService.detailCoupon(couponId) ;
	}
}
