package com.gs.lshly.biz.support.foundation.service.bbc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.service.bbc.IBbcDataArticleService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;

import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbcDataArticleServiceImpl implements IBbcDataArticleService {

    @Autowired
    private IDataArticleRepository repository;

    @Override
    public List<BbcDataArticleVO.ListVO> list(BaseDTO dto) {


        return null;
    }


}
