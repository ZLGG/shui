package com.gs.lshly.rpc.api.bbb.pc.wx;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.wx.BaseMsg;
import com.gs.lshly.common.struct.wx.QrCodeEvent;

public interface IBbbWxUserRpc {

    public static final String scanLoginEndWith = "_login";

    BaseMsg handleQrCodeEvent(QrCodeEvent qrCodeEvent);

    ResponseData<BbbUserVO.LoginVO> wasLogin(long sceneId);

    String createTempTicket(long sceneId);

    BbbUserVO.LoginVO bindPhone(long sceneId, String validCode, String phone);
}
