package com.gs.lshly.biz.support.user.service.bbc.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.gs.lshly.middleware.sms.IContactSMSService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserCoupon;
import com.gs.lshly.biz.support.user.entity.UserCtccIn;
import com.gs.lshly.biz.support.user.mapper.InUserCouponMapper;
import com.gs.lshly.biz.support.user.mapper.UserCouponDTOMapper;
import com.gs.lshly.biz.support.user.mapper.UserMapper;
import com.gs.lshly.biz.support.user.repository.IUserCtccInRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.repository.InUserCouponRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcInUserCouponService;
import com.gs.lshly.common.enums.UserCouponEnum;
import com.gs.lshly.common.enums.UserCouponStatusEnum;
import com.gs.lshly.common.enums.user.InUserCouponTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcInUserCouponDTO.CreateDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcInUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserCouponQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcInUserCouponVO.DetailVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCouponVO.ListVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.common.utils.DateUtils;
import com.gs.lshly.common.utils.ListUtil;

import cn.hutool.core.collection.CollectionUtil;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
@Component
@SuppressWarnings("unused")
public class BbcInUserCouponServiceImpl implements IBbcInUserCouponService {

	@Autowired
	private IUserRepository userRepository;
	
    @Autowired
    private InUserCouponRepository couponRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private InUserCouponMapper inUserCouponMapper;
    
    @Autowired
    private UserCouponDTOMapper userCouponDTOMapper;
    
    @Autowired
    private IUserCtccInRepository userCtccInRepository;

    @Autowired
    private IContactSMSService iContactSMSService;

    @Override
    public List<BbcInUserCouponVO.ListVO> queryInUserCouponList(BbcInUserCouponQTO.QTO qto) {
        QueryWrapper<UserCoupon> wrapper = new QueryWrapper<>();
        wrapper .eq("user_id",qto.getJwtUserId());
        wrapper.eq("coupon_status",0);
        if (qto.getCouponType() != UserCouponEnum.全部抵扣券.getCode()) {
            wrapper.eq("coupon_type",qto.getCouponType());
        }
        List<UserCoupon> userCouponList = couponRepository.list(wrapper);
        List<BbcInUserCouponVO.ListVO> resultList = ListUtil.listCover(BbcInUserCouponVO.ListVO.class,userCouponList);
        resultList.forEach(coupon ->{
            // 计算距离过期还剩的天数
            coupon.setExpireDays(DateUtils.betweenStartAndEnd(coupon.getStartTime(),coupon.getEndTime()));
        });
        return resultList;
    }

    @Override
    public void getCouponByBuy(BbcInUserCouponQTO.BuyCouponQTO qto) {
        // 判断用户是否为in会员
        User user = userMapper.selectById(qto.getUserId());
        if (0 == user.getIsInUser()) {
            throw new BusinessException("非in会员用户不能获取in会员优惠券");
        }
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getHalfNextYearDate(startTime);
        if (1 == qto.getVipType()) {
            endTime = DateUtils.getNextMonthDate(startTime);
        }
        // 去运营平台获取可发放的优惠券
        List<BbcInUserCouponVO.Coupon> couponList = inUserCouponMapper.queryCouponByBought();
        // 根据运营平台发放规则发放优惠券
        List<UserCoupon> list = new ArrayList<>();
        for (BbcInUserCouponVO.Coupon coupon : couponList) {
            UserCoupon inUserCoupon = new UserCoupon()
                    .setCouponName(coupon.getCouponName())
                    .setUserId(qto.getUserId())
                    .setCouponDesc(coupon.getCouponDesc())
                    .setCouponStatus(UserCouponStatusEnum.未使用.getCode())
                    .setCouponType(UserCouponEnum.IN会员抵扣券.getCode())
                    .setCouponPrice(coupon.getDeductionAmount())
                    .setMinPrice(coupon.getUseThreshold())
                    .setCreateTime(new Date())
                    .setModifyTime(new Date())
                    .setStartTime(startTime)
                    .setEndTime(endTime);
            list.add(inUserCoupon);
        }
        couponRepository.saveBatch(list);
/*        // 自动发放优惠券
        List<InUserCoupon> dtoList = new ArrayList<>();
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.三十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.五十抵扣券.getCode())));
        dtoList.add(generateCoupon(qto,new BigDecimal(InUserCouponPriceEnum.九十九抵扣券.getCode())));
        couponRepository.saveBatch(dtoList);*/
    }

