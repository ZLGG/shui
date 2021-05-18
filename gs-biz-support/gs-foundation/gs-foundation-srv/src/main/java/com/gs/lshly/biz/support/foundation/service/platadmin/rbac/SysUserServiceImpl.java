package com.gs.lshly.biz.support.foundation.service.platadmin.rbac;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.gs.lshly.biz.support.foundation.entity.SysUser;
import com.gs.lshly.biz.support.foundation.entity.SysUserRole;
import com.gs.lshly.biz.support.foundation.repository.ISysFuncRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysRoleRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRepository;
import com.gs.lshly.biz.support.foundation.repository.ISysUserRoleRepository;
import com.gs.lshly.common.enums.SysUserTypeEnum;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.enums.UserTypeEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.AuthDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO.CheckDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO.GetPhoneValidCodeDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.rbac.SysUserDTO.LoginDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.rbac.SysUserQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SysUserFuncVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.rbac.SysUserVO.DetailVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.common.utils.PwdUtil;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.middleware.sms.ISMSService;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *  服务实现类
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@SuppressWarnings({"unchecked","rawtypes"})
@Slf4j
@Component
public class SysUserServiceImpl implements ISysUserService {
	
    private static final String PhoneValidCodeGroup = "PlatformPhone_User_";

    @Autowired
    private ISysUserRepository repository;

    @Autowired
    private ISysUserRoleRepository userRoleRepository;

    @Autowired
    private ISysRoleRepository roleRepository;

    @Autowired
    private ISysFuncRepository funcRepository;
    
    @Autowired
    private ISMSService smsService;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Override
    public PageData<SysUserVO.ListVO> pageData(SysUserQTO.QTO qoDTO) {
        QueryWrapper<SysUser> wq =  MybatisPlusUtil.query();
        MybatisPlusUtil.like(qoDTO, wq, "name");
		IPage<SysUser> page = MybatisPlusUtil.pager(qoDTO);
        repository.page(page, wq);
        return MybatisPlusUtil.toPageData(qoDTO, SysUserVO.ListVO.class, page);
    }

    @Override
    public void saveUser(SysUserDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        user.setPwd(PwdUtil.encode(dto.getPwd()));
        repository.save(user);
    }

    @Override
    public void editUser(SysUserDTO.ETO dto) {
        this.uniqueCheck(dto);
        SysUser user = new SysUser();
        BeanUtils.copyProperties(dto, user);
        if (StrUtil.isNotBlank(dto.getPwd())) {
            user.setPwd(PwdUtil.encode(dto.getPwd()));
        }
        repository.updateById(user);
    }

    @Override
    public void delete(SysUserDTO.IdDTO dto) {
        repository.removeById(dto.getId());
    }

    @Override
    public void enable(SysUserDTO.IdDTO dto) {
        repository.updateById(new SysUser().setId(dto.getId()).setState(UserStateEnum.启用.getCode()));
    }

    @Override
    public void disable(SysUserDTO.IdDTO dto) {
        repository.updateById(new SysUser().setId(dto.getId()).setState(UserStateEnum.停用.getCode()));
    }

    @Override
    public SysUserVO.DetailVO detail(SysUserDTO.IdDTO dto) {
        SysUser user = repository.getById(dto.getId());
        SysUserVO.DetailVO detail = new SysUserVO.DetailVO();
        BeanUtils.copyProperties(user, detail);
        return detail;
    }

    @Override
    public void changePassword(SysUserDTO.EditPasswordDTO dto) {
        SysUser user = repository.getById(dto.getId());
        if (user == null) {
            throw new BusinessException("用户未找到");
        }
        if (PwdUtil.matches(dto.getOldPwd(),user.getPwd())) {
            repository.updateById(new SysUser().setId(user.getId()).setPwd(PwdUtil.encode(dto.getNewPwd())));
        } else {
            throw new BusinessException("旧密码错误");
        }
    }

    @Override
    public void uniqueCheck(SysUserDTO.ETO dto) {
		QueryWrapper qw = new QueryWrapper<SysUser>().eq("name", dto.getName());
        if (dto.getId() != null) {
            qw.ne("id", dto.getId());
        }
        if (repository.count(qw) > 0) {
            throw new BusinessException("已有[" + dto.getName() + "]登陆名的管理账号");
        }
    }

