package com.gs.lshly.biz.support.commodity.mapper;

import com.gs.lshly.biz.support.commodity.entity.GoodsAuditRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-10-29
 */
public interface GoodsAuditRecordMapper extends BaseMapper<GoodsAuditRecord> {

    @Select(" select * from gs_goods_audit_record where flag = 0 and goods_id = #{id} order by cdate desc limit 1")
    GoodsAuditRecord getOneByGoodsId(@Param("id") String id);

}
