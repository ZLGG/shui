package com.gs.lshly.facade.platform.controller.trade;

import java.util.List;
import java.util.Optional;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author yangxi
 * @create 2021/5/7 14:48
 */
@RestController
@RequestMapping("/platadmin/ctccPtActivity")
@Api(tags = "平台电信国际管理-v1.1.0")
@Module(code = "listCtccPtActivity",parent = "marketing",name = "电信国际",index = 1)
public class CtccPtActivityController {
    @DubboReference
    private ICtccPtActivityRpc iCtccPtActivityRpc;

/*    @ApiOperation("创建活动")
    @PostMapping("/addActivity")
    public ResponseData addActivity(@RequestBody CtccPtActivityDTO.AddDTO addDTO) {
        iCtccPtActivityRpc.addActivity(addDTO);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("修改活动")
    @PutMapping("/modifyActivity/{id}")
    public ResponseData modifyActivity(@PathVariable String id,@RequestBody CtccPtActivityDTO.ModifyDTO modifyDTO) {
        modifyDTO.setId(id);
        iCtccPtActivityRpc.modifyActivity(modifyDTO);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }*/

    @ApiOperation("查看活动商品详情")
    @PutMapping("getGoodsDetail/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<BbcGoodsInfoVO.CtccGoodsDetailVO> getActivityDetail(@PathVariable String id) {
        Optional.ofNullable(id).orElseThrow(() ->new BusinessException("商品id不能为空"));
        BbcGoodsInfoVO.CtccGoodsDetailVO detailVO = iCtccPtActivityRpc.getActivityDetail(id);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("电信国际类目-新增")
    @PostMapping("/addCategory")
    @Func(code = "add",name = "增")
    public ResponseData addCategory(@RequestBody CtccPtActivityDTO.AddCategoryDTO dto) {
        iCtccPtActivityRpc.addCategory(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("电信国际类目-新增类目商品")
    @PostMapping("/addCategoryGoods")
    @Func(code = "add",name = "增")
    public ResponseData addCategoryGoods(@RequestBody List<CtccPtActivityDTO.AddCategoryGoodsDTO> list) {
        iCtccPtActivityRpc.addCategoryGoods(list);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("电信国际类目-列表展示")
    @GetMapping("/getCategoryList")
    @Func(code = "view",name = "查")
    public ResponseData<CtccPtActivityVO.CategoryListVO> getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {
        CtccPtActivityVO.CategoryListVO categoryListVO = iCtccPtActivityRpc.getCategoryList(listDTO);
        return ResponseData.data(categoryListVO);
    }


    @ApiOperation("批量删除活动商品")
    @PostMapping("/deleteGoods")
    @Func(code = "delete",name = "删")
    public ResponseData deleteGoods(@RequestBody CtccPtActivityDTO.DeleteGoodsDTO list) {
        iCtccPtActivityRpc.deleteGoods(list);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("上架/下架活动商品")
    @PostMapping("/updateGoodsState")
    @Func(code = "edit",name = "改")
    public ResponseData updateGoodsState(@RequestBody List<CtccPtActivityDTO.RemoveGoodsDTO> list) {
        iCtccPtActivityRpc.updateGoodsState(list);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("添加活动商品")
    @PostMapping("/addGoods")
    @Func(code = "add",name = "增")
    public ResponseData addActivityGoods(@RequestBody List<CtccPtActivityDTO.AddGoodsDTO> dtoList) {
        iCtccPtActivityRpc.addActivityGoods(dtoList);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("活动商品列表展示")
    @GetMapping("/queryGoodsList")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<BbcGoodsInfoVO.CtccGoodsDetailVO>> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto) {
        PageData<BbcGoodsInfoVO.CtccGoodsDetailVO> pageData = iCtccPtActivityRpc.queryActivityList(dto);
        return ResponseData.data(pageData);
    }

    @ApiOperation("对商品排序")
    @GetMapping("/sortedGoods")
    @Func(code = "edit",name = "改")
    public ResponseData sortedGoods(CtccPtActivityDTO.SortedGoodsDTO dto) {
        iCtccPtActivityRpc.sortedGoods(dto);
        return ResponseData.data(MsgConst.UPDATE_SUCCESS);
    }

}
