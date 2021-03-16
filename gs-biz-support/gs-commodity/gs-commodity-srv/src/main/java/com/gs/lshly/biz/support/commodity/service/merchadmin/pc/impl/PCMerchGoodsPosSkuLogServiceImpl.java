package com.gs.lshly.biz.support.commodity.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.commodity.entity.GoodsPosLog;
import com.gs.lshly.biz.support.commodity.entity.GoodsPosSkuLog;
import com.gs.lshly.biz.support.commodity.repository.IGoodsPosSkuLogRepository;
import com.gs.lshly.biz.support.commodity.service.merchadmin.pc.IPCMerchGoodsPosSkuLogService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsPosSkuLogDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsPosSkuLogQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsPosSkuLogVO;
import com.gs.lshly.common.utils.ListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
