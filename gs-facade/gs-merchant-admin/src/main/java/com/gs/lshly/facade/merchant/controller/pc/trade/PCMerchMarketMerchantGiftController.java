package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantDiscountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGiftQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGiftVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGiftRpc;
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
@RequestMapping("/merchadmin/marketMerchantGift")
@Api(tags = "商家满赠促销管理")
public class PCMerchMarketMerchantGiftController {

    @DubboReference
    private IPCMerchMarketMerchantGiftRpc pcMerchMarketMerchantGiftRpc;

    @ApiOperation("商家满赠促销列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketMerchantGiftVO.ViewVO>> list(PCMerchMarketMerchantGiftQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketMerchantGiftRpc.pageData(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketMerchantGiftVO.View> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMarketMerchantGiftRpc.detailMarketMerchantGift(new PCMerchMarketMerchantGiftDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家满赠促销")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketMerchantGiftDTO.AddDTO dto) {
            pcMerchMarketMerchantGiftRpc.addMarketMerchantGift(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketMerchantGiftDTO.IdDTO dto = new PCMerchMarketMerchantGiftDTO.IdDTO(id);
        pcMerchMarketMerchantGiftRpc.deleteMarketMerchantGift(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id,@Valid @RequestBody PCMerchMarketMerchantGiftDTO.AddDTO eto) {
        eto.setId(id);
        pcMerchMarketMerchantGiftRpc.editMarketMerchantGift(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("提交审核按钮")
    @GetMapping(value = "/commit")
    public ResponseData<Void> commit(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        pcMerchMarketMerchantGiftRpc.commitMarketMerchantGift(eto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }
    @ApiOperation("取消按钮")
    @GetMapping(value = "/cancel")
    public ResponseData<Void> cancel(PCMerchMarketMerchantGiftDTO.IdDTO eto) {
        pcMerchMarketMerchantGiftRpc.cancelMarketMerchantGift(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }

}
