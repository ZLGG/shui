package com.gs.lshly.biz.support.trade.service.platadmin.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.trade.entity.Coupon;
import com.gs.lshly.biz.support.trade.entity.CouponGoodsRelation;
import com.gs.lshly.biz.support.trade.mapper.CouponMapper;
import com.gs.lshly.biz.support.trade.repository.ICouponGoodsRelationRepository;
import com.gs.lshly.biz.support.trade.repository.ICouponRepository;
import com.gs.lshly.biz.support.trade.service.platadmin.ICouponService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.CouponDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.CouponQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.CouponVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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


    @Override
    public Boolean saveCoupon(CouponDTO.SaveCouponDTO saveCouponDTO) {
        Coupon coupon = new Coupon();
        BeanCopyUtils.copyProperties(saveCouponDTO, coupon);
        coupon.setCreateTime(new Date());
        coupon.setModifyTime(new Date());
        coupon.setAuditStatus(0);
        boolean flag = iCouponRepository.save(coupon);
        Long couponId = coupon.getCouponId();
        CouponGoodsRelation couponGoodsRelation;
        if (saveCouponDTO.getIsAllGoods()) {
            couponGoodsRelation = new CouponGoodsRelation();
            couponGoodsRelation.setCouponId(couponId);
            couponGoodsRelation.setGoodId("-1");
            couponGoodsRelation.setCreateTime(new Date());
            couponGoodsRelation.setModifyTime(new Date());
            iCouponGoodsRelationRepository.save(couponGoodsRelation);
        } else {
            List<String> goodIdList = saveCouponDTO.getGoodIds();
            List<CouponGoodsRelation> relationList = new ArrayList<>();
            for (String goodId : goodIdList) {
                couponGoodsRelation = new CouponGoodsRelation();
                couponGoodsRelation.setCouponId(couponId);
                couponGoodsRelation.setGoodId(goodId);
                couponGoodsRelation.setCreateTime(new Date());
                couponGoodsRelation.setModifyTime(new Date());
                relationList.add(couponGoodsRelation);
            }
            iCouponGoodsRelationRepository.saveBatch(relationList);
        }
        return flag;
    }

    @Override
    public Boolean updateCoupon(CouponDTO.UpdateCouponDTO updateCouponDTO) {
        Coupon coupon = new Coupon();
        BeanCopyUtils.copyProperties(updateCouponDTO, coupon);
        coupon.setModifyTime(new Date());
        //先删除relation
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("coupon_id",updateCouponDTO.getCouponId());
        iCouponGoodsRelationRepository.removeByMap(columnMap);

        CouponGoodsRelation couponGoodsRelation;
        if (updateCouponDTO.getIsAllGoods()) {
            couponGoodsRelation = new CouponGoodsRelation();
            couponGoodsRelation.setCouponId(updateCouponDTO.getCouponId());
            couponGoodsRelation.setGoodId("-1");
            couponGoodsRelation.setCreateTime(new Date());
            couponGoodsRelation.setModifyTime(new Date());
            iCouponGoodsRelationRepository.save(couponGoodsRelation);
        } else {
            List<String> goodIdList = updateCouponDTO.getGoodIds();
            List<CouponGoodsRelation> relationList = new ArrayList<>();
            for (String goodId : goodIdList) {
                couponGoodsRelation = new CouponGoodsRelation();
                couponGoodsRelation.setCouponId(updateCouponDTO.getCouponId());
                couponGoodsRelation.setGoodId(goodId);
                couponGoodsRelation.setCreateTime(new Date());
                couponGoodsRelation.setModifyTime(new Date());
                relationList.add(couponGoodsRelation);
            }
            iCouponGoodsRelationRepository.saveBatch(relationList);
        }

        boolean flag = iCouponRepository.updateById(coupon);
        return flag;
    }

    @Override
    public Boolean deleteCoupon(Long id) {
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("coupon_id",id);
        iCouponGoodsRelationRepository.removeByMap(columnMap);
        return iCouponRepository.removeById(id);
    }

    @Override
    public CouponVO.CouponDetailVO getDetail(Long id) {
        CouponVO.CouponDetailVO couponDetailDTO = new CouponVO.CouponDetailVO();
        Coupon coupon = iCouponRepository.getById(id);
        BeanCopyUtils.copyProperties(coupon,couponDetailDTO);
        QueryWrapper<CouponGoodsRelation> wrapper = MybatisPlusUtil.query();
        wrapper.eq("coupon_id", id);
        List<CouponGoodsRelation> goodList = iCouponGoodsRelationRepository.list(wrapper);

        if(CollectionUtil.isNotEmpty(goodList)){
            List<CouponVO.CouponGoodVO> couponGoodDTOList = new ArrayList<>();
            CouponVO.CouponGoodVO couponGoodDTO;
            for (CouponGoodsRelation couponGoodsRelation : goodList) {
                couponGoodDTO = new CouponVO.CouponGoodVO();
                couponGoodDTO.setGoodId(couponGoodsRelation.getGoodId());
                couponGoodDTOList.add(couponGoodDTO);
            }
            couponDetailDTO.setGoods(couponGoodDTOList);
        }
        return couponDetailDTO;
    }

    @Override
    public PageData<CouponVO.CouponListVO> queryCouponList(CouponQTO.CouponListQTO couponListQTO) {
        QueryWrapper<Coupon> wrapper = MybatisPlusUtil.query();
        if(ObjectUtils.isNotEmpty(couponListQTO.getCouponType())){
            wrapper.eq("coupon_type", couponListQTO.getCouponType());
        }
        if(ObjectUtils.isNotEmpty(couponListQTO.getChannel())){
            wrapper.eq("channel",couponListQTO.getChannel());
        }
        if(ObjectUtils.isNotEmpty(couponListQTO.getCouponStatus())){
            wrapper.eq("coupon_status",couponListQTO.getCouponStatus());
        }
        if(ObjectUtils.isNotEmpty(couponListQTO.getCouponName())){
            wrapper.like("coupon_name",couponListQTO.getCouponName());
        }

        IPage<Coupon> page = MybatisPlusUtil.pager(couponListQTO);
        IPage<Coupon> pageData = couponMapper.queryList(page,wrapper);
        List<CouponVO.CouponListVO> activityListVOList = ListUtil.listCover(CouponVO.CouponListVO.class, pageData.getRecords());
        return new PageData<>(activityListVOList, couponListQTO.getPageNum(), couponListQTO.getPageSize(), pageData.getTotal());
    }
}
