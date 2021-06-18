package com.gs.lshly.biz.support.stock.repository.impl;

import com.gs.lshly.biz.support.stock.entity.Template;
import com.gs.lshly.biz.support.stock.mapper.TemplateMapper;
import com.gs.lshly.biz.support.stock.repository.ITemplateRepository;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 运费模板表 服务实现类
 * </p>
 *
 * @author chenyang
 * @since 2021-06-18
 */
@Service
public class TemplateRepositoryImpl extends ServiceImpl<TemplateMapper, Template> implements ITemplateRepository {

}
