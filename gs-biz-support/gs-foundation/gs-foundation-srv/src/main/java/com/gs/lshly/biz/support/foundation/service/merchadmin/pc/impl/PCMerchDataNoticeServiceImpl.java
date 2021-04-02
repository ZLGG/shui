package com.gs.lshly.biz.support.foundation.service.merchadmin.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeRecv;
import com.gs.lshly.biz.support.foundation.entity.DataNoticeType;
import com.gs.lshly.biz.support.foundation.enums.NoticeReadStateEnum;
import com.gs.lshly.biz.support.foundation.enums.NoticeScopeTypeEnum;
import com.gs.lshly.biz.support.foundation.mapper.DataNoticeMapper;
import com.gs.lshly.biz.support.foundation.mapper.view.DataNoticeView;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRecvRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeRepository;
import com.gs.lshly.biz.support.foundation.repository.IDataNoticeTypeRepository;
import com.gs.lshly.biz.support.foundation.service.merchadmin.pc.IPCMerchDataNoticeService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.dto.PCMerchDataNoticeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.qto.PCMerchDataNoticeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataNoticeTypeVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-11-16
*/
@Component
public class PCMerchDataNoticeServiceImpl implements IPCMerchDataNoticeService {

    @Autowired
    private IDataNoticeRepository repository;

    @Autowired
    private IDataNoticeRecvRepository dataNoticeRecvRepository;

    @Autowired
    private DataNoticeMapper dataNoticeMapper;

    @Autowired
    private IDataNoticeTypeRepository dataNoticeTypeRepository;


    @Override
    public List<PCMerchDataNoticeVO.NoticeTypeListVO> noticeTypeList(PCMerchDataNoticeQTO.NoticeTypeQTO qto) {
        QueryWrapper<DataNoticeType> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.orderByDesc("cdate");
        List<DataNoticeType> noticeTypeList =  dataNoticeTypeRepository.list(queryWrapper);
        return ListUtil.listCover(PCMerchDataNoticeVO.NoticeTypeListVO.class,noticeTypeList);
    }

    @Override
    public PageData<PCMerchDataNoticeVO.ListVO> pageData(PCMerchDataNoticeQTO.QTO qto) {
        QueryWrapper<DataNoticeView> wrapper =  MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getNoticeTypeId())){
            wrapper.eq("nt.notice_type_id",qto.getNoticeTypeId());
        }
        wrapper.eq("nt.scope_type",NoticeScopeTypeEnum.全部商家.getCode());
        wrapper.or();
        wrapper.eq("recv.shop_id",qto.getJwtShopId());
        IPage<DataNoticeView> page = MybatisPlusUtil.pager(qto);
        dataNoticeMapper.mapperPage(page,wrapper);
        return MybatisPlusUtil.toPageData(qto, PCMerchDataNoticeVO.ListVO.class, page);
    }

    @Override
    public PCMerchDataNoticeVO.DetailVO detailDataNotice(PCMerchDataNoticeDTO.IdDTO dto) {
        QueryWrapper<DataNoticeView> queryWrapper =  MybatisPlusUtil.query();
        queryWrapper.eq("nt.id",dto.getId());
        DataNoticeView noticeView =  dataNoticeMapper.mapperOne(queryWrapper);
        if(null == noticeView){
            throw new BusinessException("数据不存在");
        }
        if(NoticeScopeTypeEnum.ID商家.getCode().equals(noticeView.getScopeType())){
             if(null == noticeView.getShopId() || !noticeView.getShopId().equals(dto.getJwtShopId())){
                throw new BusinessException("无效的通知");
             }
            if(!NoticeReadStateEnum.已读.getCode().equals(noticeView.getState())){
                DataNoticeRecv  dataNoticeRecv  = new DataNoticeRecv();
                dataNoticeRecv.setId(noticeView.getRecvId());
                dataNoticeRecv.setState(NoticeReadStateEnum.已读.getCode());
                dataNoticeRecvRepository.updateById(dataNoticeRecv);
            }
        }
        if(NoticeScopeTypeEnum.全部商家.getCode().equals(noticeView.getScopeType())){
            //全部通知，还未读
            if(null == noticeView.getShopId() || !NoticeReadStateEnum.已读.getCode().equals(noticeView.getState())){
                DataNoticeRecv  dataNoticeRecv  = new DataNoticeRecv();
                dataNoticeRecv.setNoticeId(dto.getId());
                dataNoticeRecv.setShopId(dto.getJwtShopId());
                dataNoticeRecv.setMerchantId(dto.getJwtMerchantId());
                dataNoticeRecv.setState(NoticeReadStateEnum.已读.getCode());
                dataNoticeRecvRepository.save(dataNoticeRecv);
            }
        }
        PCMerchDataNoticeVO.DetailVO detailVo = new PCMerchDataNoticeVO.DetailVO();
        BeanUtils.copyProperties(noticeView, detailVo);
        detailVo.setState(NoticeReadStateEnum.已读.getCode());
        return detailVo;
    }

    @Override
    public List<PCMerchDataNoticeVO.ListVO> innerList(PCMerchDataNoticeDTO.innerDTO qto) {
        QueryWrapper<DataNoticeView> wrapper =  MybatisPlusUtil.query();
        wrapper.and(i->i.eq("recv.shop_id",qto.getJwtShopId()));
        wrapper.and(i->i.eq("recv.`state`",10));
        wrapper.orderByDesc("recv.`cdate`");
        List<PCMerchDataNoticeVO.ListVO> listVOS=dataNoticeMapper.innerList(wrapper);
        if (ObjectUtils.isNotEmpty(listVOS)){
            return listVOS;
        }
       return new ArrayList<>();
    }

}