    @Override
    public AuthDTO findByUserName(String username) {
        SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", username));
        if (user == null) {
            return null;
        }
        AuthDTO authDTO = new AuthDTO().setId(user.getId())
                .setType(user.getType() != null && user.getType().equals(SysUserTypeEnum.运营管理账号.getCode())
                        ? UserTypeEnum.平台运营账号.getCode()
                        : UserTypeEnum.平台超管账号.getCode())
                .setPassword(user.getPwd())
                .setUsername(user.getName())
                .setState(user.getState());
        //1，查询用户角色
//        List<SysFuncVO.List> funcs = funcRepository.baseMapper().selectUserFuncs(user.getId());
//        
        //先查询所有的跟目录
        List<SysUserFuncVO.ListVO> funcs = funcRepository.baseMapper().selectUserFuncsParent(user.getId());
        for(SysUserFuncVO.ListVO listVO:funcs){
        	String parentId = listVO.getId();
        	List<SysUserFuncVO.ListVO> children = funcRepository.baseMapper().selectUserFuncsByParent(user.getId(),parentId);
        	listVO.setChildren(children);
        	if(CollectionUtils.isNotEmpty(children)){
	        	for(SysUserFuncVO.ListVO listVO1:children){
	        		parentId = listVO1.getId();
	        		children = funcRepository.baseMapper().selectUserFuncsByParent(user.getId(),parentId);
	        		if(CollectionUtils.isNotEmpty(children)){
	        			listVO.setChildren(children);
	        		}
	        	}
        	}
        }
        return authDTO.setPermitFuncsTree(funcs);
    }
    
    /**
     * 账号角色列表
     * @param dto
     * @return
     */
    @Override
    public List<SysUserVO.UserRoleVO> roles(SysUserDTO.IdDTO dto) {
        return roleRepository.baseMapper().userRoles(dto.getId());
    }

    /**
     * 添加账号角色
     * @param eto
     */
    @Override
    public void addUserRolePermit(SysUserDTO.UserRoleETO eto) {
        List<SysUserRole> userRoles = new ArrayList<>();
        for (String roleId : eto.getRoleIds()) {
            userRoles.add(new SysUserRole().setRoleId(roleId).setUserId(eto.getUserId()));
        }
        userRoleRepository.saveBatch(userRoles);
    }

    /**
     * 删除账号角色
     * @param eto
     */
    @Override
    public void deleteUserRolePermit(SysUserDTO.UserRoleETO eto) {
        userRoleRepository.remove(new QueryWrapper<SysUserRole>().eq("user_id", eto.getUserId()).in("role_id", eto.getRoleIds()));
    }

	@Override
	public void getPhoneValidCode(GetPhoneValidCodeDTO dto) {
		//通过手机查找是已经注册了会员，如果已经注册，则发送用户登陆验证码，否则发送注册验证码
        SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", dto.getPhone()));
        String validCode = null;
        try {
            if (user != null) {
                validCode = smsService.sendLoginSMSCode(dto.getPhone());
            } else {
            	throw new BusinessException("跟据手机号码未获取到对应帐号");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BusinessException("短信发送失败!" + (e.getMessage().contains("限流") ? "发送频率过高" : ""));
        }
        //验证码失效时间10分账
        log.info("运营平台跟据手机号码获取验证码登录-手机号码："+dto.getPhone()+"-验证码："+validCode);
        redisUtil.set(PhoneValidCodeGroup + dto.getPhone(), validCode, 10 * 60);
		
	}

	@Override
	public AuthDTO login(LoginDTO dto) {
		
		/**校验验证码
		Object code = redisUtil.get(PhoneValidCodeGroup + dto.getPhone());
		String validCode = code != null ? code + "" : "";
		log.info("获取-手机号码：" + dto.getPhone() + "-验证码：" + validCode);
		if (!StringUtils.equals(validCode, dto.getValidCode())) {
			throw new BusinessException("验证码不匹配");
		}
		**/
		AuthDTO authDTO = new AuthDTO();
		
		SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", dto.getPhone()));
        authDTO = merchantUserToAuthDTO(user);
        
        return this.findByUserName(authDTO.getUsername());
        
	}
	
	private AuthDTO merchantUserToAuthDTO(SysUser user) {
        if (user == null) {
            return null;
        }
        AuthDTO authDTO =  new AuthDTO();
        authDTO.setId(user.getId())
                .setPassword(user.getPwd())
                .setUsername(user.getName())
                .setHeadImg(user.getHeadImg())
                .setState(user.getState());
        return authDTO;
    }

	@Override
	public Boolean checkPhoneCode(CheckDTO dto) {
		
		/**
		 * 验证验证码
		 */
		
		SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", dto.getUsername()));
        if(user!=null){
        	if(PwdUtil.matches(dto.getPassword(),user.getPwd())){
        		return true;
        	}else{
        		throw new BusinessException("密码输入错误！");
        	}
        }
        throw new BusinessException("帐号不存在！");
	}

	@Override
	public DetailVO getSysUserByName(String name) {
		SysUser user = repository.getOne(new QueryWrapper<SysUser>().eq("name", name));
		DetailVO detailVO = new DetailVO();
		if(user!=null){
			BeanCopyUtils.copyProperties(user, detailVO);
		}
		return detailVO;
	}
}
