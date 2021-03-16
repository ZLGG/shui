package com.gs.lshly.biz.support.foundation.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleVO;
import com.gs.lshly.rpc.api.platadmin.foundation.IMerchantArticleRpc;
import com.gs.lshly.biz.support.foundation.service.platadmin.IMerchantArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author hyy
* @since 2020-10-29
*/
@DubboService
public class MerchantArticleRpc implements IMerchantArticleRpc{
    @Autowired
    private IMerchantArticleService  MerchantArticleService;

    @Override
    public PageData<MerchantArticleVO.ListVO> pageData(Integer pageNum,Integer pageSize ){
        return MerchantArticleService.pageData(pageNum,pageSize);
    }

    @Override
    public MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto){
        return  MerchantArticleService.detailMerchantArticle(dto);
    }

    @Override
    public void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto) {
           MerchantArticleService.deleteBatchMerchantArticle(dto);
    }

    @Override
    public void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto) {
        MerchantArticleService.applyMerchantArticle(dto);
    }

}