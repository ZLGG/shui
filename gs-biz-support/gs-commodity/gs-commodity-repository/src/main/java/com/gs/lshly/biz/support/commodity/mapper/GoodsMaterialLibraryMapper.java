package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsMaterialLibrary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsMaterialLibraryView;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsMaterialLibraryVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-12-10
 */
@Repository
public interface GoodsMaterialLibraryMapper extends BaseMapper<GoodsMaterialLibrary> {

    /**
     * 查询平台商品素材库信息
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gml.id,gml.category_id,gml.brand_id,gml.goods_name,gml.goods_title," +
            "gc.gs_category_name,gb.brand_name FROM gs_goods_material_library gml\n" +
            "LEFT JOIN gs_goods_category gc ON gml.category_id = gc.id\n" +
            "LEFT JOIN gs_goods_brand gb ON gml.brand_id = gb.id\n" +
            "WHERE gml.flag =0 AND ${ew.sqlSegment} ")
    IPage<GoodsMaterialLibraryView> pageData(IPage<GoodsMaterialLibraryView> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsMaterialLibraryView> qw);

    @Select("SELECT DISTINCT gml.*,gc.gs_category_name,gb.brand_name FROM gs_goods_material_library gml\n" +
            "LEFT JOIN gs_goods_category gc ON gml.category_id = gc.id\n" +
            "LEFT JOIN gs_goods_brand gb ON gml.brand_id = gb.id\n" +
            "WHERE gml.flag =0 AND ${ew.sqlSegment} ")
    List<GoodsMaterialLibraryView> listData(@Param(Constants.WRAPPER) QueryWrapper<GoodsMaterialLibraryView> qw);

    /**
     * 获取导出数据
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "gml.id,gml.category_id,gml.brand_id,gml.goods_name,gml.goods_title," +
            "GROUP_CONCAT(gmlg.image_url) goodsImgUrl," +
            "gc.gs_category_name," +
            "gb.brand_name \n " +
            "FROM\n" +
            "gs_goods_material_library gml \n" +
            "LEFT JOIN gs_goods_material_library_img gmlg ON gml.id = gmlg.material_library_id \n" +
            "LEFT JOIN gs_goods_category gc ON gml.category_id = gc.id \n" +
            "LEFT JOIN gs_goods_brand gb ON gml.brand_id = gb.id\n " +
            "WHERE\n" +
            "gml.flag = 0 GROUP BY gml.id,gmlg.id,gc.id,gb.id")
    List<GoodsMaterialLibraryVO.exportDataVO> getExportData();

}
