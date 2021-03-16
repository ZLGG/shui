package com.gs.lshly.biz.support.foundation.mapper;

import com.gs.lshly.biz.support.foundation.entity.SiteCustomerService;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 站点平台在线客服 Mapper 接口
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-12
 */
public interface SiteCustomerServiceMapper extends BaseMapper<SiteCustomerService> {

    @Select("SELECT  id,state,type,account,phone,phone_state,cdate,udate,flag  FROM gs_site_customer_service")
    List<SiteCustomerService> siteCustomerServiceList();

}
