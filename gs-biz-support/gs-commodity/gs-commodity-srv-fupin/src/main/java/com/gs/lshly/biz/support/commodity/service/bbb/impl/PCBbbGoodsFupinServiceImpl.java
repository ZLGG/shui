package com.gs.lshly.biz.support.commodity.service.bbb.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupin;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinRepository;
import com.gs.lshly.biz.support.commodity.service.bbb.IPCBbbGoodsFupinService;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.pc.commodityfupin.vo.PCBbbGoodsFupinVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-09
*/
@Component
public class PCBbbGoodsFupinServiceImpl implements IPCBbbGoodsFupinService {

    @Autowired
    private IGoodsFupinRepository repository;


    @Override
    public PCBbbGoodsFupinVO.GoodsIdVO getGoodsIdVO(BaseQTO qto) {
        QueryWrapper<GoodsFupin> wrapper = MybatisPlusUtil.query();
        List<GoodsFupin> fupinList = repository.list(wrapper);
        if (ObjectUtils.isEmpty(fupinList)){
            return null;
        }
        PCBbbGoodsFupinVO.GoodsIdVO goodsIdVO = new PCBbbGoodsFupinVO.GoodsIdVO();
        List<String> idList = ListUtil.getIdList(GoodsFupin.class,fupinList,"goodsId");
        goodsIdVO.setGoodsId(idList);
        return goodsIdVO;
    }
}
