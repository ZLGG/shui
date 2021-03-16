package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.TradeComment;
import com.gs.lshly.biz.support.trade.mapper.TradeCommentMapper;
import com.gs.lshly.biz.support.trade.repository.ITradeCommentRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbOrderVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeCommentQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author oy
 * @since 2020-11-06
*/
@Service
public class TradeCommentRepositoryImpl extends ServiceImpl<TradeCommentMapper, TradeComment> implements ITradeCommentRepository {
    @Autowired
    private TradeCommentMapper tradeCommentMapper;

    @Override
    public IPage<PCMerchTradeCommentVO.CommentListListVO> selectListPage(IPage<PCMerchTradeCommentVO.CommentListListVO> pager, QueryWrapper<PCMerchTradeCommentQTO.QTO> qw) {
        return tradeCommentMapper.selectListPage(pager,qw);
    }

    @Override
    public List<BbbOrderVO.InnerCompareTo> selectCompareTo(QueryWrapper<BbbOrderDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectCompareTo(qw);
    }

    @Override
    public List<BbbH5TradeListVO.InnerCompareTo> selectBbbH5CompareTo(QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectBbbH5CompareTo(qw);
    }

    @Override
    public List<BbbOrderVO.InnerCompareToCount> selectCompareToCount(QueryWrapper<BbbOrderDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectCompareToCount(qw);
    }

    @Override
    public List<BbbH5TradeListVO.InnerCompareToCount> selectBbbH5CompareToCount(QueryWrapper<BbbH5TradeDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectBbbH5CompareToCount(qw);
    }

    @Override
    public List<BbcTradeVO.InnerCompareTo> selectBbcCompareTo(QueryWrapper<BbcTradeDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectBbcCompareTo(qw);
    }

    @Override
    public List<BbcTradeVO.InnerCompareToCount> selectBbcCompareToCount(QueryWrapper<BbcTradeDTO.innerCommpareTo> qw) {
        return tradeCommentMapper.selectBbcCompareToCount(qw);
    }
}