package com.gs.lshly.common.struct.common.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class CommonMerchantApplyVO implements Serializable {

    @Data
    @ApiModel("CommonMerchantApplyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("入驻申请ID")
        private String id;

        @ApiModelProperty("公司信息")
        private LegalDictVO.ListVO legalDictVO;

        @ApiModelProperty("店铺信息")
        private CommonShopVO.ListVO shopVO;

        @ApiModelProperty("拒绝时间")
        private LocalDateTime rejectTime;

        @ApiModelProperty("同意时间")
        private LocalDateTime okpassTime;

        @ApiModelProperty("拒绝通过原因")
        private String rejectWhy;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;




    }

    @Data
    @ApiModel("CommonMerchantApplyVO.DetailVO")
    public static class DetailVO implements Serializable{

        @ApiModelProperty(value = "入驻申请ID",position = 1)
        private String id;

        @ApiModelProperty(value = "流程进度[10=企业 20=银行 30=店铺 40=证照 50=审核 60=待签约]")
        private Integer progress;

        @ApiModelProperty(value = "公司信息",position = 2)
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "银行信息",position = 3)
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty(value = "店铺信息",position = 4)
        private CommonShopVO.ListVO shopVO;

        @ApiModelProperty(value = "证照信息数组",position = 5)
        private List<LegalDictVO.CertVO> certListVO;

        @ApiModelProperty(value = "审核信息",position = 6)
        private CommonMerchantApplyVO.VerifyVO verifyVO;

        @ApiModelProperty(value = "等待签约",position = 7)
        private CommonMerchantApplyVO.WaitSignUpVO waitSignUpVO;

    }

    @Data
    @ApiModel("CommonMerchantApplyVO.VerifyVO")
    public static class VerifyVO implements Serializable{

        @ApiModelProperty("申请时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("拒绝时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime rejectTime;

        @ApiModelProperty("拒绝通过原因")
        private String rejectWhy;

        @ApiModelProperty("同意时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime okpassTime;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

    }

    @Data
    @ApiModel("CommonMerchantApplyVO.WaitSignUpVO")
    public static class WaitSignUpVO implements Serializable{

        @ApiModelProperty("提交申请时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("审核通过时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime okpassTime;

    }

    @Data
    @ApiModel("CommonMerchantApplyVO.ShopVO")
    public static class ShopVO implements Serializable{

        @ApiModelProperty("入驻申请ID")
        private String id;

        @ApiModelProperty("店铺信息")
        private CommonShopVO.ListVO shopVO;

    }

    @Data
    @ApiModel("CommonMerchantApplyVO.ApplyCategoryRecordVO")
    public static class ApplyCategoryRecordVO implements Serializable{

        @ApiModelProperty("申请ID")
        private String id;

        @ApiModelProperty(value = "商品分类ID")
        private String goodsCategoryId;

        @ApiModelProperty("商品分类名称")
        private String goodsCategoryName;

        @ApiModelProperty("申请原因")
        private String applyWhy;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("审核时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime verifyTime;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;


    }
}
