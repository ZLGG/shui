package com.gs.lshly.facade.merchant.controller.h5.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.dto.H5MerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.qto.H5MerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.h5.trade.vo.H5MerchTradeDeliveryVO;
import com.gs.lshly.rpc.api.merchadmin.h5.trade.IH5MerchTradeDeliveryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/h5/tradeDelivery")
@Api(tags = "H5交易订单发货表管理")
public class H5MerchTradeDeliveryController {

    @DubboReference
    private IH5MerchTradeDeliveryRpc h5MerchTradeDeliveryRpc;

    @ApiOperation("交易订单发货表列表")
    @GetMapping("/list")
    public ResponseData<PageData<H5MerchTradeDeliveryVO.ListVO>> list(H5MerchTradeDeliveryQTO.QTO qto) {
        return ResponseData.data(h5MerchTradeDeliveryRpc.pageData(qto));
    }

    @ApiOperation("交易订单发货表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<H5MerchTradeDeliveryVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(h5MerchTradeDeliveryRpc.detailTradeDelivery(new H5MerchTradeDeliveryDTO.IdDTO(id)));
    }

    @ApiOperation("发货")
    @PostMapping("/doDelivery")
    public ResponseData<Void> doDelivery(@Valid @RequestBody H5MerchTradeDeliveryDTO.deliveryDTO dto) {
        h5MerchTradeDeliveryRpc.addTradeDelivery(dto);
        return ResponseData.success(MsgConst.SUCCESS);
    }
//
//
//    @ApiOperation("修改交易订单发货表")
//    @PutMapping(value = "/{id}")
//    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchTradeDeliveryDTO.ETO eto) {
//        eto.setId(id);
//        h5MerchTradeDeliveryRpc.editTradeDelivery(eto);
//        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
//    }
//
    @ApiOperation("商家物流列表")
    @GetMapping(value = "/shopLogisticsList")
    public ResponseData<List<CommonLogisticsCompanyVO.DetailVO>> listShopLogisticsCompany(H5MerchTradeDeliveryDTO.IdDTO dto) {
        return ResponseData.data(h5MerchTradeDeliveryRpc.listShopLogisticsCompany(dto));
    }

    @ApiOperation("自提核销")
    @PostMapping("/takeGoodsCodeCheck")
    public ResponseData<Void> takeGoodsCodeCheck(@Valid @RequestBody H5MerchTradeDeliveryDTO.takeGoodsCodeCheckDTO dto) {
        h5MerchTradeDeliveryRpc.addTakeGoodsCodeCheck(dto);
        return ResponseData.success(MsgConst.SUCCESS);
    }



}
