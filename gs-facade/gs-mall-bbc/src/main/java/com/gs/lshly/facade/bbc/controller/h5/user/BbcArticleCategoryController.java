package com.gs.lshly.facade.bbc.controller.h5.user;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataArticleCategoryRpc;
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
* @author hyy
* @since 2020-11-13
*/
@RestController
@RequestMapping("/bbc/userCenter/help")
@Api(tags = "帮助中心")
public class BbcArticleCategoryController {

    @DubboReference
    private IBbcDataArticleCategoryRpc bbcDataArticleCategoryRpc;

    @ApiOperation("帮助列表")
    @GetMapping("/list")
    public ResponseData<List<BbcDataArticleCategoryVO.ListVO>> list() {
        return ResponseData.data(bbcDataArticleCategoryRpc.list(new BaseDTO()));
    }

    @ApiOperation("帮助搜索")
    @GetMapping("/search")
    public ResponseData<List<BbcDataArticleCategoryVO.SearchListVO>> search(BbcDataArticleCategoryQTO.QTO qto) {
        return ResponseData.data(bbcDataArticleCategoryRpc.search(qto));
    }

    @ApiOperation("帮助详情")
    @GetMapping("/details/{id}")
    public ResponseData<BbcDataArticleCategoryVO.DetailsVO> details(@PathVariable String id) {
        BbcDataArticleCategoryDTO.IdDTO dto = new BbcDataArticleCategoryDTO.IdDTO(id);
        return ResponseData.data(bbcDataArticleCategoryRpc.details(dto));
    }
}
