package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.gs.lshly.biz.support.commodity.entity.GoodsServe;
import com.gs.lshly.biz.support.commodity.entity.GoodsServeCor;
import com.gs.lshly.biz.support.commodity.entity.GoodsServeCorTemp;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeCorMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorTempRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsServeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsServeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsServeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsServeVO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hanly
 */
@Component
public class PCMerchGoodsServeServiceImpl implements IPCMerchGoodsServeService {

    @Autowired
    private IGoodsServeRepository repository;

    @Autowired
    private IGoodsServeCorRepository goodsServeCorRepository;

    @Autowired
    private IGoodsServeCorTempRepository goodsServeCorTempRepository;

    @Override
    public PageData<PCMerchGoodsServeVO.ListVO> pageGoodsServeData(PCMerchGoodsServeQTO.QTO qto) {
        QueryWrapper<GoodsServe> query = MybatisPlusUtil.query();
        query.orderByDesc("cdate");
        IPage page = MybatisPlusUtil.pager(qto);
        repository.page(page, query);
        return MybatisPlusUtil.toPageData(qto, GoodsServeVO.ListVO.class, page);
    }

    @Override
    public List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetail(PCMerchGoodsServeDTO.IdDTO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsServeCor> query = MybatisPlusUtil.query();
        query.eq("goods_id", dto.getId());
        GoodsServeCor goodsServeCor = goodsServeCorRepository.getOne(query);
        if (ObjectUtil.isEmpty(goodsServeCor)) {
            return null;
        }
        List<PCMerchGoodsServeVO.ListVO> listVOS = new ArrayList<>();
        if(ObjectUtil.isEmpty(goodsServeCor.getServeId())){
            return listVOS;
        }
        List<String> serveIdList = StrUtil.split(goodsServeCor.getServeId(), ',');
        List<GoodsServe> goodsServeList = repository.list(Wrappers.<GoodsServe>lambdaQuery().in(GoodsServe::getId, serveIdList));
        if (CollUtil.isEmpty(goodsServeList)) {
            return null;
        }
        for (GoodsServe goodsServe : goodsServeList) {
            PCMerchGoodsServeVO.ListVO listVO = new PCMerchGoodsServeVO.ListVO();
            BeanUtil.copyProperties(goodsServe, listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }

    @Override
    public List<PCMerchGoodsServeVO.ListVO> getGoodsServeDetailTemp(PCMerchGoodsServeDTO.IdDTO dto) {
        if (ObjectUtil.isEmpty(dto) || StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsServeCorTemp> query = MybatisPlusUtil.query();
        query.eq("goods_id", dto.getId());
        GoodsServeCorTemp goodsServeCor = goodsServeCorTempRepository.getOne(query);
        if (ObjectUtil.isEmpty(goodsServeCor)) {
            return null;
        }
        List<PCMerchGoodsServeVO.ListVO> listVOS = new ArrayList<>();
        if(ObjectUtil.isEmpty(goodsServeCor.getServeId())){
            return listVOS;
        }
        List<String> serveIdList = StrUtil.split(goodsServeCor.getServeId(), ',');

        List<GoodsServe> goodsServeList = repository.list(Wrappers.<GoodsServe>lambdaQuery().in(GoodsServe::getId, serveIdList));
        if (CollUtil.isEmpty(goodsServeList)) {
            return null;
        }

        for (GoodsServe goodsServe : goodsServeList) {
            PCMerchGoodsServeVO.ListVO listVO = new PCMerchGoodsServeVO.ListVO();
            BeanUtil.copyProperties(goodsServe, listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }
}
