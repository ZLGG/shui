package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.FinanMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.FinanMarginVO;
import com.gs.lshly.rpc.api.platadmin.trade.IFinanMarginRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 保证金 前端控制器
 * @author
 * @since 2020-09-17
 */
@Api(tags = "保证金管理")
@RestController
@RequestMapping("/platform/finan-margin")
public class FinanMarginController {

    @DubboReference
    private IFinanMarginRpc finanMarginRpc;

    @ApiOperation("添加保证金")
    @PostMapping
    public ResponseData add(@RequestBody FinanMarginDTO.ETO dto){
        finanMarginRpc.add(dto);
        return ResponseData.success();
    }

    @ApiOperation("删除保证金")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable String id){
        finanMarginRpc.delete(new FinanMarginDTO.IdDTO(id));
        return ResponseData.success();
    }

    @ApiOperation("更改保证金")
    @PutMapping
    public ResponseData update(@RequestBody FinanMarginDTO.ETO dto){
        finanMarginRpc.update(dto);
        return ResponseData.success();
    }

    @ApiOperation("查询保证金")
    @GetMapping
    public ResponseData findAll(FinanMarginDTO.ETO dto){
        List<FinanMarginVO> data = finanMarginRpc.findAll(dto);
        return ResponseData.data(data);
    }

}
