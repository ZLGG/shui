package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCardQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCardVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCardRpc;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtMerchantCardRpc;
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
* @since 2020-12-04
*/
@RestController
@RequestMapping("/merchadmin/marketMerchantCard")
@Api(tags = "商家优惠卷管理")
public class PCMerchMarketMerchantCardController {

    @DubboReference
    private IPCMerchMarketMerchantCardRpc pcMerchMarketMerchantCardRpc;

    @ApiOperation("商家优惠卷列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketMerchantCardVO.ListVO>> list(PCMerchMarketMerchantCardQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketMerchantCardRpc.pageData(qto));
    }

    @ApiOperation("商家优惠卷详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketMerchantCardVO.View> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMarketMerchantCardRpc.viewMarketMerchantCard(new PCMerchMarketMerchantCardDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家优惠卷")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketMerchantCardDTO.ETO dto) {
            pcMerchMarketMerchantCardRpc.addMarketMerchantCard(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家优惠卷")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketMerchantCardDTO.IdDTO dto = new PCMerchMarketMerchantCardDTO.IdDTO(id);
        pcMerchMarketMerchantCardRpc.deleteMarketMerchantCard(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家优惠卷")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMarketMerchantCardDTO.ETO eto) {
        eto.setId(id);
        pcMerchMarketMerchantCardRpc.editMarketMerchantCard(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("取消按钮")
    @PutMapping(value = "/cancel")
    public ResponseData<Void> cancel(@Valid @RequestBody PCMerchMarketMerchantCardDTO.IdDTO eto) {
        pcMerchMarketMerchantCardRpc.cancelMarketMerchantCard(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }
    @ApiOperation("提交审核按钮")
    @GetMapping(value = "/commit")
    public ResponseData<Void> commit(PCMerchMarketMerchantCardDTO.IdDTO eto) {
        pcMerchMarketMerchantCardRpc.commitMarketMerchantCard(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }
}
