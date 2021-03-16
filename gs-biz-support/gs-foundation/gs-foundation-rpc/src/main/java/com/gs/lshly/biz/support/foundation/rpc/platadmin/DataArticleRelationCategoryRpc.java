package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleRelationCategoryVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IDataArticleRelationCategoryRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.IDataArticleRelationCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author 陈奇
* @since 2020-10-19
*/
@DubboService
public class DataArticleRelationCategoryRpc implements IDataArticleRelationCategoryRpc{
    @Autowired
    private IDataArticleRelationCategoryService  DataArticleRelationCategoryService;

    @Override
    public PageData<DataArticleRelationCategoryVO.ListVO> pageData(DataArticleRelationCategoryQTO.QTO qto){
        return DataArticleRelationCategoryService.pageData(qto);
    }

    @Override
    public void addDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto){
        DataArticleRelationCategoryService.addDataArticleRelationCategory(eto);
    }

    @Override
    public void deleteDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto){
        DataArticleRelationCategoryService.deleteDataArticleRelationCategory(dto);
    }


    @Override
    public void editDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto){
        DataArticleRelationCategoryService.editDataArticleRelationCategory(eto);
    }

    @Override
    public DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto){
        return  DataArticleRelationCategoryService.detailDataArticleRelationCategory(dto);
    }

}