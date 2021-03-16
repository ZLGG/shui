package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.UserPrivateUser;
import com.gs.lshly.biz.support.user.entity.UserPrivateUserLog;
import com.gs.lshly.biz.support.user.enums.PrivateUserApplyStateEnum;
import com.gs.lshly.biz.support.user.enums.PrivateUserBindStateEnum;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserLogRepository;
import com.gs.lshly.biz.support.user.repository.IUserPrivateUserRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserPrivateUserService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserDTO;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserPrivateUserLogDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserPrivateUserQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserLogVO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserPrivateUserVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-12-24
*/
@Component
public class PCBbbUserPrivateUserServiceImpl implements IPCBbbUserPrivateUserService {

    @Autowired
    private IUserPrivateUserRepository repository;
    @Autowired
    private IUserPrivateUserLogRepository privateUserLogRepository;
    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @Override
    public PageData<PCBbbUserPrivateUserVO.ListVO> pageData(PCBbbUserPrivateUserQTO.QTO qto) {
        if(null == qto.getJwtUserId()){
            throw new BusinessException("没有登录");
        }
        QueryWrapper<UserPrivateUser> queryWrapper = MybatisPlusUtil.query();
        IPage<UserPrivateUser> pager = MybatisPlusUtil.pager(qto);
        queryWrapper.eq("user_id", qto.getJwtUserId());
        queryWrapper.eq("state",PrivateUserApplyStateEnum.通过.getCode());
        queryWrapper.eq("bind_state", PrivateUserBindStateEnum.关联.getCode());
        List<PCBbbUserPrivateUserVO.ListVO> voList = new ArrayList<>();
        repository.page(pager,queryWrapper);
        if(ObjectUtils.isEmpty(pager.getRecords())){
            return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
        }
        List<String> shopIdList = new ArrayList<>();
        for(UserPrivateUser privateShop:pager.getRecords()){
            shopIdList.add(privateShop.getShopId());
            PCBbbUserPrivateUserVO.ListVO listVO =  new PCBbbUserPrivateUserVO.ListVO();
            BeanUtils.copyProperties(privateShop,listVO);
            voList.add(listVO);
        }
        List<CommonShopVO.SimpleDetailVO> simpleDetailList  = commonShopRpc.listSimpleByShopIdList(shopIdList);
        for(CommonShopVO.SimpleDetailVO simpleDetailVO:simpleDetailList){
            for(PCBbbUserPrivateUserVO.ListVO listVO:voList){
                if(listVO.getShopId().equals(simpleDetailVO.getId())){
                    listVO.setShopId(simpleDetailVO.getId());
                    listVO.setShopLogo(simpleDetailVO.getShopLogo());
                    listVO.setShopName(simpleDetailVO.getShopName());
                    listVO.setShopDesc(simpleDetailVO.getShopDesc());
                    listVO.setShopScore(simpleDetailVO.getShopScore());
                }
            }
        }
        return MybatisPlusUtil.toPageData(voList,qto.getPageNum(),qto.getPageSize(),pager.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(PCBbbUserPrivateUserDTO.IdListDTO dto) {
        if (null == dto || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的店铺！");
        }
        UpdateWrapper<UserPrivateUser> wrapper = MybatisPlusUtil.update();
        wrapper.eq("user_id",dto.getJwtUserId());
        wrapper.in("shop_id",dto.getIdList());

        UserPrivateUser userPrivateUser = new UserPrivateUser();
        userPrivateUser.setBindState(PrivateUserBindStateEnum.解除.getCode());
        repository.update(userPrivateUser,wrapper);
    }

    @Override
    public PageData<PCBbbUserPrivateUserLogVO.ListVO> pageLogData(PCBbbUserPrivateUserQTO.QTO qto) {
        if (ObjectUtils.isEmpty(qto.getJwtUserId())){
            throw new BusinessException("未登录！！");
        }
        QueryWrapper<UserPrivateUserLog> wrapper = MybatisPlusUtil.query();
        wrapper.eq("user_id",qto.getJwtUserId());
        IPage<UserPrivateUserLog> page = MybatisPlusUtil.pager(qto);
        IPage<UserPrivateUserLog> userLogIPage = privateUserLogRepository.page(page,wrapper);
        return MybatisPlusUtil.toPageData(qto,PCBbbUserPrivateUserLogVO.ListVO.class,userLogIPage);
    }

    @Override
    public void addPrivateUserLog(PCBbbUserPrivateUserLogDTO.ETO eto) {
        if (ObjectUtils.isEmpty(eto)){
            throw new BusinessException("参数为空！！");
        }
        UserPrivateUserLog privateUserLog = new UserPrivateUserLog();
        BeanUtils.copyProperties(eto,privateUserLog);
        privateUserLogRepository.save(privateUserLog);
    }

    @Override
    public List<String> list(String userId) {
        if(StringUtils.isBlank(userId)){
            return new ArrayList<>();
        }
        QueryWrapper<UserPrivateUser> queryWrapper = MybatisPlusUtil.query();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("state",PrivateUserApplyStateEnum.通过.getCode());
        queryWrapper.eq("bind_state", PrivateUserBindStateEnum.关联.getCode());
        List<UserPrivateUser> userPrivateUserList = repository.list(queryWrapper);
        return ListUtil.getIdList(UserPrivateUser.class,userPrivateUserList,"shopId");
    }
}
