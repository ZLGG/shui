package com.gs.lshly.facade.bbc.controller.h5.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsQaDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsQaQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsQaVO;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsQaRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-16
*/
@RestController
@RequestMapping("/bbb/pc/goodsQa")
@Api(tags = "商品咨询管理")
public class BbcGoodsQaController {

    @DubboReference
    private IBbcGoodsQaRpc bbcGoodsQaRpc;

    @ApiOperation("商品咨询列表")
    @GetMapping("")
    public ResponseData<PageData<BbcGoodsQaVO.ShowListVO>> list(BbcGoodsQaQTO.QTO qto) {
        return ResponseData.data(bbcGoodsQaRpc.pageData(qto));
    }

    @ApiOperation("新增商品咨询")
    @PostMapping("")
    public ResponseData<Void> add(@ApiIgnore HttpServletRequest request, @Valid @RequestBody BbcGoodsQaDTO.ETO dto) {
        //获取本机的ip地址
         dto.setIp(IpUtil.getRemoteHost(request));
        bbcGoodsQaRpc.addGoodsQa(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
}
