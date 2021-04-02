package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteTopic;
import com.gs.lshly.biz.support.foundation.entity.SiteTopicGoods;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicGoodsRepository;
import com.gs.lshly.biz.support.foundation.repository.ISiteTopicRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteTopicService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.ETO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.IdDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteTopicDTO.OnoffDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteTopicQTO.QTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCGoodsDetailVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteTopicVO.PCListVO;
import com.gs.lshly.common.utils.BeanUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午3:05:06
 */
@SuppressWarnings("unchecked")
@Component
public class SiteTopicServiceImpl implements ISiteTopicService {

    @Autowired
    private ISiteTopicRepository repository;
    
    @Autowired
    private ISiteTopicGoodsRepository topicGoodsRepository;
    
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;

	@Override
	public PageData<PCListVO> pageData(QTO qto) {
		QueryWrapper<SiteTopic> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("terminal", qto.getTerminal());
        if(StringUtils.isNotEmpty(qto.getName())&&!qto.getName().equals("null")){
        wrapper.like("name", qto.getName());
        }
        wrapper.orderByDesc("id");
        IPage<SiteTopic> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        
        PageData<PCListVO> pageData = MybatisPlusUtil.toPageData(qto, SiteTopicVO.PCListVO.class, page);
        if(pageData!=null&&pageData.getTotal()>0){
        	List<PCListVO> retList = pageData.getContent();
        	for(PCListVO pclistvo:retList){
        		String topicId = pclistvo.getId();
        		QueryWrapper<SiteTopicGoods> wrapperTopicGoods =  MybatisPlusUtil.query();
        		wrapperTopicGoods.eq("topic_id", topicId);
        		pclistvo.setGoodsCount(topicGoodsRepository.count(wrapperTopicGoods));
        	}
        }
        
        return pageData;
	}

	@Transactional
	@Override
	public void editor(ETO eto) {
		SiteTopic siteTopic = new SiteTopic();
		BeanUtils.copyProperties(eto, siteTopic);
		
		repository.saveOrUpdate(siteTopic);
		
		
		QueryWrapper<SiteTopicGoods> wrapper =  MybatisPlusUtil.query();
	    wrapper.eq("topic_id", siteTopic.getId());
	    topicGoodsRepository.remove(wrapper);
	    
	    List<String> goodsIds = eto.getGoodsIds();
	    
		if(CollectionUtil.isNotEmpty(goodsIds)){
			
			List<SiteTopicGoods> topicGoodsBatchList = new ArrayList<>();
        	for(String goodsId:goodsIds){
        		SiteTopicGoods siteTopicGoods = new SiteTopicGoods();
        		siteTopicGoods.setTopicId(siteTopic.getId());
        		siteTopicGoods.setGoodsId(goodsId);
        		topicGoodsBatchList.add(siteTopicGoods);
        	}
        	topicGoodsRepository.saveBatch(topicGoodsBatchList);
		}
	}

	@Override
	public PCDetailVO get(IdDTO dto) {
		if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        SiteTopic siteTopic = repository.getById(dto.getId());
        if (siteTopic == null){
            throw new BusinessException("查询异常！");
        }
        SiteTopicVO.PCDetailVO detailVO = new SiteTopicVO.PCDetailVO();
        BeanUtils.copyProperties(siteTopic,detailVO);
        
        QueryWrapper<SiteTopicGoods> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("topic_id", dto.getId());
        List<SiteTopicGoods> list = topicGoodsRepository.list(wrapper);
        if(CollectionUtil.isNotEmpty(list)){
        	List<PCGoodsDetailVO> goods = new ArrayList<PCGoodsDetailVO>();
        	PCGoodsDetailVO goodsDetailVO = null;
        	for(SiteTopicGoods siteTopicGoods:list){
        		goodsDetailVO = new PCGoodsDetailVO();
        		String goodsId = siteTopicGoods.getGoodsId();
        		
        		GoodsInfoVO.DetailVO goodsInfoDetailVO= goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(goodsId));
        		goodsDetailVO.setId(goodsId);
        		goodsDetailVO.setGoodsName(goodsInfoDetailVO.getGoodsName());
        		goodsDetailVO.setGoodsNo(goodsInfoDetailVO.getGoodsNo());
        		goods.add(goodsDetailVO);
        	}
        	detailVO.setGoods(goods);
        }
		return detailVO;
	}

	@Transactional
	@Override
	public void delete(IdDTO dto) {
        repository.removeById(dto);
        
        QueryWrapper<SiteTopicGoods> wrapper =  MybatisPlusUtil.query();
        wrapper.eq("topic_id", dto.getId());
        topicGoodsRepository.remove(wrapper);
		
	}

	@Override
	public void onoff(OnoffDTO dto) {
		if (dto.getId() == null){
            throw new BusinessException("参数不能为空！");
        }
        SiteTopic siteTopic = repository.getById(dto.getId());
        if (siteTopic == null){
            throw new BusinessException("查询异常！");
        }
        
        siteTopic.setOnoff(dto.getOnoff());
        repository.saveOrUpdate(siteTopic);
	}

	@Override
	public List<PCGoodsDetailVO> listGoods() {
		List<GoodsInfoVO.ListVO> list = goodsInfoRpc.listGoodsData();
		List<PCGoodsDetailVO> retList = new ArrayList<PCGoodsDetailVO>();
		if(CollectionUtil.isNotEmpty(list)){
			retList = BeanUtils.copyList(PCGoodsDetailVO.class, list);
		}
		return retList;
	}
}
