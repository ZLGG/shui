package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsParamsVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsParamsRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Starry
 * @Date 10:41 2020/10/20
 */
@RestController
@RequestMapping("/merchant/pc/goodsParams")
@Api(tags = "商品参数管理")
public class PCMerchGoodsParamsController {

    @DubboReference
    private IPCMerchAdminGoodsParamsRpc paramsRpc;

    @ApiOperation("查询与类目关联的参数列表")
    @GetMapping(value = "/{id}")
    public ResponseData<List<PCMerchGoodsParamsVO.ParamsListVO>> addParams(@PathVariable String id){
        PCMerchGoodsCategoryDTO.IdDTO dto = new PCMerchGoodsCategoryDTO.IdDTO(id);
        List<PCMerchGoodsParamsVO.ParamsListVO> listVOS = paramsRpc.listGoodsParams(dto);
        return ResponseData.data(listVOS);
    }
}
