package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsParamInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsParamInfoRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsParamInfoService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsParamInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsParamInfoVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-09-29
*/
@Component
public class GoodsParamInfoServiceImpl implements IGoodsParamInfoService {

    @Autowired
    private IGoodsParamInfoRepository repository;

    @Override
    public PageData<GoodsParamInfoVO.ListVO> pageData(GoodsParamInfoQTO.QTO qto) {
        QueryWrapper<GoodsParamInfo> wq = new QueryWrapper<>();
        IPage<GoodsParamInfo> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, GoodsParamInfoVO.ListVO.class, page);
    }

    @Override
    public void addGoodsParamInfo(GoodsParamInfoDTO.ETO eto) {
        GoodsParamInfo goodsParamInfo = new GoodsParamInfo();
        BeanUtils.copyProperties(eto, goodsParamInfo);
        repository.save(goodsParamInfo);
    }


    @Override
    public void deleteGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }


    @Override
    public void editGoodsParamInfo(GoodsParamInfoDTO.ETO eto) {
        GoodsParamInfo goodsParamInfo = new GoodsParamInfo();
        BeanUtils.copyProperties(eto, goodsParamInfo);
        repository.updateById(goodsParamInfo);
    }

    @Override
    public GoodsParamInfoVO.DetailVO detailGoodsParamInfo(GoodsParamInfoDTO.IdDTO dto) {
        GoodsParamInfo goodsParamInfo = repository.getById(dto.getId());
        GoodsParamInfoVO.DetailVO detailVo = new GoodsParamInfoVO.DetailVO();
        BeanUtils.copyProperties(goodsParamInfo, detailVo);
        return detailVo;
    }

}
