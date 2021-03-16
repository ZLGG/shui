package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IFdSysUserService;
import com.gs.lshly.common.struct.platadmin.foundation.vo.FdSysUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-06
*/
@Component
public class FdSysUserServiceImpl implements IFdSysUserService {

    @Autowired
    private ISysUserRepository repository;

    @Override
    public FdSysUserVO.SimpleVO getSysUserName(String userId) {
        SysUser sysUser = repository.getById(userId);
        if(null == sysUser){
          return null;
        }
        FdSysUserVO.SimpleVO simpleVO = new FdSysUserVO.SimpleVO();
        simpleVO.setId(sysUser.getId());
        simpleVO.setName(sysUser.getName());
      return simpleVO;
    }
}
