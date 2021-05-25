package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsServe;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsServeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsServeDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsServeQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author hanly
 */
@Component
public class GoodsServeServiceImpl implements IGoodsServeService {

    @Autowired
    private IGoodsServeRepository repository;

    /**
     * 分页查询商品服务列表
     *
     * @param qto
     * @return
     */
    @Override
    public PageData<GoodsServeVO.ListVO> pageGoodsServeData(GoodsServeQTO.QTO qto) {
        QueryWrapper<GoodsServe> query = MybatisPlusUtil.query();
        if (StrUtil.isNotEmpty(qto.getServeName())) {
            query.like("serveName", qto.getServeName());
        }
        if (StrUtil.isNotEmpty(qto.getServeContext())) {
            query.like("serveContext", qto.getServeContext());
        }
        query.orderByDesc("cdate");
        IPage page = MybatisPlusUtil.pager(qto);
        repository.page(page, query);
        return MybatisPlusUtil.toPageData(qto, GoodsServeVO.ListVO.class, page);
    }

    /**
     * 查询商品详情
     *
     * @param dto
     * @return
     */
    @Override
    public GoodsServeVO.ListVO getGoodsServeDetail(GoodsServeDTO.IdDTO dto) {
        if (StrUtil.isEmpty(dto.getId())) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsServe serve = repository.getById(dto);
        if (ObjectUtil.isEmpty(serve)) {
            throw new BusinessException("查询异常,数据不存在！");
        }
        GoodsServeVO.ListVO listVO = new GoodsServeVO.ListVO();
        BeanUtils.copyProperties(serve, listVO);
        return listVO;
    }

    /**
     * 新增服务
     *
     * @param eto
     */
    @Override
    public void addGoodsServe(GoodsServeDTO.ETO eto) {
        if (!StrUtil.isAllNotEmpty(eto.getServeName(), eto.getServeContext(), eto.getImageUrl())) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsServe goodsServe = new GoodsServe();
        BeanUtils.copyProperties(eto, goodsServe);
        repository.save(goodsServe);
    }

    /**
     * 修改服务信息
     *
     * @param eto
     */
    @Override
    public void editGoodsServe(GoodsServeDTO.ETO eto) {
        if (!StrUtil.isAllNotEmpty(eto.getServeName(), eto.getServeContext(), eto.getImageUrl())) {
            throw new BusinessException("参数不能为空！");
        }
        GoodsServe goodsServe = new GoodsServe();
        BeanUtils.copyProperties(eto, goodsServe);
        repository.updateById(goodsServe);
    }

    /**
     * 删除服务
     *
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsServe(GoodsServeDTO.IdListDTO dto) {
        if (ObjectUtil.isEmpty(dto) && CollUtil.isEmpty(dto.getIdList()) && dto.getIdList().size() == 0) {
            throw new BusinessException("参数不能为空！");
        }
        //todo 什么能删,什么不能删
        for (String id : dto.getIdList()) {
            repository.removeById(id);
        }
    }
}
