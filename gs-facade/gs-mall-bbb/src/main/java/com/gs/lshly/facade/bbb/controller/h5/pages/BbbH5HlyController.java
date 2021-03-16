package com.gs.lshly.facade.bbb.controller.h5.pages;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5HlyQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5HlyVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexHlyRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbb/h5/hly")
@Api(tags = "2BH5好粮油专栏页",description = " ")
public class BbbH5HlyController {

    @DubboReference
    private IBbbH5ComplexHlyRpc bbbH5ComplexHlyRpc;

    @ApiOperation("好粮油组合数据")
    @GetMapping("/complex")
    public ResponseData<BbbH5HlyVO.TopComplexVO> complex(BbbH5HlyQTO.QTO qto) {

        return ResponseData.data(bbbH5ComplexHlyRpc.topComplex(qto));
    }

}
