package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCardDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchMarketMerchantCutDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchMarketMerchantCutQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchMarketMerchantCutVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchMarketMerchantCutRpc;
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
* @since 2020-12-08
*/
@RestController
@RequestMapping("/merchadmin/marketMerchantCut")
@Api(tags = "商家满减促销管理")
public class PCMerchMarketMerchantCutController {

    @DubboReference
    private IPCMerchMarketMerchantCutRpc pcMerchMarketMerchantCutRpc;

    @ApiOperation("商家满减促销列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchMarketMerchantCutVO.ViewVO>> list(PCMerchMarketMerchantCutQTO.QTO qto) {
        return ResponseData.data(pcMerchMarketMerchantCutRpc.pageData(qto));
    }

    @ApiOperation("查看")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchMarketMerchantCutVO.View> get(PCMerchMarketMerchantCutDTO.IdDTO dto) {
        return ResponseData.data(pcMerchMarketMerchantCutRpc.detailMarketMerchantCut(dto));
    }

    @ApiOperation("新增商家满减促销")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMarketMerchantCutDTO.AddDTO dto) {
            pcMerchMarketMerchantCutRpc.addMarketMerchantCut(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家满减促销")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMarketMerchantCutDTO.IdDTO dto = new PCMerchMarketMerchantCutDTO.IdDTO(id);
        pcMerchMarketMerchantCutRpc.deleteMarketMerchantCut(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMarketMerchantCutDTO.AddDTO eto) {
        eto.setCutId(id);
        pcMerchMarketMerchantCutRpc.editMarketMerchantCut(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    @ApiOperation("提交审核按钮")
    @GetMapping(value = "/commit")
    public ResponseData<Void> commit(PCMerchMarketMerchantCutDTO.IdDTO eto) {
        pcMerchMarketMerchantCutRpc.commitMarketMerchantCut(eto);
        return ResponseData.success(MsgConst.APPEAL_COMMENT);
    }
    @ApiOperation("取消按钮")
    @GetMapping(value = "/cancel")
    public ResponseData<Void> cancel(PCMerchMarketMerchantCutDTO.IdDTO eto) {
        pcMerchMarketMerchantCutRpc.cancelMarketMerchantCut(eto);
        return ResponseData.success(MsgConst.CANCEL_SUCCESS);
    }

}
