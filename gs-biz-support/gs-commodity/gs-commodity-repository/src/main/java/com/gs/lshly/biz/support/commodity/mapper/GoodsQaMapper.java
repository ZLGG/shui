package com.gs.lshly.biz.support.commodity.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.commodity.entity.GoodsQa;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaCountView;
import com.gs.lshly.biz.support.commodity.mapper.view.GoodsQaView;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsQaVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsQaVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Starry
 * @since 2020-10-16
 */
@Repository
public interface GoodsQaMapper extends BaseMapper<GoodsQa> {

    /**
     * 查询商品的咨询信息(商家端）
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gqa.*,gs.goods_name,gs.goods_image goodsImage FROM `gs_goods_info` gs\n" +
            "INNER JOIN gs_goods_qa gqa ON gs.id = gqa.good_id\n" +
            "WHERE gs.flag = 0 and gqa.flag = 0 AND ${ew.sqlSegment}")
    IPage<GoodsQaView> getGoodsQaListVO(IPage<GoodsQaView> page, @Param(Constants.WRAPPER) QueryWrapper<GoodsQaView> qw);


    /**
     * 查询商品的咨询信息(用户咨询中心）
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT DISTINCT gq.*,gs.goods_name,gs.goods_title,gs.goods_image FROM gs_goods_qa gq\n" +
            "LEFT JOIN gs_goods_info gs ON gq.good_id = gs.id\n" +
            "WHERE gq.flag = 0 AND ${ew.sqlSegment}")
    IPage<GoodsQaView> getUserGoodsQaListVO(IPage<GoodsQaView> page,@Param(Constants.WRAPPER) QueryWrapper<GoodsQaView> qw);

    /**
     * 统计商品问答的相关数量
     * @param qw
     * @return
     */
    @Select("SELECT\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( quiz_type = 10, id, NULL )) goodsQuizNum,\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( quiz_type = 20, id, NULL )) invoiceWarrantyNum,\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( quiz_type = 30, id, NULL )) paymentWayNum,\n" +
            "\tCOUNT(\n" +
            "\tIF\n" +
            "\t( quiz_type = 40, id, NULL )) inventoryDistributionNum,\n" +
            "\tCOUNT( DISTINCT id ) allNum \n" +
            "FROM\n" +
            "\tgs_goods_qa WHERE flag = 0 AND ${ew.sqlSegment}")
    GoodsQaCountView countView(@Param(Constants.WRAPPER) QueryWrapper<GoodsQa> qw);
}
