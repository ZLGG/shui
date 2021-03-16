package com.gs.lshly.biz.support.merchant.mapper.views;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import lombok.Data;

@Data
public class MerchantArticleView extends MerchantArticle {

    private String categoryName;

    private String shopName;

}
