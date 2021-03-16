package com.gs.lshly.facade.bbb.controller.pc.foundation;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbArticleCategoryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/bbb/help")
@Api(tags = "帮助中心",description = " ")
public class BbbArticleCategoryController {

    @DubboReference
    private IBbbArticleCategoryRpc bbbArticleCategoryRpc;

    @ApiOperation("帮助文章栏目")
    @GetMapping("/helpList")
    public ResponseData<List<BbbArticleCategoryVO.HelpListVO>> helpList() {
        return ResponseData.data(bbbArticleCategoryRpc.helpList(new BaseDTO()));
    }

    @ApiOperation("帮助文章列表")
    @GetMapping("/articleList")
    public ResponseData<PageData<BbbArticleCategoryVO.ArticleListVO>> articleList(BbbArticleCategoryQTO.ArticleQTO qto) {
        return ResponseData.data(bbbArticleCategoryRpc.articleList(qto));
    }


    @ApiOperation("帮助文章搜索")
    @GetMapping("/search")
    public ResponseData<List<BbbArticleCategoryVO.SearchListVO>> search(BbbArticleCategoryQTO.QTO qto) {
        return ResponseData.data(bbbArticleCategoryRpc.search(qto));
    }

    @ApiOperation("帮助文章详情")
    @GetMapping("/details/{id}")
    public ResponseData<BbbArticleCategoryVO.DetailsVO> details(@PathVariable String id) {
        BbbArticleCategoryDTO.IdDTO dto = new BbbArticleCategoryDTO.IdDTO(id);
        return ResponseData.data(bbbArticleCategoryRpc.details(dto));
    }
}
