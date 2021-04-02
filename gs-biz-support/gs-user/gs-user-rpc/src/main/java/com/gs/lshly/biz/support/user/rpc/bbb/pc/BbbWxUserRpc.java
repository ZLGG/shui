package com.gs.lshly.biz.support.user.rpc.bbb.pc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserAuthService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BBcWxUserInfoDTO;
import com.gs.lshly.common.struct.wx.BaseMsg;
import com.gs.lshly.common.struct.wx.QrCodeEvent;
import com.gs.lshly.common.struct.wx.ResultType;
import com.gs.lshly.common.struct.wx.TextMsg;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.bbb.pc.wx.IBbbWxUserRpc;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@DubboService
@Slf4j
public class BbbWxUserRpc implements IBbbWxUserRpc {


    public static final String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
    // 临时二维码
    private final static String QR_SCENE = "QR_SCENE";
    // 创建二维码
    private final static String create_ticket_path = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    // 通过ticket换取二维码
    private final static String showqrcode_path = "https://mp.weixin.qq.com/cgi-bin/showqrcode";

    // 获取用户信息
    private final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token={0}&openid={1}";

    private final static String wxSrvAppid = "wxf80677e392751457";

    private final static String wxSrvAppSecret = "2e5266ba14e2c3341356ed670cd8c2d3";

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IBbbUserAuthService userAuthService;


    public static void main(String[] args) {
        BbbWxUserRpc ctrl = new BbbWxUserRpc();
        String token = ctrl.getToken();
        System.out.println("token:" + token);
        String qrcode = ctrl.createTempTicket(111);
        System.out.println("二维码:" + qrcode);
    }
    /**
     * 创建临时带参数二维码
     * @expireSeconds
     * @param sceneId 场景Id
     * @return
     */
    @Override
    public String createTempTicket(long sceneId) {

        Map<String, Long> intMap = new HashMap<String, Long>();
        intMap.put("scene_id", sceneId);
        Map<String, Map<String, Long>> mapMap = new HashMap<String, Map<String, Long>>();
        mapMap.put("scene", intMap);

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        //该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
        paramsMap.put("expire_seconds", "2592000");
        paramsMap.put("action_name", QR_SCENE);
        paramsMap.put("action_info", mapMap);
        String data = new Gson().toJson(paramsMap);
        String result = HttpsUtil.doPost(create_ticket_path + "?access_token="+getToken(), data);
        JSONObject json = JSON.parseObject(result);

        String ticket = json.getString("ticket");

        if (ticket == null) {
            ResultType tokenResult = ResultType.get(json.getString("errcode"));
            throw new BusinessException("微信ticket获取失败："+tokenResult.getDescription()+"："+result);
        }

        return showqrcode_path + "?ticket=" + ticket;
    }

    public String getToken() {
        String url = MessageFormat.format(token_url, wxSrvAppid, wxSrvAppSecret);
        log.info(url);
        String result = HttpsUtil.doGet(url, "UTF-8");
        JSONObject json = JSON.parseObject(result);
        if (json != null) {
            String accessToken = json.getString("access_token");
            if (accessToken == null) {
                ResultType tokenResult = ResultType.get(json.getString("errcode"));
                throw new BusinessException("微信token获取失败："+tokenResult.getDescription()+"："+result);
            }
            return accessToken;
        } else {
            throw new BusinessException("微信token获取失败："+result);
        }
    }

    @Override
    public BaseMsg handleQrCodeEvent(QrCodeEvent qrCodeEvent) {
        String sceneId = qrCodeEvent.getEventKey().replace("qrscene_", "");
        //获取场景对应的用户信息
        log.info("获取场景对应的用户信息，场景id：" + sceneId);
        String url = MessageFormat.format(user_info_url, getToken(), qrCodeEvent.getFromUserName());
        String result = HttpsUtil.doGet(url);
        JSONObject json = JSON.parseObject(result);
        log.info("扫码后获取到的用户信息："+json.toJSONString());
        BBcWxUserInfoDTO dto = new BBcWxUserInfoDTO();
        dto.setOpenId(json.getString("openid"));
        dto.setUnionId(json.getString("unionid"));
        dto.setAvatarUrl(json.getString("headimgurl"));
        dto.setCountry(json.getString("country"));
        dto.setGender(json.getString("sex"));
        dto.setCity(json.getString("city"));
        dto.setNickName(json.getString("nickname"));
        dto.setProvince(json.getString("province"));
        dto.setAppId(wxSrvAppid);
        userAuthService.loadUserBySceneId(sceneId, dto);
        return new TextMsg("欢迎登录");
    }

    @Override
    public ResponseData<BbbUserVO.LoginVO> wasLogin(long sceneId) {
        return userAuthService.loadUserBySceneId(sceneId);
    }

    @Override

    public BbbUserVO.LoginVO bindPhone(long sceneId, String validCode, String phone) {
        return userAuthService.bindQRPhone(sceneId, validCode, phone);
    }
}
