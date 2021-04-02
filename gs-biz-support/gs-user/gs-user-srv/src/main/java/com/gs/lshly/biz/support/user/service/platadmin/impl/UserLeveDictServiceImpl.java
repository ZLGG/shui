package com.gs.lshly.biz.support.user.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserLeveDict;
import com.gs.lshly.biz.support.user.repository.IUserLeveDictRepository;
import com.gs.lshly.biz.support.user.service.platadmin.IUserLeveDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLeveDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLeveDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLeveDictVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
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
* @author xxfc
* @since 2020-10-05
*/
@Component
public class UserLeveDictServiceImpl implements IUserLeveDictService {

    @Autowired
    private IUserLeveDictRepository repository;

    @Override
    public PageData<UserLeveDictVO.ListVO> pageData(UserLeveDictQTO.QTO qto) {
        QueryWrapper<UserLeveDict> wrapper = new QueryWrapper<>();
        if(ObjectUtils.isNull(qto.getLeveType())){
            throw new BusinessException("查询参数不能为Null");
        }
        wrapper.eq("leve_type",qto.getLeveType());
        wrapper.orderByDesc("cdate");
        IPage<UserLeveDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wrapper);
        return MybatisPlusUtil.toPageData(qto, UserLeveDictVO.ListVO.class, page);
    }

    @Override
    public void addUserLeveDict(UserLeveDictDTO.ETO eto) {
        if(repository.checkNameIsNotEmptyOnInsert(eto.getName(),eto.getLeveType())){
            throw new BusinessException("等级名称已存在");
        }
        UserLeveDict userLeveDict = new UserLeveDict();
        BeanCopyUtils.copyProperties(eto, userLeveDict);
        repository.save(userLeveDict);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatchUserLeveDict(UserLeveDictDTO.IdListDTO dto) {
        if(null == dto || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("删除的数据不能为空");
        }
        repository.removeByIds(dto.getIdList());
    }


    @Override
    public void editUserLeveDict(UserLeveDictDTO.ETO eto) {
        if(repository.checkNameIsNotEmptyOnUpdate(eto.getId(),eto.getName(),eto.getLeveType())){
            throw new BusinessException("等级名称已存在");
        }
        UserLeveDict userLeveDict = new UserLeveDict();
        BeanCopyUtils.copyProperties(eto, userLeveDict);
        repository.updateById(userLeveDict);
    }

    @Override
    public UserLeveDictVO.DetailVO detailUserLeveDict(UserLeveDictDTO.IdDTO dto) {
        UserLeveDict userLeveDict = repository.getById(dto.getId());
        UserLeveDictVO.DetailVO detailVo = new UserLeveDictVO.DetailVO();
        if(ObjectUtils.isEmpty(userLeveDict)){
            throw new BusinessException("没有数据");
        }
        BeanCopyUtils.copyProperties(userLeveDict, detailVo);
        return detailVo;
    }

    @Override
    public List<UserLeveDictVO.LevelListVO> lisLevelListVO(UserLeveDictDTO.UsingTypeDTO dto) {
        if (dto == null){
            throw new BusinessException("参数为空，异常！！");
        }
        QueryWrapper<UserLeveDict> wrapper = MybatisPlusUtil.query();
        wrapper.eq("leve_type",dto.getLeveType());
        wrapper.select("id","name");
        List<UserLeveDict> leveDicts = repository.list(wrapper);
        if (ObjectUtils.isEmpty(leveDicts)){
            return new ArrayList<>();
        }
        List<UserLeveDictVO.LevelListVO> levelListVOS = ListUtil.listCover(UserLeveDictVO.LevelListVO.class,leveDicts);

        return levelListVOS;
    }

}
