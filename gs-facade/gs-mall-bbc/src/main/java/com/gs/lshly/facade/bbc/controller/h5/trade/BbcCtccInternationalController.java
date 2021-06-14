package com.gs.lshly.facade.bbc.controller.h5.trade;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcCtccCategoryGoodsDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcCtccCategoryGoodsVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcCtccCategoryGoodsRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcCtccPtActivityGoodsRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-23
*/
@RestController
@RequestMapping("/bbc/international")
@Api(tags = "2C电信国际-v1.1.0")
@SuppressWarnings("unchecked")
public class BbcCtccInternationalController {

    @DubboReference
    private IBbcCtccPtActivityGoodsRpc bbcCtccPtActivityGoodsRpc;

	@ApiOperation("2C电信国际-v1.1.0")
    @GetMapping("")
    public ResponseData<BbcCtccCategoryGoodsVO.CtccInternationalHomeVO> home(BbcCtccCategoryGoodsDTO.DTO dto) {
        return ResponseData.data(bbcCtccPtActivityGoodsRpc.ctccInternationalHomeVO(dto));
    }

}
