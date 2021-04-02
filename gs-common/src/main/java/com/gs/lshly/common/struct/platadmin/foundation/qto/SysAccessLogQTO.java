package com.gs.lshly.common.struct.platadmin.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 */
@Data
@Accessors(chain = true)
@ApiModel("SysAccessLogQTO")
public class SysAccessLogQTO implements Serializable {

    @Data
    @ApiModel("SysAccessLogQTO.Operator")
    @Accessors(chain = true)
    public static class Operator extends BaseQTO {

        @ApiModelProperty("管理员id")
        String userId;

        @ApiModelProperty("管理员用户名")
        String userName;
    }

    @Data
    @ApiModel("SysAccessLogQTO.UserLogin")
    @Accessors(chain = true)
    public static class UserLogin extends BaseQTO {

        @ApiModelProperty("会员id")
        String userId;

        @ApiModelProperty("会员账号")
        String userName;
    }

    @Data
    @ApiModel("SysAccessLogQTO.MerchantLogin")
    @Accessors(chain = true)
    public static class MerchantLogin extends BaseQTO {

        @ApiModelProperty("店员id")
        String userId;

        @ApiModelProperty("店员用户名")
        String userName;

        @ApiModelProperty("店铺id")
        String shopId;

        @ApiModelProperty("店铺名称")
        String shopName;

    }

    @Data
    @ApiModel("SysAccessLogQTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty("日志id")
        List<String> ids;

    }

}
