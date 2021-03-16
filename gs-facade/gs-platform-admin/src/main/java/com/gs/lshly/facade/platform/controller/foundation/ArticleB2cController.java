package com.gs.lshly.facade.platform.controller.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataArticleRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

/**
* <p>
*  前端控制器
* </p>
*
* @author 陈奇
* @since 2020-10-19
*/
@RestController
@RequestMapping("/platadmin/BBCDataArticle")
@Api(tags = "2C文章管理")
@Module(code = "listArticle2C", parent = "site", name = "2C文章列表", index = 12)
public class ArticleB2cController {

    @DubboReference
    private IDataArticleRpc DataArticleRpc;

    @ApiOperation("2C-平台文章列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<DataArticleVO.ListVO>> list(DataArticleQTO.QTO qto) throws IOException {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(DataArticleRpc.pageData(qto));
    }

    @ApiOperation("2C平台文章详情")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<DataArticleVO.DVO> get(@PathVariable String id) {
        return ResponseData.data(DataArticleRpc.detailDataArticle(new DataArticleDTO.IdDTO(id)));
    }

    @ApiOperation("2C新增平台文章")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> add(@Valid @RequestBody DataArticleDTO.ETO dto) {
        dto.setTerminal(TerminalEnum.BBC.getCode());
        DataArticleRpc.addDataArticle(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }


    @ApiOperation("2C批量-删除平台文章")
    @PostMapping(value = "/deleteList")
    @Func(code="delete", name="删")
    public ResponseData<Void> deleteList(DataArticleDTO.IdListDTO dto) {
        DataArticleRpc.deleteDataArticleList(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("2C修改平台文章")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody DataArticleDTO.ETO eto) {
        eto.setId(id);
        DataArticleRpc.editDataArticle(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
