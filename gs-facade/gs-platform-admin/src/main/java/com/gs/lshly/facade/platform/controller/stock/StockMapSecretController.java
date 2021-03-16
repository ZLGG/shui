package com.gs.lshly.facade.platform.controller.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockMapSecretDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockMapSecretVO;
import com.gs.lshly.rpc.api.platadmin.stock.IStockMapSecretRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2021-01-15
*/
@RestController
@RequestMapping("/platadmin/stockMapSecret")
@Api(tags = "地图密钥管理")
public class StockMapSecretController {

    @DubboReference
    private IStockMapSecretRpc StockMapSecretRpc;

    @ApiOperation("地图密钥列表")
    @GetMapping("/list")
    public ResponseData<PageData<StockMapSecretVO.ListVO>> list(BaseQTO qto) {
        return ResponseData.data(StockMapSecretRpc.pageData(qto));
    }

    @ApiOperation("新增地图密钥")
    @PostMapping("/add")
    public ResponseData<Void> add(@Valid @RequestBody StockMapSecretDTO.ETO dto) {
            StockMapSecretRpc.addStockMapSecret(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


}
