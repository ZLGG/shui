package com.gs.lshly.facade.possync.receiver;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.PosStockRequestDTO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.pos.IPosStockRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

/**
 * @Author Starry
 * @Date 11:30 2021/2/23
 */
@RestController
@RequestMapping("/api/ifc/pos/stock")
@Api(tags = "商品POS接口调用(参数签名)")
public class PosStockReceiverController {

    @Value("${pos.pubKey}")
    private String pubKey;

    @DubboReference
    private IPosStockRpc iPosStockRpc;

    @ApiOperation("POS库存推送至平台接口")
    @PostMapping("/stockPush")
    public ResponseData<Void> stockPush(@RequestBody TreeMap<String, Object> params) {
        //1, 数据验签(使用POS系统提供的公钥)
        System.out.println("params: "+JsonUtils.toJson(params));
        boolean valid = chckSign(params);
        System.out.println(valid);
        if (!valid){
            return ResponseData.fail("签名认证失败");
        }
        //2, 持久化
        PosStockRequestDTO.DTO posStockRequestDTO=null;
        try {
            posStockRequestDTO= MapToolsUtil.mapToBean(params, PosStockRequestDTO.DTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.fail(e.getMessage());
        }
        System.out.println(posStockRequestDTO);
        try {

            //持久化 将数据存入临时表中
            iPosStockRpc.addAndFlush(posStockRequestDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseData.fail();
        }

        return ResponseData.success();
    }

    private boolean chckSign(TreeMap<String,Object> params) {
        String sign = params.get("sign").toString();
        params.remove("sign");
        String signStr = RSAUtil.toSignStr(params);
        System.out.println(signStr);
        System.out.println(pubKey);
        System.out.println(sign);
        boolean valid = RSAUtil.verifySign(pubKey, signStr, sign);
        return valid;
    }
}
