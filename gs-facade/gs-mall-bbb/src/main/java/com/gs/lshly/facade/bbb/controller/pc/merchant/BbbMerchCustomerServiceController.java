package com.gs.lshly.facade.bbb.controller.pc.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bbb/merchService")
@Api(tags = "2B商城客服数据",description = " ")
public class BbbMerchCustomerServiceController {

    @DubboReference
    private IBbbShopRpc bbbShopRpc;

    @DubboReference
    private ICommonShopRpc iCommonShopRpc;

    @DubboReference
    private ICommonSiteCustomerServiceRpc commonSiteCustomerServiceRpc;

    @ApiOperation("店铺客服")
    @GetMapping("/service/{id}")
    public ResponseData<PCBbbShopHomeVO.ShopServiceVO> service(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.shopService(new BbbShopDTO.IdDTO(id)));
    }

    @ApiOperation("店铺访问量")
    @GetMapping("/visits")
    public ResponseData<Void> visits( CommonShopDTO.VisitsDTO dto)  {
        iCommonShopRpc.visits(dto);
        return ResponseData.success(MsgConst.RECORD_SUCCESS);
    }

    @ApiOperation("客服配置")
    @GetMapping("/getServiceVO")
    public ResponseData<CommonSiteCustomerServiceVO.ServiceVO> getServiceVO() {
        return ResponseData.data(commonSiteCustomerServiceRpc.getService(new BaseDTO()));
    }

}
