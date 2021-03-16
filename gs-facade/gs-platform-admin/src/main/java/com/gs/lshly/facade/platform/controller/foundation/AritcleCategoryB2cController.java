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
@RequestMapping("/platform/BBCAritcleCategory")
@Api(tags = "2C文章栏目")
@Module(code = "columnArticle2C", parent = "site", name = "2C文章栏目", index = 10)
public class AritcleCategoryB2cController {
    @DubboReference
    private IDataArticleCategoryRpc dataArticleCategoryRpc;



    /**
     * 获取全部文章栏目
     * 一级为主，对应一级下的二级以数组传输
     * @param qto
     * @return
     */
    @ApiOperation("文章栏目-列表")
    @GetMapping("/FirstLevel")
    @Func(code="view", name="查")
    public ResponseData<List<DataArticleCategoryVO.FirstListVO>> allCategory(DataArticleCategoryQTO.FirstQTO qto) {
        qto.setLeveone(ArticleCategoryLevelEnum.ONE.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(dataArticleCategoryRpc.pageData(qto));
    }

    /**
     * 根据一级类目id获取
     * @param id
     * @return
     */
    @ApiOperation("二级文章栏目-列表")
    @GetMapping("/SecondLevel/{id}")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataArticleCategoryVO.SecondListVO>> listSecondLevel(@PathVariable String id) {
        DataArticleCategoryQTO.SecondQTO qto = new DataArticleCategoryQTO.SecondQTO(TerminalEnum.BBC.getCode(),id);
        return ResponseData.data(dataArticleCategoryRpc.pageData2(qto));
    }

    /**
     * 获取所有二级
     * @param
     * @return
     */
    @ApiOperation("二级文章栏目-列表-All")
    @GetMapping("/SecondLevel/All")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataArticleCategoryVO.SecondListVO>> listSecondLevelAll(DataArticleCategoryQTO.SecondQTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(dataArticleCategoryRpc.pageData3(qto));
    }

    @ApiOperation("add-文章栏目")
    @PostMapping("/addSecondLevel")
    @Func(code="add", name="增")
    public ResponseData<Void> addTopLink(@Valid @RequestBody DataArticleCategoryDTO.ADTO adto) {
        adto.setTerminal(TerminalEnum.BBC.getCode());
        dataArticleCategoryRpc.addDataArticleCategory(adto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("delete-文章栏目")
    @DeleteMapping("/deleteAritcleCategory/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteAritcleCategory(@PathVariable String id) {
        DataArticleCategoryDTO.IdDTO dto = new DataArticleCategoryDTO.IdDTO(id);
        dataArticleCategoryRpc.deleteDataArticleCategory(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("update-文章栏目")
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