    @Override
    public List<BbcInUserCouponVO.CardList> getCardList(BbcInUserCouponQTO.CardQTO qto) {
        List<BbcInUserCouponVO.CardList> cardList = inUserCouponMapper.getCardList(qto.getJwtUserId());
        return cardList;
    }

    @Override
    public List<BbcInUserCouponVO.GoodsCouponListVO> getGoodsCoupon(BbcInUserCouponQTO.GoodsCouponQTO qto) {
        QueryWrapper<BbcInUserCouponVO.Coupon> boost = new QueryWrapper<>();
        boost.eq("c.audit_status", true);
        boost.eq("c.flag", false);
        // 1.in会员专区优惠券
        Boolean isInGoods = couponRepository.isInUserGoods(qto.getGoodsId());
        if (isInGoods) {
            boost.eq("c.coupon_type", UserCouponEnum.IN会员抵扣券.getCode());
            List<BbcInUserCouponVO.GoodsCouponListVO> resultList = inUserCouponMapper.getGoodsCoupon(boost);
            return resultList;
        }
        // 2.当前商品所有匹配优惠券
        List<String> levelIds = new ArrayList<>();
        if (StringUtils.isNotBlank(qto.getGoodsId())) {
            levelIds.add(qto.getGoodsId());
        }
        if (StringUtils.isNotBlank(qto.getZoneId())) {
            levelIds.add(qto.getZoneId());
        }
        if (StringUtils.isNotBlank(qto.getCategoryId())) {
            levelIds.add(qto.getCategoryId());
        }
        if (ObjectUtils.isNotEmpty(levelIds)) {
            boost.in("r.level_id", qto.getGoodsId());
        }
        List<BbcInUserCouponVO.GoodsCouponListVO> couponList = inUserCouponMapper.getAllGoodsCoupon(boost);
        return couponList;
    }

    @Override
    public List<BbcInUserCouponVO.MyCouponListVO> getMyCouponToUse(BbcInUserCouponQTO.MyCouponQTO qto) {
        // 1.获取当前用户所有优惠券
        List<UserCoupon> userCouponList = couponRepository.list(new QueryWrapper<UserCoupon>()
                .eq("user_id", qto.getJwtUserId())
                .eq("coupon_status", 0));
        // 2.筛选当前商品可用优惠券
        List<BbcInUserCouponVO.MyCouponListVO> resultList = new ArrayList<>();
        // 查询可否使用in会员优惠券
        Boolean isInGoods = couponRepository.isInUserGoods(qto.getGoodsId());
        if (isInGoods) {
            List<UserCoupon> inCouponList = userCouponList.stream().filter(m -> m.getCouponType().equals(UserCouponEnum.IN会员抵扣券.getCode())).collect(Collectors.toList());
            resultList = ListUtil.listCover(BbcInUserCouponVO.MyCouponListVO.class, inCouponList);
        }
        // 查询商品可用优惠券
        for (UserCoupon coupon : userCouponList) {
            // 根据商品id匹配可用优惠券
            if (StringUtils.isNotBlank(qto.getGoodsId())) {
                Boolean isExist = couponRepository.getMyCouponByGoodsId(qto.getGoodsId(), coupon.getCouponId());
                if (isExist) {
                    BbcInUserCouponVO.MyCouponListVO couponVO = new BbcInUserCouponVO.MyCouponListVO();
                    BeanUtils.copyProperties(coupon, couponVO);
                    resultList.add(couponVO);
                }
            }
            // 查询专区可用优惠券
            if (StringUtils.isNotBlank(qto.getZoneId())) {
                // 根据专区id匹配可用优惠券
                Boolean isExist = couponRepository.getMyCouponByGoodsId(qto.getZoneId(), coupon.getCouponId());
                if (isExist) {
                    BbcInUserCouponVO.MyCouponListVO couponVO = new BbcInUserCouponVO.MyCouponListVO();
                    BeanUtils.copyProperties(coupon, couponVO);
                    resultList.add(couponVO);
                }
            }
            // 查询类目可用优惠券
            if (StringUtils.isNotBlank(qto.getCategoryId())) {
                // 根据类目id匹配可用优惠券
                Boolean isExist = couponRepository.getMyCouponByGoodsId(qto.getCategoryId(), coupon.getCouponId());
                if (isExist) {
                    BbcInUserCouponVO.MyCouponListVO couponVO = new BbcInUserCouponVO.MyCouponListVO();
                    BeanUtils.copyProperties(coupon, couponVO);
                    resultList.add(couponVO);
                }
            }
        }
        return resultList;
    }

