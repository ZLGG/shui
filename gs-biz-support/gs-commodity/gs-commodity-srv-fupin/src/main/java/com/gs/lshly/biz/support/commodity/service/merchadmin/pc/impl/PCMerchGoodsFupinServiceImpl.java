package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsFupinService;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupin;
import com.gs.lshly.biz.support.commodity.entity.GoodsFupinImage;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinImageRepository;
import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinRepository;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinImageDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.qto.PCMerchGoodsFupinQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinImageVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinVO;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-08
*/
@Component
public class PCMerchGoodsFupinServiceImpl implements IPCMerchGoodsFupinService {

    @Autowired
    private IGoodsFupinRepository repository;
    @Autowired
    private IGoodsFupinImageRepository goodsFupinImageRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveGoodsFupin(PCMerchGoodsFupinDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数为空，异常！！！");
        }
        if (ObjectUtils.isEmpty(eto.getEtoList())){
            throw new BusinessException("请为该扶贫商品上传凭证！！");
        }
        GoodsFupin goodsFupin = new GoodsFupin();
        BeanUtils.copyProperties(eto, goodsFupin);
        repository.saveOrUpdate(goodsFupin);

        if (StringUtils.isNotBlank(eto.getId())){
            QueryWrapper<GoodsFupinImage> wrapper = MybatisPlusUtil.query();
            wrapper.eq("fupin_goods_id",eto.getId());
            goodsFupinImageRepository.remove(wrapper);
        }
        for (PCMerchGoodsFupinImageDTO.ETO image : eto.getEtoList()){
            GoodsFupinImage fupinImage = new GoodsFupinImage();
            BeanUtils.copyProperties(image,fupinImage);
            fupinImage.setFupinGoodsId(goodsFupin.getId());
            goodsFupinImageRepository.save(fupinImage);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsFupin(PCMerchGoodsFupinDTO.IdDTO dto) {
        if (ObjectUtils.isEmpty(dto)){
            throw new BusinessException("参数为空，异常！！");
        }
        QueryWrapper<GoodsFupin> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id",dto.getGoodsId());
        GoodsFupin goodsFupin = repository.getOne(wrapper);

        if (ObjectUtils.isNotEmpty(goodsFupin)){
            //删除扶贫凭证
            QueryWrapper<GoodsFupinImage> queryWrapper = MybatisPlusUtil.query();
            queryWrapper.eq("fupin_goods_id",goodsFupin.getId());
            goodsFupinImageRepository.remove(queryWrapper);

            QueryWrapper<GoodsFupin> query = MybatisPlusUtil.query();
            query.eq("id",goodsFupin.getId());
            repository.remove(query);
        }
    }

    @Override
    public PCMerchGoodsFupinVO.DetailVO getDetailVO(PCMerchGoodsFupinQTO.QTO qto) {
        PCMerchGoodsFupinVO.DetailVO detailVO = new PCMerchGoodsFupinVO.DetailVO();

        QueryWrapper<GoodsFupin> wrapper = MybatisPlusUtil.query();
        wrapper.eq("goods_id",qto.getGoodsId());
        GoodsFupin goodsFupin = repository.getOne(wrapper);
        if (ObjectUtils.isEmpty(goodsFupin)){
            return null;
        }
        BeanUtils.copyProperties(goodsFupin,detailVO);

        QueryWrapper<GoodsFupinImage> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("fupin_goods_id",goodsFupin.getId());
        List<GoodsFupinImage> fupinImages = goodsFupinImageRepository.list(queryWrapper);
        if (ObjectUtils.isEmpty(fupinImages)){
            throw new BusinessException("扶贫凭证查询异常！！");
        }
        List<PCMerchGoodsFupinImageVO.ListVO> listVOS = ListUtil.listCover(PCMerchGoodsFupinImageVO.ListVO.class,fupinImages);
        detailVO.setListVOS(listVOS);
        return detailVO;
    }

    @Override
    public List<String> listFuPinGoodsId(BaseQTO qto) {
        QueryWrapper<GoodsFupin> wrapper = MybatisPlusUtil.query();
        List<GoodsFupin> fupinList = repository.list(wrapper);
        if (ObjectUtils.isEmpty(fupinList)){
            return new ArrayList<>();
        }
        List<String> fupinGoodsId = ListUtil.getIdList(GoodsFupin.class,fupinList,"goodsId");
        return fupinGoodsId;
    }


}
