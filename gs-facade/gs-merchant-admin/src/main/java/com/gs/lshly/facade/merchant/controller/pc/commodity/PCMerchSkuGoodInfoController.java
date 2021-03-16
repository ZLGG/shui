package com.gs.lshly.facade.merchant.controller.pc.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchSkuGoodInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchSkuGoodInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminSkuGoodInfoRpc;
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
* @author Starry
* @since 2020-10-08
*/
@RestController
@RequestMapping("/merchant/pc/skuGoodInfo")
@Api(tags = "sku商品信息管理")
public class PCMerchSkuGoodInfoController {

    @DubboReference
    private IPCMerchAdminSkuGoodInfoRpc skuGoodInfoRpc;

    @ApiOperation("sku商品信息管理列表")
    @GetMapping("")
    public ResponseData<PageData<PCMerchSkuGoodInfoVO.ListVO>> list(PCMerchSkuGoodInfoQTO.QTO qto) {
        return ResponseData.data(skuGoodInfoRpc.pageData(qto));
    }

    @ApiOperation("sku商品信息管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchSkuGoodInfoVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(skuGoodInfoRpc.detailSkuGoodInfo(new PCMerchSkuGoodInfoDTO.IdDTO(id)));
    }

    @ApiOperation("新增sku商品信息管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchSkuGoodInfoDTO.ETO dto) {
        skuGoodInfoRpc.addSkuGoodInfo(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除sku商品信息管理")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchSkuGoodInfoDTO.IdDTO dto = new PCMerchSkuGoodInfoDTO.IdDTO(id);
        skuGoodInfoRpc.deleteSkuGoodInfo(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改sku商品信息管理")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchSkuGoodInfoDTO.ETO eto) {
        eto.setId(id);
        skuGoodInfoRpc.editSkuGoodInfo(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
