package com.gs.lshly.biz.support.trade.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.trade.entity.MarketMerchantCardUsers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gs.lshly.biz.support.trade.mapper.MarketMerchantCardUsersMapper;
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
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商家优惠卷会员令取记录 服务类
 * </p>
 *
 * @author zdf
 * @since 2021-01-08
 */
public interface IMarketMerchantCardUsersRepository extends IService<MarketMerchantCardUsers> {
    IPage<PCBbbMarketMerchantCardUsersVO.PageData> selectListPage(IPage<PCBbbMarketMerchantCardUsersVO.PageData> page, @Param(Constants.WRAPPER) QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> qw);
    List<BbbTradeListVO.UseCard> selectUseCard(@Param(Constants.WRAPPER) QueryWrapper<BbbOrderDTO.UseCard> qw);
    List<BbcTradeListVO.UseCard> selectBbcUseCard(@Param(Constants.WRAPPER) QueryWrapper<BbcTradeDTO.UseCard> qw);
    List<BbbH5TradeListVO.UseCard> selectH5UseCard(@Param(Constants.WRAPPER) QueryWrapper<BbbH5TradeDTO.UseCard> qw);
    IPage<BbbH5MarketMerchantCardUsersVO.PageData> selectH5ListPage(IPage<BbbH5MarketMerchantCardUsersVO.PageData> page, @Param(Constants.WRAPPER) QueryWrapper<BbbH5MarketMerchantCardUsersQTO.QTO> qw);
    IPage<BbcTradeListVO.PageData> selectBbcListPage(IPage<BbcTradeListVO.PageData> pager,@Param(Constants.WRAPPER) QueryWrapper<BbcTradeQTO.UserCardQTO> qw);

    MarketMerchantCardUsersMapper baseMapper();
}
