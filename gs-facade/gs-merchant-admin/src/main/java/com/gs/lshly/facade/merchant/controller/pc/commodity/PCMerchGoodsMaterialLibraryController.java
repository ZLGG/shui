package com.gs.lshly.facade.merchant.controller.pc.commodity;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsMaterialLibraryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsMaterialLibraryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-12-10
*/
@RestController
@RequestMapping("/merchant/pc/goodsMaterialLibrary")
@Api(tags = "商品素材库商品图片管理管理")
public class PCMerchGoodsMaterialLibraryController {

    @DubboReference
    private IPCMerchGoodsMaterialLibraryRpc pcMerchGoodsMaterialLibraryRpc;

    @ApiOperation("根据来自店铺的申请类目id查询素材模版类目信息")
    @GetMapping(value = "/listMaterialVO")
    public ResponseData<List<PCMerchGoodsMaterialLibraryVO.DetailVO>> get(PCMerchGoodsMaterialLibraryQTO.QTO qto) {
        return ResponseData.data(pcMerchGoodsMaterialLibraryRpc.detailGoodsMaterialLibrary(qto));
    }

}
