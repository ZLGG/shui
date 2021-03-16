package com.gs.lshly.biz.support.user.service.bbb.pc.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserCard;
import com.gs.lshly.biz.support.user.repository.IUserCardRepository;
import com.gs.lshly.biz.support.user.service.bbb.pc.IBbbUserCardService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.user.dto.PCBbbUserCardDTO;
import com.gs.lshly.common.struct.bbb.pc.user.qto.PCBbbUserCardQTO;
import com.gs.lshly.common.struct.bbb.pc.user.vo.PCBbbUserCardVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;


/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-12-09
*/
@Component
public class BbbUserCardServiceImpl implements IBbbUserCardService {

    @Autowired
    private IUserCardRepository repository;

    @Override
    public PageData<PCBbbUserCardVO.ListVO> pageData(PCBbbUserCardQTO.QTO qto) {
        QueryWrapper<UserCard> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",qto.getJwtUserId());
        IPage<UserCard> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, PCBbbUserCardVO.ListVO.class, page);
    }

    @Override
    public void addUserCard(PCBbbUserCardDTO.ETO eto) {
        UserCard userCard = new UserCard();
        BeanUtils.copyProperties(eto, userCard);
        repository.save(userCard);
    }

    @Override
    public void deleteUserCard(PCBbbUserCardDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public PCBbbUserCardVO.DetailVO detailUserCard(PCBbbUserCardDTO.IdDTO dto) {
        UserCard userCard = repository.getById(dto.getId());
        PCBbbUserCardVO.DetailVO detailVo = new PCBbbUserCardVO.DetailVO();
        if(ObjectUtils.isEmpty(userCard)){
            throw new BusinessException("没有数据");
        }
        BeanUtils.copyProperties(userCard, detailVo);
        return detailVo;
    }

}
