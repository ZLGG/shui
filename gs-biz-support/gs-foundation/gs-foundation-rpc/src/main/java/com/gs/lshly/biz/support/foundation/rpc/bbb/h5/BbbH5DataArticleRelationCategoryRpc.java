package com.gs.lshly.biz.support.foundation.rpc.bbb.h5;

import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleRelationCategoryService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleRelationCategoryVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5DataArticleRelationCategoryRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbbH5DataArticleRelationCategoryRpc implements IBbbH5DataArticleRelationCategoryRpc {
    
    @Autowired
    private IBbbH5DataArticleRelationCategoryService bbcDataArticleRelationCategoryService;

    @Override
    public PageData<BbbH5DataArticleRelationCategoryVO.ListVO> pageData(BbbH5DataArticleRelationCategoryQTO.QTO qto){
        return bbcDataArticleRelationCategoryService.pageData(qto);
    }

    @Override
    public void addDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto){
        bbcDataArticleRelationCategoryService.addDataArticleRelationCategory(eto);
    }

    @Override
    public void deleteDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto){
        bbcDataArticleRelationCategoryService.deleteDataArticleRelationCategory(dto);
    }


    @Override
    public void editDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto){
        bbcDataArticleRelationCategoryService.editDataArticleRelationCategory(eto);
    }

    @Override
    public BbbH5DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto){
        return  bbcDataArticleRelationCategoryService.detailDataArticleRelationCategory(dto);
    }

}