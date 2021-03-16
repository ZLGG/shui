package com.gs.lshly.facade.bbc.controller.pc.user;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserCardRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-27
*/
@RestController
@RequestMapping("/bbc/pc/userCenter")
@Api(tags = "2C PC会员中心管理")
public class BbcPcUserCenterController {

    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @DubboReference
    private IBbbUserCardRpc bbbUserCardRpc;

    @ApiOperation("我的会员信息")
    @GetMapping("/getUserInfo")
    public ResponseData<BbbUserVO.DetailVO> getUserInfo(BbbUserQTO.QTO qto) {
        return ResponseData.data(bbbUserRpc.getUserInfo(qto));
    }

    @ApiOperation("我的优惠卷")
    @GetMapping("/userCardList")
    public  ResponseData<PageData<PCBbbUserCardVO.ListVO>> userCardList(PCBbbUserCardQTO.QTO qto) {
        return ResponseData.data(bbbUserCardRpc.pageData(qto));
    }


    @ApiOperation("获取用户类型")
    @PostMapping("/userType")
    public ResponseData<BbbUserVO.UserTypeVO> userType() {
        return ResponseData.data(bbbUserRpc.userType(new BaseDTO()));
    }


    @ApiOperation("我的积分(可用 + 将过期)")
    @GetMapping("/integral")
    public ResponseData<BbbUserVO.UserIntegralVO> integral(BaseDTO dto) {
        return ResponseData.data(bbbUserRpc.integral(dto));
    }

    @ApiOperation("我的积分记录")
    @GetMapping("/integralLog")
    public ResponseData<List<BbbUserVO.UserIntegralRecordVO>> integralLog(BbbUserQTO.IntegralLogQTO qto) {
        return ResponseData.data(bbbUserRpc.integralLog(qto));
    }

}