    @Override
    public void getCouponByShare(BbcInUserCouponQTO.ShareCouponQTO qto) {
        // 判断用户是否为in会员
        User user = userMapper.selectById(qto.getUserId());
        if (0 == user.getIsInUser()) {
            throw new BusinessException("非in会员用户转发不能获取in会员优惠券");
        }
        // 判断本月是否已转发获取过优惠券
        Integer num = inUserCouponMapper.selectIsShare(qto.getUserId());
        if (num != 0) {
            throw new BusinessException("本月已分享获得过优惠券，不可重复获得");
        }
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getNextMonthDate(startTime);
        // 去运营平台获取可发放的优惠券
        List<BbcInUserCouponVO.Coupon> couponList = inUserCouponMapper.queryCouponByShare();
        // 根据运营平台发放规则发放优惠券
        List<UserCoupon> list = new ArrayList<>();
        for (BbcInUserCouponVO.Coupon coupon : couponList) {
        	UserCoupon inUserCoupon = new UserCoupon()
                    .setCouponName(coupon.getCouponName())
                    .setUserId(qto.getUserId())
                    .setCouponDesc(coupon.getCouponDesc())
                    .setCouponStatus(UserCouponStatusEnum.未使用.getCode())
                    .setCouponType(UserCouponEnum.IN会员抵扣券.getCode())
                    .setCouponPrice(coupon.getDeductionAmount())
                    .setMinPrice(coupon.getUseThreshold())
                    .setCreateTime(new Date())
                    .setModifyTime(new Date())
                    .setStartTime(startTime)
                    .setEndTime(endTime);
            list.add(inUserCoupon);
        }
        couponRepository.saveBatch(list);

       /* List<InUserCoupon> userCouponList = new ArrayList<>();
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.三十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.五十抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.九十九抵扣券.getCode())));
        userCouponList.add(getCoupon(qto,new BigDecimal(InUserCouponPriceEnum.二百抵扣券.getCode())));
        couponRepository.saveBatch(userCouponList);*/
    }

    private UserCoupon getCoupon(BbcInUserCouponQTO.ShareCouponQTO qto, BigDecimal money) {
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getNextMonthDate(startTime);
        UserCoupon dto = new UserCoupon()
                .setCouponPrice(money)
                .setCouponDesc("仅限in会员精品专区使用")
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setUserId(qto.getUserId())
                .setCouponStatus(UserCouponStatusEnum.未使用.getCode())
                .setCouponType(InUserCouponTypeEnum.分享赠送.getCode())
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        dto.setUserId(qto.getUserId());
        return dto;
    }

	private UserCoupon generateCoupon(BbbInUserCouponQTO.BuyCouponQTO qto, BigDecimal money) {
        // 判断in会员类型 0-年 1-月
        LocalDate startTime = LocalDate.now();
        LocalDate endTime =  DateUtils.getHalfNextYearDate(startTime);
        if (1 == qto.getVipType()) {
            endTime = DateUtils.getNextMonthDate(startTime);
        }
        UserCoupon dto = new UserCoupon()
                .setCouponPrice(money)
                .setCouponDesc("仅限in会员精品专区使用")
                .setStartTime(startTime)
                .setEndTime(endTime)
                .setUserId(qto.getUserId())
                .setCouponStatus(UserCouponStatusEnum.未使用.getCode())
                .setCouponType(InUserCouponTypeEnum.购买赠送.getCode())
                .setCreateTime(new Date())
                .setModifyTime(new Date());
        dto.setUserId(qto.getUserId());
        return dto;
    }

