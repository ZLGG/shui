package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import java.util.*;

import com.gs.lshly.biz.support.trade.mapper.CouponGoodsRelationMapper;
import com.gs.lshly.biz.support.trade.mapper.CouponZoneGoodsRelationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.entity.CouponGoodsRelation;
import com.gs.lshly.biz.support.trade.entity.CouponZoneGoodsRelation;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.repository.ICouponGoodsRelationRepository;
import com.gs.lshly.biz.support.trade.repository.ICouponRepository;
import com.gs.lshly.biz.support.trade.repository.ICouponZoneGoodsRelationRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ICouponService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.lakala.boss.api.utils.UuidUtil;

import cn.hutool.core.collection.CollectionUtil;

/**
 * 优惠券 service
 *
 * @author chenyang
 */
@Component
public class CouponServiceImpl implements ICouponService {

    @Autowired
    private ICouponRepository iCouponRepository;

    @Autowired
    private ICouponGoodsRelationRepository iCouponGoodsRelationRepository;

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private ICouponZoneGoodsRelationRepository zoneGoodsRelationRepository;

    @Autowired
    private CouponGoodsRelationMapper relationMapper;

    @Autowired
    private CouponZoneGoodsRelationMapper zoneGoodsRelationMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveCoupon(CouponQTO.SaveCouponQTO qto) {
        Coupon coupon = new Coupon();
        BeanCopyUtils.copyProperties(qto, coupon);
        String couponId = UuidUtil.getUuid();
        coupon.setCouponId(couponId);
        coupon.setCdate(new Date());
        coupon.setUdate(new Date());
        coupon.setAuditStatus(0);
        coupon.setFlag(false);
        //保存优惠券表
        boolean flag = iCouponRepository.save(coupon);

        if (CollectionUtil.isNotEmpty(qto.getLevelIds())) {
            //表示专区
            if (qto.getLevel() == 1) {
                saveBatchZoneCouponGoodsRelation(qto.getLevelIds(), couponId);
            }
            //表示类目或者商品
            else {
                saveBatchCouponGoodsRelation(qto.getLevelIds(), couponId);
            }
        }
        return flag;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCoupon(CouponQTO.UpdateCouponQTO qto) {
        Coupon coupon = new Coupon();
        BeanCopyUtils.copyProperties(qto, coupon);
        coupon.setUdate(new Date());
        //专区
        if (qto.getLevel() == 1) {
            //先删除黑名单商品
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("coupon_id", qto.getCouponId());
            zoneGoodsRelationRepository.removeByMap(columnMap);
            //再删除relation
            iCouponGoodsRelationRepository.removeByMap(columnMap);
            //再新增
            saveBatchZoneCouponGoodsRelation(qto.getLevelIds(), qto.getCouponId());
        } else {
            //先删除relation
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put("coupon_id", qto.getCouponId());
            iCouponGoodsRelationRepository.removeByMap(columnMap);
            saveBatchCouponGoodsRelation(qto.getLevelIds(), qto.getCouponId());
        }
        boolean flag = iCouponRepository.updateById(coupon);
        return flag;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatchCouponGoodsRelation(List<CouponQTO.LevelQTO> levelQTOS, String couponId) {
        if (CollectionUtil.isEmpty(levelQTOS)) {
            return false;
        }
        List<CouponGoodsRelation> relationList = new ArrayList<>();
        CouponGoodsRelation couponGoodsRelation;
        for (CouponQTO.LevelQTO levelQTO : levelQTOS) {
            couponGoodsRelation = new CouponGoodsRelation();
            couponGoodsRelation.setCouponId(couponId);
            couponGoodsRelation.setLevelId(levelQTO.getLevelId());
            couponGoodsRelation.setCdate(new Date());
            couponGoodsRelation.setUdate(new Date());
            couponGoodsRelation.setFlag(false);
            relationList.add(couponGoodsRelation);
        }
        return iCouponGoodsRelationRepository.saveBatch(relationList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveBatchZoneCouponGoodsRelation(List<CouponQTO.LevelQTO> levelQTOS, String couponId) {
        CouponGoodsRelation couponGoodsRelation;
        for (CouponQTO.LevelQTO levelQTO : levelQTOS) {
            couponGoodsRelation = new CouponGoodsRelation();
            String relationId = UuidUtil.getUuid();
            couponGoodsRelation.setId(relationId);
            couponGoodsRelation.setCouponId(couponId);
            couponGoodsRelation.setLevelId(levelQTO.getLevelId());
            couponGoodsRelation.setCdate(new Date());
            couponGoodsRelation.setUdate(new Date());
            couponGoodsRelation.setFlag(false);
            //保存优惠券关联表
            iCouponGoodsRelationRepository.save(couponGoodsRelation);

            CouponZoneGoodsRelation zoneGoodsRelation;
            List<CouponZoneGoodsRelation> zoneGoodsRelationList = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(levelQTO.getExcludeGoodIds())) {
                for (String goodId : levelQTO.getExcludeGoodIds()) {
                    zoneGoodsRelation = new CouponZoneGoodsRelation();
                    zoneGoodsRelation.setId(UuidUtil.getUuid());
                    zoneGoodsRelation.setCouponId(couponId);
                    zoneGoodsRelation.setRelationId(relationId);
                    zoneGoodsRelation.setZoneId(levelQTO.getLevelId());
                    zoneGoodsRelation.setGoodId(goodId);
                    zoneGoodsRelation.setCdate(new Date());
                    zoneGoodsRelation.setFlag(false);
                    zoneGoodsRelation.setUdate(new Date());
                    zoneGoodsRelationList.add(zoneGoodsRelation);
                }
                zoneGoodsRelationRepository.saveBatch(zoneGoodsRelationList);
            }
        }
    }

    @Override
    public Boolean deleteCoupon(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        if(CollectionUtil.isEmpty(idList)){
            return false;
        }
        zoneGoodsRelationMapper.deleteByCouponIds(idList);
        relationMapper.deleteByCouponIds(idList);
        return iCouponRepository.removeByIds(idList);
    }

    @Override
    public CouponVO.CouponDetailVO getDetail(String id) {
        CouponVO.CouponDetailVO couponDetailDTO = new CouponVO.CouponDetailVO();
        Coupon coupon = iCouponRepository.getById(id);
        BeanCopyUtils.copyProperties(coupon, couponDetailDTO);
        QueryWrapper<CouponGoodsRelation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("coupon_id", id);
        List<CouponGoodsRelation> relationList = iCouponGoodsRelationRepository.list(wrapper);

        if (CollectionUtil.isNotEmpty(relationList)) {
            List<CouponVO.LevelVO> couponGoodDTOList = new ArrayList<>();
            CouponVO.LevelVO levelVO;

            if (coupon.getLevel() == 1) {
                QueryWrapper<CouponZoneGoodsRelation> zoneWrapper = MybatisPlusUtil.query();
                zoneWrapper.eq("coupon_id", id);
                List<CouponZoneGoodsRelation> zoneGoodList = zoneGoodsRelationRepository.list(zoneWrapper);

                for (CouponGoodsRelation couponGoodsRelation : relationList) {
                    levelVO = new CouponVO.LevelVO();
                    levelVO.setLevelId(couponGoodsRelation.getLevelId());

                    if(CollectionUtil.isNotEmpty(zoneGoodList)){
                        List<String> excludeGoodIds = new ArrayList<>();
                        for (CouponZoneGoodsRelation zoneGoodsRelation : zoneGoodList) {
                            if(zoneGoodsRelation.getZoneId().equals(couponGoodsRelation.getLevelId())){
                                excludeGoodIds.add(zoneGoodsRelation.getGoodId());
                            }
                        }
                        levelVO.setExcludeGoodIds(excludeGoodIds);
                    }
                    couponGoodDTOList.add(levelVO);
                }
                couponDetailDTO.setLevelIds(couponGoodDTOList);
            } else {
                for (CouponGoodsRelation couponGoodsRelation : relationList) {
                    levelVO = new CouponVO.LevelVO();
                    levelVO.setLevelId(couponGoodsRelation.getLevelId());
                    couponGoodDTOList.add(levelVO);
                }
                couponDetailDTO.setLevelIds(couponGoodDTOList);
            }

        }
        return couponDetailDTO;
    }

    @Override
    public PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO) {
        QueryWrapper<Coupon> wrapper = MybatisPlusUtil.query();
        if (ObjectUtils.isNotEmpty(couponListQTO.getCouponType())) {
            wrapper.eq("coupon_type", couponListQTO.getCouponType());
        }
        if (ObjectUtils.isNotEmpty(couponListQTO.getChannel())) {
            wrapper.eq("channel", couponListQTO.getChannel());
        }
        if (ObjectUtils.isNotEmpty(couponListQTO.getCouponStatus())) {
            wrapper.eq("coupon_status", couponListQTO.getCouponStatus());
        }
        if (ObjectUtils.isNotEmpty(couponListQTO.getCouponName())) {
            wrapper.like("coupon_name", couponListQTO.getCouponName());
        }

        IPage<Coupon> page = MybatisPlusUtil.pager(couponListQTO);
        IPage<Coupon> pageData = couponMapper.queryList(page, wrapper);
        List<CouponVO.CouponListVO> activityListVOList = ListUtil.listCover(CouponVO.CouponListVO.class, pageData.getRecords());
        return new PageData<>(activityListVOList, couponListQTO.getPageNum(), couponListQTO.getPageSize(), pageData.getTotal());
    }

    @Override
    public Boolean updateCouponByCondition(CouponDTO.UpdateCouponByConDTO updateCouponByConDTO) {
        UpdateWrapper<Coupon> wrapper = MybatisPlusUtil.update();

        if (ObjectUtils.isNotEmpty(updateCouponByConDTO.getAuditStatus())) {
            wrapper.set("audit_status", updateCouponByConDTO.getAuditStatus());
        }

        if (ObjectUtils.isNotEmpty(updateCouponByConDTO.getCouponStatus())) {
            wrapper.set("coupon_status", updateCouponByConDTO.getCouponStatus());
        }

        if (ObjectUtils.isNotEmpty(updateCouponByConDTO.getStockNum())) {
            wrapper.set("stock_num", updateCouponByConDTO.getStockNum());
        }

        if(ObjectUtils.isNotEmpty(updateCouponByConDTO.getRejectReason())){
            wrapper.set("reject_reason",updateCouponByConDTO.getRejectReason());
        }
        wrapper.eq("coupon_id", updateCouponByConDTO.getCouponId());
        return iCouponRepository.update(wrapper);
    }
}
