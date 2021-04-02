package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserCardQTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserCardVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserDTO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserCardRpc;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserRpc;
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
@RequestMapping("/bbb/h5/userCenter")
@Api(tags = "会员中心管理",description = " ")
public class BbbH5UserCenterController {

    @DubboReference
    private IBbbH5UserRpc bbbH5UserRpc;
    @DubboReference
    private IBbbH5UserCardRpc bbbH5UserCardRpc;

    @ApiOperation("我的会员信息")
    @GetMapping("/getUserInfo")
    public ResponseData<BbbH5UserVO.DetailVO> getUserInfo(BbbH5UserQTO.QTO qto) {
        BbbH5UserVO.DetailVO detailVO =  bbbH5UserRpc.getUserInfo(qto);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("我的优惠卷")
    @GetMapping("/shoppingCardList")
    public ResponseData<PageData<BbbH5UserCardVO.ListVO>> pageListShoppingCard(BbbH5UserCardQTO.QTO qto) {
        return ResponseData.data(bbbH5UserCardRpc.pageData(qto));
    }

    @ApiOperation("我的积分(可用 + 将过期)")
    @GetMapping("/integral")
    public ResponseData<BbbH5UserVO.UserIntegralVO> integral(BaseDTO dto) {
        return ResponseData.data(bbbH5UserCardRpc.integral(dto));
    }

    @ApiOperation("我的积分记录")
    @GetMapping("/integralLog")
    public ResponseData<PageData<BbbH5UserVO.UserIntegralRecordVO>> integralLog(BaseQTO qto) {
        return ResponseData.data(bbbH5UserCardRpc.integralLog(qto));
    }
    @ApiOperation("签到送积分")
    @GetMapping("/signInIntegralLog")
    public ResponseData<Void> signInIntegralLog(BaseDTO dto) {
        bbbH5UserCardRpc.signInIntegralLog(dto);
        return ResponseData.success(MsgConst.SIGNIN_SUCCESS);
    }
    @ApiOperation("签到状态")
    @GetMapping("/signInIntegralLogState")
    public ResponseData<BbbH5UserVO.UserIntegralStatusVO> signInIntegralLogState(BaseDTO dto) {

        return ResponseData.data(bbbH5UserCardRpc.signInIntegralLogState(dto));
    }
    @ApiOperation("个人信息编辑")
    @PutMapping("/editorUserInfo")
    public ResponseData<Void> editorUserInfo(@Valid @RequestBody BbbH5UserDTO.UserInfoETO dto) {
        bbbH5UserRpc.editorUserInfo(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }



}
