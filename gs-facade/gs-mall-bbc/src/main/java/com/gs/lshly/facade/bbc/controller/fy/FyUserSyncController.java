package com.gs.lshly.facade.bbc.controller.fy;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.common.struct.fy.wallet.dto.FyWalletUserDTO;
import com.gs.lshly.rpc.api.fy.IFyWalletRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author zhaoqiang
 * @Description
 * @date 2021/1/4 下午2:26
 */
@Api(tags = "工会用户管理")
@RestController
@RequestMapping("/fy/user/sync")
public class FyUserSyncController {

    @DubboReference
    private IFyWalletRpc fyWalletRpc;

    @ApiOperation("用户同步")
    @GetMapping("")
    public ResponseData<BbcUserVO.LoginVO> auth(@ApiIgnore HttpServletRequest req, @ApiIgnore HttpServletResponse resp) {
        String nonceStr = req.getParameter("nonceStr");
        String openid = req.getParameter("openid");
        String phone = req.getParameter("phone");
        String accountNo = req.getParameter("accountNo");
        String fuMerchantNum = req.getParameter("fuMerchantNum");
        String sign = req.getParameter("sign");

        SortedMap<Object, Object> parameters = new TreeMap<>();
        parameters.put("nonceStr", nonceStr);
        parameters.put("openid", openid);
        parameters.put("phone", phone);
        parameters.put("accountNo", accountNo);
        parameters.put("fuMerchantNum", fuMerchantNum);


        if (StringUtils.isBlank(openid)) {
            return ResponseData.fail("openid不能为空");
        }
        if (StringUtils.isBlank(accountNo)) {
            return ResponseData.fail("accountNo不能为空");
        }
        if (StringUtils.isBlank(nonceStr)) {
            return ResponseData.fail("nonceStr不能为空");
        }
        if (StringUtils.isBlank(fuMerchantNum)) {
            return ResponseData.fail("fuMerchantNum不能为空");
        }
        if (StringUtils.isBlank(sign)) {
            return ResponseData.fail("sign不能为空");
        }

        sign = StringUtils.replace(sign, " ", "+");

        StringBuffer signStr = new StringBuffer();
        Set es = parameters.entrySet();  //所有参与传参的参数按照accsii排序（升序）
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            Object v = entry.getValue();

            if (StringUtils.isNotBlank(signStr.toString())) {
                signStr.append("&");
            }
            //空值不传递，不参与签名组串
            signStr.append(k + "=" + v);
        }

        FyWalletUserDTO.ETO eto = new FyWalletUserDTO.ETO();
        eto.setAccountNo(accountNo);
        eto.setOpenId(openid);
        eto.setPhone(phone);
        eto.setFuMerchantNum(fuMerchantNum);
        eto.setSign(sign);
        eto.setSignStr(signStr.toString());

        BbcUserVO.LoginVO result = fyWalletRpc.syncUserWallet(eto);
        //增加token返回 add by lxus
        if (result != null) {
            return ResponseData.data(result);
        } else {
            return ResponseData.success("验签失败");
        }
    }
}
