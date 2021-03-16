package com.gs.lshly.biz.support.foundation.service.bbb.h5.impl;

import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import com.gs.lshly.biz.support.foundation.service.bbb.h5.IBbbH5DataArticleService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author hyy
* @since 2020-11-13
*/
@Component
public class BbbH5DataArticleServiceImpl implements IBbbH5DataArticleService {

    @Autowired
    private IDataArticleRepository repository;

    @Override
    public List<BbbH5DataArticleVO.ListVO> list(BaseDTO dto) {


        return null;
    }


}
