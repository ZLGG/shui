package com.gs.lshly.facade.bbb.controller.pc.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonStockDTO;
import com.gs.lshly.common.struct.common.CommonStockVO;
import com.gs.lshly.rpc.api.common.ICommonStockRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zzg
* @since 2020-11-02
*/
@RestController
@RequestMapping("/bbb/pc/userCenter/stock")
@Api(tags = "内部服务测试(库存)")
public class BbbPcStockController {

    @DubboReference
    private ICommonStockRpc commonStock;


    @ApiOperation("检查库存(一个SKU-ID)")
    @PostMapping("/innerCheckStock")
    public  ResponseData<CommonStockVO.InnerCheckStockVO> innerCheckStock(BaseDTO dto,String shopId,String skuId,Integer quantity){

        return commonStock.innerCheckStock(dto,shopId,skuId,quantity);
    }


    @ApiOperation("多个SKU-ID")
    @PostMapping("/innerListCheckStock")
    public ResponseData<CommonStockVO.InnerListCheckStockVO> innerListCheckStock(@RequestBody CommonStockDTO.InnerCheckStockDTO dto){
       return commonStock.innerListCheckStock(dto);
    }


    @ApiOperation("销量列表(多个商品)")
    @PostMapping("/innerListSalesVolume")
    public  ResponseData<List<CommonStockVO.InnerCountSalesVO>> innerListSalesVolume(@RequestBody BaseDTO dto, @RequestBody List<String> goodsIdList){
        return commonStock.innerListSalesVolume(dto,goodsIdList);
    }


    @ApiOperation("销量列表(多个商品)")
    @PostMapping("/innerComplexSalesVolume")
    public ResponseData<CommonStockVO.InnerComplexCountSalesVO> innerComplexSalesVolume(@RequestBody CommonStockDTO.InnerCountSalesDTO dto){
        return commonStock.innerComplexSalesVolume(dto);
    }


    @ApiOperation("销量(一个商品)")
    @PostMapping("/innerSalesVolume")
    public ResponseData<CommonStockVO.InnerCountSalesVO> innerSalesVolume(@RequestBody BaseDTO dto,String goodsId){
        return commonStock.innerSalesVolume(dto,goodsId);
    }


    @ApiOperation("库存变动")
    @PostMapping("/innerChangeStock")
    public ResponseData<CommonStockVO.InnerChangeStockVO> innerChangeStock(@RequestBody CommonStockDTO.InnerChangeStockDTO dto){
        return commonStock.innerChangeStock(dto);
    }




}
