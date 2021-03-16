package com.gs.lshly.biz.support.user.rpc.common;

import com.gs.lshly.biz.support.user.service.common.ICommonUserService;
import com.gs.lshly.common.struct.common.CommonUserVO;
import com.gs.lshly.rpc.api.common.ICommonUserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-05
*/
@DubboService
public class CommonUserRpc implements ICommonUserRpc{

    @Autowired
    private ICommonUserService userService;


    @Override
    public CommonUserVO.DetailVO details(String userId) {
        return userService.details(userId);
    }

    @Override
    public CommonUserVO.DetailVO detailsByUserName(String userName) {
        return userService.detailsByUserName(userName);
    }

    @Override
    public List<CommonUserVO.DetailVO> moreDetail(List<String> userIdList) {
        return userService.moreDetail(userIdList);
    }

    @Override
    public CommonUserVO.selectUserIdByShopIdVO selectUserIdByShopId(String userId) {
        return userService.selectUserIdByShopId(userId);
    }

}