package com.gs.lshly.biz.support.user.service.fy.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.Activity;
import com.gs.lshly.biz.support.user.entity.ActivityShop;
import com.gs.lshly.biz.support.user.mapper.ActivityMapper;
import com.gs.lshly.biz.support.user.mapper.ActivityShopMapper;
import com.gs.lshly.biz.support.user.mapper.ActivityUserMapper;
import com.gs.lshly.biz.support.user.repository.IActivityRepository;
import com.gs.lshly.biz.support.user.repository.IActivityShopRepository;
import com.gs.lshly.biz.support.user.repository.IActivityUserRepository;
import com.gs.lshly.biz.support.user.service.fy.IFyActivityService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.fy.activity.dto.ActivityDTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityQTO;
import com.gs.lshly.common.struct.fy.activity.qto.ActivityUserQTO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityShopVO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityUserVO;
import com.gs.lshly.common.struct.fy.activity.vo.ActivityVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午2:11
 */

@Component
@Slf4j
public class FyActivityServiceImpl implements IFyActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityShopMapper activityShopMapper;

    @Autowired
    private IActivityRepository activityRepository;

    @Autowired
    private IActivityShopRepository activityShopRepository;

    @Autowired
    private IActivityUserRepository activityUserRepository;

    @Autowired
    private ActivityUserMapper activityUserMapper;


    @Override
    public PageData<ActivityVO.ListVO> pageData(ActivityQTO.QTO qto) {
        if (null == qto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }

        QueryWrapper<ActivityVO.ListVO> queryWrapper = MybatisPlusUtil.query();
        IPage<ActivityVO.ListVO> pager = MybatisPlusUtil.pager(qto);
        IPage<ActivityVO.ListVO> supListIPage = activityMapper.activityList(pager, queryWrapper);
        if (ObjectUtils.isEmpty(supListIPage)) {
            return new PageData<>();
        }
        return MybatisPlusUtil.toPageData(qto, ActivityVO.ListVO.class, supListIPage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addActivity(ActivityDTO.ETO eto) {
        if (null == eto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }

        if (eto == null) {
            throw new BusinessException("参数不能为空！");
        }
        Activity insert = new Activity();
        BeanUtils.copyProperties(eto, insert);
        activityRepository.save(insert);

        if (CollectionUtils.isNotEmpty(eto.getShopIds())) {
            List<ActivityShop> activityShops = new ArrayList<>();

            eto.getShopIds().forEach(item -> {
                ActivityShop activityShop = new ActivityShop();
                activityShop.setActivityId(insert.getId());
                activityShop.setShopId(item);
                activityShops.add(activityShop);
            });
            activityShopRepository.saveBatch(activityShops);
        }

    }

    @Override
    public ActivityVO.DetailVO get(String id) {
        if (StringUtils.isBlank(id)) {
            throw new BusinessException("参数不能为空！");
        }
        Activity activity = activityRepository.getById(id);
        ActivityVO.DetailVO detailVO = new ActivityVO.DetailVO();
        BeanUtils.copyProperties(activity, detailVO);

        List<ActivityShopVO.ListVO> listVOList = activityShopMapper.activityShop(id);
        if (CollectionUtils.isNotEmpty(listVOList)) {
            StringBuffer idBuf = new StringBuffer();
            StringBuffer nameBuf = new StringBuffer();
            listVOList.forEach(vo -> {
                if (StringUtils.isNotBlank(idBuf.toString())) {
                    idBuf.append(",");
                }
                if (StringUtils.isNotBlank(nameBuf.toString())) {
                    nameBuf.append(",");
                }
                idBuf.append(vo.getId());
                nameBuf.append(vo.getShopName());
            });
            detailVO.setShopIds(idBuf.toString());
            detailVO.setShopName(nameBuf.toString());
        }

        return detailVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editActivity(ActivityDTO.PCUDTO dto) {
        if (null == dto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }

        if (dto == null || StringUtils.isBlank(dto.getId())) {
            throw new BusinessException("参数不能为空！");
        }

        Activity update = new Activity();
        BeanUtils.copyProperties(dto, update);
        activityRepository.updateById(update);


        //删除活动关联的店铺
        QueryWrapper<ActivityShop> queryWrapperDelete = MybatisPlusUtil.query();
        queryWrapperDelete.eq("activity_id", dto.getId());
        activityShopMapper.delete(queryWrapperDelete);

        if (CollectionUtils.isNotEmpty(dto.getShopIds())) {
            List<ActivityShop> activityShops = new ArrayList<>();
            dto.getShopIds().forEach(item -> {
                ActivityShop activityShop = new ActivityShop();
                activityShop.setActivityId(dto.getId());
                activityShop.setShopId(item);
                activityShops.add(activityShop);
            });
            activityShopRepository.saveBatch(activityShops);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(ActivityDTO.ActivityIdListDTO dto) {
        if (null == dto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }
        activityRepository.removeByIds(dto.getActivityIds());
    }

    @Override
    public PageData<ActivityUserVO.ListVO> activityUserPageData(ActivityUserQTO.QTO qto) {
        if (null == qto.getJwtUserId()) {
            throw new BusinessException("没有登录");
        }

        return null;
    }
}
