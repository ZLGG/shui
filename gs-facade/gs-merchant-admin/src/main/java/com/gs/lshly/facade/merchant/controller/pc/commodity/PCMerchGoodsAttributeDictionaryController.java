package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsAttributeDictionaryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsAttributeDictionaryRpc;
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
 * @Date 14:44 2020/10/20
 */
@RestController
@RequestMapping("/merchant/pc/goodsAttributeDictionary")
@Api(tags = "商家商品属性管理")
public class PCMerchGoodsAttributeDictionaryController {

    @DubboReference
    private IPCMerchAdminGoodsAttributeDictionaryRpc adminGoodsAttributeDictionaryRpc;

    @ApiOperation("获取跟类目关联的属性以及属性详情信息")
    @GetMapping(value = "/listAttributes/{id}")
    public ResponseData<List<PCMerchGoodsAttributeDictionaryVO.DetailVO>> listAttributes(@PathVariable String id){
        PCMerchGoodsCategoryDTO.IdDTO dto = new PCMerchGoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(adminGoodsAttributeDictionaryRpc.listAttributeDetail(dto));
    }
}
