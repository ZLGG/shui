package com.gs.lshly.facade.bbc.controller.h5.commodity;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsCategoryDTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 电信产品
 * 
 * @author yingjun
 * @date 2021年4月25日 上午11:09:22
 */
@RestController
@RequestMapping("/bbc/ctccgoodsinfo")
@Api(tags = "2C电信产品-v1.1.0")
public class BbcCtccGoodsInfoController {

    @DubboReference
    private IBbcGoodsCategoryRpc bbcGoodsCategoryRpc;

    @ApiOperation("2C电信产品-v1.1.0")
    @GetMapping("")
    public ResponseData<BbcGoodsCategoryVO.CtccHomeVO> ctcchome(BbcGoodsCategoryDTO.CtccDTO ctccDTO) {
        return ResponseData.data(bbcGoodsCategoryRpc.ctcchome(ctccDTO));
    }

}
