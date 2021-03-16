package com.gs.lshly.biz.support.foundation.repository.impl;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.foundation.entity.MerchantArticle;
import com.gs.lshly.biz.support.foundation.mapper.MerchantArticleMapper;
import com.gs.lshly.biz.support.foundation.repository.IMerchantArticleRepository;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
* <p>
 *  服务实现类
 * </p>
*
* @author hyy
 * @since 2020-10-29
*/
@Service
public class MerchantArticleRepositoryImpl extends ServiceImpl<MerchantArticleMapper, MerchantArticle> implements IMerchantArticleRepository {

    @Override
    public boolean checkIdListExist(List<String> idList) {
        List<MerchantArticle> dataList = this.listByIds(idList);
            if(ObjectUtils.isEmpty(dataList)){
                return false;
            }
            if(dataList.size() != idList.size()){
                return false;
            }
            return true;
    }
}