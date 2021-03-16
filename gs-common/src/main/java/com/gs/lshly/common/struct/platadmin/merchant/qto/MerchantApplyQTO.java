package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseDTO;
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
* @since 2020-10-14
*/
public abstract class MerchantApplyQTO implements Serializable {

    @Data
    @ApiModel("MerchantApplyQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询条件类型[10=用户名 20=手机号]")
        private Integer conditionType;

        @ApiModelProperty("查询条件值")
        private String conditionValue;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审 40=全部]")
        private Integer state;
    }

    @Data
    @ApiModel("MerchantApplyQTO.SearchQTO")
    @Accessors(chain = true)
    public static class SearchQTO extends BaseQTO {

        @ApiModelProperty("申请状态[10=待审 20=通过 30=拒审 31=已开店铺 40=全部]")
        private Integer state;

        @ApiModelProperty("修改时间类型[10=今天 20=昨天 30=最近7天 40=取近30天]")
        private Integer udateType;

        @ApiModelProperty("商家帐号")
        private String userName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺类型[10品牌旗舰店 20品牌专卖店 30类目专营店 40运营商自营 50多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("公司名称")
        private String corpName;
    }

    @Data
    @ApiModel("MerchantApplyQTO.handBrandQueryQTO")
    @Accessors(chain = true)
    public static class HandBrandQueryQTO extends BaseDTO {

        @ApiModelProperty("查询条件类型[10=用户名 20=手机号]")
        private Integer conditionType;

        @ApiModelProperty("查询条件值")
        private String conditionValue;

    }

    @Data
    @ApiModel("MerchantApplyQTO.ServiceDetailQTO")
    @Accessors(chain = true)
    public static class ServiceDetailQTO extends BaseQTO {

        @ApiModelProperty("申请数据ID")
        private String id;

        @ApiModelProperty("申请会员和商家类型")
        private Integer serMerchantTtype;


    }
}
