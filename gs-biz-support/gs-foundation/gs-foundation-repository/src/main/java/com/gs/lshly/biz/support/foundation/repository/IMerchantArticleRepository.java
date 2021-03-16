package com.gs.lshly.biz.support.foundation.repository;

import com.gs.lshly.biz.support.foundation.entity.MerchantArticle;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商家文章 服务类
 * </p>
 *
 * @author hyy
 * @since 2020-10-29
 */
public interface IMerchantArticleRepository extends IService<MerchantArticle> {

    boolean checkIdListExist(List<String> idList);
}
