package com.gs.lshly.facade.bbc.controller.test;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.rpc.api.test.IMQTestRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* <p>
*  前端控制器
* </p>
*
* @author lxus
* @since 2020-10-16
*/
@RestController
@RequestMapping("/test/mq")
@Api(tags = "消息队列测试")
public class RocketMQTestController {

    @DubboReference
    private IMQTestRpc imqTestRPC;

    @ApiOperation("发送消息,并返回消息id")
    @GetMapping("/sendMsg")
    public ResponseData<Void> sendMsg() {
        String orderId = imqTestRPC.sendOrder();
        return ResponseData.success(orderId);
    }

    @ApiOperation("根据消息id,查询消息内容")
    @GetMapping("/queryMsg")
    public ResponseData<Void> queryMsg(String orderId) {
        String msgToJson = imqTestRPC.queryOrderById(orderId);
        return ResponseData.data(msgToJson);
    }

}
