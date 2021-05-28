package com.gs.lshly.biz.support.commodity.elasticsearch.document;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2021-05-19
 */
@Document(indexName = "goods", type = "spu")
@Data
@Accessors(chain = true)
public class EsGoodsInfo implements Serializable {

    /**
     * 商品id
     */
    @Id
    private String id;

    /**
     * pos店铺spuID
     */
    @Field(type = FieldType.Keyword)
    private String posSpuId;

    /**
     * 商家id
     */
    @Field(type = FieldType.Keyword)
    private String merchantId;

    /**
     * 店铺id
     */
    @Field(type = FieldType.Keyword)
    private String shopId;

    /**
     * 品牌id
     */
    @Field(type = FieldType.Keyword)
    private String brandId;

    /**
     * 类目id
     */
    @Field(type = FieldType.Keyword)
    private String categoryId;

    /**
     * 商品名称
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String goodsName;

    /**
     * 商品标题
     */
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String goodsTitle;

    /**
     * 商品状态
     */
    @Field(type = FieldType.Keyword)
    private Integer goodsState;

    /**
     * 商品编号
     */
    @Field(type = FieldType.Keyword)
    private String goodsNo;

}
