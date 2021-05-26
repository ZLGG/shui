package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsServeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant/pc/goodsServe")
@Api(tags = "商品信息管理")
public class PCMerchGoodsServeController {

    @DubboReference
    private IPCMerchGoodsServeRpc goodsServeRpc;

    /**
     * 分页查询商品服务列表
     *
     * @return
     */
    @ApiOperation("商品服务列表")
    @GetMapping("")
    @Func(code = "view", name = "查看")
    public ResponseData<PageData<GoodsServeVO.ListVO>> pageGoodsServeData(@RequestBody GoodsServeQTO.QTO qto) {
        return ResponseData.data(goodsServeRpc.pageGoodsServeData(qto));
    }
}
