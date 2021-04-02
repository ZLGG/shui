package com.gs.lshly.facade.possync.fetcher;

import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.facade.possync.config.QianmiProperties;
import com.gs.lshly.rpc.api.pos.IPosOAuthRpc;
import com.qianmi.open.api.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
public abstract class BasePosFetcher<T extends QianmiResponse> {

    @Autowired
    private QianmiProperties properties;

    @DubboReference
    private IPosOAuthRpc oAuthRpc;

    protected <Params> T getFetcher(Params params) {

        OpenClient client = new DefaultOpenClient(properties.getServerUrl(), properties.getKey(), properties.getSecret());
        QianmiRequest<T> req = setRequest(params);
        String accessToken = accessToken(properties.getKey(), properties.getSecret());
        try {

            log.info("serverUrl:{},accessToken:{},key:{},secret:{}",
                    properties.getServerUrl(), accessToken, properties.getKey(), properties.getSecret());

            return client.execute(req, accessToken);
        } catch (ApiException e) {
            String msg = "接口调用异常:code[" + e.getErrCode() + "]message[" + e.getErrMsg() + "];" + e.getMessage();
            log.error(msg, e);
            throw new BusinessException(msg);
        }
    }

    private String accessToken(String key, String secret) {

        String token = oAuthRpc.getToken(key, secret);

        if (StringUtils.isBlank(token)) {
            throw new BusinessException("未找到千米访问token,请先进行用户授权操作");
        }
        return token;
    }

    protected abstract <Params> QianmiRequest<T> setRequest(Params params);


    public  <Params> T fetch(Params params) {
        T t = getFetcher(params);
        return fetchData(t);
    }
    protected abstract T fetchData(T response);
}
