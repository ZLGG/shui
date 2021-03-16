package com.gs.lshly.biz.support.user.service.pos.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.user.entity.User;
import com.gs.lshly.biz.support.user.repository.IUserRepository;
import com.gs.lshly.biz.support.user.service.pos.IPosMemberService;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.pos.dto.PosMemberRequestDTO;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Starry
 * @Date 13:21 2021/2/25
 */
@Component
@Slf4j
public class PosMemberServiceImpl implements IPosMemberService {

    @Autowired
    private IUserRepository repository;

    private String pwd = "123456";


    @Override
    public void pushPosMember(PosMemberRequestDTO.DTO dto) {

      //数据校验
      checkData(dto);

      //手机号是否注册检查
        QueryWrapper<User> userQueryWrapper = MybatisPlusUtil.query();
        userQueryWrapper.eq("phone",dto.getPhone());
        User user = repository.getOne(userQueryWrapper);
        if(null == user){
            user  = new User();
            user.setPhone(dto.getPhone());
            user.setUserName(dto.getPhone());
            user.setNick(dto.getNickName());
            user.setUserPwd(PwdUtil.encode(pwd));
            user.setType(UserTypeEnum._2C用户.getCode());
            user.setState(UserStateEnum.启用.getCode());
            repository.save(user);
        }
    }

    private void checkData(PosMemberRequestDTO.DTO dto){
        if (null == dto){
            throw new BusinessException("参数不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getTimestamp())){
            throw new BusinessException("当前时间毫秒数不能为空！");
        }
        if (ObjectUtils.isEmpty(dto.getNonce())){
            throw new BusinessException("6位随机字符不能为空！");
        }
        if (StringUtils.isEmpty(dto.getPhone())){
            throw new BusinessException("手机号不能为空！");
        }
        if (StringUtils.isNotBlank(dto.getPhone()) && isPhone(dto.getPhone()) == false){
            throw new BusinessException("手机号格式不正确！");
        }

    }

    private static boolean isPhone(String phone){
        Pattern p = Pattern.compile("^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
}
