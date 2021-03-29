package com.gs.lshly.biz.support.user.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    private UserIntegralMapper userIntegralMapper;
    @Autowired
    private IUserIntegralRepository userIntegralRepository;

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @Override
    public BbcUserVO.DetailVO getUserInfo(BbcUserQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        User user =  repository.getById(qto.getJwtUserId());
        BbcUserVO.DetailVO detailVO = new BbcUserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        detailVO.setCountCard(10);
        BbcTradeDTO.IdDTO idDTO = new BbcTradeDTO.IdDTO();
        idDTO.setJwtUserId(qto.getJwtUserId());
        detailVO.setTradeStateList( bbcTradeRpc.tradeStateCount(idDTO));
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
        QueryWrapper<UserIntegral> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",dto.getJwtUserId());
        queryWrapper.groupBy("user_id");
        UserIntegralView sumCountView = userIntegralMapper.sumCount(queryWrapper);
        BbcUserVO.UserIntegralVO userIntegralVO  = new BbcUserVO.UserIntegralVO();
        if(null != sumCountView){
            userIntegralVO.setOkIntegral(sumCountView.getQuantity());
        }
        return userIntegralVO;
    }

    @Override
    public List<BbcUserVO.UserIntegralRecordVO> integralLog(BbcUserDTO.IntegralLogQTO qto) {
        QueryWrapper<UserIntegral> userIntegralQueryWrapper = MybatisPlusUtil.query();
        userIntegralQueryWrapper.eq("user_id",qto.getJwtUserId());
        List<UserIntegral> userIntegralList =  userIntegralRepository.list(userIntegralQueryWrapper);
        return ListUtil.listCover(BbcUserVO.UserIntegralRecordVO.class,userIntegralList);
    }

}
