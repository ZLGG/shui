package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleCategoryVO;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleCategoryService;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataArticleCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author 陈奇
* @since 2020-10-17
*/
@DubboService
public class DataArticleCategoryRpc implements IDataArticleCategoryRpc{

    @Autowired
    private IDataArticleCategoryService dataArticleCategoryService;

    @Override
    public List<DataArticleCategoryVO.FirstListVO> pageData(DataArticleCategoryQTO.FirstQTO qto){
        return dataArticleCategoryService.pageData(qto);
    }

    @Override
    public void addDataArticleCategory(DataArticleCategoryDTO.ADTO adto){
        dataArticleCategoryService.addDataArticleCategory(adto);
    }

    @Override
    public void deleteDataArticleCategory(DataArticleCategoryDTO.IdDTO dto){
        dataArticleCategoryService.deleteDataArticleCategory(dto);
    }

    @Override
    public void editDataArticleCategory(DataArticleCategoryDTO.UDTO udto){
        dataArticleCategoryService.editDataArticleCategory(udto);
    }

    @Override
    public PageData<DataArticleCategoryVO.SecondListVO> pageData2(DataArticleCategoryQTO.SecondQTO qto) {
        return dataArticleCategoryService.pageData2(qto);
    }

    @Override
    public PageData<DataArticleCategoryVO.SecondListVO> pageData3(DataArticleCategoryQTO.SecondQTO qto) {
        return dataArticleCategoryService.pageData3(qto);
    }

    @Override
    public void changeIdx(List<DataArticleCategoryDTO.IdxDTO> idxDTOS) {
        dataArticleCategoryService.changeIdx(idxDTOS);
    }

}