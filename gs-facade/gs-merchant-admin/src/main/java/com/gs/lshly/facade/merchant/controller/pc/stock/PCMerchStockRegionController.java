package com.gs.lshly.facade.merchant.controller.pc.stock;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.rpc.api.common.ICommonRegionRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zzg
* @since 2020-10-24
*/
@RestController
@RequestMapping("/merchadmin/region")
@Api(tags = "国标准地区库管理")
public class PCMerchStockRegionController {

    @DubboReference
    private ICommonRegionRpc commonRegionRpc;

    @ApiOperation("国标准地区库列表(到县)")
    @GetMapping("/toCounty")
    public ResponseData<List<CommonRegionVO.ProvinceVO>> listToCounty() {
        return ResponseData.data(commonRegionRpc.listToCounty());
    }

    @ApiOperation("国标准地区库(到市)")
    @GetMapping("/toCity")
    public ResponseData<List<CommonRegionVO.ProvinceShortVO>> listToCity() {
        return ResponseData.data(commonRegionRpc.listToCity());
    }

}
