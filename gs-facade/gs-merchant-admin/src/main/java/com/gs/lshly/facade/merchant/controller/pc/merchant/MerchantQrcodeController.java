package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.struct.common.dto.CommonQrcodeDTO;
import com.gs.lshly.common.utils.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lxus
 * @since 2020-10-26
 */
@RestController
@RequestMapping("/oauth/merchant/qrcode")
@Api(tags = "商家端生成店铺二维码")
public class MerchantQrcodeController {

    @ApiOperation("二维码")
    @GetMapping("")
    public void allocateCode(HttpServletResponse response, CommonQrcodeDTO.ETO dto) throws Exception {
        QRCodeUtil.createCodeToOutputStream(dto.getContent(),dto.getWidth(),dto.getHeight(),response.getOutputStream());
    }


}
