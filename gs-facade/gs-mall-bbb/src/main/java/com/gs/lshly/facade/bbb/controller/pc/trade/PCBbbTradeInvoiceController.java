package com.gs.lshly.facade.bbb.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbTradeInvoiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2020-12-24
*/
@RestController
@RequestMapping("/bbb/pc/userCenter/tradeInvoice")
@Api(tags = "发票信息管理")
public class PCBbbTradeInvoiceController {

    @DubboReference
    private IPCBbbTradeInvoiceRpc pcBbbTradeInvoiceRpc;

    @ApiOperation("发票模版信息列表")
    @GetMapping("/list")
    public ResponseData<PageData<PCBbbTradeInvoiceVO.ListVO>> list(PCBbbTradeInvoiceQTO.QTO qto) {
        return ResponseData.data(pcBbbTradeInvoiceRpc.pageData(qto));
    }

    @ApiOperation("发票模版信息详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCBbbTradeInvoiceVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcBbbTradeInvoiceRpc.detailTradeInvoice(new PCBbbTradeInvoiceDTO.IdDTO(id)));
    }

    @ApiOperation("新增模版发票信息")
    @PostMapping("/save")
    public ResponseData<Void> add(@Valid @RequestBody PCBbbTradeInvoiceDTO.ETO dto) {
            pcBbbTradeInvoiceRpc.addTradeInvoice(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除发票模版信息")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCBbbTradeInvoiceDTO.IdDTO dto = new PCBbbTradeInvoiceDTO.IdDTO(id);
        pcBbbTradeInvoiceRpc.deleteTradeInvoice(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改发票模版信息")
    @PutMapping(value = "/edit/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCBbbTradeInvoiceDTO.ETO eto) {
        eto.setId(id);
        pcBbbTradeInvoiceRpc.editTradeInvoice(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("默认发票模版设置")
    @PutMapping(value = "/isDefault/{id}")
    public ResponseData updateByIsDefault(@PathVariable String id ,@Valid @RequestBody PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {
        eto.setId(id);
        pcBbbTradeInvoiceRpc.updateByIsDefault(eto);
        return ResponseData.success("默认发票设置成功");
    }

    @ApiOperation("默认发票地址模版设置")
    @PutMapping(value = "/address/isDefault/{id}")
    public ResponseData updateByAddressIsDefault(@PathVariable String id ,@Valid @RequestBody PCBbbTradeInvoiceDTO.IsDefaultDTO eto) {
        eto.setId(id);
        pcBbbTradeInvoiceRpc.updateByAddressIsDefault(eto);
        return ResponseData.success("默认地址设置成功");
    }

    @ApiOperation("我的订单申请开票管理")
    @GetMapping(value = "/applyInvoiceList")
    public ResponseData<PCBbbTradeInvoiceVO.ApplyInvoiceVO> applyInvoiceList(PCBbbTradeInvoiceDTO.ETO qto) {
        return ResponseData.data(pcBbbTradeInvoiceRpc.applyInvoiceList(qto));
    }

    @ApiOperation("点击申请开票")
    @GetMapping(value = "/clickBilling")
    public ResponseData<PCBbbTradeInvoiceVO.clickBillingVO> clickBilling(PCBbbTradeInvoiceDTO.clickBilingDTO dto) {
        return ResponseData.data(pcBbbTradeInvoiceRpc.clickBilling(dto));
    }

    @ApiOperation("提交申请")
    @PutMapping(value = "/updateByIsDefaultList")
    public ResponseData<Void> updateByIsDefaultList(@Valid @RequestBody PCBbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO eto) {
        pcBbbTradeInvoiceRpc.updateByIsDefaultList(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("选择开票")
    @PostMapping("/choose")
    public ResponseData<PCBbbTradeInvoiceVO.ChooseVO> choose(@Valid @RequestBody PCBbbTradeInvoiceDTO.ETO dto) {
//        pcBbbTradeInvoiceRpc.choose(dto);
        return ResponseData.data(pcBbbTradeInvoiceRpc.choose(dto));
    }

}
