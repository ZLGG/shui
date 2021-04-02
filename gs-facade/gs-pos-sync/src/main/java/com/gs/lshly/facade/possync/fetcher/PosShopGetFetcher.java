package com.gs.lshly.facade.possync.fetcher;


import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.request.ShopGetRequest;
import com.qianmi.open.api.request.StoreRelationshipListRequest;
import com.qianmi.open.api.response.ShopGetResponse;
import com.qianmi.open.api.response.StoreRelationshipListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 获取商家店铺的基本信息
 */
@Component
@Slf4j
public class PosShopGetFetcher extends BasePosFetcher<ShopGetResponse> {


    @DubboReference
    IPosShopRpc shopRpc;

    @Override
    protected <Params> QianmiRequest<ShopGetResponse> setRequest(Params params) {
        ShopGetRequest request = new ShopGetRequest();

        return request;
    }

    @Override
    protected ShopGetResponse fetchData(ShopGetResponse response) {

        //1, 接口返回数据, rpc持久化数据

        //2, 并告知pos消息消费结果

        log.info(JsonUtils.toJson(response));

        return response;

    }


}
