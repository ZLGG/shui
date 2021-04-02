package com.gs.lshly.facade.possync.receiver;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.PosTradeRightRequestDTO;
import com.gs.lshly.common.struct.pos.dto.PosTradeStateRequestDTO;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.pos.IPosTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.TreeMap;

@RestController
@RequestMapping("/api/ifc/pos/order")
@Api(tags = "订单POS接口调用(参数签名)")
public class PosTradeReceiverController {

    @Value("${pos.pubKey}")
    private String pubKey;

    @DubboReference
    private IPosTradeRpc iPosTradeRpc;
    @ApiOperation("POS推送订单履约信息接口")
    @PostMapping("/orderState")
    public ResponseData<Void> orderState(@RequestBody TreeMap<String, Object> params) {
        System.out.println("param: "+params);
        //1, 数据验签(使用POS系统提供的公钥)
        boolean valid = chckSign(params);
        System.out.println(valid);
        if (!valid){
            return ResponseData.fail("签名认证失败");
        }
        //2, 持久化
        PosTradeStateRequestDTO.DTO posTradeStateRequestDTO=null;
        try {
            posTradeStateRequestDTO= MapToolsUtil.mapToBean(params, PosTradeStateRequestDTO.DTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dto: "+posTradeStateRequestDTO);
        try {
            iPosTradeRpc.orderState(posTradeStateRequestDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseData.fail();
        }

        return ResponseData.success();
    }

    @ApiOperation("POS推送订单退货申请处理结果到商城系统接口")
    @PostMapping("/orderRight")
    public ResponseData<Void> orderRight(@RequestBody TreeMap<String, Object> params) {
        System.out.println("param: "+params);
        //1, 数据验签(使用POS系统提供的公钥)
        boolean valid = chckSign(params);
        System.out.println(valid);
        if (!valid){
            return ResponseData.fail("签名认证失败");
        }
        //2, 持久化
        PosTradeRightRequestDTO.DTO posTradeRightRequestDTO=null;
        try {
            posTradeRightRequestDTO= MapToolsUtil.mapToBean(params, PosTradeRightRequestDTO.DTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("dto:  "+posTradeRightRequestDTO);
        try {
            iPosTradeRpc.orderRight(posTradeRightRequestDTO);
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
        System.out.println("signStr:  "+signStr);
        boolean valid = RSAUtil.verifySign(pubKey, signStr, sign);
        return valid;
    }

}
