package com.gs.lshly.facade.possync.controller;


import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.PosOAuthDTO;
import com.gs.lshly.facade.possync.config.QianmiProperties;
import com.gs.lshly.facade.possync.timer.RefreshTokenTimer;
import com.gs.lshly.facade.possync.util.OAuthUtil;
import com.gs.lshly.rpc.api.pos.IPosOAuthRpc;
import com.qianmi.open.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
//@RestController
//@RequestMapping("/pos-sync/oauth")
//@Api(tags = "POS授权认证接口")
public class PosOAuthController {

    private static final String Qianmi_Auth_Url = "https://oauth.qianmi.com/authorize?client_id=CLIENTID&response_type=code&redirect_uri=REDIRECTURI&state=gs-lshly&view=web";

    @Autowired
    private QianmiProperties properties;

    @Autowired
    private OAuthUtil oAuthUtil;

    @DubboReference
    private IPosOAuthRpc oAuthRpc;

    @Autowired
    private RefreshTokenTimer timer;

    @ApiOperation("获取用户授权跳转地址")
    @GetMapping("/url")
    public ResponseData<String> url() {

        String url = Qianmi_Auth_Url.replace("CLIENTID", properties.getKey()).replace("REDIRECTURI", properties.getRedirectURI());

        return ResponseData.data(url);
    }

    @ApiOperation("刷新token")
    @GetMapping("/refresh")
    public ResponseData<String> refresh() {

        timer.refresh();

        return ResponseData.success();
    }

    /**
     * 供千米回调
     * @return
     */
    @GetMapping("")
    public ResponseData<String> code(String code, String state) {

        log.info("收到千米回调:code:{},state:{}", code, state);

        try {
            //获取accessToken,并持久化
            PosOAuthDTO.TokenDTO token = oAuthUtil.getToken(code);
            oAuthRpc.persistenceToken(token);
        } catch (ApiException e) {
            String msg = "获取token异常:code[" + e.getErrCode() + "]message[" + e.getErrMsg() + "];" + e.getMessage();
            log.error(msg, e);
            throw new BusinessException(msg);
        }

        return ResponseData.success();
    }

}
