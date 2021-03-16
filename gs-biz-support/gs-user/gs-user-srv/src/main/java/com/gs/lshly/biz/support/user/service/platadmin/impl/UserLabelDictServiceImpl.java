package com.gs.lshly.biz.support.user.service.platadmin.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.user.entity.UserLabel;
import com.gs.lshly.biz.support.user.entity.UserLabelDict;
import com.gs.lshly.biz.support.user.mapper.UserLabelMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserLabelView;
import com.gs.lshly.biz.support.user.repository.IUserLabelDictRepository;
import com.gs.lshly.biz.support.user.repository.IUserLabelRepository;
import com.gs.lshly.biz.support.user.service.platadmin.IUserLabelDictService;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.user.dto.UserLabelDictDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserLabelDictQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.ListUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-05
*/
@Component
public class UserLabelDictServiceImpl implements IUserLabelDictService {

    @Autowired
    private IUserLabelDictRepository repository;

    @Autowired
    private IUserLabelRepository userLabelRepository;

    @Autowired
    private UserLabelMapper userLabelMapper;

    @Override
    public PageData<UserLabelDictVO.ListVO> pageData(UserLabelDictQTO.QTO qto) {
        QueryWrapper<UserLabelDict> wq = new QueryWrapper<>();
        IPage<UserLabelDict> page = MybatisPlusUtil.pager(qto);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qto, UserLabelDictVO.ListVO.class, page);
    }

    @Override
    public List<UserLabelDictVO.ListVO> list() {
        return ListUtil.listCover(UserLabelDictVO.ListVO.class,repository.list());
    }

    @Override
    public void addUserLabelDict(UserLabelDictDTO.ETO eto) {
        if(repository.checkNameIsNotEmptyOnInsert(eto.getLabelName())){
            throw new BusinessException("标签名称以存在");
        }
        UserLabelDict userLabelDict = new UserLabelDict();
        BeanCopyUtils.copyProperties(eto, userLabelDict);
        repository.save(userLabelDict);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteBatchUserLabelDict(UserLabelDictDTO.IdListDTO dto) {
        if(ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("数组不能为空");
        }

        repository.removeByIds(dto.getIdList());
        QueryWrapper<UserLabel> removeQueryWrapper = new QueryWrapper<>();
        removeQueryWrapper.in("label_id",dto.getIdList());
        userLabelMapper.delete(removeQueryWrapper);

    }


    @Override
    public void editUserLabelDict(UserLabelDictDTO.ETO eto) {
        if(repository.checkNameIsNotEmptyOnUpdate(eto.getId(),eto.getLabelName())){
            throw new BusinessException("标签名称以存在");
        }
        UserLabelDict userLabelDict = new UserLabelDict();
        BeanCopyUtils.copyProperties(eto, userLabelDict);
        repository.updateById(userLabelDict);
    }


    @Override
    public void addUserLabel(UserLabelDictDTO.AddUserLabelDTO dto) {
        if(ObjectUtils.isEmpty(dto.getUserIdList()) || ObjectUtils.isEmpty(dto.getLabelIdList())){
            throw new BusinessException("数据不能为空");
        }
        userLabelRepository.remove(new QueryWrapper<UserLabel>().in("user_id",dto.getUserIdList()));
        List<UserLabel> userLabelList = new ArrayList<>();
        for(String userId:dto.getUserIdList()){
            for(String labelId:dto.getLabelIdList()){
                UserLabel userLabel = new UserLabel();
                userLabel.setUserId(userId).setLabelId(labelId);
                userLabelList.add(userLabel);
            }
        }
        userLabelRepository.saveBatch(userLabelList);
    }

//    @Override
//    public UserLabelDictVO.UserLabelVO getUserLabel(UserLabelDictQTO.UserIdQTO qto) {
//        QueryWrapper<UserLabelView> wrapper = new QueryWrapper<>();
//        wrapper.eq("md.user_id",qto.getUserId());
//        List<UserLabelView> dataList =  userLabelMapper.getUserLabel(wrapper);
//        UserLabelDictVO.UserLabelVO userLabelVO = new UserLabelDictVO.UserLabelVO();
//        userLabelVO.setLabelList(new ArrayList<>());
//        for(UserLabelView item:dataList){
//            userLabelVO.setUser_id(item.getUser_id());
//            UserLabelDictVO.UserLabelItemVO userLabelItem = new  UserLabelDictVO.UserLabelItemVO();
//            userLabelItem.setLabel_id(item.getLabel_id())
//                    .setLable_name(item.getLable_name())
//                    .setLable_color(item.getLable_color());
//            userLabelVO.getLabelList().add(userLabelItem);
//        }
//        return userLabelVO;
//    }

    @Override
    public List<UserLabelDictVO.UserLabelVO> getMoreUserLabel(UserLabelDictQTO.UserIdListQTO qto) {
        QueryWrapper<UserLabelView> wrapper = new QueryWrapper<>();
        wrapper.in("md.user_id",qto.getUserIdList());
        List<UserLabelView> dataList =  userLabelMapper.getUserLabel(wrapper);
        Map<String,UserLabelDictVO.UserLabelVO> dataMap = new HashMap<>();
        for(UserLabelView item:dataList){
            if(null == dataMap.get(item.getUser_id())){
                dataMap.put(item.getUser_id(),new UserLabelDictVO.UserLabelVO().setLabelList(new ArrayList<>()));
            }
            UserLabelDictVO.UserLabelVO userLabelVO = dataMap.get(item.getUser_id());
            userLabelVO.setUser_id(item.getUser_id());
            UserLabelDictVO.UserLabelItemVO userLabelItem= new  UserLabelDictVO.UserLabelItemVO();
            userLabelItem.setLabel_id(item.getLabel_id())
                    .setLable_name(item.getLable_name())
                    .setLable_color(item.getLable_color());
            userLabelVO.getLabelList().add(userLabelItem);
        }
        return new ArrayList(dataMap.values());
    };

}
