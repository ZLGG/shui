package com.gs.lshly.facade.possync.receiver;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.pos.dto.PosMemberRequestDTO;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.pos.IPosMemberRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeMap;

/**
 * @Author Starry
 * @Date 10:54 2021/2/23
 */
@RestController
@RequestMapping("/api/ifc/pos/user")
@Api(tags = "会员POS接口调用(参数签名)")
public class PosMemberReceiverController {

    @Value("${pos.pubKey}")
    private String pubKey;

    @DubboReference
    private IPosMemberRpc posMemberRpc;


    @ApiOperation("POS会员推送至平台接口")
    @PostMapping("/memberPush")
    public ResponseData<Void> memberPush(@RequestBody TreeMap<String, Object> params) {
        //1, 数据验签(使用POS系统提供的公钥)
        boolean valid = chckSign(params);
        System.out.println("valid==="+valid);
        if (!valid){
            return ResponseData.fail("签名认证失败");
        }
        //2, 持久化
        PosMemberRequestDTO.DTO posMemberRequestDTO=null;
        try {
            System.out.println("开始----------");
            System.out.println("params==="+params);
            posMemberRequestDTO= MapToolsUtil.mapToBean(params, PosMemberRequestDTO.DTO.class);
        } catch (Exception e) {
            System.out.println("e1==="+e);
            e.printStackTrace();
        }
        System.out.println("posMemberRequestDTO======"+posMemberRequestDTO);
        try {

            //持久化 将数据存入临时表中
            posMemberRpc.pushPosMember(posMemberRequestDTO);

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
        System.out.println("params==="+signStr);
        boolean valid = RSAUtil.verifySign(pubKey, signStr, sign);
        return valid;
    }
}
