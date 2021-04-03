package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserCardRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/bbb/userCenter")
@Api(tags = "会员中心管理",description = " ")
public class BbbUserCenterController {

    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @DubboReference
    private IBbbUserCardRpc bbbUserCardRpc;

    @ApiOperation(value = "我的会员信息-v1.1.0",response = BbbUserVO.DetailVO.class)
    @GetMapping("/getUserInfo")
    public ResponseData<BbbUserVO.DetailVO> getUserInfo(BbbUserQTO.QTO qto) {
        return ResponseData.data(bbbUserRpc.getUserInfo(qto));
    }

    @ApiOperation("我的优惠卷")
    @GetMapping("/userCardList")
    public  ResponseData<PageData<PCBbbUserCardVO.ListVO>> userCardList(PCBbbUserCardQTO.QTO qto) {
        return ResponseData.data(bbbUserCardRpc.pageData(qto));
    }


    @ApiOperation("获取用户类型-v1.1.0")
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
    public ResponseData<PageData<BbbUserVO.UserIntegralRecordVO>> integralLog(BbbUserQTO.IntegralLogQTO qto) {
        return ResponseData.data(bbbUserRpc.integralLog(qto));
    }
    @ApiOperation("签到送积分")
    @GetMapping("/signInIntegralLog")
    public ResponseData<Void> signInIntegralLog(BaseDTO dto) {
        bbbUserRpc.signInIntegralLog(dto);
        return ResponseData.success(MsgConst.SIGNIN_SUCCESS);
    }
    @ApiOperation("签到状态")
    @GetMapping("/signInIntegralLogState")
    public ResponseData<BbbUserVO.UserIntegralStatusVO> signInIntegralLogState(BaseDTO dto) {

        return ResponseData.data(bbbUserRpc.signInIntegralLogState(dto));
    }



}
