package com.gs.lshly.facade.bbb.controller.pc.trade;

import cn.hutool.core.util.StrUtil;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbPcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.QRCodeUtil;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
* <p>
*  前端控制器
* </p>
*
* @author TANGXU
* @since 2020-12-15
*/
@RestController
@RequestMapping("/bbb")
@Api(tags = "交易订单管理")
@Slf4j
public class BbbPcTradeController {

    @DubboReference
    private IBbbPcTradeRpc bbbTradeRpc;



    @ApiOperation("支付(返回支付二维码请求地址)")
    @PostMapping("/pc/userCenter/doPay")
    public ResponseData<Void> doPay(@Valid @RequestBody BbbPcTradePayBuildDTO.ETO dto) {
        ResponseData responseData= bbbTradeRpc.orderPay(dto);
        if(responseData.isSuccess()){
            if(!ObjectUtils.isEmpty(responseData.getData())){
                String qrCodeContent=(String) responseData.getData();
                try {
                    String tmpFileName = StrUtil.uuid();
                    QRCodeUtil.genAndSaveFile(qrCodeContent, 300, 300, tmpFileName);
                    return ResponseData.data(tmpFileName);
                }catch(Exception e){
                    e.printStackTrace();
                    return ResponseData.fail("二维码生成失败");
                }
            }
        }
        return responseData;
    }

    @ApiOperation("支付回调")
    @PostMapping("/pc/doPayNotify")
    public String doPayNotify(BbcTradeResultNotifyVO.notifyVO notifyVO, HttpServletRequest request) {
        log.info("pc回调:"+ JsonUtils.toJson(notifyVO));
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            log.info(name + ":" + request.getParameter(name));
        }
        return bbbTradeRpc.payNotify(notifyVO);
    }

    @ApiOperation("支付二维码")
    @GetMapping("/pc/userCenter/payImg/{fileName}")
    public void payImg(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        try {
            QRCodeUtil.writeToResponse(response, fileName);
        }catch (IOException e){
            log.error(e.getMessage(), e);
            PrintWriter toClient=response.getWriter();
            response.setContentType("text/html;charset=gb2312");
            toClient.write("无法打开图片");
            toClient.close();
        }
    }

    @ApiOperation("支付是否完成")
    @PostMapping("/pc/userCenter/isFinishedPay/{tradeId}")
    public ResponseData<Void> isFinishedPay(@PathVariable("tradeId") String tradeId,String userCard) {
        return ResponseData.data(bbbTradeRpc.isFinishedPay(tradeId,userCard));
    }

}
