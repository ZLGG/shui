package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantApplyVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantApplyRpc;
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
* @since 2020-10-16
*/
@RestController
@RequestMapping("/platform/merchantApply")
@Api(tags = "商家入驻申请管理",description = " ")
@Module(code = "listApplyResidence", parent = "bussiness", name = "入驻申请列表", index = 4)
public class MerchantApplyController {

    @DubboReference
    private IMerchantApplyRpc merchantApplyRpc;

    @ApiOperation("商家入驻申请列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<MerchantApplyVO.ListVO>> pageData(MerchantApplyQTO.QTO qto) {
        return ResponseData.data(merchantApplyRpc.pageData(qto));
    }

    @ApiOperation("高级搜索")
    @GetMapping("/search")
    @Func(code="view", name="查")
    public ResponseData<PageData<MerchantApplyVO.ListVO>> pageSearch(MerchantApplyQTO.SearchQTO qto) {
        return ResponseData.data(merchantApplyRpc.pageSearch(qto));
    }

    @ApiOperation("商家入驻申请详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<MerchantApplyVO.DetailVO> detail(@PathVariable String id) {
        return ResponseData.data(merchantApplyRpc.detailMerchantApply(new MerchantApplyDTO.IdDTO(id)));
    }

    @ApiOperation("批量删除商家入驻申请")
    @PostMapping(value = "/deleteBatch")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody MerchantApplyDTO.IdListDTO dto) {
        merchantApplyRpc.deleteBatchMerchantApply(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("商家入驻申请开通店铺")
    @PutMapping(value = "/openShop/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> openShop(@PathVariable String id) {
        merchantApplyRpc.openShop(new  MerchantApplyDTO.IdDTO(id));
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("商家入驻申请审核处理")
    @PutMapping(value = "/apply/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> apply(@PathVariable String id, @Valid @RequestBody MerchantApplyDTO.ApplyDTO dto) {
        dto.setId(id);
        merchantApplyRpc.apply(dto);
        return ResponseData.success(MsgConst.APPLY_SUCCESS);
    }

    @ApiOperation("商家入驻申请关联品牌查询")
    @GetMapping(value = "/handBrandQuery/{id}")
    @Func(code="view", name="查")
    public ResponseData<MerchantApplyVO.BrandVO> handBrandQuery(@PathVariable String id) {
        return ResponseData.data(merchantApplyRpc.handBrandQuery(new MerchantApplyDTO.IdDTO(id)));
    }

    @ApiOperation("商家入驻申请关联品牌处理提交")
    @PutMapping(value = "/handBrandSubmit/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> handBrandSubmit(@PathVariable String id,@Valid @RequestBody MerchantApplyDTO.HandBrandDTO dto) {
        dto.setId(id);
        merchantApplyRpc.handBrandSubmit(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
