package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleCategoryService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleCategoryVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataArticleCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbbH5DataArticleCategoryRpc implements IBbbH5DataArticleCategoryRpc {

    @Autowired
    private IBbbH5DataArticleCategoryService bbbH5DataArticleCategoryService;

    @Override
    public List<BbbH5DataArticleCategoryVO.ListVO> list(BaseDTO dto){
        return bbbH5DataArticleCategoryService.list(dto);
    }

    @Override
    public List<BbbH5DataArticleCategoryVO.SearchListVO> search(BbbH5DataArticleCategoryQTO.QTO qto) {
        return bbbH5DataArticleCategoryService.search(qto);
    }

    @Override
    public BbbH5DataArticleCategoryVO.DetailsVO details(BbbH5DataArticleCategoryDTO.IdDTO dto) {
        return bbbH5DataArticleCategoryService.details(dto);
    }
}