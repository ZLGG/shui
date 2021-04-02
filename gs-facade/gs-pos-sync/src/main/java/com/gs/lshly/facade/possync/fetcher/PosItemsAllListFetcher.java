package com.gs.lshly.facade.possync.fetcher;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosLogDTO;
import com.gs.lshly.common.struct.pos.dto.PosItemsAllListRequestDTO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosLogRpc;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.domain.cloudshop.Item;
import com.qianmi.open.api.domain.cloudshop.Sku;
import com.qianmi.open.api.domain.cloudshop.StoreRelationshipInfo;
import com.qianmi.open.api.request.ItemsAllListRequest;
import com.qianmi.open.api.request.MembersListRequest;
import com.qianmi.open.api.response.ItemsAllListResponse;
import com.qianmi.open.api.response.MembersListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取商家所有商品列表
 * 初始化同步商品接口，获取商家所有的商品仓库商品列表,该API 包含了所有上下架商品，新接入用户初始化商品请调用此接口
 */
@Component
@Slf4j
public class PosItemsAllListFetcher extends BasePosFetcher<ItemsAllListResponse> {

    private static  final  String separate = ",";

    @DubboReference
    IPCMerchGoodsPosLogRpc goodsPosLogRpc;

    private String buildFields(){
        StringBuffer buffer = new StringBuffer();
        //商品主图地址
        buffer.append("pic_url").append(separate);
        //商品数量
        buffer.append("num").append(separate);
        //商品图片列表
        buffer.append("item_imgs").append(separate);
        //商品价格 单位元 两位小数
        buffer.append("price").append(separate);
        //是否虚拟商品
        buffer.append("is_virtual").append(separate);
        //商品编号
        buffer.append("num_iid").append(separate);
        //商品标题
        buffer.append("title").append(separate);
        //商品编号
        buffer.append("num_iid").append(separate);
        //商品标题
        buffer.append("title").append(separate);
        //商品编号
        buffer.append("num_iid").append(separate);
        //商品描述
        buffer.append("desc").append(separate);
        //sku列表
        buffer.append("skus").append(separate);
        //属性名称，格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2
        buffer.append("props_name").append(separate);
        //商品计量单位
        buffer.append("unit").append(separate);
        //是否拍下减库存 0拍下减库存 1付款减库存
        buffer.append("sub_stock").append(separate);
        //商品的重量 单位 克
        buffer.append("item_weight").append(separate);
        //商品品牌编号
        buffer.append("brand_id").append(separate);
        //品牌名称
        buffer.append("brand_name").append(separate);
        //成本价,单位元，保留2位小数
        buffer.append("cost_price").append(separate);
        //市场价，单位元，保留2位小数
        buffer.append("mkt_price").append(separate);
        //关键属性，格式为：epid1:evid1:epid_name1:evid_name1;epid2:evid2:epid_name2:evid_name2
        buffer.append("ext_props").append(separate);
        //属性参数 ， 格式有两种：第一种：groupName1:name1:value1;groupName2:name2:value2;groupName2:name3:value4，第二种：文本格式
        buffer.append("param_props");
        return buffer.toString();
    }

    @Override
    protected <Params> QianmiRequest<ItemsAllListResponse> setRequest(Params params) {
        PosItemsAllListRequestDTO.DTO dto = (PosItemsAllListRequestDTO.DTO) params;
        ItemsAllListRequest req = new ItemsAllListRequest();
        req.setFields(this.buildFields());
        req.putOtherTextParam("actual_user_id",dto.getShopId());
        //最大50
        req.setPageSize(dto.getPageSize());
        req.setPageNo(dto.getPageNum());
        return req;
    }

    @Override
    protected ItemsAllListResponse  fetchData(ItemsAllListResponse  response) {
        List<Item> itemList = response.getItems();
        if (!ObjectUtils.isEmpty(itemList)){
            List<PCMerchGoodsPosLogDTO.ETO> etoList = itemList.parallelStream().map(e ->{
                PCMerchGoodsPosLogDTO.ETO eto = new PCMerchGoodsPosLogDTO.ETO();
                BeanUtils.copyProperties(e,eto);
                if (StringUtils.isNotBlank(e.getSubStock())){
                    eto.setSubStock(Integer.parseInt(e.getSubStock()));
                }
                eto.setPrice(ObjectUtils.isEmpty(e.getPrice())?BigDecimal.ZERO:new BigDecimal(e.getPrice()));
                eto.setCostPrice(ObjectUtils.isEmpty(e.getCostPrice())?BigDecimal.ZERO:new BigDecimal(e.getCostPrice()));
                eto.setItemWeight(ObjectUtils.isEmpty(e.getItemWeight())?BigDecimal.ZERO:new BigDecimal(e.getItemWeight()));
                eto.setMktPrice(ObjectUtils.isEmpty(e.getMktPrice())?BigDecimal.ZERO:new BigDecimal(e.getMktPrice()));
                return eto;
            }).collect(Collectors.toList());
            goodsPosLogRpc.addGoodsPosLog(etoList);
        }
        return response;
    }


}
