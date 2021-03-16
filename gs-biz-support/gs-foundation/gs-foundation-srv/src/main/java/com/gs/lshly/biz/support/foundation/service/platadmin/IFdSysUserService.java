package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.struct.platadmin.foundation.vo.FdSysUserVO;

public interface IFdSysUserService {

    FdSysUserVO.SimpleVO getSysUserName(String userId);

}