package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsSearchHistory;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/4/16 15:17
 */
@Repository
public interface GoodsSearchHistoryMapper extends BaseMapper<GoodsSearchHistory> {

    /**
     * 查询历史搜索记录
     * @param wrapper
     * @return
     */
    @Select("select keyword from gs_goods_search_history where ${ew.sqlSegment}")
    List<String> getSearchHistory(@Param(Constants.WRAPPER) QueryWrapper<GoodsSearchHistory> wrapper);

    /**
     * 清空历史搜索记录
     * @param wrapper
     */
    @Update("update gs_goods_search_history set flag = 1, udate = now() where ${ew.sqlSegment}")
    void emptySearchHistory(@Param(Constants.WRAPPER) QueryWrapper<GoodsSearchHistory> wrapper);
}
