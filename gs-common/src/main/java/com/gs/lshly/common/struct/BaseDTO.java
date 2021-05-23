package com.gs.lshly.common.struct;

import com.gs.lshly.common.exception.BusinessException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * BaseDTO
 * @author lxus
 * @since 2020/9/14
 */
@SuppressWarnings("serial")
@Data
@Accessors(chain = true)
public class BaseDTO implements Serializable {

    @ApiModelProperty(value = "登陆账户ID", hidden = true)
    private String jwtUserId;

    @ApiModelProperty(value = "登陆账户名称", hidden = true)
    private String jwtUserName;

    @ApiModelProperty(value = "登陆用户类型(10=2B用户;20=2C用户;30=2B商家账号;40=2C商家账号;50=平台运营账号;60=平台超管账号)", hidden = true)
    private Integer jwtUserType;

    @ApiModelProperty(value = "商家ID", hidden = true)
    private String jwtMerchantId;

    @ApiModelProperty(value = "店铺ID", hidden = true)
    private String jwtShopId;

    @ApiModelProperty(value = "微信openid", hidden = true)
    private String jwtWxOpenid;

    public <T,S> List<T> toListData(Class<T> clazz, List<S> data, IObjConvert<T,S>... converts) {
        List<T> voList = new ArrayList<>();
        if (data==null || data.isEmpty()) {
            return voList;
        }
        for (S source : data) {
            try {
                T t = clazz.newInstance();
                BeanUtils.copyProperties(source, t);
                voList.add(t);

                if (converts == null) {
                    continue;
                }

                for(IObjConvert<T,S> convert : converts){
                    convert.cnv(t, source);
                }
            } catch (Exception e) {
                throw new BusinessException("during copy vo.");
            }
        }
        return voList;
    }
    public <T,S> List<T> toListData(Class<T> clazz, List<S> data) {
        return toListData(clazz, data, null);
    }

}
