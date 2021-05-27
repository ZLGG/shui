package com.gs.lshly.biz.support.user.service.platadmin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.entity.UserIntegral;
import com.gs.lshly.biz.support.user.entity.UserLabel;
import com.gs.lshly.biz.support.user.enums.IntegralFromTypeEnum;
import com.gs.lshly.biz.support.user.enums.UserQueryTypeEnum;
import com.gs.lshly.biz.support.user.mapper.UserIntegralMapper;
import com.gs.lshly.biz.support.user.mapper.UserMapper;
import com.gs.lshly.biz.support.user.mapper.view.UserIntegralView;
import com.gs.lshly.biz.support.user.mapper.view.UserView;
import com.gs.lshly.biz.support.user.repository.IUserIntegralRepository;
import com.gs.lshly.biz.support.user.repository.IUserLabelRepository;
import com.gs.lshly.biz.support.user.repository.IUserLeveDictRepository;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.platadmin.IUserService;
import com.gs.lshly.common.enums.BusinessTypeEnum;
import com.gs.lshly.common.enums.EdAbleStateEnum;
import com.gs.lshly.common.enums.LegalTypeEnum;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.enums.user.TimePropEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;
import com.gs.lshly.common.struct.platadmin.user.qto.UserQTO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import com.gs.lshly.common.struct.platadmin.user.vo.UserVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.rpc.api.common.ILegalDictRpc;

