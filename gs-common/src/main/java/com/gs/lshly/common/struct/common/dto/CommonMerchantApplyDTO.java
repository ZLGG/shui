package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class CommonMerchantApplyDTO implements Serializable {

    @Data
    @ApiModel("CommonMerchantApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "企业字典主体类型[10=个人 20=企业]",hidden = true)
        private Integer legalType;

        @ApiModelProperty(value = "提交信息类型[10=企业信息 20=银行信息 30=店铺信息 40=证照信息]",position = 2)
        private Integer dataType;

        @ApiModelProperty(value = "公司信息",position = 3)
        private LegalDictDTO.CompanyDTO companyETO;

        @ApiModelProperty(value = "银行信息",position = 4)
        private LegalDictDTO.BankDTO bankDTO;

        @ApiModelProperty(value = "店铺信息",position = 5)
        private CommonShopDTO.ETO shopETO;

        @ApiModelProperty(value = "证照信息数组",position = 6)
        private List<LegalDictDTO.CertDTO> certListDTO;

    }


    @Data
    @ApiModel("CommonCommonMerchantApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("CommonCommonMerchantApplyDTO.QueryDTO")
    public static class QueryDTO extends BaseDTO {
        @ApiModelProperty(value = "查询信息类型[10=企业信息 20=银行信息 30=店铺信息 40=证照信息 50=等待审核 59=审核失败 60=待签约 70=流程进度]")
        private Integer queryType;
    }


    @Data
    @ApiModel("CommonCommonMerchantApplyDTO.ApplyCategoryDTO")
    public static class ApplyCategoryDTO extends BaseDTO {

        @ApiModelProperty(value = "申请原因")
        private String applyWhy;

        @ApiModelProperty(value = "终端",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "商品分类信息")
        private CommonShopDTO.CategoryETO category;

    }



}
