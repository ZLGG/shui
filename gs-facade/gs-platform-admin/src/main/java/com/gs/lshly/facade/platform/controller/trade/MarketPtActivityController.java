package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.stock.dto.MarketPtActivityGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.MarketPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.IMarketPtActivityRpc;
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
* @since 2020-11-28
*/
@RestController
@RequestMapping("/platadmin/marketPtActivity")
@Api(tags = "平台活动管理")
@Module(code = "active",parent = "marketing",name = "活动",index = 6)
public class MarketPtActivityController {

    @DubboReference
    private IMarketPtActivityRpc MarketPtActivityRpc;

    @ApiOperation("平台活动列表")
    @GetMapping("")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<MarketPtActivityVO.ListVO>> list(MarketPtActivityQTO.QTO qto) {
        return ResponseData.data(MarketPtActivityRpc.pageData(qto));
    }

    @ApiOperation("平台活动详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<MarketPtActivityVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(MarketPtActivityRpc.detailMarketPtActivity(new MarketPtActivityDTO.IdDTO(id)));
    }

    @ApiOperation("新增平台活动")
    @PostMapping("")
    @Func(code = "add",name = "增")
    public ResponseData<Void> add(@RequestBody MarketPtActivityDTO.ETO dto) {
            MarketPtActivityRpc.addMarketPtActivity(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("批量删除平台活动")
    @PostMapping(value = "/deleteBatches")
    @Func(code = "delete",name = "删除")
    public ResponseData<Void> delete(@Valid @RequestBody  MarketPtActivityDTO.IdListDTO dto) {
        MarketPtActivityRpc.deleteMarketPtActivity(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改平台活动")
    @PutMapping(value = "/{id}")
    @Func(code = "edit",name = "改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody MarketPtActivityDTO.ETO eto) {
        eto.setId(id);
        MarketPtActivityRpc.editMarketPtActivity(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
