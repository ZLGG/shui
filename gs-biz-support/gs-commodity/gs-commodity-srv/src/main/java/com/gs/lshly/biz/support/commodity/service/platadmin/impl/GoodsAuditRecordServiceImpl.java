package com.gs.lshly.biz.support.commodity.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.commodity.entity.GoodsAuditRecord;
import com.gs.lshly.biz.support.commodity.repository.IGoodsAuditRecordRepository;
import com.gs.lshly.biz.support.commodity.service.platadmin.IGoodsAuditRecordService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAuditRecordDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAuditRecordQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAuditRecordVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;

/**
* <p>
*  服务实现类
* </p>
* @author Starry
* @since 2020-10-29
*/
@Component
public class GoodsAuditRecordServiceImpl implements IGoodsAuditRecordService {

    @Autowired
    private IGoodsAuditRecordRepository repository;


    @Override
    public List<GoodsAuditRecordVO.ListVO> listAuditRecordByGoodsId(GoodsAuditRecordQTO.QTO qto) {
        if (qto == null){
            throw new BusinessException("参数不能为空！");
        }
        QueryWrapper<GoodsAuditRecord> boost = MybatisPlusUtil.query();
        boost.eq("goods_id",qto.getGoodsId());
        List<GoodsAuditRecord> auditRecords = repository.list(boost);
        if (ObjectUtils.isNotEmpty(auditRecords)){
            List<GoodsAuditRecordVO.ListVO> listVOS = new ArrayList<>();
            for (GoodsAuditRecord auditRecord : auditRecords){
                GoodsAuditRecordVO.ListVO listVO = new GoodsAuditRecordVO.ListVO();
                BeanUtils.copyProperties(auditRecord,listVO);
                listVOS.add(listVO);
            }
            return listVOS;
        }
        return null;
    }

    @Override
    public void addGoodsAuditRecord(GoodsAuditRecordDTO.ETO eto) {
        GoodsAuditRecord goodsAuditRecord = new GoodsAuditRecord();
        BeanUtils.copyProperties(eto, goodsAuditRecord);
        repository.save(goodsAuditRecord);
    }


}
