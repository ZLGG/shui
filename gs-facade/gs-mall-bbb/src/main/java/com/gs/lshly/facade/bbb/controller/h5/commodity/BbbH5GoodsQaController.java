package com.gs.lshly.facade.bbb.controller.h5.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsQaDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsQaQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsQaVO;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsQaRpc;
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
@RequestMapping("/bbb/h5/goodsQa")
@Api(tags = "商品咨询管理")
public class BbbH5GoodsQaController {

    @DubboReference
    private IBbbH5GoodsQaRpc bbcGoodsQaRpc;

    @ApiOperation("商品咨询列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5GoodsQaVO.ShowListVO>> list(BbbH5GoodsQaQTO.QTO qto) {
        return ResponseData.data(bbcGoodsQaRpc.pageData(qto));
    }

    @ApiOperation("新增商品咨询")
    @PostMapping("")
    public ResponseData<Void> add(@ApiIgnore HttpServletRequest request, @Valid @RequestBody BbbH5GoodsQaDTO.ETO dto) {
        //获取本机的ip地址
         dto.setIp(IpUtil.getRemoteHost(request));
        bbcGoodsQaRpc.addGoodsQa(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }



}
