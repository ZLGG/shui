package com.gs.lshly.facade.bbb.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.H5BbbTradeInvoiceAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.H5BbbTradeInvoiceAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.H5BbbTradeInvoiceAddressVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.PCBbbTradeInvoiceAddressDTO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IH5BbbTradeInvoiceAddressRpc;
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
@RequestMapping("/bbb/tradeInvoiceAddress")
@Api(tags = "收货地址")
public class BbbH5TradeInvoiceAddressController {

    @DubboReference
    private IH5BbbTradeInvoiceAddressRpc h5BbbTradeInvoiceAddressRpc;

    @ApiOperation("收货地址列表")
    @GetMapping("/list")
    public ResponseData<PageData<H5BbbTradeInvoiceAddressVO.ListVO>> list(H5BbbTradeInvoiceAddressQTO.IdQTO qto) {
        return ResponseData.data(h5BbbTradeInvoiceAddressRpc.pageData(qto));
    }

    @ApiOperation("收货地址详情")
    @GetMapping(value = "/detailTradeInvoiceAddress/{id}")
    public ResponseData<H5BbbTradeInvoiceAddressVO.DetailVO> detailTradeInvoiceAddress(@PathVariable String id) {
        return ResponseData.data(h5BbbTradeInvoiceAddressRpc.detailTradeInvoiceAddress(new H5BbbTradeInvoiceAddressDTO.IdDTO(id)));
    }

    @ApiOperation("新增收货地址")
    @PostMapping("/addTradeInvoiceAddress")
    public ResponseData<Void> addTradeInvoiceAddress(@Valid @RequestBody H5BbbTradeInvoiceAddressDTO.AddETO dto) {
        h5BbbTradeInvoiceAddressRpc.addTradeInvoiceAddress(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除收货地址")
    @DeleteMapping(value = "/deleteTradeInvoiceAddress/{id}")
    public ResponseData<Void> deleteTradeInvoiceAddress(@PathVariable String id) {
        H5BbbTradeInvoiceAddressDTO.IdDTO dto = new H5BbbTradeInvoiceAddressDTO.IdDTO(id);
        h5BbbTradeInvoiceAddressRpc.deleteTradeInvoiceAddress(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改收货地址")
    @PutMapping(value = "/editTradeInvoiceAddress/{id}")
    public ResponseData<Void> editTradeInvoiceAddress(@PathVariable String id, @Valid @RequestBody H5BbbTradeInvoiceAddressDTO.AddETO eto) {
        eto.setId(id);
        h5BbbTradeInvoiceAddressRpc.editTradeInvoiceAddress(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("默认发票地址设置")
    @PutMapping(value = "/isDefault/{id}")
    public ResponseData updateByIsDefault(@PathVariable String id ,@Valid @RequestBody PCBbbTradeInvoiceAddressDTO.IsDefaultDTO eto) {
        eto.setId(id);
        h5BbbTradeInvoiceAddressRpc.updateByIsDefault(eto);
        return ResponseData.success("默认发票地址设置成功");
    }
}
