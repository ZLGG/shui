package com.gs.lshly.facade.bbb.controller.h5.stock;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.StockAddressTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockAddressDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.qto.BbbH5StockAddressQTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockAddressVO;
import com.gs.lshly.rpc.api.bbb.h5.stock.IBbbH5StockAddressRpc;
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
@RequestMapping("/bbb/h5/userCenter/stockAddress")
@Api(tags = "会员地址管理")
public class BbbH5StockAddressController {

    @DubboReference
    private IBbbH5StockAddressRpc stockAddressRpc;



    @ApiOperation("新增地址")
    @PostMapping("")
    public ResponseData<BbbH5StockAddressVO.DetailVO> addAddress(@Valid @RequestBody BbbH5StockAddressDTO.DetailETO eto) {

        return ResponseData.data(stockAddressRpc.saveStockAddress(eto));
    }

    @ApiOperation("修改地址")
    @PutMapping("{id}")
    public ResponseData<BbbH5StockAddressVO.DetailVO> updateAddress(@PathVariable String id,@RequestBody BbbH5StockAddressDTO.DetailETO eto) {
        eto.setId(id);
        return ResponseData.data(stockAddressRpc.saveStockAddress(eto));
    }

    @ApiOperation("删除地址")
    @DeleteMapping("remove/{id}")
    public ResponseData<Void> deleteAddress(@PathVariable String id) {
        stockAddressRpc.deleteAddress(new BbbH5StockAddressDTO.IdDTO(id));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("地址详情")
    @PostMapping("detail")
    public ResponseData<BbbH5StockAddressVO.DetailVO> detailAddress(@RequestBody BbbH5StockAddressDTO.IdAndTypeDTO dto) {
        return ResponseData.data(stockAddressRpc.detailStockAddress(dto));
    }

    @ApiOperation("获取默认地址")
    @PostMapping("getDefaultAddress")
    public ResponseData<BbbH5StockAddressVO.DetailVO> getDefaultAddress(BaseDTO dto) {
        return ResponseData.data(stockAddressRpc.innerGetDefault(dto, StockAddressTypeEnum.收货.getCode()));
    }

    @ApiOperation("个人地址列表")
    @PostMapping("pageListVO")
    public ResponseData<PageData<BbbH5StockAddressVO.ListVO>> pageListVo(@RequestBody BbbH5StockAddressQTO.QTO qto) {
        return ResponseData.data(stockAddressRpc.pageAddressListVO(qto));
    }


}
