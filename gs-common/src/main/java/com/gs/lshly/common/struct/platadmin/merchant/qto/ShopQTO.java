package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class ShopQTO implements Serializable {

    @Data
    @ApiModel("ShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺名字")
        private String shopName;

        @ApiModelProperty("店主手机号")
        private String phone;

        @ApiModelProperty("身份证号")
        private String userCardId;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("店主姓名")
        private String realName;

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;

    }

    @Data
    @ApiModel("ShopQTO.SelfShopQTO")
    @Accessors(chain = true)
    public static class SelfShopQTO extends BaseQTO {

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;

    }


}
