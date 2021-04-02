package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserSignIn;
import com.gs.lshly.biz.support.user.repository.IUserSignInRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IPCBbbUserSignInService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserSignInDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserSignInQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserSignInVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author zdf
* @since 2021-01-13
*/
@Component
public class PCBbbUserSignInServiceImpl implements IPCBbbUserSignInService {

    @Autowired
    private IUserSignInRepository repository;

    @Override
    public PageData<PCBbbUserSignInVO.ListVO> pageData(PCBbbUserSignInQTO.QTO qto) {
        QueryWrapper<UserSignIn> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("cdate");
        IPage<UserSignIn> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCBbbUserSignInVO.ListVO.class, page);
    }

    @Override
    public void addUserSignIn(PCBbbUserSignInDTO.ETO eto) {
        UserSignIn userSignIn = new UserSignIn();
        BeanUtils.copyProperties(eto, userSignIn);
        repository.save(userSignIn);
    }


    @Override
    public void deleteUserSignIn(PCBbbUserSignInDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }
    @Override
    public void editUserSignIn(PCBbbUserSignInDTO.ETO eto) {
        UserSignIn userSignIn = new UserSignIn();
        BeanUtils.copyProperties(eto, userSignIn);
        repository.updateById(userSignIn);
    }

    @Override
    public PCBbbUserSignInVO.DetailVO detailUserSignIn(PCBbbUserSignInDTO.IdDTO dto) {
        UserSignIn userSignIn = repository.getById(dto.getId());
        PCBbbUserSignInVO.DetailVO detailVo = new PCBbbUserSignInVO.DetailVO();
        if(ObjectUtils.isEmpty(userSignIn)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(userSignIn, detailVo);
        return detailVo;
    }

}
