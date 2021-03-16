package com.gs.lshly.facade.bbb.controller.pc.user;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.BbbUserFavoritesShopDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.BbbUserFavoritesShopQTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserFavoritesShopVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.BbbUserVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserFavoritesShopRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;
import com.gs.lshly.rpc.api.bbb.pc.user.IPCBbbUserPrivateUserRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbb/userCenter/privateShop")
@Api(tags = "私域店铺管理",description = " ")
public class PCBbbUserPrivateShopController {

    @DubboReference
    private IPCBbbUserPrivateUserRpc bbbUserPrivateUserRpc;
    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @ApiOperation("我绑定的私域店铺")
    @GetMapping("")
    public ResponseData<PageData<PCBbbUserPrivateUserVO.ListVO>> list(PCBbbUserPrivateUserQTO.QTO qto) {
        return ResponseData.data(bbbUserPrivateUserRpc.pageData(qto));
    }

    @ApiOperation("批量删除")
    @PostMapping("deleteBatch")
    public ResponseData<Void> list(@RequestBody PCBbbUserPrivateUserDTO.IdListDTO dto) {
        bbbUserPrivateUserRpc.deleteBatch(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("加入私域店铺记录")
    @PostMapping("pageLogData")
    public ResponseData<PageData<PCBbbUserPrivateUserLogVO.ListVO>> pageLogData(PCBbbUserPrivateUserQTO.QTO qto) {
        return ResponseData.data(bbbUserPrivateUserRpc.pageLogData(qto));
    }

}
