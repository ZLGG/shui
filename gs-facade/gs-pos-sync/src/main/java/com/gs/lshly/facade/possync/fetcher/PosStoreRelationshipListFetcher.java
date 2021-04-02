package com.gs.lshly.facade.possync.fetcher;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.domain.cloudshop.StoreRelationshipInfo;
import com.qianmi.open.api.request.StoreRelationshipListRequest;
import com.qianmi.open.api.response.StoreRelationshipListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 获取连锁或则商圈关系内的店铺
 */
@Component
@Slf4j
public class PosStoreRelationshipListFetcher extends BasePosFetcher<StoreRelationshipListResponse> {

    private static  final  String separate = ",";

    @DubboReference
    IPosShopRpc shopRpc;

    private String buildFields(){
        StringBuffer buffer = new StringBuffer();
        //会员编号
        buffer.append("admin_id").append(separate);
        //会员昵称
        buffer.append("shop_name").append(separate);
        //会员手机号码
        buffer.append("province").append(separate);
        //用户状态，0：锁定，1：正常
        buffer.append("city").append(separate);
        //积分
        buffer.append("area").append(separate);
        //调用是否成功
        buffer.append("address_detail").append(separate);
        buffer.append("longitude").append(separate);
        buffer.append("latitude").append(separate);
        buffer.append("cellphone").append(separate);
        return buffer.toString();
    }

    @Override
    protected <Params> QianmiRequest<StoreRelationshipListResponse> setRequest(Params params) {
        StoreRelationshipListRequest req = new StoreRelationshipListRequest();
        req.setFields("admin_id,shop_id,shop_name,province,city,area,street,address_detail,longitude,latitude,cellphone");
        req.setPageNo(0);
        req.setPageSize(10);
        return req;
    }

    @Override
    protected StoreRelationshipListResponse fetchData(StoreRelationshipListResponse response) {
        //1, 接口返回数据, rpc持久化数据
        //2, 并告知pos消息消费结果
        JSONObject jsonObject = JSONUtil.parseObj(response.getBody());
        if(null !=  jsonObject){
            JSONObject   storeRelationshipListResponse  = jsonObject.getJSONObject("store_relationship_list_response");
            if(null != storeRelationshipListResponse){
                JSONArray jsonArray =  storeRelationshipListResponse.getJSONArray("stores");
                if(null != jsonArray){
                    List<StoreRelationshipInfo> storeList = JSONUtil.toList(jsonArray,StoreRelationshipInfo.class);
                    if(!ObjectUtils.isEmpty(storeList)){
                        //保存到数据库
                        for(StoreRelationshipInfo storeRelationshipInfo:storeList){
                            log.info("store:{}",storeRelationshipInfo.getLatitude());
                        }
                    }
                }
            }
        }
        return response;

    }


}
