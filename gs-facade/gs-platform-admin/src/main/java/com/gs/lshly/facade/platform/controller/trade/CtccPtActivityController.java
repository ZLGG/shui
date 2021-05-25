package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CtccPtActivityDTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CtccPtActivityVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.trade.ICtccPtActivityRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

/**
 * @Author yangxi
 * @create 2021/5/7 14:48
 */
@RestController
@RequestMapping("/platadmin/ctccPtActivity")
@Api(tags = "平台电信国际管理-v1.1.0")
//@Module(code = "ctcc",parent = "marketing",name = "电信国际",index = 8)
public class CtccPtActivityController {
    @DubboReference
    private ICtccPtActivityRpc iCtccPtActivityRpc;

    @ApiOperation("创建活动")
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
    }

    @ApiOperation("查看活动")
    @PutMapping("getActivityDetail/{id}")
    public ResponseData<CtccPtActivityVO.DetailVO> getActivityDetail(@PathVariable String id) {
        Optional.ofNullable(id).orElseThrow(() ->new BusinessException("活动id不能为空"));
        CtccPtActivityVO.DetailVO detailVO = iCtccPtActivityRpc.getActivityDetail(id);
        return ResponseData.data(detailVO);
    }

    @ApiOperation("电信国际类目-新增")
    @PostMapping("/addCategory")
    public ResponseData addCategory(@RequestBody CtccPtActivityDTO.AddCategoryDTO dto) {
        iCtccPtActivityRpc.addCategory(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("电信国际类目-新增类目商品")
    @PostMapping("/addCategoryGoods")
    public ResponseData addCategoryGoods(@RequestBody List<CtccPtActivityDTO.AddCategoryGoodsDTO> list) {
        iCtccPtActivityRpc.addCategoryGoods(list);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("电信国际类目-列表展示")
    @GetMapping("/getCategoryList")
    public ResponseData<CtccPtActivityVO.CategoryListVO> getCategoryList(CtccPtActivityDTO.CateGoryListDTO listDTO) {
        CtccPtActivityVO.CategoryListVO categoryListVO = iCtccPtActivityRpc.getCategoryList(listDTO);
        return ResponseData.data(categoryListVO);
    }


    @ApiOperation("批量删除商品")
    @GetMapping("/deleteGoods")
    public ResponseData deleteGoods(List<CtccPtActivityDTO.DeleteGoodsDTO> list) {
        iCtccPtActivityRpc.deleteGoods(list);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiModelProperty("上架/下架活动商品")
    @GetMapping("/updateGoodsState")
    public ResponseData updateGoodsState(List<CtccPtActivityDTO.RemoveGoodsDTO> list) {
        iCtccPtActivityRpc.updateGoodsState(list);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("添加活动商品")
    @PostMapping("/addActivityGoods")
    public ResponseData addActivityGoods(@RequestBody List<CtccPtActivityDTO.AddActivityGoodsDTO> activityGoodsDTOList) {
        iCtccPtActivityRpc.addActivityGoods(activityGoodsDTOList);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("活动列表展示")
    @GetMapping("/queryActivityList")
    public ResponseData<PageData<CtccPtActivityVO.ActivityListVO>> queryActivityList(CtccPtActivityDTO.ActivityListDTO dto) {
        PageData<CtccPtActivityVO.ActivityListVO> pageData = iCtccPtActivityRpc.queryActivityList(dto);
        return ResponseData.data(pageData);
    }
}
