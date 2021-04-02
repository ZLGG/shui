package com.gs.lshly.biz.support.trade.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.mapper.view.TradeAppealCommentView;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCommentQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCommentListVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCommentQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbOrderVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeCommentListVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCommentQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCommentListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 交易评论表 Mapper 接口
 * </p>
 *
 * @author oy
 * @since 2020-11-06
 */
@Repository
public interface TradeCommentMapper extends BaseMapper<TradeComment> {

    @Select("SELECT c.* FROM `gs_trade_comment` c WHERE c.`flag` = 0 AND ${ew.sqlSegment}")
    IPage<BbcTradeCommentListVO.ListVO> orderCommentPage(IPage<BbcTradeCommentListVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbcTradeCommentQTO.CommentList> qw);
    @Select("SELECT c.* FROM `gs_trade_comment` c WHERE c.`flag` = 0 AND ${ew.sqlSegment}")
    IPage<BbbH5TradeCommentListVO.ListVO> orderBbbH5CommentPage(IPage<BbbH5TradeCommentListVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeCommentQTO.CommentList> qw);
    @Select("SELECT c.*,t.`comment_flag` comment_flag  FROM `gs_trade_comment`  c   \n" +
            " LEFT JOIN gs_trade_goods t ON c.trade_goods_id = t.id\n" +
            " WHERE c.`flag` = 0 AND t.`flag` = 0 AND ${ew.sqlSegment}")
    IPage<BbbTradeCommentListVO.ListVO> orderBbbCommentPage(IPage<BbbTradeCommentListVO.ListVO> page, @Param(Constants.WRAPPER) QueryWrapper<BbbTradeCommentQTO.CommentList> qw);

    /**
     * 商家申诉列表
     * @param page
     * @param qw
     * @return
     */
    @Select("SELECT \n" +
            "\tDISTINCT \n" +
            "\t\ttc.id,tc.cdate,\n" +
            "\t\t(CASE tc.describe_grade WHEN 5 THEN '好评' WHEN 4 THEN '中评' WHEN 3 THEN '中评' ELSE '差评' END) AS commentLevel,\n" +
            "\t\ttc.user_name userName,\n" +
            "\t\t(CASE tc.appeal_count WHEN 1 THEN '一次申诉' WHEN 2 THEN '再次申诉' END) AS progress,\n" +
            "\t\ttc.appeal_state appealState,\n" +
            "\t\tIF(tc.appeal_count = 1 AND tc.appeal_state = 30,TRUE,FALSE) AS openReAppeal,\n" +
            "\t\tt.trade_code tradeCode,\n" +
            "\t\ttg.goods_name AS goodsName,\n" +
            "\t\tt.goods_amount AS salePrice\n" +
            "FROM gs_trade_comment tc\n" +
            "LEFT JOIN gs_trade t ON tc.trade_id = t.id\n" +
            "LEFT JOIN gs_trade_goods tg ON  tc.trade_goods_id = tg.id AND t.id = tg.trade_id\n" +
            "WHERE\n" +
            "\ttc.flag = 0 AND t.flag = 0  AND tg.flag = 0 AND ${ew.sqlSegment}")
    IPage<TradeAppealCommentView> pageCommentAppeal(IPage<TradeAppealCommentView> page, @Param(Constants.WRAPPER) QueryWrapper<TradeAppealCommentView> qw);
    @Select("SELECT tc.*,tg.goods_name goods_name,tg.sale_price sale_price,tg.quantity quantity FROM gs_trade_comment tc \n" +
            "left JOIN gs_trade gt ON tc.trade_id = gt.id\n" +
            "LEFT JOIN gs_trade_goods tg ON gt.id = tg.trade_id AND tc.goods_id=tg.goods_id\n" +
            "WHERE tc.flag = 0 AND gt.flag = 0 AND tg.flag = 0  AND ${ew.sqlSegment}")
    IPage<PCMerchTradeCommentVO.CommentListListVO> selectListPage(IPage<PCMerchTradeCommentVO.CommentListListVO> pager,@Param(Constants.WRAPPER) QueryWrapper<PCMerchTradeCommentQTO.QTO> qw);
    @Select("select trade_goods_id id,avg(describe_grade) grade FROM gs_trade_comment where flag= 0  AND ${ew.sqlSegment}")
    List<BbbOrderVO.InnerCompareTo> selectCompareTo(@Param(Constants.WRAPPER)QueryWrapper<BbbOrderDTO.innerCommpareTo> qw);
    @Select("SELECT tg.goods_id id,SUM(IF(t.trade_state = 40,tg.quantity,0))  count FROM gs_trade_goods tg LEFT JOIN gs_trade t ON tg.trade_id=t.id where tg.flag=0 AND t.flag=0 AND ${ew.sqlSegment} GROUP BY tg.goods_id")
    List<BbbOrderVO.InnerCompareToCount> selectCompareToCount(@Param(Constants.WRAPPER)QueryWrapper<BbbOrderDTO.innerCommpareTo> qw);
    @Select("select trade_goods_id id,avg(describe_grade) grade FROM gs_trade_comment where flag= 0  AND ${ew.sqlSegment}")
    List<BbcTradeVO.InnerCompareTo> selectBbcCompareTo(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeDTO.innerCommpareTo> qw);
    @Select("SELECT tg.goods_id id,SUM(IF(t.trade_state = 40,tg.quantity,0))  count FROM gs_trade_goods tg LEFT JOIN gs_trade t ON tg.trade_id=t.id where tg.flag=0 AND t.flag=0 AND ${ew.sqlSegment} GROUP BY tg.goods_id")
    List<BbcTradeVO.InnerCompareToCount> selectBbcCompareToCount(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeDTO.innerCommpareTo> qw);
    @Select("select trade_goods_id id,avg(describe_grade) grade FROM gs_trade_comment where flag= 0  AND ${ew.sqlSegment}")
    List<BbbH5TradeListVO.InnerCompareTo> selectBbbH5CompareTo(@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw);
    @Select("SELECT tg.goods_id id,SUM(IF(t.trade_state = 40,tg.quantity,0))  count FROM gs_trade_goods tg LEFT JOIN gs_trade t ON tg.trade_id=t.id where tg.flag=0 AND t.flag=0 AND ${ew.sqlSegment} GROUP BY tg.goods_id")
    List<BbbH5TradeListVO.InnerCompareToCount> selectBbbH5CompareToCount(@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw);
}
