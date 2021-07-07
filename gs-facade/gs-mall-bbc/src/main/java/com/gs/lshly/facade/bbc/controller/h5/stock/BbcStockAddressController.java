package com.gs.lshly.facade.bbc.controller.h5.stock;
import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.StockAddressTypeEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockAddressDTO;
import com.gs.lshly.common.struct.bbc.stock.qto.BbcStockAddressQTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockAddressVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.BasicAreasVO;
import com.gs.lshly.rpc.api.bbc.stock.IBbcStockAddressRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author zzg
* @since 2020-11-02
*/
@RestController
@RequestMapping("/bbc/userCenter/stockAddress")
@Api(tags = "会员地址管理-v1.1.0")
public class BbcStockAddressController {

    @DubboReference
    private IBbcStockAddressRpc bbcStockAddressRpc;

    @ApiOperation("收货地址列表")
    @GetMapping("")
    public ResponseData<List<BbcStockAddressVO.ListVO>> list(BbcStockAddressQTO.QTO qto) {
        return ResponseData.data(bbcStockAddressRpc.list(qto,StockAddressTypeEnum.收货.getCode()));
    }

    @ApiOperation("收货地址详情")
    @GetMapping(value = "/{id}")
    public ResponseData<BbcStockAddressVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(bbcStockAddressRpc.detailStockAddress(new BbcStockAddressDTO.IdDTO(id)));
    }

    @ApiOperation("收获地址新增")
    @PostMapping("")
    public ResponseData<BbcStockAddressVO.DetailVO> Add(@Valid @RequestBody BbcStockAddressDTO.ETO dto) {
        return ResponseData.data(bbcStockAddressRpc.addStockAddress(dto,StockAddressTypeEnum.收货.getCode()));
    }

    @ApiOperation("收货地址编辑")
    @PutMapping("/{id}")
    public ResponseData<Void> editor(@PathVariable String id,@Valid @RequestBody BbcStockAddressDTO.ETO dto) {
        dto.setId(id);
        bbcStockAddressRpc.editorStockAddress(dto,StockAddressTypeEnum.收货.getCode());
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("收货地址删除")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        BbcStockAddressDTO.IdDTO dto = new BbcStockAddressDTO.IdDTO(id);
        bbcStockAddressRpc.deleteStockAddress(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("默认收获地址获取")
    @GetMapping("/getDefault")
    public ResponseData<BbcStockAddressVO.DetailVO> getDefault(){
        BbcStockAddressVO.DetailVO detailVO = bbcStockAddressRpc.getDefault(new BaseDTO(),StockAddressTypeEnum.收货.getCode());
        return ResponseData.data(detailVO);
    }

    @ApiOperation("默认收获地址设置")
    @PutMapping("/setDefault/{id}")
    public ResponseData<Void>  setDefault(@PathVariable String id){
        bbcStockAddressRpc.setDefault(new BbcStockAddressDTO.IdDTO(id), StockAddressTypeEnum.收货.getCode());
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
    
    @ApiOperation("跟据父ID查询子区县,默认第一级0")
    @GetMapping("/listBasicAreas/{pid}")
    public ResponseData<List<BasicAreasVO.DropListVO>> listBasicAreas(@PathVariable Integer pid){
        return ResponseData.data(bbcStockAddressRpc.listBasicAreas(pid));
    }

}
