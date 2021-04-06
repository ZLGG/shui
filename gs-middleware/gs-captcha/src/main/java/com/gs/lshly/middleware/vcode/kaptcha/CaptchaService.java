package com.gs.lshly.middleware.vcode.kaptcha;

import cn.hutool.core.util.StrUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.vcode.vo.CaptchaVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
public class CaptchaService {

    @Autowired
    private DefaultKaptcha producer;

    @Autowired
    private RedisUtil redisUtils;

    private final String CAPTCHA_KEY = "captcha:verification:";

    public CaptchaVO generateCaptcha() {

        //生成一个随机标识符,相当于是验证码的key
        String captchaKey = UUID.randomUUID().toString();
        String captcha = producer.createText();

        //缓存验证码,10分钟过期
        redisUtils.set(CAPTCHA_KEY.concat(captchaKey), captcha, 60 * 10);

        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(captcha);

        outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, "jpg", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray()).replace("\n", "").replace("\r", "");

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setBase64Img(base64Img);
        captchaVO.setCaptchaKey(captchaKey);

        return captchaVO;
    }

    public boolean match(String vcId, String inputValidCode) {
        if (StrUtil.isBlank(vcId) || StrUtil.isBlank(inputValidCode)) {
            return false;
        }
        String cacheKey = CAPTCHA_KEY.concat(vcId);
        Object object = redisUtils.get(cacheKey);
        String validCode = object != null ? object + "" : "";
        if (!StringUtils.equals(validCode, inputValidCode)) {
            return false;
        }
        redisUtils.del(cacheKey);
        return true;
    }

}