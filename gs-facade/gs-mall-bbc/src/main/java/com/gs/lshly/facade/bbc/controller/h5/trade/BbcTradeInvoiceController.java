package com.gs.lshly.facade.bbc.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeInvoiceDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeInvoiceQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeInvoiceVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeInvoiceRpc;
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
@RequestMapping("/bbc/userCenter/tradeInvoice")
@Api(tags = "我的发票管理")
public class BbcTradeInvoiceController {

    @DubboReference
    private IBbcTradeInvoiceRpc bbcTradeInvoiceRpc;

    @ApiOperation("发票管理列表")
    @GetMapping("/list")
    public ResponseData<PageData<BbcTradeInvoiceVO.BbcListVO>> list(BbcTradeInvoiceQTO.Ids qto) {
        return ResponseData.data(bbcTradeInvoiceRpc.pageData(qto));
    }

    @ApiOperation("详情发票信息")
    @GetMapping(value = "/detailTradeInvoice/{id}")
    public ResponseData<BbcTradeInvoiceVO.DetailVO> detailTradeInvoice(@PathVariable String id) {
        return ResponseData.data(bbcTradeInvoiceRpc.detailTradeInvoice(new BbcTradeInvoiceDTO.IdDTO(id)));
    }

    @ApiOperation("创建发票信息")
    @PostMapping("/addTradeInvoice")
    public ResponseData<Void> addTradeInvoice(@Valid @RequestBody BbcTradeInvoiceDTO.AddETO dto) {
            bbcTradeInvoiceRpc.addTradeInvoice(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除发票信息")
    @DeleteMapping(value = "/deleteTradeInvoice/{id}")
    public ResponseData<Void> deleteTradeInvoice(@PathVariable String id) {
        BbcTradeInvoiceDTO.IdDTO dto = new BbcTradeInvoiceDTO.IdDTO(id);
        bbcTradeInvoiceRpc.deleteTradeInvoice(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改发票信息")
    @PutMapping(value = "/editTradeInvoice/{id}")
    public ResponseData<Void> editTradeInvoice(@PathVariable String id, @Valid @RequestBody BbcTradeInvoiceDTO.EditETO eto) {
        eto.setId(id);
        bbcTradeInvoiceRpc.editTradeInvoice(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
