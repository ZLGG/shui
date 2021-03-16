package com.gs.lshly.biz.support.foundation.rpc.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleRelationCategoryVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcDataArticleRelationCategoryRpc;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleRelationCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-11-13
*/
@DubboService
public class BbcDataArticleRelationCategoryRpc implements IBbcDataArticleRelationCategoryRpc{
    @Autowired
    private IBbcDataArticleRelationCategoryService  bbcDataArticleRelationCategoryService;

    @Override
    public PageData<BbcDataArticleRelationCategoryVO.ListVO> pageData(BbcDataArticleRelationCategoryQTO.QTO qto){
        return bbcDataArticleRelationCategoryService.pageData(qto);
    }

    @Override
    public void addDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto){
        bbcDataArticleRelationCategoryService.addDataArticleRelationCategory(eto);
    }

    @Override
    public void deleteDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto){
        bbcDataArticleRelationCategoryService.deleteDataArticleRelationCategory(dto);
    }


    @Override
    public void editDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto){
        bbcDataArticleRelationCategoryService.editDataArticleRelationCategory(eto);
    }

    @Override
    public BbcDataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto){
        return  bbcDataArticleRelationCategoryService.detailDataArticleRelationCategory(dto);
    }

}