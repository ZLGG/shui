package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsCategory;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsCategoryView;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Starry
 * @Date 15:17 2020/9/27
 */
@Repository
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {

    /**
     * 分类id
     * @param id
     * @return
     */
    @Select("select \n" +
            "a.id as lev, a.gs_category_name as levName,\n"+
            "a.parent_id AS lev1Id, b.gs_category_name as lev1Name,\n" +
            "b.parent_id AS lev2Id, c.gs_category_name as lev2Name\n" +
            "from gs_goods_category a\n" +
            "LEFT JOIN gs_goods_category b ON a.parent_id  = b.id\n" +
            "LEFT JOIN gs_goods_category c ON b.parent_id  = c.id\n" +
            "where a.id = #{id}")
    GoodsCategoryVO.ParentCategoryVO selectParentCategoryVO(@Param("id") String id);

    /**
     * 查询符合条件的一级分类信息
     * @return
     */
    @Select("select DISTINCT c.*,GROUP_CONCAT(a.id) level3IdList from gs_goods_category a\n" +
            "INNER JOIN gs_goods_category b ON a.parent_id  = b.id\n" +
            "INNER JOIN gs_goods_category c ON b.parent_id  = c.id\n" +
            "and c.flag =0 AND a.flag =0 AND a.use_filed IN(10,20) AND b.flag =0\n" +
            "GROUP BY c.id  ORDER BY c.idx,c.id ")
    List<GoodsCategoryView> listLevelCategory();

    /**
     * 分类id
     * @param id
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "\ta.id \n" +
            "FROM\n" +
            "\tgs_goods_category a\n" +
            "\tLEFT JOIN gs_goods_category b ON a.parent_id = b.id\n" +
            "\tLEFT JOIN gs_goods_category c ON b.parent_id = c.id \n" +
            "WHERE\n" +
            "\tc.id = #{id} and a.flag = 0")
    List<String> selectLevel3CategoryList(@Param("id") String id);

    @Select("SELECT DISTINCT\n" +
            "\ta.id,a.parent_id,a.gs_category_name\n" +
            "FROM\n" +
            "\tgs_goods_category a\n" +
            "\tLEFT JOIN gs_goods_category b ON a.parent_id = b.id\n" +
            "\tLEFT JOIN gs_goods_category c ON b.parent_id = c.id \n" +
            "WHERE\n" +
            "\ta.flag = 0 AND ${ew.sqlSegment}")
    List<GoodsCategory> getLevel3CategoryList(@Param(Constants.WRAPPER) QueryWrapper<GoodsCategory> qw);

    @Select("select * from gs_goods_category c " +
            "left join gs_category_brand b on c.id = b.category_id  " +
            "where c.flag = 0 and b.flag = 0 AND ${ew.sqlSegment}")
    List<GoodsCategory> listCategoryForBrandId(@Param(Constants.WRAPPER) QueryWrapper<GoodsCategory> qw);
}
