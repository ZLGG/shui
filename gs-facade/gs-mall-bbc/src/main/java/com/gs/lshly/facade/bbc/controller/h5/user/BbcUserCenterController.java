package com.gs.lshly.facade.bbc.controller.h5.user;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbc.user.dto.BbcUserDTO;
import com.gs.lshly.common.struct.bbc.user.qto.BbcUserQTO;
import com.gs.lshly.common.struct.bbc.user.vo.BbcUserVO;
import com.gs.lshly.rpc.api.bbc.user.IBbcUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/bbc/userCenter")
@Api(tags = "会员中心管理")
public class BbcUserCenterController {

    @DubboReference
    private IBbcUserRpc bbcUserRpc;

    @ApiOperation("我的会员信息")
    @GetMapping("/getUserInfo")
    public ResponseData<BbcUserVO.DetailVO> getUserInfo(BbcUserQTO.QTO qto) {
        BbcUserVO.DetailVO detailVO =  bbcUserRpc.getUserInfo(qto);
        return ResponseData.data(detailVO);
    }

//    @ApiOperation("我的优惠卷[暂时不做]")
//    @GetMapping("/pageListShoppingCard")
//    public ResponseData<PageData<BbcUserVO.ShoppingCardVO>> pageListShoppingCard(BbcUserQTO.QTO qto) {
//        return ResponseData.data(bbcUserRpc.pageListShoppingCard(qto));
//    }

    @ApiOperation("我的积分(可用)")
    @GetMapping("/integral")
    public ResponseData<BbcUserVO.UserIntegralVO> integral(BaseDTO dto) {
        return ResponseData.data(bbcUserRpc.integral(dto));
    }

    @ApiOperation("我的积分记录")
    @GetMapping("/integralLog")
    public ResponseData<List<BbcUserVO.UserIntegralRecordVO>> integralLog(BbcUserDTO.IntegralLogQTO qto) {
        return ResponseData.data(bbcUserRpc.integralLog(qto));
    }




}
