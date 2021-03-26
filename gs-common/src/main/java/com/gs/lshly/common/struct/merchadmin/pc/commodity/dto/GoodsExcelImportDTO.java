package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GoodsExcelImportDTO extends BaseDTO {

    private String categoryLevel1Name;

    private String categoryLevel2Name;

    private String categoryLevel3Name;

    private String goodsName;

    private String goodsTitle;

    private String brandName;

    private String salePrice;

    private String oldPrice;

    private String costPrice;

    private String stockNum;

    private String stockSubtractType;

    private String goodsWeight;

    private String chargeUnit;

    private String goodsValidDays;

    private String specValue;

    private String attributeValue;

    private String templateName;

    private String shopNavigationName;

    private String shopNavigation2cName;

    private String goodsNo;

    private String remarks;

    private String isPointGood;

    private String pointPrice;

    private String isInMemberGift;

    private String inMemberPointPrice;

    private String saleType;

    private String thirdProductId;

    private String exchangeType;



}