	@Override
	public List<ListVO> listByCouponId(BbcUserCouponQTO.ListByCouponIdQTO qto) {
		List<ListVO> list = userCouponDTOMapper.listByCouponId(qto.getCouponId(), qto.getJwtUserId());
		return list;
	}

	@Override
	public void createInUserCoupon(CreateDTO dto) {
		
		/**
		 * 改状态
		 */
		Integer day = 0;
		if(dto.getInCouponType()==1){
			day = 30;
		}else if(dto.getInCouponType()==2){
			day = 365;
		}
		String phone = dto.getPhone();
		//跟据手机号码查询用户
		QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",dto.getPhone());
        User user = userRepository.getOne(wrapper);
        if(user==null)
        	throw new BusinessException("跟据手机号码未找到对应的用户！");
        
        if(user.getIsInUser()==1){	//原来已经是IN会员了
        	LocalDate inUserEndDate = user.getInUserEndDate();
        	//1=30 2=356
        	if(inUserEndDate!=null){
        		inUserEndDate = inUserEndDate.plusDays(day);
        	}else{
        		inUserEndDate = LocalDate.now().plusDays(day);
        	}
        }else{
        	user.setInUserEndDate(LocalDate.now().plusDays(day));
        	user.setIsInUser(1);
        }
        
        userRepository.saveOrUpdate(user);
        
        //加明细
        UserCtccIn userCtccIn = new UserCtccIn();
        userCtccIn.setEndTime(LocalDate.now().plusDays(day));
        userCtccIn.setStartTime(LocalDate.now());
        userCtccIn.setStatus(1);
        userCtccIn.setType(dto.getInCouponType());
        userCtccIn.setUserId(user.getId());
        userCtccInRepository.save(userCtccIn);
        
        //加优惠券
        List<BbcCouponVO.InCouponVO> list = new ArrayList<BbcCouponVO.InCouponVO>();
        if(CollectionUtil.isNotEmpty(list)){
        	UserCoupon userCoupon =null;
        	for(BbcCouponVO.InCouponVO inCouponVO:list){
        		userCoupon  = new UserCoupon();
        		BeanCopyUtils.copyProperties(inCouponVO, userCoupon);
        		userCoupon.setUserId(user.getId());
        		couponRepository.saveOrUpdate(userCoupon);
        	}
        }

//        iContactSMSService.couponIssuanceReminder(dto.getPhone(),);
	}

	@Override
	public List<String> modifyUserCoupon(List<String> couponIds, String userId, Integer status) {
		List<String> retList = new ArrayList<String>();
		if(CollectionUtil.isNotEmpty(couponIds)){
			UserCoupon userCoupon = null;
			for(String couponId:couponIds){
				QueryWrapper<UserCoupon> wrapper = new QueryWrapper<>();
		        wrapper .eq("user_id",userId);
		        if(status.equals(UserCouponStatusEnum.使用中.getCode())){
		        	wrapper.eq("coupon_status",UserCouponStatusEnum.未使用.getCode());
		        }else if(status.equals(UserCouponStatusEnum.已使用.getCode())){
		        	wrapper.eq("coupon_status",UserCouponStatusEnum.使用中.getCode());
		        }
		        wrapper.eq("id", couponId);
		        userCoupon = couponRepository.getOne(wrapper);
		        if(userCoupon==null)
		        	throw new BusinessException("没有找到对应的优惠券！");
		        userCoupon.setCouponStatus(status);
		        couponRepository.saveOrUpdate(userCoupon);
		        retList.add(userCoupon.getId());
			}
		}
		return retList;
	}

	@Override
	public DetailVO detailCoupon(String couponId) {
		UserCoupon userCoupon = couponRepository.getById(couponId);
		DetailVO detailVO = new DetailVO();
		if(userCoupon!=null)
			BeanCopyUtils.copyProperties(userCoupon, detailVO);
		return detailVO;
	}
}
