package com.gs.lshly.facade.possync.receiver;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosTemporaryDTO;
import com.gs.lshly.common.struct.pos.dto.PosCommodityRequestDTO;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsPosTemporaryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * @Author Starry
 * @Date 18:10 2021/2/22
 */
@RestController
@RequestMapping("/api/ifc/pos/sku")
@Api(tags = "商品POS接口调用(参数签名)")
public class PosCommodityReceiverController {

    @Value("${pos.pubKey}")
    private String pubKey;

    @DubboReference
    private IPCMerchGoodsPosTemporaryRpc posTemporaryRpc;


    @ApiOperation("POS商品推送至平台接口")
    @PostMapping("/commodityPush")
    public ResponseData<Void> commodityPush(@RequestBody TreeMap<String, Object> params) {
        //1, 数据验签(使用POS系统提供的公钥)
        boolean valid = chckSign(params);
        System.out.println("valid===="+valid);
        if (!valid){
            return ResponseData.fail("签名认证失败");
        }
        //2, 持久化
        PosCommodityRequestDTO.DTO posCommodityRequestDTO=null;
        try {
            System.out.println("开始-----------");
            System.out.println("params==="+params);
            posCommodityRequestDTO= MapToolsUtil.mapToBean(params, PosCommodityRequestDTO.DTO.class);
        } catch (Exception e) {
            System.out.println("e1==="+e);
            e.printStackTrace();
            e.getMessage();
        }
        System.out.println("posCommodityRequestDTO===="+posCommodityRequestDTO);
        try {

            //持久化 将数据存入临时表中
            PCMerchGoodsPosTemporaryDTO.ETO eto = new PCMerchGoodsPosTemporaryDTO.ETO();
            BeanUtils.copyProperties(posCommodityRequestDTO,eto);
            eto.setPosSku69Code(posCommodityRequestDTO.getPosSKU69Code());
            eto.setTimestamp(posCommodityRequestDTO.getTimestamp().toString());
            eto.setPrice(new BigDecimal(posCommodityRequestDTO.getPrice()));
            System.out.println("dto:"+eto);
            posTemporaryRpc.addGoodsPosTemporary(eto);

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
        System.out.println("params===="+signStr);
        boolean valid = RSAUtil.verifySign(pubKey, signStr, sign);
        return valid;
    }
}
