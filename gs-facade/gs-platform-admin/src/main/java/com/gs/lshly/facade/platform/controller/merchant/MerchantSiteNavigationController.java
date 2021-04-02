package com.gs.lshly.facade.platform.controller.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantSiteNavigationDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantSiteNavigationVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantSiteNavigationRpc;
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
* @author Starry
* @since 2021-03-08
*/
@RestController
@RequestMapping("/platadmin/merchantSiteNavigation")
@Api(tags = "商家入驻底部链接管理管理")
public class MerchantSiteNavigationController {

    @DubboReference
    private IMerchantSiteNavigationRpc MerchantSiteNavigationRpc;

    @ApiOperation("商家入驻底部链接管理列表")
    @GetMapping("")
    public ResponseData<List<MerchantSiteNavigationVO.ListVO>> list() {
        return ResponseData.data(MerchantSiteNavigationRpc.listSiteNavigation());
    }


    @ApiOperation("保存商家入驻底部链接管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody List<MerchantSiteNavigationDTO.ETO> etoList) {
            MerchantSiteNavigationRpc.saveMerchantSiteNavigation(etoList);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }


}
