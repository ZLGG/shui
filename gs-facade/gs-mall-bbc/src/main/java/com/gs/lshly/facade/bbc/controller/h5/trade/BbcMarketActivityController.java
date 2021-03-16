package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcMarketActivityDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcMarketActivityVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcMarketActivityRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author zdf
* @since 2020-12-17
*/
@RestController
@RequestMapping("/bbc/marketActivity")
@Api(tags = "商品活动")
public class BbcMarketActivityController {

    @DubboReference
    private IBbcMarketActivityRpc iBbcMarketActivityRpc;

    @ApiOperation("活动")
    @PostMapping("/activityCard")
    public ResponseData<BbcMarketActivityVO.ListVO> activityList(@Valid @RequestBody BbcMarketActivityDTO.DTO dto) {

        return iBbcMarketActivityRpc.activityList(dto);
    }
    @ApiOperation("优惠卷")
    @PostMapping("/activity")
    public ResponseData<List<BbcMarketActivityVO.ListCardVO>> activityCardList(@Valid @RequestBody BbcMarketActivityDTO.DTO dto) {

        return iBbcMarketActivityRpc.activityCardList(dto);
    }



}
