package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantArticleQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantArticleVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantArticleRpc;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantArticleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-11-09
*/
@DubboService
public class MerchantArticleRpc implements IMerchantArticleRpc{

    @Autowired
    private IMerchantArticleService  MerchantArticleService;

    @Override
    public PageData<MerchantArticleVO.ListVO> pageData(MerchantArticleQTO.QTO qto){
        return MerchantArticleService.pageData(qto);
    }

    @Override
    public void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto){
        MerchantArticleService.deleteBatchMerchantArticle(dto);
    }


    @Override
    public void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto){
        MerchantArticleService.applyMerchantArticle(dto);
    }

    @Override
    public MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto){
        return  MerchantArticleService.detailMerchantArticle(dto);
    }
}