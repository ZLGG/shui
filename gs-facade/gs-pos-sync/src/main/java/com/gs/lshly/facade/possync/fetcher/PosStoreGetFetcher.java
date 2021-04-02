package com.gs.lshly.facade.possync.fetcher;


import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.DefaultOpenClient;
import com.qianmi.open.api.OpenClient;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.request.StoreGetRequest;
import com.qianmi.open.api.request.StoreRelationshipListRequest;
import com.qianmi.open.api.response.StoreGetResponse;
import com.qianmi.open.api.response.StoreRelationshipListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 获取店铺基本信息
 */
@Component
@Slf4j
public class PosStoreGetFetcher extends BasePosFetcher<StoreGetResponse> {


    @DubboReference
    IPosShopRpc shopRpc;

    @Override
    protected <Params> QianmiRequest<StoreGetResponse> setRequest(Params params) {
        StoreGetRequest req = new StoreGetRequest();
        req.setFields("admin_id,store_name,province,city,area,street,address_detail,longitude,latitude,cellphone,scene_name,industry_name");
        req.setActualUserId("");
        return req;
    }

    @Override
    protected StoreGetResponse fetchData(StoreGetResponse response) {

        //1, 接口返回数据, rpc持久化数据

        //2, 并告知pos消息消费结果

        log.info(JsonUtils.toJson(response));

        return response;

    }


}
