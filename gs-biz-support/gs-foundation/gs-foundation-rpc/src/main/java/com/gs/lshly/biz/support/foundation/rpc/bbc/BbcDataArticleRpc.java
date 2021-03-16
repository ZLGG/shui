package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataArticleRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbcDataArticleRpc implements IBbcDataArticleRpc{

    @Autowired
    private IBbcDataArticleService  bbcDataArticleService;


    @Override
    public List<BbcDataArticleVO.ListVO> list(BaseDTO dto) {
        return bbcDataArticleService.list(dto);
    }
}