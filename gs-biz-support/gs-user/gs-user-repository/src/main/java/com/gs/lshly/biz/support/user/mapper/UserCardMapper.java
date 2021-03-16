package com.gs.lshly.biz.support.user.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gs.lshly.biz.support.user.entity.UserCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserCardView;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 我的优惠卷 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-27
 */
@Repository
public interface UserCardMapper extends BaseMapper<UserCard> {

    @Select("")
    IPage<UserCardView> pageList(IPage<UserCardView> page, @Param(value = "ew") QueryWrapper<UserCardView> qw);


    @Select("SELECT count(1) count " +
            "FROM `gs_market_merchant_card_users` td " +
            "LEFT JOIN `gs_market_merchant_card` t ON t.id=td.`card_id` " +
            "WHERE td.`flag`=0 AND t.`flag`=0 AND ${ew.sqlSegment}")
    Integer countCard(@Param(Constants.WRAPPER)QueryWrapper<PCBbbMarketMerchantCardUsersQTO.QTO> qw);


}
