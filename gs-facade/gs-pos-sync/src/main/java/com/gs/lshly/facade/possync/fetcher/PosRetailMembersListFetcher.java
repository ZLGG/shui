package com.gs.lshly.facade.possync.fetcher;


import com.gs.lshly.common.struct.pos.dto.PosMembersListRequestDTO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.facade.possync.fetcher.BasePosFetcher;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.request.MembersListRequest;
import com.qianmi.open.api.request.StoreGetRequest;
import com.qianmi.open.api.response.MembersListResponse;
import com.qianmi.open.api.response.StoreGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * 获取商城/小店的商家会员列表
 * 获取云商城/云小店零售系统的 商家会员列表，分销商会员请调用分销会员API
 */
@Component
@Slf4j
public class PosRetailMembersListFetcher extends BasePosFetcher<MembersListResponse> {

    @DubboReference
    IPosShopRpc shopRpc;

    private String buildFields(){
        StringBuffer buffer = new StringBuffer();
        //会员编号
        buffer.append("member_id").append(",");
        //会员昵称
        buffer.append("member_nick").append(",");
        //会员手机号码
        buffer.append("mobile").append(",");
        //用户状态，0：锁定，1：正常
        buffer.append("status").append(",");
        //积分
        buffer.append("integral").append(",");
        //调用是否成功
        buffer.append("is_success");
        return buffer.toString();
    }



    @Override
    protected <Params> QianmiRequest<MembersListResponse> setRequest(Params params) {
        PosMembersListRequestDTO.DTO dto = (PosMembersListRequestDTO.DTO)params;
        MembersListRequest req = new MembersListRequest ();
        req.putOtherTextParam("actual_user_id",dto.getShopId());
        req.setFields(this.buildFields());
        req.setPageSize(dto.getPageSize());
        req.setPageNo(dto.getPageNum());
        return req;
    }

    @Override
    protected MembersListResponse  fetchData(MembersListResponse  response) {

        //1, 接口返回数据, rpc持久化数据

        //2, 并告知pos消息消费结果

        log.info(JsonUtils.toJson(response));

        return response;

    }


}
