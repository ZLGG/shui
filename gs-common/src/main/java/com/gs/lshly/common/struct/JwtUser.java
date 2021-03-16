package com.gs.lshly.common.struct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.gs.lshly.common.enums.UserStateEnum;
import com.gs.lshly.common.struct.common.PermitNodeVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lxus
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtUser implements UserDetails, Serializable {

    private String username;
    private String password;
    private Set<? extends GrantedAuthority> authorities;

    //自定义字段业务字段

    private String id;
    private Integer type;
    private String merchantId;
    private String shopId;
    private String phone;
    private String headImg;
    private String wxOpenid;
    @JsonIgnore
    private Integer state;

    //自定义框架字段

    @JsonIgnore
    private Date expire;
    @JsonIgnore
    private PermitNodeVO.PermitNodeTreeVO permitNode;

    private Boolean rememberMe;

    public JwtUser() {}

    public JwtUser(AuthDTO dto) {
        BeanCopyUtils.copyProperties(dto, this);
        this.setAuthorities(new HashSet<>());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getState() {
        return state;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public PermitNodeVO.PermitNodeTreeVO getPermitNode() {
        return permitNode;
    }

    public void setPermitNode(PermitNodeVO.PermitNodeTreeVO permitNode) {
        this.permitNode = permitNode;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() { // 最重点Ⅰ
        return this.password;
    }

    @Override
    public String getUsername() { // 最重点Ⅱ
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return state!=null && state.equals(UserStateEnum.启用.getCode());
    }
}
