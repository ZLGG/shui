package com.gs.lshly.biz.support.commodity.service.bbc.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.commodity.entity.GoodsServe;
import com.gs.lshly.biz.support.commodity.entity.GoodsServeCor;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeCorMapper;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeRepository;
import com.gs.lshly.biz.support.commodity.service.bbc.IBbcGoodsServeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsServeQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BbcGoodsServeServiceImpl implements IBbcGoodsServeService {
    @Autowired
    private IGoodsServeRepository goodsServeRepository;
    @Autowired
    private IGoodsServeCorRepository goodsServeCorRepository;

    @Override
    public List<BbcGoodsServeVO.ListVO> getGoodsServeDetail(BbcGoodsServeQTO.GoodsInfoQTO qto) {
        if (ObjectUtil.isEmpty(qto) || StrUtil.isEmpty(qto.getId())) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsServeCor> query = MybatisPlusUtil.query();
        query.eq("goods_id", qto.getId());
        GoodsServeCor goodsServeCor = goodsServeCorRepository.getOne(query);
        List<String> serveIdList = StrUtil.split(goodsServeCor.getServeId(), ',');
        List<GoodsServe> goodsServeList = goodsServeRepository.list(Wrappers.<GoodsServe>lambdaQuery().in(GoodsServe::getId, serveIdList));
        List<BbcGoodsServeVO.ListVO> listVOS = new ArrayList<>();
        for (GoodsServe goodsServe : goodsServeList) {
            BbcGoodsServeVO.ListVO listVO = new BbcGoodsServeVO.ListVO();
            BeanUtil.copyProperties(goodsServe, listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }
}
