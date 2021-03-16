package com.gs.lshly.facade.bbb.controller.h5.user;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleCategoryVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataArticleCategoryRpc;
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
@RequestMapping("/bbb/h5/userCenter/help")
@Api(tags = "帮助中心")
public class BbbH5ArticleCategoryController {

    @DubboReference
    private IBbbH5DataArticleCategoryRpc bbbH5DataArticleCategoryRpc;

    @ApiOperation("帮助列表")
    @GetMapping("/list")
    public ResponseData<List<BbbH5DataArticleCategoryVO.ListVO>> list() {
        return ResponseData.data(bbbH5DataArticleCategoryRpc.list(new BaseDTO()));
    }

    @ApiOperation("帮助搜索")
    @GetMapping("/search")
    public ResponseData<List<BbbH5DataArticleCategoryVO.SearchListVO>> search(BbbH5DataArticleCategoryQTO.QTO qto) {
        return ResponseData.data(bbbH5DataArticleCategoryRpc.search(qto));
    }

    @ApiOperation("帮助详情")
    @GetMapping("/details/{id}")
    public ResponseData<BbbH5DataArticleCategoryVO.DetailsVO> details(@PathVariable String id) {
        BbbH5DataArticleCategoryDTO.IdDTO dto = new BbbH5DataArticleCategoryDTO.IdDTO(id);
        return ResponseData.data(bbbH5DataArticleCategoryRpc.details(dto));
    }
}
