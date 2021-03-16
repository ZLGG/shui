package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataArticleRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbbH5DataArticleRpc implements IBbbH5DataArticleRpc {

    @Autowired
    private IBbbH5DataArticleService bbcDataArticleService;


    @Override
    public List<BbbH5DataArticleVO.ListVO> list(BaseDTO dto) {
        return bbcDataArticleService.list(dto);
    }
}