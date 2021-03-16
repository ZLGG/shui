package com.gs.lshly.facade.platform.config;

import cn.hutool.core.thread.ThreadUtil;
import com.gs.lshly.common.struct.PermitNode;
import com.gs.lshly.middleware.auth.config.RbacConfig;
import com.gs.lshly.middleware.auth.intercept.PermissionInterceptor;
import com.gs.lshly.rpc.api.platadmin.foundation.rbac.ISysFuncRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 权限初始化到DB
 * @author lxus
 * @since 2020-12-08
 */
@Configuration
@Slf4j
@Import(RbacConfig.class)
public class PlatformRbacConfig implements CommandLineRunner {

    @DubboReference
    private ISysFuncRpc funcRpc;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

	@Override
	public void run(String... args) {
		new Thread() {
			public void run() {
				while (true) {
					try {
						PermitNode allPermitNode = funcRpc.initFuncTree2DB(permissionInterceptor.getAllPermitNode());
						log.info("完成权限数持久化");
						permissionInterceptor.updateAllPermitNode(allPermitNode);
						log.info("更新缓存");
						return;
					} catch (RpcException rpcException) {
						log.info("等待远程服务10秒");
						ThreadUtil.sleep(1000 * 10);
					}
				}
			}
		}.start();
	}
}
