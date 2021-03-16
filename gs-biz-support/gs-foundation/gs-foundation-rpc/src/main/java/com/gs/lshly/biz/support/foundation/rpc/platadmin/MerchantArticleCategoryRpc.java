package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleCategoryVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IMerchantArticleCategoryRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.IMerchantArticleCategoryService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-10-29
*/
@DubboService
public class MerchantArticleCategoryRpc implements IMerchantArticleCategoryRpc{
    @Autowired
    private IMerchantArticleCategoryService  MerchantArticleCategoryService;

    @Override
    public PageData<MerchantArticleCategoryVO.ListVO> pageData(MerchantArticleCategoryQTO.QTO qto){
        return MerchantArticleCategoryService.pageData(qto);
    }

    @Override
    public void addMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto){
        MerchantArticleCategoryService.addMerchantArticleCategory(eto);
    }

    @Override
    public void deleteMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto){
        MerchantArticleCategoryService.deleteMerchantArticleCategory(dto);
    }


    @Override
    public void editMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto){
        MerchantArticleCategoryService.editMerchantArticleCategory(eto);
    }

    @Override
    public MerchantArticleCategoryVO.DetailVO detailMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto){
        return  MerchantArticleCategoryService.detailMerchantArticleCategory(dto);
    }

}