package com.gs.lshly.biz.support.foundation.repository.impl;

import com.gs.lshly.biz.support.foundation.entity.DataArticle;
import com.gs.lshly.biz.support.foundation.mapper.DataArticleMapper;
import com.gs.lshly.biz.support.foundation.repository.IDataArticleRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author 陈奇
 * @since 2020-10-19
*/
@Service
public class DataArticleRepositoryImpl extends ServiceImpl<DataArticleMapper, DataArticle> implements IDataArticleRepository {

}