package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.repository.IGoodsLabelRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsLabelService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsRelationLabelService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsLabelQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-15
*/
@Component
public class GoodsLabelServiceImpl implements IGoodsLabelService {

    @Autowired
    private IGoodsLabelRepository repository;
    @Autowired
    private IGoodsRelationLabelService relationLabelService;

    @Override
    public PageData<GoodsLabelVO.ListVO> pageData(GoodsLabelQTO.QTO qto) {
        QueryWrapper<GoodsLabel> wrapper = new QueryWrapper<>();
        IPage<GoodsLabel> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, GoodsLabelVO.ListVO.class, page);
    }

    @Override
    public void addGoodsLabel(GoodsLabelDTO.ETO eto) {
        //如果新建的标签名是已有的则不添加
        if (filterSameName(eto) >0 ){
            throw new BusinessException(eto.getLabelName()+"已存在");
        }
        GoodsLabel goodsLabel = new GoodsLabel();
        BeanUtils.copyProperties(eto, goodsLabel);
        repository.save(goodsLabel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGoodsLabel(GoodsLabelDTO.IdListDTO dto) {
        if (dto == null || dto.getIdList() == null || dto.getIdList().size() == 0){
            throw new BusinessException("参数不能为空！");
        }
        //删除商品与商品标签的关联
        for(String id : dto.getIdList()){
            GoodsRelationLabelDTO.LabelIdDTO labelIdDTO = new GoodsRelationLabelDTO.LabelIdDTO(id);
            relationLabelService.deleteGoodsRelationLabel(labelIdDTO);
        }
        repository.removeByIds(dto.getIdList());
    }
    @Override
    public void editGoodsLabel(GoodsLabelDTO.ETO eto) {
        //如果修改的标签名是已有的则不添加
        if (filterSameName(eto) >0 ){
            throw new BusinessException("商品标签名称:"+eto.getLabelName()+"已存在");
        }
        GoodsLabel goodsLabel = new GoodsLabel();
        BeanUtils.copyProperties(eto, goodsLabel);
        repository.updateById(goodsLabel);
    }

    @Override
    public GoodsLabelVO.DetailVO detailGoodsLabel(GoodsLabelDTO.IdDTO dto) {
        GoodsLabel goodsLabel = repository.getById(dto.getId());
        GoodsLabelVO.DetailVO detailVo = new GoodsLabelVO.DetailVO();
        if(ObjectUtils.isEmpty(goodsLabel)){
            return null;
        }
        BeanUtils.copyProperties(goodsLabel, detailVo);
        return detailVo;
    }

    @Override
    public List<GoodsLabelVO.ListVO> listGoodsLabel() {
        List<GoodsLabel> labels = repository.list();
        if (ObjectUtils.isEmpty(labels)){
            return new ArrayList<>();
        }
        List<GoodsLabelVO.ListVO> listVOS = new ArrayList<>();
        for (GoodsLabel label : labels) {
            GoodsLabelVO.ListVO listVO = new GoodsLabelVO.ListVO();
            BeanUtils.copyProperties(label,listVO);
            listVOS.add(listVO);
        }
        return listVOS;
    }

    private int filterSameName(GoodsLabelDTO.ETO eto){
        if (eto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsLabel> wrapper = new QueryWrapper<>();
        wrapper.eq("label_name",eto.getLabelName());
        int count = repository.count(wrapper);

        if (StringUtils.isNotEmpty(eto.getId())){
            QueryWrapper<GoodsLabel> boost = MybatisPlusUtil.query();
            boost.eq("id",eto.getId());
            GoodsLabel label = repository.getOne(boost);
            if (label.getLabelName().equals(eto.getLabelName())){
                return 0;
            }else {
                return count;
            }
        }
        return count;
    }
}
