package com.gs.lshly.biz.support.user.service.bbc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.user.entity.UserCtccPoint;
import com.gs.lshly.biz.support.user.repository.IUserCtccPointRepository;
import com.gs.lshly.biz.support.user.service.bbc.IBbcUserCtccPointService;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserCtccPointVO.DetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
 * @Author yangxi
 * @create 2021/3/22 14:56
 */
@Component
public class BbcUserCtccPointServiceImpl implements IBbcUserCtccPointService {

    @Autowired
    private IUserCtccPointRepository repository;
    
    
	@Override
	public DetailVO getCtccPointByUserId(String userId) {
        QueryWrapper<UserCtccPoint> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",userId);
        UserCtccPoint userCtccPoint = repository.getOne(queryWrapper);
        DetailVO detailVO = new DetailVO();
        
        if(userCtccPoint!=null)
        	BeanCopyUtils.copyProperties(userCtccPoint, detailVO);
		return detailVO;
	}

    
}
