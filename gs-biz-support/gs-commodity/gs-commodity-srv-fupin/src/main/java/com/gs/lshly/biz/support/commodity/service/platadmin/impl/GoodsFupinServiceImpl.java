package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupin;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupinImage;
import com.gs.lshly.biz.support.commodity.mapper.GoodsFupinMapper;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinImageRepository;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsFupinService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsInfoService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.dto.GoodsFupinDTO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.qto.GoodsFupinQTO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.vo.GoodsFupinImageVO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.vo.GoodsFupinVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-09
*/
@Component
public class GoodsFupinServiceImpl implements IGoodsFupinService {

    @Autowired
    private IGoodsFupinRepository repository;
    @Autowired
    private IGoodsFupinImageRepository fupinImageRepository;
    @Autowired
    private GoodsFupinMapper fupinMapper;


    @Override
    public GoodsFupinVO.DetailVO detailGoodsFupin(GoodsFupinQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto)){
            throw new BusinessException("参数为空，异常！！！");
        }
        QueryWrapper<GoodsFupin> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id",qto.getGoodsId());
        GoodsFupin goodsFupin = repository.getOne(wrapper);
        if(ObjectUtils.isEmpty(goodsFupin)){
            return null;
        }
        GoodsFupinVO.DetailVO detailVo = new GoodsFupinVO.DetailVO();
        BeanUtils.copyProperties(goodsFupin, detailVo);

        QueryWrapper<GoodsFupinImage> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("fupin_goods_id",goodsFupin.getId());
        List<GoodsFupinImage> list = fupinImageRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(list)){
            throw new BusinessException("扶贫凭证数据异常！！");
        }
        List<GoodsFupinImageVO.ListVO> listVOS = ListUtil.listCover(GoodsFupinImageVO.ListVO.class,list);
        detailVo.setListImage(listVOS);
        return detailVo;
    }

    @Override
    public List<String> listFuPinGoodsId(BaseQTO qto) {
        QueryWrapper<GoodsFupin> wrapper = new QueryWrapper<>();
        List<GoodsFupin> fupinList = repository.list(wrapper);
        if (ObjectUtils.isEmpty(fupinList)){
            return new ArrayList<>();
        }
        List<String> fupinGoodsId = ListUtil.getIdList(GoodsFupin.class,fupinList,"goodsId");
        return fupinGoodsId;
    }

}
