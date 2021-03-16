package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gs.lshly.biz.support.foundation.entity.MerchantArticle;
import com.gs.lshly.biz.support.foundation.mapper.MerchantArticleMapper;
import com.gs.lshly.biz.support.foundation.repository.IMerchantArticleRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.IMerchantArticleService;
import com.gs.lshly.common.enums.MerchantArticleEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleDTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-10-29
*/
@Component
public class MerchantArticleServiceImpl implements IMerchantArticleService {

    @Autowired
    private IMerchantArticleRepository repository;

    @Autowired
    private MerchantArticleMapper merchantArticleMapper;


    public IPage<MerchantArticleVO.ListVO> getList(Page page ) {
        return  merchantArticleMapper.queryAll(page );
    }

    @Override
    public PageData<MerchantArticleVO.ListVO> pageData(Integer pageNum,Integer pageSize) {
        Page page=new Page(pageNum,pageSize);
        getList(page);
        PageData pageData = new PageData();
        pageData.setPageNum(pageNum);
        pageData.setPageSize(pageSize);
        pageData.setContent(page.getRecords());
        pageData.setTotal(page.getTotal());

        return pageData;
    }




    @Override
    public MerchantArticleVO.DetailVO detailMerchantArticle(MerchantArticleDTO.IdDTO dto) {
        MerchantArticle merchantArticle = repository.getById(dto.getId());
        MerchantArticleVO.DetailVO detailVo = new MerchantArticleVO.DetailVO();
        if(ObjectUtils.isEmpty(merchantArticle)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(merchantArticle, detailVo);
        return detailVo;
    }

    @Override
    public void deleteBatchMerchantArticle(MerchantArticleDTO.IdListDTO dto) {
        if(ObjectUtils.isNull(dto.getIdList())){
            throw new BusinessException("ID数组不能为Null");
        }
        if(!repository.checkIdListExist(dto.getIdList())){
            throw new BusinessException("无效的ID");
        }
        repository.removeByIds(dto.getIdList());
    }

    @Override
    public void applyMerchantArticle(MerchantArticleDTO.ApplyDTO dto) {
        if(ObjectUtils.isEmpty(dto.getId())|| ObjectUtils.isNull(dto.getState())){
            throw new BusinessException("参数不能为空[id,state]");
        }
        UpdateWrapper<MerchantArticle> updateWrapper = MybatisPlusUtil.update();
        if(dto.getState().equals(MerchantArticleEnum.通过.getCode())){
            updateWrapper.set("state", MerchantArticleEnum.通过.getCode());
        }else if(dto.getState().equals(MerchantArticleEnum.拒审.getCode())){
            updateWrapper.set("state", MerchantArticleEnum.拒审.getCode());
            updateWrapper.set("why",dto.getRevokeWhy());
        }
        updateWrapper.eq("shop_id",dto.getId());
        boolean bool = repository.update(updateWrapper);
        if(!bool){
            throw new BusinessException("会员审核失败");
        }
    }

}
