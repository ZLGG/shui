package com.gs.lshly.facade.possync.fetcher;


import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;
import com.gs.lshly.common.struct.pos.dto.PosItemSkusGetRequestDTO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosSkuLogRpc;
import com.gs.lshly.rpc.api.pos.IPosShopRpc;
import com.qianmi.open.api.QianmiRequest;
import com.qianmi.open.api.domain.cloudshop.Sku;
import com.qianmi.open.api.request.ItemSkusGetRequest;
import com.qianmi.open.api.request.MembersListRequest;
import com.qianmi.open.api.response.ItemSkusGetResponse;
import com.qianmi.open.api.response.MembersListResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取多个商品的SKU列表
 */
@Component
@Slf4j
public class PosItemSkusGetFetcher extends BasePosFetcher<ItemSkusGetResponse> {

    @DubboReference
    IPCMerchGoodsPosSkuLogRpc posSkuLogRpc;

    private static  final  String separate = ",";

    private String buildFields(){
        StringBuffer buffer = new StringBuffer();
        //sku编号，g开头
        buffer.append("sku_id").append(separate);
        //sku对应的商品编号
        buffer.append("num_iid").append(separate);
        //属于这个SKU的商品的自有库存
        buffer.append("quantity").append(separate);
        //属于这个sku的商品的价格，保留2位小数，单位元
        buffer.append("price").append(separate);
        //sku状态，normal正常，delete删除
        buffer.append("status").append(separate);
        //sku对应的属性名称，格式为：pid1:vid1:pid_name1:vid_name1; pid2:vid2:pid_name2:vid_name2
        buffer.append("properties_name").append(separate);
        //成本价，单位元，保留2位小数
        buffer.append("cost_price").append(separate);
        //级别价，单位元，保留2位小数
        buffer.append("level_price").append(separate);
        //sku的销售属性组合字符串（颜色，大小，等等，可通过类目API获取某类目下的销售属性）,格式是p1:v1;p2:v2
        buffer.append("properties").append(separate);
        //卖点(副标题)
        buffer.append("sell_point").append(separate);
        //货源库存
        buffer.append("source_stock").append(separate);
        //sku图片列表
        buffer.append("sku_imgs").append(separate);
        //SKU的市场价，保留2位小数，单位元
        buffer.append("market_price").append(separate);
        //SKU的重量，单位kg, 最多支持3位小数。
        buffer.append("weight").append(separate);
        //对SKU的操作是否成功
        buffer.append("is_success");
        return buffer.toString();
    }

    @Override
    protected <Params> QianmiRequest<ItemSkusGetResponse> setRequest(Params params) {
        PosItemSkusGetRequestDTO.DTO dto = (PosItemSkusGetRequestDTO.DTO) params;
        ItemSkusGetRequest req = new ItemSkusGetRequest ();
        req.putOtherTextParam("actual_user_id",dto.getShopId());
        req.setFields(this.buildFields());
        req.setNumIids(String.join(",",dto.getGoodsIdList()));
        return req;
    }

    @Override
    protected ItemSkusGetResponse  fetchData(ItemSkusGetResponse  response) {
        log.info(JsonUtils.toJson(response));

        //1, 接口返回数据, rpc持久化数据
        List<Sku> skuList = response.getSkus();
        if (!ObjectUtils.isEmpty(skuList)){
            List<PCMerchGoodsPosSkuLogDTO.ETO> etoList = skuList.parallelStream().map(e ->{
                PCMerchGoodsPosSkuLogDTO.ETO eto = new PCMerchGoodsPosSkuLogDTO.ETO();
                BeanCopyUtils.copyProperties(e,eto);
                eto.setCostPrice(ObjectUtils.isEmpty(e.getCostPrice())? BigDecimal.ZERO:new BigDecimal(e.getCostPrice()));
                eto.setMarketPrice(ObjectUtils.isEmpty(e.getMarketPrice())? BigDecimal.ZERO:new BigDecimal(e.getMarketPrice()));
                eto.setPrice(ObjectUtils.isEmpty(e.getPrice())? BigDecimal.ZERO:new BigDecimal(e.getPrice()));
                eto.setWeight(ObjectUtils.isEmpty(e.getWeight())? BigDecimal.ZERO:new BigDecimal(e.getWeight()));
                if (!ObjectUtils.isEmpty(e.getSkuImgs())){
                    eto.setSkuImgs(JsonUtils.toJson(e.getSkuImgs()));
                }
                return eto;
            }).collect(Collectors.toList());
            posSkuLogRpc.addGoodsPosSkuLog(etoList);
        }

        //2, 并告知pos消息消费结果



        return response;

    }


}
