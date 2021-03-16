package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountRoleView;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantApplyView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家入驻申请 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
@Repository
public interface MerchantApplyMapper extends BaseMapper<MerchantApply> {

    @Select("select distinct apply.*,acc.user_name merchant_account " +
            "from gs_merchant_apply apply " +
            "left join gs_merchant_account acc on apply.account_id = acc.id " +
            "WHERE apply.flag = 0 and ${ew.sqlSegment}")
    IPage<MerchantApplyView> mapperPageList(IPage<MerchantApplyView> page,@Param(value = "ew") QueryWrapper<MerchantApplyView> we);

}
