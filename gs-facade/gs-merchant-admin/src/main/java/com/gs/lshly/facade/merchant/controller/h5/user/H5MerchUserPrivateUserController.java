package com.gs.lshly.facade.merchant.controller.h5.user;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.stock.vo.H5MerchStockAddressVO;
import com.gs.lshly.common.struct.merchadmin.h5.user.dto.H5MerchUserPrivateUserDTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.qto.H5MerchUserPrivateUserQTO;
import com.gs.lshly.common.struct.merchadmin.h5.user.vo.H5MerchUserPrivateUserVO;
import com.gs.lshly.common.struct.merchadmin.pc.user.dto.PCMerchUserDTO;
import com.gs.lshly.rpc.api.merchadmin.h5.stock.IH5MerchStockAddressRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.user.IH5MerchUserPrivateUserRpc;
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
* @author zst
* @since 2021-01-20
*/
@RestController
@RequestMapping("/merchadmin/H5/user")
@Api(tags = "H5商家私域会员管理")
public class H5MerchUserPrivateUserController {

    @DubboReference
    private IH5MerchUserPrivateUserRpc h5MerchUserPrivateUserRpc;

    @DubboReference
    private IH5MerchStockAddressRpc ih5MerchStockAddressRpc;

    @ApiOperation("商家私域会员列表")
    @GetMapping("")
    public ResponseData<PageData<H5MerchUserPrivateUserVO.ListVO>> list(H5MerchUserPrivateUserQTO.QTO qto) {
        return ResponseData.data(h5MerchUserPrivateUserRpc.pageData(qto));
    }


    @ApiOperation("商家私域会员列表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<H5MerchUserPrivateUserVO.PrivateUserDetailVO> get(@PathVariable String id) {
        return ResponseData.data(h5MerchUserPrivateUserRpc.detailUserPrivateUser(new H5MerchUserPrivateUserDTO.IdDTO(id)));
    }


    @ApiOperation("商家私域会员审核列表")
    @GetMapping("/applyList")
    public ResponseData<PageData<H5MerchUserPrivateUserVO.ApplyListVO>> applyPageList(H5MerchUserPrivateUserQTO.ApplyListQTO qto) {
        return ResponseData.data(h5MerchUserPrivateUserRpc.applyPageList(qto));
    }


    @ApiOperation("商家私域会员审核数据详情")
    @GetMapping(value = "/applyDetail/{id}")
    public ResponseData<H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO> applyDetailUser(@PathVariable String id) {
        PCMerchUserDTO.IdDTO dto = new PCMerchUserDTO.IdDTO(id);
        return ResponseData.data(h5MerchUserPrivateUserRpc.applyDetailUser(dto));
    }


    @ApiOperation("商家私域会员审核")
    @PutMapping(value = "/apply/{id}")
    public ResponseData<Void> apply(@PathVariable String id,@Valid @RequestBody H5MerchUserPrivateUserDTO.ApplyDTO dto) {
        h5MerchUserPrivateUserRpc.apply(dto.setId(id));
        return ResponseData.success(MsgConst.HANDER_SUCCESS);
    }


    @ApiOperation("查看地址信息")
    @GetMapping(value = "/detailStockAddress")
    public ResponseData<H5MerchStockAddressVO.ListVO> detailStockAddress(BaseDTO dto) {
        return ResponseData.data(ih5MerchStockAddressRpc.detailStockAddress(dto));
    }

}
