package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsPosSkuLog;
import com.gs.lshly.biz.support.commodity.repository.IGoodsPosSkuLogRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosSkuLogService;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;
import com.gs.lshly.common.utils.ListUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-12-15
*/
@Component
public class PCMerchGoodsPosSkuLogServiceImpl implements IPCMerchGoodsPosSkuLogService {

    @Autowired
    private IGoodsPosSkuLogRepository repository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodsPosSkuLog(List<PCMerchGoodsPosSkuLogDTO.ETO> etoList) {
        if (ObjectUtils.isNotEmpty(etoList)){
            List<GoodsPosSkuLog> skuLogs = ListUtil.listCover(GoodsPosSkuLog.class,etoList);
            repository.saveBatch(skuLogs);
        }
    }



}
