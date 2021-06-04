package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsBrandVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsBrandRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Starry
 * @Date 20:06 2020/10/19
 */
@RestController
@RequestMapping("/merchant/pc/goodsBrand")
@Api(tags = "商品品牌管理")
public class PCMerchGoodsBrandController {

    @DubboReference
    private IPCMerchAdminGoodsBrandRpc goodsBrandRpc;

    @ApiOperation("查询类目关联的品牌信息")
    @GetMapping(value = "/listBrand/{id}")
    public ResponseData<List<PCMerchGoodsBrandVO.ListVO>> getList(@PathVariable String id) {
        return ResponseData.data(goodsBrandRpc.listGoodsBrand(new PCMerchGoodsCategoryDTO.IdDTO(id)));
    }

    @ApiOperation("查询品牌信息列表")
    @GetMapping(value = "/listBrandVO")
    public ResponseData<List<PCMerchGoodsBrandVO.ListVO>> getListBrandVO(BaseQTO qto) {
        return ResponseData.data(goodsBrandRpc.listBrandVO(qto));
    }

    @ApiOperation("根据一级类目Id查询品牌信息列表")
    @PostMapping(value = "/listBrandVoByCategoryIds")
    public ResponseData<List<PCMerchGoodsBrandVO.ListVO>> listBrandVoByCategoryIds(@Valid  @RequestBody PCMerchGoodsCategoryDTO.IdListDTO dto) {
        return ResponseData.data(goodsBrandRpc.listBrandVOByCategoryLevel1(dto));
    }

}
