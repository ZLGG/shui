package com.gs.lshly.biz.support.user.service.bbb.h5.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserCard;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.biz.support.user.repository.IUserCardRepository;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.repository.IUserSignInRepository;
import com.gs.lshly.biz.support.user.service.bbb.h5.IBbbH5UserCardService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.h5.user.dto.BbbH5UserCardDTO;
import com.gs.lshly.common.struct.bbb.h5.user.qto.BbbH5UserCardQTO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserCardVO;
import com.gs.lshly.common.struct.bbb.h5.user.vo.BbbH5UserVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsIntegralVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SettingsReportVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsIntegralRpc;
import com.gs.lshly.rpc.api.platadmin.foundation.ISettingsReportRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2021-01-05
*/
@Component
public class BbbH5UserCardServiceImpl implements IBbbH5UserCardService {

    @Autowired
    private IUserCardRepository repository;
    @Autowired
    private UserIntegralMapper userIntegralMapper;
    @Autowired
    private IUserIntegralRepository userIntegralRepository;
    @Autowired
    private IUserSignInRepository iUserSignInRepository;
    @DubboReference
    private ISettingsReportRpc iSettingsReportRpc;
    @DubboReference
    private ISettingsIntegralRpc iSettingsIntegralRpc;

    @Override
    public PageData<BbbH5UserCardVO.ListVO> pageData(BbbH5UserCardQTO.QTO qto) {
        QueryWrapper<UserCard> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<UserCard> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        //todo 会员优惠卷
        return MybatisPlusUtil.toPageData(qto, BbbH5UserCardVO.ListVO.class, page);
    }

    @Override
    public void addUserCard(BbbH5UserCardDTO.ETO eto) {
        UserCard userCard = new UserCard();
        BeanUtils.copyProperties(eto, userCard);
        repository.save(userCard);
    }


    @Override
    public void deleteUserCard(BbbH5UserCardDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public BbbH5UserCardVO.DetailVO detailUserCard(BbbH5UserCardDTO.IdDTO dto) {
        UserCard userCard = repository.getById(dto.getId());
        BbbH5UserCardVO.DetailVO detailVo = new BbbH5UserCardVO.DetailVO();
        if(ObjectUtils.isEmpty(userCard)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(userCard, detailVo);
        return detailVo;
    }

    @Override
    public BbbH5UserVO.UserIntegralVO integral(BaseDTO dto) {
        QueryWrapper<UserIntegral> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id",dto.getJwtUserId());
        queryWrapper.groupBy("user_id");
        UserIntegralView sumCountView = userIntegralMapper.sumCount(queryWrapper);
        UserIntegralView sumCountPassView =  userIntegralMapper.sumCountPass(10,queryWrapper);
        BbbH5UserVO.UserIntegralVO userIntegralVO  = new BbbH5UserVO.UserIntegralVO();
        if(null != sumCountView){
            userIntegralVO.setOkIntegral(sumCountView.getQuantity());
        }
        if(null != sumCountPassView){
            userIntegralVO.setJpassIntegral(sumCountPassView.getQuantity());
        }
        return userIntegralVO;
    }

    @Override
    public PageData<BbbH5UserVO.UserIntegralRecordVO> integralLog(BaseQTO qto) {
        QueryWrapper<UserIntegral> userIntegralQueryWrapper = MybatisPlusUtil.query();
        userIntegralQueryWrapper.eq("user_id",qto.getJwtUserId());
        IPage<UserIntegral> pager = MybatisPlusUtil.pager(qto);
        userIntegralRepository.page(pager,userIntegralQueryWrapper);
        if (ObjectUtils.isNotEmpty(pager)){
            return MybatisPlusUtil.toPageData(qto,BbbH5UserVO.UserIntegralRecordVO.class,pager);
        }
        return new PageData<>();
    }

    @Override
    @Transactional
    public void signInIntegralLog(BaseDTO dto) {
        //在系统设置里面查询是否开启了签到送积分
        SettingsReportVO.DetailVO detailVO = iSettingsReportRpc.detailSettingsReport(dto);
        if (ObjectUtils.isEmpty(detailVO)){
            throw new BusinessException("系统设置不能为空");
        }
        if (ObjectUtils.isNotEmpty(detailVO.getIsOpenState())) {
            if (detailVO.getIsOpenState() != 10) {
                throw new BusinessException("不能签到");
            }
        }
        //通过userID查询当天是否已经签到了，，，
        QueryWrapper<BaseDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",dto.getJwtUserId()));
        UserSignIn userSignIn=iUserSignInRepository.seletcNowDay(query);
        if (ObjectUtils.isNotEmpty(userSignIn)){
            throw new BusinessException("已经签到过了");
        }
        UserSignIn userSignIn1 = new UserSignIn();
        userSignIn1.setUserId(  dto.getJwtUserId());
        iUserSignInRepository.save(userSignIn1);
        if (detailVO.getIsReportInteg()==10){
            //开启签到领积分
            UserIntegral userIntegral = new UserIntegral();
            userIntegral.setQuantity(detailVO.getReportInteg());
            userIntegral.setFromType(30);//积分来源[10=平台添加 20=订单 30=签到]
            userIntegral.setUserId(dto.getJwtUserId());
            userIntegral.setFromId(userSignIn1.getId());
            //获取积分获取月份
            SettingsIntegralVO.DetailVO detailVO1 = iSettingsIntegralRpc.detailSettingsIntegral(dto);
            LocalDateTime now = LocalDateTime.now();//获取当前月份
            if (now.getMonthValue()<detailVO1.getMonthToExpire()){
                userIntegral.setEndDate(LocalDateTime.of(now.getYear(),detailVO1.getMonthToExpire(),1,0,0,0));
            }else {
                userIntegral.setEndDate(LocalDateTime.of(now.getYear()+1,detailVO1.getMonthToExpire(),1,0,0,0));
            }
            userIntegralMapper.insert(userIntegral);
        }
    }

    @Override
    public BbbH5UserVO.UserIntegralStatusVO signInIntegralLogState(BaseDTO dto) {
        QueryWrapper<BaseDTO> query = MybatisPlusUtil.query();
        query.and(i->i.eq("user_id",dto.getJwtUserId()));
        UserSignIn userSignIn=iUserSignInRepository.seletcNowDay(query);
        BbbH5UserVO.UserIntegralStatusVO userIntegralStatusVO = new BbbH5UserVO.UserIntegralStatusVO();
        if (ObjectUtils.isNotEmpty(userSignIn)){
            userIntegralStatusVO.setStatus(20);
        }else {
            userIntegralStatusVO.setStatus(10);
        }
        return userIntegralStatusVO;
    }

}
