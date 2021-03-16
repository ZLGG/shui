package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsSpecDictionaryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsSpecDictionaryRpc;
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
 * @Date 20:27 2020/10/19
 */
@RestController
@RequestMapping("/merchant/pc/GoodsSpecDictionary")
@Api(tags = "商家商品规格管理")
public class PCMerchGoodsSpecDictionaryController {
    @DubboReference
    private IPCMerchAdminGoodsSpecDictionaryRpc dictionaryRpc;

    @ApiOperation("查询类目关联的规格以及规格详情列表")
    @GetMapping(value = "/listSpecDetail/{id}")
    public ResponseData<List<PCMerchGoodsSpecDictionaryVO.DetailVO>> listSpecDetail(@PathVariable String id){
        PCMerchGoodsCategoryDTO.IdDTO dto = new PCMerchGoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(dictionaryRpc.listSpecDetail(dto));
    }
}
