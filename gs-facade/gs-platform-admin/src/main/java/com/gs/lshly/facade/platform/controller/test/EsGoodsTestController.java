package com.gs.lshly.facade.platform.controller.test;


import com.gs.lshly.common.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
* <p>
*  前端控制器
* </p>
*
* @author lxus
* @since 2020-10-16
*/
@RestController
@RequestMapping("/platform/test/es/goods")
@Api(tags = "测试文件授权访问")
public class EsGoodsTestController {


    @ApiOperation("产品导入到搜索引擎")
    @PostMapping("/impportGoods/{goodsId}")
    public ResponseData<Void> impportGoods(@PathVariable String goodsId) {
        return ResponseData.success("import");
    }

    @ApiOperation("搜索商品")
    @GetMapping("/search")
    public ResponseData<Void> search() {
        return ResponseData.success("search");
    }

}