/**
* <p>
*  服务实现类
* </p>
* @author xxfc
* @since 2020-10-05
*/
@Component
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserLeveDictRepository userLeveDictRepository;

    @Autowired
    private IUserIntegralRepository userIntegralRepository;

    @Autowired
    private UserIntegralMapper userIntegralMapper;

    @Autowired
    private IUserLabelRepository userLabelRepository;

    @DubboReference
    private ILegalDictRpc legalDictRpc;

    @Override
    public PageData<UserVO.ListVO> fullSearchList(UserQTO.FullSearchQTO qto) {
        QueryWrapper<UserView> wrapper = MybatisPlusUtil.query();
        wrapper.eq("us.type",qto.getType());
        if(StringUtils.isNotBlank(qto.getNick())){
            wrapper.like("us.nick",qto.getNick());
        }
        if(StringUtils.isNotBlank(qto.getRealName())){
            wrapper.like("us.real_name",qto.getRealName());
        }
        if(StringUtils.isNotBlank(qto.getFromShopId())){
            wrapper.like("us.from_shop_id",qto.getFromShopId());
        }
        if(StringUtils.isNotBlank(qto.getRealAddress())){
            wrapper.like("us.real_address",qto.getRealAddress());
        }
        if(ObjectUtils.isNotEmpty(qto.getCountyText())){
            wrapper.like("us.county_text",qto.getCountyText());
        }
        if(ObjectUtils.isNotEmpty(qto.getTimeProp()) && qto.getTimeProp().equals(TimePropEnum.是.getCode())){
            wrapper.eq("us.cdate",qto.getCdate1());
        }
        if(ObjectUtils.isNotEmpty(qto.getTimeProp()) && qto.getTimeProp().equals(TimePropEnum.早于.getCode())){
            wrapper.le("us.cdate",qto.getCdate1());
        }
        if(ObjectUtils.isNotEmpty(qto.getTimeProp()) && qto.getTimeProp().equals(TimePropEnum.晚于.getCode())){
            wrapper.ge("us.cdate",qto.getCdate1());
        }
        if(ObjectUtils.isNotEmpty(qto.getTimeProp()) && qto.getTimeProp().equals(TimePropEnum.介于.getCode())){
            wrapper.between("us.cdate",qto.getCdate1(),qto.getCdate2());
        }
        if(ObjectUtils.isNotEmpty(qto.getSex())){
            wrapper.eq("us.sex",qto.getSex());
        }
        if(ObjectUtils.isNotEmpty(qto.getIsInUser())){
            wrapper.eq("us.is_in_user",qto.getIsInUser());
        }
        if(ObjectUtils.isNotEmpty(qto.getTelecomsLevel())){
            wrapper.eq("us.telecoms_level",qto.getTelecomsLevel());
        }
        if(ObjectUtils.isNotEmpty(qto.getCity())){
            wrapper.eq("us.city",qto.getCity());
        }

        if(ObjectUtils.isNotEmpty(qto.getLabelId())){
            wrapper.like("tm.label_ids",qto.getLabelId());
        }

        wrapper.orderByDesc("us.cdate");
        IPage<UserView> page = MybatisPlusUtil.pager(qto);
        userMapper.pageList(page,wrapper);
        List< UserVO.ListVO> resultList = new ArrayList<>();
        for(UserView userViewItem:page.getRecords()){
            UserVO.ListVO listVO = new UserVO.ListVO();
            BeanCopyUtils.copyProperties(userViewItem,listVO);
            listVO.setUserLabelList(new ArrayList<>());
            if(ObjectUtils.isNotNull(userViewItem.getLabelIds(),userViewItem.getColors(),userViewItem.getLabelNames())){
                String [] ids = userViewItem.getLabelIds().split(",");
                String [] colors = userViewItem.getColors().split(",");
                String [] names = userViewItem.getLabelNames().split(",");
                for(int i =0;i<ids.length;i++){
                    UserLabelDictVO.UserLabelItemVO userLabelItemVO = new UserLabelDictVO.UserLabelItemVO();
                    userLabelItemVO.setLabel_id(ids[i]);
                    userLabelItemVO.setLable_name(names[i]);
                    userLabelItemVO.setLable_color(colors[i]);
                    listVO.getUserLabelList().add(userLabelItemVO);
                }
            }
            resultList.add(listVO);
        }
        return new PageData<>(resultList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public PageData<UserVO.ListVO> pageData(UserQTO.QTO qto) {
        QueryWrapper<UserView> wrapper = MybatisPlusUtil.query();
        if(StringUtils.isNotBlank(qto.getQueryValue())){
            if(UserQueryTypeEnum.用户名.getCode().equals(qto.getQueryType())){
                wrapper.like("us.user_name",qto.getQueryValue());
            }
            else if(UserQueryTypeEnum.手机号.getCode().equals(qto.getQueryType())){
                wrapper.like("us.phone",qto.getQueryValue());
            }
            else if(UserQueryTypeEnum.真实姓名.getCode().equals(qto.getQueryType())){
                wrapper.like("us.real_name",qto.getQueryValue());
            }
            else if(UserQueryTypeEnum.标签.getCode().equals(qto.getQueryType())){
                wrapper.like("tm.label_names",qto.getQueryValue());
            }
            else if(UserQueryTypeEnum.邮箱.getCode().equals(qto.getQueryType())){
                wrapper.like("us.to",qto.getQueryValue());
            }
        }
        wrapper.eq("us.type",qto.getType());
        IPage<UserView> page = MybatisPlusUtil.pager(qto);
        userMapper.pageList(page,wrapper);
        List< UserVO.ListVO> resultList = new ArrayList<>();
        for(UserView userViewItem:page.getRecords()){
            UserVO.ListVO listVO = new UserVO.ListVO();
            BeanCopyUtils.copyProperties(userViewItem,listVO);
            listVO.setUserLabelList(new ArrayList<>());
            if(ObjectUtils.isNotNull(userViewItem.getLabelIds(),userViewItem.getColors(),userViewItem.getLabelNames())){
                String [] ids = userViewItem.getLabelIds().split(",");
                String [] colors = userViewItem.getColors().split(",");
                String [] names = userViewItem.getLabelNames().split(",");
                for(int i =0;i<ids.length;i++){
                    UserLabelDictVO.UserLabelItemVO userLabelItemVO = new UserLabelDictVO.UserLabelItemVO();
                    userLabelItemVO.setLabel_id(ids[i]);
                    userLabelItemVO.setLable_name(names[i]);
                    userLabelItemVO.setLable_color(colors[i]);
                    listVO.getUserLabelList().add(userLabelItemVO);
                }
            }
            resultList.add(listVO);
        }
        return new PageData<>(resultList, qto.getPageNum(), qto.getPageSize(), page.getTotal());
    }

    @Override
    public UserVO.DetailVO details(UserDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("会员ID不能为空");
        }
        User user =  repository.getById(dto.getId());
        if(null == user){
            throw new BusinessException("会员不存在");
        }
        UserVO.DetailVO detailVO = new UserVO.DetailVO();
        BeanUtils.copyProperties(user,detailVO);
        QueryWrapper<UserIntegral> sumQueryWrapper = MybatisPlusUtil.query();
        sumQueryWrapper.eq("user_id",user.getId());
        sumQueryWrapper.groupBy("user_id");
        //可用积分
        UserIntegralView integralView = userIntegralMapper.sumCount(sumQueryWrapper);
        int integral = 0;
        if(null != integralView){
            integral = integralView.getQuantity();
        }
        detailVO.setIntegral(integral);
        //要过期积分
        UserIntegralView integralPassView = userIntegralMapper.sumCountPass(10,sumQueryWrapper);
        int integralPass = 0;
        if(null != integralPassView){
            integralPass = integralPassView.getQuantity();
        }
        detailVO.setPassIntegral(integralPass);
        return detailVO;
    }

    @Override
    public void setLabels(List<UserDTO.SetLabelDTO> dto) {
        if(ObjectUtils.isEmpty(dto)){
            throw new BusinessException("数组不能为空");
        }
        List<UserLabel> userLabelBatchList = new ArrayList<>();
        for(UserDTO.SetLabelDTO itemDTO:dto){
            if(ObjectUtils.isEmpty(itemDTO.getLabelList())){
                for(String labelId:itemDTO.getLabelList()){
                    UserLabel userLabel = new UserLabel();
                    userLabel.setUserId(itemDTO.getUserId());
                    userLabel.setLabelId(labelId);
                    userLabelBatchList.add(userLabel);
                }
            }
        }
        if(ObjectUtils.isEmpty(userLabelBatchList)){
            userLabelRepository.saveBatch(userLabelBatchList);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO.AddETO eto) {
        if (eto == null){
            throw new BusinessException("参数为空异常!!!");
        }
        if (StringUtils.isBlank(eto.getUserName())){
            throw new BusinessException("用户名名称不能为空！！");
        }
        if (StringUtils.isBlank(eto.getPhone())){
            throw new BusinessException("手机号不能为空！！");
        }
        if (StringUtils.isBlank(eto.getUserPwd())){
            throw new BusinessException("用户密码不能为空！！");
        }
        if (StringUtils.isBlank(eto.getCorpPersal())){
            throw new BusinessException("请填写企业联系人！");
        }
        if (StringUtils.isBlank(eto.getCorpPhone())){
            throw new BusinessException("请填写企业联系人电话！");
        }
        if (StringUtils.isBlank(eto.getPersonName())){
            throw new BusinessException("请填写法人姓名！");
        }
        if (StringUtils.isBlank(eto.getCorpLicenseCert())){
            throw new BusinessException("请上传营业执照！！");
        }
        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.eq("phone",eto.getPhone());
        int count = repository.count(wrapper);
        if (count >0){
            throw new BusinessException("该手机号已被注册！！");
        }
        //企业信息
        LegalDictDTO.ETO eto1 = new LegalDictDTO.ETO();
        eto1.setPersonName(eto.getPersonName());
        eto1.setCorpPersal(eto.getCorpPersal());
        eto1.setCorpPhone(eto.getCorpPersal());
        eto1.setCorpLicenseCert(eto.getCorpLicenseCert());
        eto1.setBusinessType(BusinessTypeEnum.买家.getCode());
        eto1.setLegalType(LegalTypeEnum.企业.getCode());

        String legalId =  legalDictRpc.addLegalDict(eto1);

        User user  = new User();
        user.setPhone(eto.getPhone());
        user.setUserName(eto.getUserName());
        user.setUserPwd(PwdUtil.encode(eto.getUserPwd()));
        user.setType(UserTypeEnum._2B用户.getCode());
        user.setState(UserStateEnum.启用.getCode());
        user.setLegalId(legalId);
        repository.save(user);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(UserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void deleteBatchUser(UserDTO.IdListDTO dto) {
        if (null == dto ||ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要删除的会员！！");
        }
        repository.removeByIds(dto.getIdList());
    }


    @Override
    public void editorUserInfo(UserDTO.ETO eto) {
        if (eto == null){
            throw new BusinessException("参数为空，异常");
        }
        User user = repository.getById(eto.getId());
        if(null == user){
            throw new BusinessException("会员不在存");
        }
        BeanCopyUtils.copyProperties(eto, user);
        user.setNick(eto.getWxname());
        repository.updateById(user);
    }

    @Override
    public void editorPassworld(UserDTO.PassworldETO eto) {
        if (eto == null ){
            throw new BusinessException("参数为空异常！！");
        }
        if(!eto.getUserPwd().equals(eto.getUserPwdCfm())){
            throw new BusinessException("确认密码输入错误");
        }
        User user = repository.getById(eto.getId());
        if(null == user){
            throw new BusinessException("会员不在存");
        }
        user.setUserPwd(PwdUtil.encode(eto.getUserPwd()));
        repository.updateById(user);
    }

    @Override
    public void editorIntegral(UserDTO.IntegralETO eto) {
        if (eto == null){
            throw new BusinessException("参数为空异常！！");
        }
        if (ObjectUtils.isEmpty(eto.getIntegral())){
            throw new BusinessException("请填写积分变动！！");
        }
        User user = repository.getById(eto.getId());
        if(null == user){
            throw new BusinessException("会员不在存");
        }
        UserIntegral userIntegral = new UserIntegral();
        userIntegral.setUserId(user.getId());
        userIntegral.setQuantity(eto.getIntegral());
        if(eto.getIntegral() > 0){
            userIntegral.setFromType(IntegralFromTypeEnum.平台添加.getCode());
            userIntegral.setEndDate(eto.getEndTime());
        }else{
            userIntegral.setFromType(IntegralFromTypeEnum.平台扣除.getCode());
        }
        userIntegralRepository.updateById(userIntegral);
    }

    @Override
    public void editorLeve(UserDTO.LeveETO eto) {
        if (eto == null){
            throw new BusinessException("参数为空异常！！");
        }
        User user = repository.getById(eto.getId());
        if(null == user){
            throw new BusinessException("会员不在存");
        }
        user.setLeveId(eto.getLeveId());
        user.setLeveName(eto.getLeveName());
        repository.updateById(user);
    }

    @Override
    public UserVO.DetailVO detailUser(UserDTO.IdDTO dto) {
        if(StringUtils.isBlank(dto.getId())){
            throw new BusinessException("会员ID不能为空");
        }
        User user =  repository.getById(dto.getId());
        if(null == user){
            throw new BusinessException("会员不存在");
        }
        UserVO.DetailVO detailVo = new UserVO.DetailVO();
        BeanCopyUtils.copyProperties(user, detailVo);
        return detailVo;
    }

    @Override
    public void enableUser(UserDTO.IdListDTO dto) {
        if (dto == null || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要启用的会员！！");
        }

        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());

        User user = new User();
        user.setState(EdAbleStateEnum.启用.getCode());
        repository.update(user,wrapper);
    }

    @Override
    public void disableUser(UserDTO.IdListDTO dto) {
        if (dto == null || ObjectUtils.isEmpty(dto.getIdList())){
            throw new BusinessException("请选择要停用的会员！！");
        }

        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.in("id",dto.getIdList());

        User user = new User();
        user.setState(EdAbleStateEnum.停用.getCode());
        repository.update(user,wrapper);
    }

    @Override
    public List<UserVO.ListVO> exportData(UserDTO.ExportDTO dto) {
        if (null == dto || ObjectUtils.isEmpty(dto.getUserIdList())){
            throw new BusinessException("请选择要导出的会员信息！！");
        }
        List<User> users = repository.listByIds(dto.getUserIdList());
        if (ObjectUtils.isEmpty(users)){
            throw new BusinessException("查询异常！！！");
        }
        List<UserVO.ListVO> listVOS = users.parallelStream().map(e ->{
            UserVO.ListVO listVO = new UserVO.ListVO();
            BeanCopyUtils.copyProperties(e,listVO);

            return listVO;
        }).collect(Collectors.toList());
        return listVOS;
    }


    @Override
    public void setUserLeve(UserDTO.SetUserLeveDTO dto) {
        if(StringUtils.isBlank(dto.getLeveId())){
            throw new BusinessException("会员等级ID不能为空");
        }
        if(StringUtils.isBlank(dto.getUserId())){
            throw new BusinessException("会员ID不能为空");
        }
       User user =  repository.getById(dto.getUserId());
        if(null == user){
            throw new BusinessException("会员不在存");
        }
        if(!userLeveDictRepository.checkIdExist(dto.getLeveId())){
            throw new BusinessException("无效的会员等级ID");
        }
        user.setLeveId(dto.getLeveId());
        repository.updateById(user);
    }

    @Override
    public UserVO.MiniVO mini(UserDTO.IdDTO dto) {
        User user = repository.getById(dto.getId());
        if(null == user){
            return null;
        }
        UserVO.MiniVO miniVO = new UserVO.MiniVO();
        BeanUtils.copyProperties(user,miniVO);
        return miniVO;
    }

    @Override
    public void addGongHuiUser(UserDTO.AddETO eto) {
        User user = new User();
        BeanCopyUtils.copyProperties(eto, user);
        repository.save(user);
    }

    @Override
    public UserVO.ListVO innerByPhone(String phone) {
        QueryWrapper<User> wrapper = MybatisPlusUtil.query();
        wrapper.eq("phone",phone);
        User user = repository.getOne(wrapper);
        if (ObjectUtils.isNotEmpty(user)){
            UserVO.ListVO listVO = new UserVO.ListVO();
            BeanUtils.copyProperties(user,listVO);
            return listVO;
        }
        return null;
    }

    @Override
    public UserVO.ListVO innerSave2BUser(UserDTO.InnerETO eto) {
        User user = new User();
        BeanUtils.copyProperties(eto,user);
        repository.saveOrUpdate(user);
        UserVO.ListVO listVO = new  UserVO.ListVO();
        BeanUtils.copyProperties(user,listVO);
        return listVO;
    }
}
