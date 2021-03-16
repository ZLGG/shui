package com.gs.lshly.facade.bbb.controller.pc.pages;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.merchant.qto.BbbShopQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopListVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbb/shopList")
@Api(tags = "2Bpc端店铺列表",description = " ")
public class PCBbbShopListController {

    @DubboReference
    private IBbbShopRpc bbbShopRpc;


    @ApiOperation("2Bpc店铺搜索")
    @PostMapping("")
    public ResponseData<PageData<List<PCBbbShopListVO.ShopInfoVo>>> shopList(@Valid @RequestBody BbbShopQTO.SearchQTO qto) {
        return ResponseData.data(bbbShopRpc.pageData(qto));
    }

}
