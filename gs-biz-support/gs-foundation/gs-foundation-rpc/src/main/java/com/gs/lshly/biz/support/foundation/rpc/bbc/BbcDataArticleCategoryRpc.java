package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataArticleCategoryRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbcDataArticleCategoryRpc implements IBbcDataArticleCategoryRpc{

    @Autowired
    private IBbcDataArticleCategoryService  bbcDataArticleCategoryService;

    @Override
    public List<BbcDataArticleCategoryVO.ListVO> list(BaseDTO dto){
        return bbcDataArticleCategoryService.list(dto);
    }

    @Override
    public List<BbcDataArticleCategoryVO.SearchListVO> search(BbcDataArticleCategoryQTO.QTO qto) {
        return bbcDataArticleCategoryService.search(qto);
    }

    @Override
    public BbcDataArticleCategoryVO.DetailsVO details(BbcDataArticleCategoryDTO.IdDTO dto) {
        return bbcDataArticleCategoryService.details(dto);
    }
}