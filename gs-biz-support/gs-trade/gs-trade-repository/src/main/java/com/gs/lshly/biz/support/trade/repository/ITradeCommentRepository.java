package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbOrderVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 交易评论表 服务类
 * </p>
 *
 * @author oy
 * @since 2020-11-06
 */
public interface ITradeCommentRepository extends IService<TradeComment> {

    IPage<PCMerchTradeCommentVO.CommentListListVO> selectListPage(IPage<PCMerchTradeCommentVO.CommentListListVO> pager, @Param(Constants.WRAPPER)QueryWrapper<PCMerchTradeCommentQTO.QTO> qw);
    List<BbbOrderVO.InnerCompareTo> selectCompareTo(@Param(Constants.WRAPPER)QueryWrapper<BbbOrderDTO.innerCommpareTo> qw);

    List<BbbH5TradeListVO.InnerCompareTo> selectBbbH5CompareTo(@Param(Constants.WRAPPER)QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw);
    List<BbbOrderVO.InnerCompareToCount> selectCompareToCount(@Param(Constants.WRAPPER)QueryWrapper<BbbOrderDTO.innerCommpareTo> qw);
    List<BbbH5TradeListVO.InnerCompareToCount> selectBbbH5CompareToCount(@Param(Constants.WRAPPER)QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw);
    List<BbcTradeVO.InnerCompareTo> selectBbcCompareTo(@Param(Constants.WRAPPER)QueryWrapper<BbcTradeDTO.innerCommpareTo> qw);
    List<BbcTradeVO.InnerCompareToCount> selectBbcCompareToCount(@Param(Constants.WRAPPER)QueryWrapper<BbcTradeDTO.innerCommpareTo> qw);
}
