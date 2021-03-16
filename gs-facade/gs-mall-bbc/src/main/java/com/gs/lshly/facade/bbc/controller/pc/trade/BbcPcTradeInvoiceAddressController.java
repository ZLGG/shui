package com.gs.lshly.facade.bbc.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceAddressVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeInvoiceAddressRpc;
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
* @since 2020-12-24
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/tradeInvoiceAddress")
@Api(tags = "2C PC发票寄送地址")
public class BbcPcTradeInvoiceAddressController {

    @DubboReference
    private IPCBbbTradeInvoiceAddressRpc pcBbbTradeInvoiceAddressRpc;

    @ApiOperation("寄送地址列表")
    @GetMapping("/list")
    public ResponseData<PageData<PCBbbTradeInvoiceAddressVO.ListVO>> list(PCBbbTradeInvoiceAddressQTO.QTO qto) {
        return ResponseData.data(pcBbbTradeInvoiceAddressRpc.pageData(qto));
    }

    @ApiOperation("地址信息详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCBbbTradeInvoiceAddressVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcBbbTradeInvoiceAddressRpc.detailTradeInvoiceAddress(new PCBbbTradeInvoiceAddressDTO.IdDTO(id)));
    }

    @ApiOperation("新增寄送地址信息")
    @PostMapping("/save")
    public ResponseData<Void> add(@Valid @RequestBody PCBbbTradeInvoiceAddressDTO.ETO dto) {
            pcBbbTradeInvoiceAddressRpc.addTradeInvoiceAddress(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除寄送地址信息")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCBbbTradeInvoiceAddressDTO.IdDTO dto = new PCBbbTradeInvoiceAddressDTO.IdDTO(id);
        pcBbbTradeInvoiceAddressRpc.deleteTradeInvoiceAddress(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改寄送地址信息")
    @PutMapping(value = "edit/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCBbbTradeInvoiceAddressDTO.ETO eto) {
        eto.setId(id);
        pcBbbTradeInvoiceAddressRpc.editTradeInvoiceAddress(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("默认发票地址设置")
    @PutMapping(value = "/isDefault/{id}")
    public ResponseData updateByIsDefault(@PathVariable String id ,@Valid @RequestBody PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto) {
        eto.setId(id);
        pcBbbTradeInvoiceAddressRpc.updateByIsDefault(eto);
        return ResponseData.success("默认发票地址设置成功");
    }

}
