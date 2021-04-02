package com.gs.lshly.facade.possync.timer;


import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;
import com.gs.lshly.facade.possync.config.QianmiProperties;
import com.gs.lshly.facade.possync.util.OAuthUtil;
import com.gs.lshly.rpc.api.pos.IPosOAuthRpc;
import com.qianmi.open.api.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 每天晚上10点30,定期更新token
 */
@EnableScheduling
@Configuration
@Slf4j
public class RefreshTokenTimer {

    @DubboReference
    private IPosOAuthRpc oAuthRpc;

    @Autowired
    private QianmiProperties properties;

    @Autowired
    private OAuthUtil oAuthUtil;

    @Scheduled(cron = "0 30 11 * * ?")
    public void refresh() {

        String refreshToken = oAuthRpc.getRefreshToken(properties.getKey(), properties.getSecret());
              PosOAuthDTO.TokenDTO token = null;
        try {
            token = oAuthUtil.refreshToken(refreshToken);
        } catch (ApiException e) {
            String msg = "刷新token异常:code[" + e.getErrCode() + "]message[" + e.getErrMsg() + "];" + e.getMessage();
            log.error(msg, e);
        }
//把所有的店铺同步过来 100
        //一个一个店铺的商品

        oAuthRpc.persistenceToken(token);

    }

}
