package com.gs.lshly.facade.merchant.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeCancelDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeCancelQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeCancelVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeCancelRpc;
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
* @author oy
* @since 2020-11-20
*/
@RestController
@RequestMapping("/merchadmin/h5/tradeCancel")
@Api(tags = "H5交易订单取消表管理")
public class H5MerchTradeCancelController {

    @DubboReference
    private IH5MerchTradeCancelRpc h5MerchTradeCancelRpc;

    @ApiOperation("交易订单取消表列表")
    @GetMapping("")
    public ResponseData<PageData<H5MerchTradeCancelVO.ListVO>> list(H5MerchTradeCancelQTO.QTO qto) {
        return ResponseData.data(h5MerchTradeCancelRpc.pageData(qto));
    }

    @ApiOperation("交易订单取消表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<H5MerchTradeCancelVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(h5MerchTradeCancelRpc.detailTradeCancel(new H5MerchTradeCancelDTO.IdDTO(id)));
    }

    @ApiOperation("新增交易订单取消表")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody H5MerchTradeCancelDTO.ETO dto) {
            h5MerchTradeCancelRpc.addTradeCancel(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除交易订单取消表")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        H5MerchTradeCancelDTO.IdDTO dto = new H5MerchTradeCancelDTO.IdDTO(id);
        h5MerchTradeCancelRpc.deleteTradeCancel(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改交易订单取消表")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody H5MerchTradeCancelDTO.ETO eto) {
        eto.setId(id);
        h5MerchTradeCancelRpc.editTradeCancel(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
