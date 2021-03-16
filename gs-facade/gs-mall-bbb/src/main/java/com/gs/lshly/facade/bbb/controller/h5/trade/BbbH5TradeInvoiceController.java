package com.gs.lshly.facade.bbb.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IH5BbbTradeInvoiceRpc;
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
* @since 2021-01-15
*/
@RestController
@RequestMapping("/bbb/userCenter/tradeInvoice")
@Api(tags = "我的发票管理")
public class BbbH5TradeInvoiceController {

    @DubboReference
    private IH5BbbTradeInvoiceRpc h5BbbTradeInvoiceRpc;

    @ApiOperation("我的发票列表")
    @GetMapping("/list")
    public ResponseData<PageData<H5BbbTradeInvoiceVO.ListVO>> list(H5BbbTradeInvoiceQTO.IdQTO qto) {
        return ResponseData.data(h5BbbTradeInvoiceRpc.pageData(qto));
    }

    @ApiOperation("我的发票详情")
    @GetMapping(value = "/detailTradeInvoice/{id}")
    public ResponseData<H5BbbTradeInvoiceVO.DetailVO> detailTradeInvoice(@PathVariable String id) {
        return ResponseData.data(h5BbbTradeInvoiceRpc.detailTradeInvoice(new H5BbbTradeInvoiceDTO.IdDTO(id)));
    }

    @ApiOperation("新增我的发票")
    @PostMapping("/addTradeInvoice")
    public ResponseData<Void> addTradeInvoice(@Valid @RequestBody H5BbbTradeInvoiceDTO.AddETO dto) {
            h5BbbTradeInvoiceRpc.addTradeInvoice(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除我的发票")
    @DeleteMapping(value = "/deleteTradeInvoice/{id}")
    public ResponseData<Void> deleteTradeInvoice(@PathVariable String id) {
        H5BbbTradeInvoiceDTO.IdDTO dto = new H5BbbTradeInvoiceDTO.IdDTO(id);
        h5BbbTradeInvoiceRpc.deleteTradeInvoice(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改我的发票")
    @PutMapping(value = "/editTradeInvoice/{id}")
    public ResponseData<Void> editTradeInvoice(@PathVariable String id, @Valid @RequestBody H5BbbTradeInvoiceDTO.EditETO eto) {
        eto.setId(id);
        h5BbbTradeInvoiceRpc.editTradeInvoice(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("我的订单申请开票管理")
    @GetMapping(value = "/applyInvoiceList")
    public ResponseData<H5BbbTradeInvoiceVO.ApplyInvoiceVO> applyInvoiceList(H5BbbTradeInvoiceQTO.QTO qto) {
        return ResponseData.data(h5BbbTradeInvoiceRpc.applyInvoiceList(qto));
    }


    @ApiOperation("提交申请")
    @PutMapping(value = "/updateByIsDefaultList")
    public ResponseData<Void> updateByIsDefaultList(@Valid @RequestBody H5BbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO dto) {
        h5BbbTradeInvoiceRpc.updateByIsDefaultList(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("根据订单编号查发票信息")
    @GetMapping(value = "/detailByTradeId")
    public ResponseData<H5BbbTradeInvoiceVO.DetailVO> detailByTradeId(H5BbbTradeInvoiceQTO.TradeIdQTO qto) {
        return ResponseData.data(h5BbbTradeInvoiceRpc.detailByTradeId(qto));
    }
}
