package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCardUsersMapper;
import com.gs.lshly.biz.support.trade.repository.IMarketMerchantCardUsersRepository;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5MarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5MarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author zdf
 * @since 2021-01-08
*/
@Service
public class MarketMerchantCardUsersRepositoryImpl extends ServiceImpl<MarketMerchantCardUsersMapper, MarketMerchantCardUsers> implements IMarketMerchantCardUsersRepository {
    @Autowired
    private MarketMerchantCardUsersMapper marketMerchantCardUsersMapper;
    @Override
    public IPage<PCBbbMarketMerchantCardUsersVO.PageData> selectListPage(IPage<PCBbbMarketMerchantCardUsersVO.PageData> page, QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> qw) {
        return marketMerchantCardUsersMapper.selectListPage(page,qw);
    }

    @Override
    public List<BbbTradeListVO.UseCard> selectUseCard(QueryWrapper<BbbOrderDTO.UseCard> qw) {
        return marketMerchantCardUsersMapper.selectUseCard(qw);
    }

    @Override
    public List<BbcTradeListVO.UseCard> selectBbcUseCard(QueryWrapper<BbcTradeDTO.UseCard> qw) {
        return marketMerchantCardUsersMapper.selectBbcUseCard(qw);
    }

    @Override
    public List<BbbH5TradeListVO.UseCard> selectH5UseCard(QueryWrapper<BbbH5TradeDTO.UseCard> qw) {
        return marketMerchantCardUsersMapper.selectH5UseCard(qw);
    }

    @Override
    public IPage<BbbH5MarketMerchantCardUsersVO.PageData> selectH5ListPage(IPage<BbbH5MarketMerchantCardUsersVO.PageData> page, QueryWrapper<BbbH5MarketMerchantCardUsersQTO.QTO> qw) {
        return marketMerchantCardUsersMapper.selectH5ListPage(page,qw);
    }

    @Override
    public IPage<BbcTradeListVO.PageData> selectBbcListPage(IPage<BbcTradeListVO.PageData> pager, QueryWrapper<BbcTradeQTO.UserCardQTO> qw) {
        return marketMerchantCardUsersMapper.selectBbcListPage(pager,qw);
    }

    @Override
    public MarketMerchantCardUsersMapper baseMapper() {
        return getBaseMapper();
    }
}