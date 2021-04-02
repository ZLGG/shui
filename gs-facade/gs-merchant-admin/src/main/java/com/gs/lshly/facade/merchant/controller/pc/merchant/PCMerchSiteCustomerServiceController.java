package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopServiceDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopServiceQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopServiceVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopServiceRpc;
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
* @author hyy
* @since 2020-11-09
*/
@RestController
@RequestMapping("/merchadmin/siteCustomerService")
@Api(tags = "商家在线客服管理")
public class PCMerchSiteCustomerServiceController {

    @DubboReference
    private IPCMerchShopServiceRpc pcMerchSiteCustomerServiceRpc;
    @DubboReference
    private ICommonSiteCustomerServiceRpc siteCustomerServiceRpc;

    @ApiOperation("商家在线客服展示")
    @GetMapping("/list")
    public ResponseData<List<PCMerchShopServiceVO.ListVO>>  list(PCMerchShopServiceQTO.QTO qto){
        return ResponseData.data(pcMerchSiteCustomerServiceRpc.list(qto));
    }

    @ApiOperation("新增或者修改商家在线客服")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchShopServiceDTO.ETO dto) {
        pcMerchSiteCustomerServiceRpc.addSiteCustomerService(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("商家电话客服展示")
    @GetMapping("/listPhone")
    public ResponseData<List<PCMerchShopServiceVO.PhoneVO>> listPhone(PCMerchShopServiceQTO.QTO qto){
        return ResponseData.data(pcMerchSiteCustomerServiceRpc.listPhone(qto));
    }

    @ApiOperation("新增或者修改商家电话客服")
    @PostMapping("/addPhone")
    public ResponseData<Void> addPhone(@Valid @RequestBody PCMerchShopServiceDTO.ETOPhone dto) {
        pcMerchSiteCustomerServiceRpc.addSiteCustomerServicPhone(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("平台电话客服展示")
    @GetMapping("/platformShow")
    public ResponseData<CommonSiteCustomerServiceVO.ServiceVO> platformShow(BaseDTO dto){
        return ResponseData.data(siteCustomerServiceRpc.getService(dto));
    }

}
