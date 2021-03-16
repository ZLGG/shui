package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantShopCategoryApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantShopCategoryApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantShopCategoryApplyVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantShopCategoryApplyRpc;
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
@RequestMapping("/platform/merchantShopCategoryApply")
@Api(tags = "店铺商品类目申请管理",description = " ")
public class MerchantShopCategoryApplyController {

    @DubboReference
    private IMerchantShopCategoryApplyRpc merchantShopCategoryApplyRpc;

    @ApiOperation("店铺商品类目申请列表")
    @GetMapping("")
    public ResponseData<PageData<MerchantShopCategoryApplyVO.ListVO>> list(MerchantShopCategoryApplyQTO.QTO qto) {
        return ResponseData.data(merchantShopCategoryApplyRpc.pageData(qto));
    }

    @ApiOperation("店铺商品类目申请详情")
    @GetMapping("/details/{id}")
    public ResponseData<MerchantShopCategoryApplyVO.DetailVO> details(@PathVariable String id) {
        return ResponseData.data(merchantShopCategoryApplyRpc.details(new MerchantShopCategoryApplyDTO.IdDTO(id)));
    }

    @ApiOperation("批量删除店铺商品类目申请")
    @PostMapping(value = "/deleteBatch")
    public ResponseData<Void> deleteBatch(@Valid @RequestBody MerchantShopCategoryApplyDTO.IdListDTO dto) {
        merchantShopCategoryApplyRpc.deleteBatchMerchantShopCategoryApply(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("店铺商品类目申请审核")
    @PutMapping(value = "/apply/{id}")
    public ResponseData<Void> apply(@PathVariable String id, @Valid @RequestBody MerchantShopCategoryApplyDTO.ApplyDTO dto) {
        dto.setId(id);
        merchantShopCategoryApplyRpc.apply(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
