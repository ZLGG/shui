package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserAuthRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserCardRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-27
*/
@Component
public class BbbH5UserServiceImpl implements IBbbH5UserService {

    @Autowired
    private IUserRepository repository;

    @DubboReference
    private IBbbH5TradeRpc bbbTradeRpc;

    @DubboReference
    private IBbbH5UserCardRpc bbbH5UserCardRpc;

    @DubboReference
    private IBbbH5UserAuthRpc iBbbH5UserAuthRpc;

    @Override
    public BbbH5UserVO.DetailVO getUserInfo(BbbH5UserQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  repository.getById(qto.getJwtUserId());
        BbbH5UserVO.DetailVO detailVO = new BbbH5UserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        Integer integer = bbbTradeRpc.myMerchantCard(qto);
        detailVO.setCountCard(integer);
        BbbH5TradeDTO.IdDTO idDTO = new BbbH5TradeDTO.IdDTO();
        idDTO.setJwtUserId(qto.getJwtUserId());
        BbbH5UserVO.ThirdVO thirdVO = iBbbH5UserAuthRpc.innerGetWXNickName(qto.getJwtUserId());
        if (ObjectUtils.isNotEmpty(thirdVO)){
            detailVO.setNickName(thirdVO.getNickName());
        }
        //todo 欧阳
        detailVO.setTradeStateList( bbbTradeRpc.tradeStateCount(idDTO));
        //获取用户的积分
        BbbH5UserVO.UserIntegralVO integral = bbbH5UserCardRpc.integral(qto);
        if (ObjectUtils.isNotEmpty(integral)){
            detailVO.setIntegral(integral.getOkIntegral());
        }
        return detailVO;
    }

    @Override
    public BbbH5UserVO.InnerUserInfoVO innerGetUserInfo(String userId) {
        User user =  repository.getById(userId);
        if(null == user){
            return null;
        }
        BbbH5UserVO.InnerUserInfoVO detailVO = new BbbH5UserVO.InnerUserInfoVO();
        BeanUtils.copyProperties(user,detailVO);
        return detailVO;
    }

    @Override
    public void editorUserInfo(BbbH5UserDTO.UserInfoETO dto) {
        if(null == dto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  repository.getById(dto.getJwtUserId());
        BeanUtils.copyProperties(dto,user);
        repository.updateById(user);
    }

}
