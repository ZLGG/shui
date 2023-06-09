package com.gs.lshly.biz.support.user.service.bbc.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserCtccPoint;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.repository.IUserCtccPointRepository;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserCtccPointService;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserIntegralService;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO.DetailVO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO.UserTypeVO;
import com.gs.lshly.common.utils.AESUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserAuthRpc;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-27
*/
@Component
public class BbcUserServiceImpl implements IBbcUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private IUserCtccPointRepository userCtccPointRepository;

    @Autowired
    private UserIntegralMapper userIntegralMapper;
    @Autowired
    private IUserIntegralRepository userIntegralRepository;

    @Autowired
    private IBbcUserCtccPointService bbcUserCtccPointService;

    @Autowired
    private IBbcUserIntegralService iBbcUserIntegralService;

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @DubboReference
    private IBbcUserAuthRpc iBbcUserAuthRpc;


    /**
     * 获取用户详情
     */
    @Override
    public BbcUserVO.DetailVO getUserInfo(BbcUserQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  repository.getById(qto.getJwtUserId());
        BbcUserVO.DetailVO detailVO = new BbcUserVO.DetailVO();
        detailVO.setCountCard(bbcTradeRpc.myMerchantCard(qto));
        BeanUtils.copyProperties(user,detailVO);

        /**
         * 获取电信积分数据 DUBBO
         */
        BbcUserCtccPointVO.DetailVO ctccPoint = bbcUserCtccPointService.getCtccPointByUserId(qto.getJwtUserId());

        if(ctccPoint!=null){
        	detailVO.setTelecomsIntegral(ctccPoint.getPointBalance());
        	detailVO.setTelecomsPass(ctccPoint.getYearBalance());
        }

        detailVO.setCountCard(10);
        BbcTradeDTO.IdDTO idDTO = new BbcTradeDTO.IdDTO();
        idDTO.setJwtUserId(qto.getJwtUserId());
        detailVO.setTradeStateList( bbcTradeRpc.tradeStateCount(idDTO));
        BbcUserVO.UserIntegralVO integral = iBbcUserIntegralService.integral(qto);
        if (ObjectUtils.isNotEmpty(integral)){
            detailVO.setIntegral(integral.getOkIntegral());
        }
        BbcUserVO.ThirdVO thirdVO = iBbcUserAuthRpc.innerGetWXNickName(qto.getJwtUserId());
        if (ObjectUtils.isNotEmpty(thirdVO)){
            detailVO.setNickName(thirdVO.getNickName());
        }
        // 查询购物车是否加购商品
        Integer isExist = userIntegralMapper.goodsIsInCart(qto.getJwtUserId());
        detailVO.setGoodsIsInCart(isExist);
        if (isExist != 0) {
            detailVO.setGoodsIsInCart(1);
        }
        return detailVO;
    }

    @Override
    public BbcUserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        User user =  repository.getById(userId);
        if(null == user){
            return null;
        }
        BbcUserVO.InnerUserInfoVO detailVO = new BbcUserVO.InnerUserInfoVO();
        BeanUtils.copyProperties(user,detailVO);
        return detailVO;
    }

    @Override
    public BbcUserVO.UserIntegralVO integral(BaseDTO dto) {
        QueryWrapper<UserCtccPoint> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",dto.getJwtUserId());
        UserCtccPoint userCtccPoint = userCtccPointRepository.getOne(queryWrapper);
        BbcUserVO.UserIntegralVO userIntegralVO  = new BbcUserVO.UserIntegralVO();
        userIntegralVO.setOkIntegral(0);
    	userIntegralVO.setJpassIntegral(0);
        if(null != userCtccPoint){
        	userIntegralVO.setOkIntegral(userCtccPoint.getPointBalance());
        	userIntegralVO.setJpassIntegral(userCtccPoint.getYearBalance());
        }
        return userIntegralVO;
    }

    @Override
    public BbcUserVO.MyIntegralVO myIntegral(String userId) {
        BbcUserVO.MyIntegralVO myIntegralVO = userIntegralMapper.myIntegral(userId);
    	/**
         * 获取电信积分数据 DUBBO
         */
        BbcUserCtccPointVO.DetailVO ctccPoint = bbcUserCtccPointService.getCtccPointByUserId(userId);
        myIntegralVO.setDirectionIntegral(0);
        myIntegralVO.setOkIntegral(ctccPoint.getPointBalance());
        myIntegralVO.setYearBalance(ctccPoint.getYearBalance());
        String phone = AESUtil.aesDecrypt(myIntegralVO.getPhone());
        myIntegralVO.setPhone(phone);
        return myIntegralVO;
    }

    @Override
    public List<BbcUserVO.UserIntegralRecordVO> integralLog(BbcUserDTO.IntegralLogQTO qto) {
        QueryWrapper<UserIntegral> userIntegralQueryWrapper = MybatisPlusUtil.query();
        userIntegralQueryWrapper.eq("user_id",qto.getJwtUserId());
        List<UserIntegral> userIntegralList =  userIntegralRepository.list(userIntegralQueryWrapper);
        return ListUtil.listCover(BbcUserVO.UserIntegralRecordVO.class,userIntegralList);
    }

	@Override
	public DetailVO getUserInfoNoLogin(BaseDTO dto) {
		BbcUserVO.DetailVO detailVO = new BbcUserVO.DetailVO();
		if(null == dto.getJwtUserId())
			return detailVO;

        User user =  repository.getById(dto.getJwtUserId());
        if(user == null)
        	return detailVO;

        BeanUtils.copyProperties(user,detailVO);

        /**
         * 获取电信积分数据 DUBBO
         */
        BbcUserCtccPointVO.DetailVO ctccPoint = bbcUserCtccPointService.getCtccPointByUserId(dto.getJwtUserId());

        if(ctccPoint!=null){
        	detailVO.setTelecomsIntegral(ctccPoint.getPointBalance());
        	detailVO.setTelecomsPass(ctccPoint.getYearBalance());
        }
        /**
        detailVO.setCountCard(bbcTradeRpc.myMerchantCard(dto));
        detailVO.setCountCard(10);


        BbcTradeDTO.IdDTO idDTO = new BbcTradeDTO.IdDTO();
        idDTO.setJwtUserId(dto.getJwtUserId());
        detailVO.setTradeStateList( bbcTradeRpc.tradeStateCount(idDTO));


         * 商城原积分

        BbcUserVO.UserIntegralVO integral = iBbcUserIntegralService.integral(dto);
        if (ObjectUtils.isNotEmpty(integral)){
            detailVO.setIntegral(integral.getOkIntegral());
        }

        /**
         * 微信信息
        BbcUserVO.ThirdVO thirdVO = iBbcUserAuthRpc.innerGetWXNickName(dto.getJwtUserId());
        if (ObjectUtils.isNotEmpty(thirdVO)){
            detailVO.setNickName(thirdVO.getNickName());
        }
        */
        return detailVO;
	}

	@Override
	public UserTypeVO getUserType(BaseDTO dto) {
		BbcUserVO.UserTypeVO userTypeVO = new BbcUserVO.UserTypeVO();
		if(null == dto.getJwtUserId())
			return userTypeVO;

        User user =  repository.getById(dto.getJwtUserId());
        if(user == null)
        	return userTypeVO;

        BeanUtils.copyProperties(user,userTypeVO);
        return userTypeVO;
	}

    @Override
    public String gePhoneById(String userId) {
        return repository.getById(userId).getPhone();
    }

}
