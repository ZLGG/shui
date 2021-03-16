package com.gs.lshly.biz.support.merchant.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.merchant.entity.QianmiToken;
import com.gs.lshly.biz.support.merchant.entity.ShopFloor;
import com.gs.lshly.biz.support.merchant.mapper.QianmiTokenMapper;
import com.gs.lshly.biz.support.merchant.mapper.ShopFloorMapper;
import com.gs.lshly.biz.support.merchant.repository.IQianmiTokenRepository;
import com.gs.lshly.biz.support.merchant.repository.IShopFloorRepository;
import org.springframework.stereotype.Service;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author lxus
 * @since 2020-11-24
*/
@Service
public class QianmiRepositoryImpl extends ServiceImpl<QianmiTokenMapper, QianmiToken> implements IQianmiTokenRepository {

}