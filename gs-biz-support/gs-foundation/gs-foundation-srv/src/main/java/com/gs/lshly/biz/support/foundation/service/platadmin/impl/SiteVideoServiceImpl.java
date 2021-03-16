package com.gs.lshly.biz.support.foundation.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.foundation.entity.SiteVideo;
import com.gs.lshly.biz.support.foundation.repository.ISiteVideoRepository;
import com.gs.lshly.biz.support.foundation.service.platadmin.ISiteVideoService;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteVideoDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteVideoQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVideoVO;
import com.gs.lshly.common.utils.EnumUtil;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
* <p>
*  服务实现类
* </p>
* @author 陈奇
* @since 2020-10-20
*/
@Component
public class SiteVideoServiceImpl implements ISiteVideoService {

    @Autowired
    private ISiteVideoRepository repository;

    @Override
    public List<SiteVideoVO.H5ListVO> h5List(SiteVideoQTO.H5QTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(), SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show",PcH5Enum.NO.getCode());
        List<SiteVideo> videoList = repository.list( wrapper);
        return ListUtil.listCover(SiteVideoVO.H5ListVO.class,videoList);
    }


    /**
     * 修改站点视频
     * @param eto
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void h5Editor(SiteVideoDTO.H5DTO eto) {
        if(ObjectUtils.isNotEmpty(eto.getVideoList())){
            List<SiteVideo> siteVideoList =new ArrayList<>();
            eto.getVideoList().forEach(item ->{
                SiteVideo siteVideo = new SiteVideo();
                BeanUtils.copyProperties(item, siteVideo);
                siteVideo.setPcShow(PcH5Enum.NO.getCode());
                if(TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteVideo.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        if(eto.getRemoveIdList().contains(siteVideo.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteVideoList.add(siteVideo);
            });
            repository.saveOrUpdateBatch(siteVideoList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }

    @Override
    public List<SiteVideoVO.PCListVO> pcList(SiteVideoQTO.PCQTO qto) {
        if(!EnumUtil.checkEnumCode(qto.getSubject(), SubjectEnum.class)){
            throw new BusinessException("专栏类型错误");
        }
        QueryWrapper<SiteVideo> wrapper = MybatisPlusUtil.query();
        wrapper.eq("terminal",qto.getTerminal());
        wrapper.eq("subject",qto.getSubject());
        wrapper.eq("pc_show",PcH5Enum.YES.getCode());
        List<SiteVideo> videoList=repository.list( wrapper);
        return ListUtil.listCover(SiteVideoVO.PCListVO.class,videoList);
    }

    @Override
    public void pcEditor(SiteVideoDTO.PCDTO eto) {
        if(ObjectUtils.isNotEmpty(eto.getVideoList())){
            List<SiteVideo> siteVideoList =new ArrayList<>();
            eto.getVideoList().forEach(item ->{
                SiteVideo siteVideo = new SiteVideo();
                BeanUtils.copyProperties(item, siteVideo);
                siteVideo.setPcShow(PcH5Enum.YES.getCode());
                if(TrueFalseEnum.是.getCode().equals(item.getIsNew())){
                    siteVideo.setId(null);
                }else{
                    if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
                        if(eto.getRemoveIdList().contains(siteVideo.getId())){
                            throw new BusinessException("保存的数据和删除的数据冲突");
                        }
                    }
                }
                siteVideoList.add(siteVideo);
            });
            repository.saveOrUpdateBatch(siteVideoList);
        }
        if(ObjectUtils.isNotEmpty(eto.getRemoveIdList())){
            repository.removeByIds(eto.getRemoveIdList());
        }
    }
}
