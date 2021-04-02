package com.gs.lshly.middleware.auth.security;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.JwtUser;
import com.gs.lshly.common.utils.JwtUtil;
import com.gs.lshly.middleware.log.aop.LogAspect;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;

/**
 * @author lxus
 */
@Activate(group = CONSUMER)
public class BaseDTOFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Object[] objects = invocation.getArguments();
        if (objects != null) {
            for (Object object : objects) {
                if (object instanceof BaseDTO) {
                    JwtUser jwtUser = JwtUtil.session();
                    BaseDTO baseDTO = (BaseDTO) object;
                    if (jwtUser != null) {
                        baseDTO.setJwtUserId(jwtUser.getId())
                                .setJwtUserName(jwtUser.getUsername())
                                .setJwtUserType(jwtUser.getType())
                                .setJwtShopId(jwtUser.getShopId())
                                .setJwtMerchantId(jwtUser.getMerchantId())
                                .setJwtWxOpenid(jwtUser.getWxOpenid());
                    } else {
                        BaseDTO logThreadDTO = LogAspect.get();
                        if (logThreadDTO != null) {
                            baseDTO.setJwtUserId(logThreadDTO.getJwtUserId())
                                    .setJwtUserName(logThreadDTO.getJwtUserName())
                                    .setJwtUserType(logThreadDTO.getJwtUserType())
                                    .setJwtShopId(logThreadDTO.getJwtShopId())
                                    .setJwtMerchantId(logThreadDTO.getJwtMerchantId())
                                    .setJwtWxOpenid(logThreadDTO.getJwtWxOpenid());
                        }
                    }
                }
            }
        }
        return invoker.invoke(invocation);
    }
}
