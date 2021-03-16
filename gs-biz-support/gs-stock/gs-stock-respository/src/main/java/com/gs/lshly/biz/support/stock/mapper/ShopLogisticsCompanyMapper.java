package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

public interface ShopLogisticsCompanyMapper extends BaseMapper {

    /**
     * 保存店铺和使用物流公司的关联
     * @param logisticsCorpId
     * @param shopId
     * @return
     */
    @Insert("INSERT INTO gs_shop_relation_logistics_corp (logistics_corp_id, shop_id) VALUES (#{logisticsCorpId}, #{shopId})")
    int relateLogisticsCompany(@Param("logisticsCorpId")String logisticsCorpId, @Param("shopId")String shopId);

    /**
     * 删除店铺和使用物流公司的一个关联
     * @param logisticsCorpId
     * @param shopId
     * @return
     */
    @Delete("DELETE FROM gs_shop_relation_logistics_corp WHERE shop_id=#{shopId} AND logistics_corp_id=#{logisticsCorpId}")
    int deleteRelationLogisticsCompany(@Param("logisticsCorpId")String logisticsCorpId, @Param("shopId")String shopId);


    /**
     * 获取店铺关联的使用物流公司的ID
     * @param shopId
     * @return
     */
    @Select("SELECT logistics_corp_id FROM gs_shop_relation_logistics_corp WHERE shop_id=#{shopId}")
    Set<String> getRelationLogisticsCompanyIds(@Param("shopId")String shopId);

}
