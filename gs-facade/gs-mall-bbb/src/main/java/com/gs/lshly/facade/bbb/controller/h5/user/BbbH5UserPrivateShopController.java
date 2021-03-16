package com.gs.lshly.facade.bbb.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserPrivateUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserLogVO;
import com.gs.lshly.rpc.api.bbb.h5.user.IBbbH5UserPrivateUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbb/h5/userCenter/privateShop")
@Api(tags = "H5私域店铺管理",description = " ")
public class BbbH5UserPrivateShopController {

    @DubboReference
    private IBbbH5UserPrivateUserRpc bbbH5UserPrivateUserRpc;

    @ApiOperation("我绑定的私域店铺")
    @GetMapping("")
    public ResponseData<PageData<BbbH5UserPrivateUserVO.ListVO>> list(BbbH5UserPrivateUserQTO.QTO qto) {
        return ResponseData.data(bbbH5UserPrivateUserRpc.pageData(qto));
    }

    @ApiOperation("批量删除")
    @PostMapping("deleteBatch")
    public ResponseData<Void> list(@RequestBody BbbH5UserPrivateUserVO.IdListDTO dto) {
        bbbH5UserPrivateUserRpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("加入私域店铺记录")
    @PostMapping("pageLogData")
    public ResponseData<PageData<PCBbbUserPrivateUserLogVO.ListVO>> pageLogData(PCBbbUserPrivateUserQTO.QTO qto) {
        return ResponseData.data(bbbH5UserPrivateUserRpc.pageLogData(qto));
    }

}
