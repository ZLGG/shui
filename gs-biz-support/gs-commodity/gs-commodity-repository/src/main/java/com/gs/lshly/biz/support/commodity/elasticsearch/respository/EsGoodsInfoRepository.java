package com.gs.lshly.biz.support.commodity.elasticsearch.respository;

import com.gs.lshly.biz.support.commodity.elasticsearch.document.EsGoodsInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * @author lxus
 * @since 2021-05-19
 */
public interface EsGoodsInfoRepository extends ElasticsearchRepository<EsGoodsInfo, String> {
    /**
     * 搜索查询
     *
     * @param goodsName              商品名称
     * @param goodsTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsGoodsInfo> findByGoodsNameOrGoodsTitle(String goodsName, String goodsTitle, Pageable page);

}
