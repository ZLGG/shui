package com.gs.lshly.middleware.vcode.kaptcha;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.middleware.vcode.vo.CaptchaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/oauth/captcha")
public class CaptchaController {

    @Autowired
    CaptchaService captchaService;

    @ResponseBody
    @GetMapping("")
    public ResponseData<CaptchaVO> getCaptcha() throws IOException {
        return ResponseData.data(captchaService.generateCaptcha());
    }

}