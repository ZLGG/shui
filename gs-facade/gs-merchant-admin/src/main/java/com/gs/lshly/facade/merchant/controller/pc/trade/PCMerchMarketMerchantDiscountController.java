package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantDiscountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantDiscountVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantDiscountRpc;
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
* @author zdf
* @since 2020-12-09
*/
@RestController
@RequestMapping("/merchadmin/marketMerchantDiscount")
@Api(tags = "商家满折促销管理")
@Module(code = "limitDiscounts",parent = "marketing",name = "限时折扣",index = 2)
public class PCMerchMarketMerchantDiscountController {

    @DubboReference
    private IPCMerchMarketMerchantDiscountRpc pcMerchMarketMerchantDiscountRpc;

    @ApiOperation("商家满折促销列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<PCMerchMarketMerchantDiscountVO.ViewVO>> list(PCMerchMarketMerchantDiscountQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketMerchantDiscountRpc.pageData(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<PCMerchMarketMerchantDiscountVO.View> get(PCMerchMarketMerchantDiscountDTO.IdDTO dto) {
        return ResponseData.data(pcMerchMarketMerchantDiscountRpc.detailMarketMerchantDiscount(dto));
    }

    @ApiOperation("新增商家满折促销")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketMerchantDiscountDTO.AddDTO dto) {
            pcMerchMarketMerchantDiscountRpc.addMarketMerchantDiscount(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家满折促销")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete",name = "删除")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketMerchantDiscountDTO.IdDTO dto = new PCMerchMarketMerchantDiscountDTO.IdDTO(id);
        pcMerchMarketMerchantDiscountRpc.deleteMarketMerchantDiscount(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id,@Valid @RequestBody PCMerchMarketMerchantDiscountDTO.AddDTO eto) {
        eto.setId(id);
        pcMerchMarketMerchantDiscountRpc.editMarketMerchantDiscount(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("提交审核按钮")
    @GetMapping(value = "/commit")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> commit( PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        pcMerchMarketMerchantDiscountRpc.commitMarketMerchantDiscount(eto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }
    @ApiOperation("取消按钮")
    @GetMapping(value = "/cancel")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> cancel(PCMerchMarketMerchantDiscountDTO.IdDTO eto) {
        pcMerchMarketMerchantDiscountRpc.cancelMarketMerchantDiscount(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }


}
