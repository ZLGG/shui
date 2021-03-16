package com.gs.lshly.facade.merchant.controller.pc.stock;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopLogisticsCompanyVO;
import com.gs.lshly.common.struct.platadmin.stock.dto.LogisticsCompanyDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.LogisticsCompanyQTO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.stock.IShopLogisticsCompanyRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

@Api(tags = "店铺的物流公司使用接口")
@RestController
@RequestMapping("/merchant/pc/logisticsCompany")
@Module(code = "logisticsCompany",parent = "transaction",name = "物流公司" ,index = 7)
public class ShopLogisticsCompanyController {

    @DubboReference
    private IShopLogisticsCompanyRpc shopLogisticsCompanyRpc;


    @ApiOperation("开启使用物流公司")
    @PostMapping("/open/{id}")
    @Func(code = "enable" ,name = "启动")
    public ResponseData openLogisticsCompany(@PathVariable String id){
        String shopId = "1";    //TODO 模拟店ID

        shopLogisticsCompanyRpc.openLogisticsCompany(new LogisticsCompanyDTO.IdDTO(id), new LogisticsCompanyDTO.ShopIdDTO(shopId));
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("关闭使用物流公司")
    @PostMapping("/close/{id}")
    @Func(code = "disable" ,name = "禁用")
    public ResponseData closeLogisticsCompany(@PathVariable String id){
        String shopId = "1";    //TODO 模拟店ID

        shopLogisticsCompanyRpc.closeLogisticsCompany(new LogisticsCompanyDTO.IdDTO(id), new LogisticsCompanyDTO.ShopIdDTO(shopId));
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("分页查询物流公司列表")
    @GetMapping
    @Func(code = "view" ,name = "查看")
    public ResponseData findPage(LogisticsCompanyQTO.QueryParam queryParam){
        String shopId = "1";    //TODO 模拟店ID

        PageData<ShopLogisticsCompanyVO.ListVO> data = shopLogisticsCompanyRpc.findPageOfShop(queryParam, new LogisticsCompanyDTO.ShopIdDTO(shopId));

        return ResponseData.data(data);
    }

}
