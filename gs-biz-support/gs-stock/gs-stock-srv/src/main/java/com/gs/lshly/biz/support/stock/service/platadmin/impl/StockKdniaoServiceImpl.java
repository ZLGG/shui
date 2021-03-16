package com.gs.lshly.biz.support.stock.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockKdniao;
import com.gs.lshly.biz.support.stock.repository.IStockKdniaoRepository;
import com.gs.lshly.biz.support.stock.service.platadmin.IStockKdniaoService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.qto.StockKdniaoQTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.StockKdniaoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zst
* @since 2021-01-29
*/
@Component
public class StockKdniaoServiceImpl implements IStockKdniaoService {

    @Autowired
    private IStockKdniaoRepository repository;

    @Override
    public PageData<StockKdniaoVO.ListVO> pageData(StockKdniaoQTO.QTO qto) {
        QueryWrapper<StockKdniao> wrapper = new QueryWrapper<>();
        IPage<StockKdniao> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, StockKdniaoVO.ListVO.class, page);
    }

    @Override
    public void addStockKdniao(StockKdniaoDTO.ETO eto) {
        QueryWrapper<StockKdniao> wrapper = MybatisPlusUtil.query();
        StockKdniao stockKdniao = repository.getOne(wrapper);
        if(null != stockKdniao){
            if(!stockKdniao.getId().equals(eto.getId())){
                throw new BusinessException("不能添加多条数据");
            }
        }
        StockKdniao stockKdniao1 = new StockKdniao();
        BeanUtils.copyProperties(eto, stockKdniao1);
        repository.saveOrUpdate(stockKdniao1);
    }


    @Override
    public void deleteStockKdniao(StockKdniaoDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editStockKdniao(StockKdniaoDTO.ETO eto) {
        StockKdniao stockKdniao = new StockKdniao();
        BeanUtils.copyProperties(eto, stockKdniao);
        repository.updateById(stockKdniao);
    }

    @Override
    public StockKdniaoVO.DetailVO detailStockKdniao(StockKdniaoDTO.IdDTO dto) {
        StockKdniao stockKdniao = repository.getById(dto.getId());
        StockKdniaoVO.DetailVO detailVo = new StockKdniaoVO.DetailVO();
        if(ObjectUtils.isEmpty(stockKdniao)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(stockKdniao, detailVo);
        return detailVo;
    }

}
