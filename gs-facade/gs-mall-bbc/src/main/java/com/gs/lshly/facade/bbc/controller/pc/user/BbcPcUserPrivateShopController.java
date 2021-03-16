package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserPrivateUserRpc;
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
* @author xxfc
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/privateShop")
@Api(tags = "2C PC私域店铺管理")
public class BbcPcUserPrivateShopController {

    @DubboReference
    private IPCBbbUserPrivateUserRpc bbbUserPrivateUserRpc;
    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @ApiOperation("我绑定的私域店铺")
    @GetMapping("")
    public ResponseData<PageData<PCBbbUserPrivateUserVO.ListVO>> list(PCBbbUserPrivateUserQTO.QTO qto) {
        return ResponseData.data(bbbUserPrivateUserRpc.pageData(qto));
    }

//    @ApiOperation("私域会员等级测试")
//    @GetMapping("/test")
//    public ResponseData<BbbUserVO.PrivateUserInfoVO> list2(PCBbbUserPrivateUserQTO.QTO qto) {
//
//        return ResponseData.data( bbbUserRpc.oneShopPrivateUserInfo("1326412542981033986","1341934392737529858"));
//    }
}
