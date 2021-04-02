package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopNavigationVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchShopVO;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopNavigationRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchShopRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountAuthRpc;
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
* @author xxfc
* @since 2020-11-07
*/
@RestController
@RequestMapping("/merchadmin/shop")
@Api(tags = "店铺管理")
public class PCMerchShopController {

    @DubboReference
    private IPCMerchShopRpc pcMerchShopRpc;

    @DubboReference
    private IPCMerchShopNavigationRpc pcMerchShopNavigationRpc;

    @DubboReference
    private IPCMerchMerchantAccountAuthRpc pcMerchMerchantAccountAuthRpc;

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @ApiOperation("店铺列表(右上角可切换店铺列表)")
    @GetMapping("")
    public ResponseData<List<PCMerchShopVO.ListVO>> list(PCMerchShopQTO.QTO qto) {
        return ResponseData.data(pcMerchShopRpc.list(qto));
    }

    @ApiOperation("切换店铺")
    @PostMapping("/changeShop/{shopId}")
    public ResponseData<List<PCMerchShopVO.ChangeShopVO>> changeShop(@PathVariable String shopId) {
        return ResponseData.data(pcMerchMerchantAccountAuthRpc.changeShop(shopId,new BaseDTO()));
    }

    @ApiOperation("店铺详情")
    @GetMapping(value = "/details")
    public ResponseData<PCMerchShopVO.DetailVO> details() {
        return ResponseData.data(pcMerchShopRpc.detailShop(new BaseDTO()));
    }

    @ApiOperation("店铺编辑")
    @PutMapping(value = "/editor")
    public ResponseData<Void> editor(@Valid @RequestBody PCMerchShopDTO.ETO eto) {
        pcMerchShopRpc.editShop(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("店铺自定义分类")
    @GetMapping("/navigationList")
    public ResponseData<List<PCMerchShopNavigationVO.ListVO>> list(PCMerchShopNavigationQTO.QTO qto) {
        return ResponseData.data(pcMerchShopNavigationRpc.list(qto));
    }


    @ApiOperation("店铺自定义分类(一级)")
    @GetMapping("/navigationList001")
    public ResponseData<List<PCMerchShopNavigationVO.NavigationVO>> listLevel001() {
        return ResponseData.data(pcMerchShopNavigationRpc.listLevel001(new BaseDTO()));
    }

    @ApiOperation("商家入驻信息")
    @GetMapping("/SettledInfo")
    public ResponseData<LegalDictVO.SettledInfoVO> SettledInfo() {
        return ResponseData.data(legalDictRpc.getSettledInfo(new BaseDTO()));
    }

    @ApiOperation("编辑商家入驻信息")
    @PutMapping("/saveSettledInfo/{legalId}")
    public ResponseData<LegalDictVO.MerchantApplyIdVO> saveSettledInfo(@PathVariable String legalId, @RequestBody LegalDictDTO.SettledInfoETO eto) {
        eto.setId(legalId);
        return ResponseData.data(legalDictRpc.editSettledInfo(eto));

    }
}
