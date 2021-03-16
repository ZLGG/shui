package com.gs.lshly.facade.bbb.controller.pc.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.commodity.dto.PCBbbGoodsQaDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsQaQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.utils.IpUtil;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsQaRpc;
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
@RequestMapping("/bbb/goodsQa")
@Api(tags = "2Bpc商品咨询管理")
public class PCBbbGoodsQaController {

    @DubboReference
    private IPCBbbGoodsQaRpc bbbGoodsQaRpc;

    @ApiOperation("商品咨询列表")
    @GetMapping("")
    public ResponseData<PageData<PCBbbGoodsQaVO.ShowListVO>> list(PCBbbGoodsQaQTO.QTO qto) {
        return ResponseData.data(bbbGoodsQaRpc.pageData(qto));
    }

    @ApiOperation("会员中心商品咨询管理列表")
    @GetMapping("/listUserGoodsQa")
    public ResponseData<PageData<PCBbbGoodsQaVO.UserGoodsQaListVO>> listUserGoodsQa(PCBbbGoodsQaQTO.UserQTO qto) {
        return ResponseData.data(bbbGoodsQaRpc.pageUserGoodsQa(qto));
    }

    @ApiOperation("新增商品咨询")
    @PostMapping("")
    public ResponseData<Void> add(@ApiIgnore HttpServletRequest request, @Valid @RequestBody PCBbbGoodsQaDTO.ETO dto) {
        //获取本机的ip地址
        dto.setIp(IpUtil.getRemoteHost(request));
        bbbGoodsQaRpc.addGoodsQa(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("统计商品的咨询数量")
    @GetMapping(value = "countQuiz")
    public ResponseData<PCBbbGoodsQaVO.CountQuizVO> update(PCBbbGoodsQaQTO.GoodsIdQTO qto) {
        return ResponseData.data(bbbGoodsQaRpc.countQuiz(qto));
    }

    @ApiOperation("删除商品咨询")
    @PostMapping("/deleteBatch")
    public ResponseData<Void> deleteBatch(@RequestBody PCBbbGoodsQaDTO.IdListDTO dto) {
        bbbGoodsQaRpc.deleteGoodsQa(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
