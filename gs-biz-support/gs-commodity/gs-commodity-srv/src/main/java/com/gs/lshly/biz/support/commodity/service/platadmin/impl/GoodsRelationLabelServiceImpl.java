package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsLabel;
import com.gs.lshly.biz.support.commodity.entity.GoodsRelationLabel;
import com.gs.lshly.biz.support.commodity.repository.IGoodsLabelRepository;
import com.gs.lshly.biz.support.commodity.repository.IGoodsRelationLabelRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsLabelService;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsRelationLabelService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsLabelVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsRelationLabelVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-15
*/
@Component
public class GoodsRelationLabelServiceImpl implements IGoodsRelationLabelService {

    @Autowired
    private IGoodsRelationLabelRepository repository;
    @Autowired
    private IGoodsLabelService labelService;

    @Autowired
    private IGoodsLabelRepository goodsLabelRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsRelationLabel(GoodsRelationLabelDTO.ETO etoList) {
        if (etoList == null || ObjectUtils.isEmpty(etoList.getGoodsId())){
            throw new BusinessException("参数不能为空！");
        }

        //如果商品id已经与标签id建立关联先清除关联
        deleteBatches(etoList.getGoodsId());


        if (ObjectUtils.isNotEmpty(etoList.getLabelId())){
            List<GoodsRelationLabel> goodsRelationLabels = new ArrayList<>();
            for (String goodsId:etoList.getGoodsId()) {
                for (String labelId : etoList.getLabelId()){
                    GoodsRelationLabel relationLabel = new GoodsRelationLabel();
                    relationLabel.setLabelId(labelId);
                    relationLabel.setGoodsId(goodsId);
                    goodsRelationLabels.add(relationLabel);
                }
            }
            repository.saveBatch(goodsRelationLabels);
        }
    }

    @Override
    public List<GoodsLabelVO.ListVO> getGoodsLabel(GoodsRelationLabelDTO.GoodsIdDTO dto) {
        QueryWrapper<GoodsRelationLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_id",dto.getGoodsId());
        List<GoodsRelationLabel> relationLabels = repository.list(queryWrapper);

        List<GoodsLabelVO.ListVO> labels = new ArrayList<>();
        if ( !ObjectUtils.isEmpty(relationLabels)){
            for (GoodsRelationLabel relationLabel:relationLabels){
                if (StringUtils.isNotEmpty(relationLabel.getLabelId())){
                    GoodsLabelVO.DetailVO detailVO = labelService.detailGoodsLabel(new GoodsLabelDTO.IdDTO(relationLabel.getLabelId()));
                    if (detailVO == null){
                        continue;
                    }
                    GoodsLabelVO.ListVO label = new GoodsLabelVO.ListVO();
                    BeanUtils.copyProperties(detailVO,label);
                    labels.add(label);
                }
            }
        }
        return labels;
    }

    @Override
    public void deleteGoodsRelationLabel(GoodsRelationLabelDTO.LabelIdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数异常");
        }
        QueryWrapper<GoodsRelationLabel> relationLabelQueryWrapper = new QueryWrapper<>();
        relationLabelQueryWrapper.eq("label_id",dto.getLabelId());
        repository.remove(relationLabelQueryWrapper);
    }

    @Override
    public List<GoodsRelationLabelVO.ListVO> getRelationLabel(GoodsLabelDTO.IdDTO dto) {
        if (dto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsRelationLabel> boost = MybatisPlusUtil.query();
        boost.eq("label_id",dto.getId());
        List<GoodsRelationLabel> relationLabels = repository.list(boost);
        List<GoodsRelationLabelVO.ListVO> listVOS = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(relationLabels)){
            for (GoodsRelationLabel relationLabel: relationLabels){
                GoodsRelationLabelVO.ListVO listVO = new GoodsRelationLabelVO.ListVO();
                BeanUtils.copyProperties(relationLabel,listVO);
                listVOS.add(listVO);
            }
        }
        return listVOS;
    }

    private void deleteBatches(List<String> goodsId){
        QueryWrapper<GoodsRelationLabel> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.in("goods_id",goodsId);
        repository.remove(queryWrapper);
    }

	@Override
	public List<String> listGoodsLabelByGoodsId(String goodsId) {
		List<String> retList =new ArrayList<String>();
		QueryWrapper<GoodsRelationLabel> boost = MybatisPlusUtil.query();
        boost.eq("goods_id",goodsId);
        List<GoodsRelationLabel> list = repository.list(boost);
        if(CollectionUtils.isNotEmpty(list)){
        	for(GoodsRelationLabel goodsRelationLabel:list){
        		String labelId = goodsRelationLabel.getLabelId();
        		GoodsLabel goodsLabel = goodsLabelRepository.getById(labelId);
        		if(goodsLabel!=null)
        			retList.add(goodsLabel.getLabelName());
        	}
        }
		return retList;
	}

}
