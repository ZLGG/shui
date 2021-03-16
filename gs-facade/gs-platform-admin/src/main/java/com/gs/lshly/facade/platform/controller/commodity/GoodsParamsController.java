package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamsVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsParamsRpc;
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
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/platform/goods-params")
@Api(tags = "平台参数管理")
public class GoodsParamsController {

    @DubboReference
    private IGoodsParamsRpc paramsRpc;

    @ApiOperation("查询与类目关联的参数列表")
    @GetMapping(value = "/{id}")
    public ResponseData<List<GoodsParamsVO.ParamsListVO>> addParams(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        List<GoodsParamsVO.ParamsListVO> listVOS = paramsRpc.listGoodsParams(dto);
        return ResponseData.data(listVOS);
    }

}
