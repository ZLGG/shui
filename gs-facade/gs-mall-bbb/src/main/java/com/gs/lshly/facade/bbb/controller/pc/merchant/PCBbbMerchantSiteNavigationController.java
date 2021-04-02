package com.gs.lshly.facade.bbb.controller.pc.merchant;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantSiteNavigationVO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IPCBbbMerchantSiteNavigationRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2021-03-09
*/
@RestController
@RequestMapping("/bbb/merchantSiteNavigation")
@Api(tags = "商家入驻底部链接管理管理")
public class PCBbbMerchantSiteNavigationController {

    @DubboReference
    private IPCBbbMerchantSiteNavigationRpc pcBbbMerchantSiteNavigationRpc;

    @ApiOperation("商家入驻底部链接管理列表")
    @GetMapping("")
    public ResponseData<List<PCBbbMerchantSiteNavigationVO.ListVO>> list() {
        return ResponseData.data(pcBbbMerchantSiteNavigationRpc.listData());
    }


}
