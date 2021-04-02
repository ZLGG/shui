package com.gs.lshly.biz.support.merchant.rpc.common;

import com.gs.lshly.biz.support.merchant.service.common.ICommonMerchantArticleService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantArticleVO;
import com.gs.lshly.rpc.api.common.ICommonMerchantArticleRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Starry
 * @Date 17:57 2021/3/21
 */
@DubboService
public class CommonMerchantArticleRpc  implements ICommonMerchantArticleRpc {

    @Autowired
    private ICommonMerchantArticleService commonMerchantArticleService;

    @Override
    public PageData<CommonMerchantArticleVO.ListVO> pageMerchantArticleVO(CommonMerchantArticleQTO.QTO qto) {
        return commonMerchantArticleService.pageMerchantArticleVO(qto);
    }

    @Override
    public CommonMerchantArticleVO.DetailVO detailMerchantArticle(CommonMerchantArticleDTO.IdDTO dto) {
        return commonMerchantArticleService.detailMerchantArticle(dto);
    }
}
