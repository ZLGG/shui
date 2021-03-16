package com.gs.lshly.biz.support.commodity.service.platadmin.impl;


import com.gs.lshly.biz.support.commodity.respository.IGoodsFupinImageRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsFupinImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-09
*/
@Component
public class GoodsFupinImageServiceImpl implements IGoodsFupinImageService {

    @Autowired
    private IGoodsFupinImageRepository repository;

}
