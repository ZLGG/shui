package com.gs.lshly.facade.platform.controller.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ArticleCategoryLevelEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleCategoryVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataArticleCategoryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platform/BBBAritcleCategory")
@Api(tags = "2B文章栏目")
@Module(code = "columnArticle2B", parent = "site", name = "2B文章栏目", index = 9)
public class AritcleCategoryB2bController {
    @DubboReference
    private IDataArticleCategoryRpc dataArticleCategoryRpc;


    @ApiOperation("文章栏目列表(1-2级栏目树)")
    @GetMapping("/FirstLevel")
    @Func(code="view", name="查")
    public ResponseData<List<DataArticleCategoryVO.FirstListVO>> allCategory(DataArticleCategoryQTO.FirstQTO qto) {
        qto.setLeveone(ArticleCategoryLevelEnum.ONE.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(dataArticleCategoryRpc.pageData(qto));
    }


    @ApiOperation("二级文章栏目列表(一级栏目ID查询)")
    @GetMapping("/SecondLevel/{id}")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataArticleCategoryVO.SecondListVO>> listSecondLevel(@PathVariable String id) {
        DataArticleCategoryQTO.SecondQTO qto = new DataArticleCategoryQTO.SecondQTO(10,id);
        return ResponseData.data(dataArticleCategoryRpc.pageData2(qto));
    }

    @ApiOperation("二级文章栏目列表(全部)")
    @GetMapping("/SecondLevel/All")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataArticleCategoryVO.SecondListVO>> listSecondLevelAll(DataArticleCategoryQTO.SecondQTO qto) {
        qto.setTerminal(TerminalEnum.BBB.getCode());
        return ResponseData.data(dataArticleCategoryRpc.pageData3(qto));
    }

    @ApiOperation("文章栏目新增")
    @PostMapping("/addSecondLevel")
    @Func(code="add", name="增")
    public ResponseData<Void> addTopLink(@Valid @RequestBody DataArticleCategoryDTO.ADTO adto) {
        adto.setTerminal(TerminalEnum.BBB.getCode());
        dataArticleCategoryRpc.addDataArticleCategory(adto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("文章栏目删除")
    @DeleteMapping("/deleteAritcleCategory/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteAritcleCategory(@PathVariable String id) {
        DataArticleCategoryDTO.IdDTO dto = new DataArticleCategoryDTO.IdDTO(id);
        dataArticleCategoryRpc.deleteDataArticleCategory(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("文章栏目编辑")
    @PutMapping("/updateAritcleCategory")
    @Func(code="edit", name="改")
    public ResponseData<Void> updateTopLink( @Valid @RequestBody DataArticleCategoryDTO.UDTO udto) {
        dataArticleCategoryRpc.editDataArticleCategory(udto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("对文章栏目进行排序")
    @PutMapping(value = "/changeIdx")
    @Func(code="edit", name="改")
    public ResponseData<Void> changeIdx(@RequestBody List<DataArticleCategoryDTO.IdxDTO> idxDTOS) {
        dataArticleCategoryRpc.changeIdx(idxDTOS);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
