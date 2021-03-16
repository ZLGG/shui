package com.gs.lshly.facade.bbc.controller.pc.stock;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.StockAddressTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.qto.BbbStockAddressQTO;
import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockAddressVO;
import com.gs.lshly.rpc.api.bbb.pc.stock.IBbbPcStockAddressRpc;
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
* @author zzg
* @since 2020-11-02
*/
@RestController
@RequestMapping("/bbc/pc/userCenter/stockAddress")
@Api(tags = "2C PC会员收货地址管理")
public class BbcPcStockAddressController {


    @DubboReference
    private IBbbPcStockAddressRpc stockAddressRpc;

    @ApiOperation("默认收获地址获取")
    @GetMapping("/getDefault")
    public ResponseData<BbbStockAddressVO.DetailVO> getDefault(){
        BbbStockAddressVO.DetailVO detailVO = stockAddressRpc.getDefault(new BaseDTO(),StockAddressTypeEnum.收货.getCode());
        return ResponseData.data(detailVO);
    }

    @ApiOperation("地址新增")
    @PostMapping("/addStockAddress")
    public ResponseData<Void> addInvoiceAddress(@Valid @RequestBody BbbStockAddressDTO.DetailETO eto) {
        stockAddressRpc.saveStockAddress(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("地址修改")
    @PostMapping("/updateStockAddress/{id}")
    public ResponseData<Void> updateStockAddress(@PathVariable String id, @RequestBody BbbStockAddressDTO.DetailETO eto) {
        eto.setId(id);
        stockAddressRpc.saveStockAddress(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("个人中心收货列表")
    @GetMapping("/pageStockAddress")
    public ResponseData<PageData<BbbStockAddressVO.ListVO>> pageStockAddress(BbbStockAddressQTO.QTO qto){
        return ResponseData.data(stockAddressRpc.pageAddressListVO(qto));
    }


    @ApiOperation("设置默认地址")
    @PostMapping("/setDefault")
    public ResponseData<Void> setDefault(@RequestBody BbbStockAddressDTO.SetDefaultDTO dto){
        stockAddressRpc.setDefaultStockAddress(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("删除地址")
    @PostMapping("/deleteAddress/{id}")
    public ResponseData<Void> setDefault(@PathVariable String id){
        stockAddressRpc.deleteStockAddress(new BbbStockAddressDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("获取地址详情")
    @PostMapping("/getDetail")
    public ResponseData<BbbStockAddressVO.ListVO> getDetail(@RequestBody BbbStockAddressDTO.EditDTO dto){
        return ResponseData.data(stockAddressRpc.detailStockAddress(dto));
    }

    @ApiOperation("地址列表")
    @GetMapping("/listAddress")
    public ResponseData<BbbStockAddressVO.ListVO> listAddress(BbbStockAddressDTO.AddressTypeDTO dto){
        return ResponseData.data(stockAddressRpc.listStockAddressVO(dto));
    }


}
