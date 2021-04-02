package com.gs.lshly.facade.merchant.controller.h5.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.dto.H5MerchShopDTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopNavigationQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.qto.H5MerchShopQTO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchMerchantAccountVO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopNavigationVO;
import com.gs.lshly.common.struct.merchadmin.h5.merchant.vo.H5MerchShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchMerchantAccountAuthRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchShopNavigationRpc;
import com.gs.lshly.rpc.api.merchadmin.h5.merchant.IH5MerchShopRpc;
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
* @author zst
* @since 2021-1-20
*/
@RestController
@RequestMapping("/merchadmin/H5/shop")
@Api(tags = "H5店铺管理")
public class H5MerchShopController {

    @DubboReference
    private IH5MerchShopRpc h5MerchShopRpc;
    @DubboReference
    private IH5MerchMerchantAccountAuthRpc ih5MerchMerchantAccountAuthRpc;
    @DubboReference
    private IH5MerchShopNavigationRpc ih5MerchShopNavigationRpc;

    @ApiOperation("店铺列表(右上角可切换店铺列表)")
    @GetMapping("")
    public ResponseData<List<H5MerchShopVO.ListVO>> list(H5MerchShopQTO.QTO qto) {
        return ResponseData.data(h5MerchShopRpc.list(qto));
    }

    @ApiOperation("切换店铺")
    @PostMapping("/changeShop/{shopId}")
    public ResponseData<List<H5MerchShopVO.ChangeShopVO>> changeShop(@PathVariable String shopId) {
        return ResponseData.data(ih5MerchMerchantAccountAuthRpc.changeShop(shopId,new BaseDTO()));
    }

    @ApiOperation("店铺详情")
    @GetMapping(value = "/{id}")
    public ResponseData<H5MerchShopVO.DetailVO> details(@PathVariable String id) {
        return ResponseData.data(h5MerchShopRpc.detailShop(new H5MerchShopDTO.IdDTO(id)));
    }

    @ApiOperation("店铺编辑信息")
    @PutMapping(value = "/editor")
    public ResponseData<Void> editor(@Valid @RequestBody H5MerchShopDTO.ETO eto) {
        h5MerchShopRpc.editShop(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("店铺自定义分类")
    @GetMapping("/navigationList")
    public ResponseData<List<H5MerchShopNavigationVO.ListVO>> list(H5MerchShopNavigationQTO.QTO qto) {
        return ResponseData.data(ih5MerchShopNavigationRpc.list(qto));
    }


    @ApiOperation("店铺自定义分类(一级)")
    @GetMapping("/navigationList001")
    public ResponseData<List<H5MerchShopNavigationVO.NavigationVO>> listLevel001() {
        return ResponseData.data(ih5MerchShopNavigationRpc.listLevel001(new BaseDTO()));
    }


    @ApiOperation("检查店铺(根据店铺ID返回商家店铺数据)")
    @GetMapping("/checkShopByShopId")
    public ResponseData<H5MerchMerchantAccountVO.checkShopByShopId> checkShopByShopId(H5MerchShopQTO.CutShopQTO qto) {
        return ResponseData.data(ih5MerchShopNavigationRpc.checkShopByShopId(qto));
    }


    @ApiOperation("检查店铺(商家帐号登录的时候检查店铺是不是有开通)")
    @GetMapping("/checkShop")
    public ResponseData<H5MerchMerchantAccountVO.CheckShopVO> checkShop() {
        return ResponseData.data(ih5MerchShopNavigationRpc.checkShop(new BaseDTO()));
    }

}
