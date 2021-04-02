package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGiftDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantGroupbuyDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantGroupbuyQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantGroupbuyVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantGroupbuyRpc;
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
* @since 2020-12-10
*/
@RestController
@RequestMapping("/merchadmin/marketMerchantGroupbuy")
@Api(tags = "商家团购促销管理")
public class PCMerchMarketMerchantGroupbuyController {

    @DubboReference
    private IPCMerchMarketMerchantGroupbuyRpc pcMerchMarketMerchantGroupbuyRpc;

    @ApiOperation("商家团购促销列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketMerchantGroupbuyVO.ViewVO>> list(PCMerchMarketMerchantGroupbuyQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketMerchantGroupbuyRpc.pageData(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketMerchantGroupbuyVO.View> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMarketMerchantGroupbuyRpc.detailMarketMerchantGroupbuy(new PCMerchMarketMerchantGroupbuyDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家团购促销")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketMerchantGroupbuyDTO.AddDTO dto) {
            pcMerchMarketMerchantGroupbuyRpc.addMarketMerchantGroupbuy(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketMerchantGroupbuyDTO.IdDTO dto = new PCMerchMarketMerchantGroupbuyDTO.IdDTO(id);
        pcMerchMarketMerchantGroupbuyRpc.deleteMarketMerchantGroupbuy(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id,@Valid @RequestBody PCMerchMarketMerchantGroupbuyDTO.AddDTO dto) {
        dto.setId(id);
        pcMerchMarketMerchantGroupbuyRpc.editMarketMerchantGroupbuy(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("提交审核按钮")
    @GetMapping(value = "/commit")
    public ResponseData<Void> commit( PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        pcMerchMarketMerchantGroupbuyRpc.commitMarketMerchantGroupbuy(eto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }
    @ApiOperation("取消按钮")
    @GetMapping(value = "/cancel")
    public ResponseData<Void> cancel(PCMerchMarketMerchantGroupbuyDTO.AddDTO eto) {
        pcMerchMarketMerchantGroupbuyRpc.cancelMarketMerchantGroupbuy(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }

}
