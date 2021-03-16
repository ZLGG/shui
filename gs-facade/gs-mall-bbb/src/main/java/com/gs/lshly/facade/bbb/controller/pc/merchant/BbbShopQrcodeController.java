package com.gs.lshly.facade.bbb.controller.pc.merchant;

import com.gs.lshly.common.struct.common.dto.CommonQrcodeDTO;
import com.gs.lshly.common.utils.QRCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/bbb/merchant/qrcode")
@Api(tags = "生成店铺二维码")
public class BbbShopQrcodeController {
    
    @ApiOperation("二维码")
    @GetMapping("")
    public void allocateCode(HttpServletResponse response, CommonQrcodeDTO.ETO dto) throws Exception {
        QRCodeUtil.createCodeToOutputStream(dto.getContent(),dto.getWidth(),dto.getHeight(),response.getOutputStream());
    }


}
