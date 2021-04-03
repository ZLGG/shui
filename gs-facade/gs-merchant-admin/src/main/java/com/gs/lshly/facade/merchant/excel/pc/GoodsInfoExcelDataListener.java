package com.gs.lshly.facade.merchant.excel.pc;

import com.alibaba.excel.annotation.ExcelProperty;
import com.gs.lshly.common.enums.GoodsUsePlatformEnums;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.enums.commondity.GoodsStockSubtractTypeEnum;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.GoodsExcelImportDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsAttributeInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsSpecInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.common.utils.BeanCopyUtils;
import com.gs.lshly.middleware.redis.RedisUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsInfoImportRpc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

/**
 * @author lxus
 */
@Slf4j
public class GoodsInfoExcelDataListener extends BaseExcelImportDataListener<GoodsInfoExcelDataListener.GoodsInfoExcelData> {

    private IPCMerchGoodsInfoImportRpc importRpc;


    public GoodsInfoExcelDataListener(IPCMerchGoodsInfoImportRpc importRpc, RedisUtil redisUtil, String traceId) {
        super(redisUtil, traceId);
        this.importRpc = importRpc;
    }

    @Override
    protected void doValidSuccess(GoodsInfoExcelData data) {
        GoodsExcelImportDTO dto = new GoodsExcelImportDTO();
        BeanCopyUtils.copyProperties(data, dto);
        importRpc.saveOrUpdateData(dto);
    }

    @Override
    protected boolean doValid(Integer rowIndex, GoodsInfoExcelData data) {
        String shopId = importRpc.getShopId(new BaseDTO());
        GoodsExcelErrorInfoData errorInfoData = handleExcelData(data, shopId);
        if (errorInfoData.isError) {
            addError(rowIndex, errorInfoData.errColIndex, errorInfoData.errMsg, data);
            return false;
        }
        //只有返回true，才会调用doValidSuccess
        return true;
    }

    private GoodsExcelErrorInfoData handleExcelData(GoodsInfoExcelData data, String shopId) {
        GoodsExcelErrorInfoData errData = new GoodsExcelErrorInfoData();
        errData.isError = false;
        Integer useFiled = GoodsUsePlatformEnums.B商城和C商城.getCode();
        //第一步 校验必填数据
        if (StringUtils.isBlank(data.getCategoryLevel1Name())) {
            errData.errMsg = "商品一级类目不能为空！";
            errData.errColIndex = 1;
            errData.isError = true;
        } else if (StringUtils.isNotBlank(data.getCategoryLevel1Name())) {
            //判断类目是否店铺拥有的类目
            CommonShopVO.ShopGoodsCategoryVO categoryLevel1VO = importRpc.categoryLevelVO(data.getCategoryLevel1Name(), shopId);
            if (null == categoryLevel1VO) {
                errData.errMsg = "店铺没有该一级类目！";
                errData.errColIndex = 1;
                errData.isError = true;
            } else {
                //如果一级类目不为空 进行二级类目的判断
                if (StringUtils.isBlank(data.getCategoryLevel2Name())) {
                    errData.errMsg = "商品二级类目不能为空！";
                    errData.errColIndex = 2;
                    errData.isError = true;
                } else if (StringUtils.isNotBlank(data.getCategoryLevel2Name())) {
                    PCMerchGoodsCategoryVO.innerCategoryVO categoryLevel2VO = importRpc.categoryVo(data.getCategoryLevel2Name(), categoryLevel1VO.getCategoryId());
                    if (null == categoryLevel2VO) {
                        errData.errMsg = "店铺没有该二级类目！";
                        errData.errColIndex = 2;
                        errData.isError = true;
                    } else if (!categoryLevel1VO.getCategoryId().equals(categoryLevel2VO.getParentId())) {
                        errData.errMsg = "店铺商品二级类目与店铺商品一级类目不是子父级关系！";
                        errData.errColIndex = 2;
                        errData.isError = true;
                    } else {
                        //如果二级类目不为空，则进行三级类目判断
                        if (StringUtils.isBlank(data.getCategoryLevel3Name())) {
                            errData.errMsg = "商品三级类目不能为空！";
                            errData.errColIndex = 3;
                            errData.isError = true;
                        } else if (StringUtils.isNotBlank(data.getCategoryLevel3Name())) {
                            PCMerchGoodsCategoryVO.innerCategoryVO categoryLevel3VO = importRpc.categoryVo(data.getCategoryLevel3Name(), categoryLevel2VO.getId());
                            if (null == categoryLevel3VO) {
                                errData.errMsg = "店铺没有该三级类目！";
                                errData.errColIndex = 3;
                                errData.isError = true;
                            } else if (!categoryLevel3VO.getParentId().equals(categoryLevel2VO.getId())) {
                                errData.errMsg = "店铺商品二级类目与店铺商品三级类目不是子父级关系！";
                                errData.errColIndex = 3;
                                errData.isError = true;
                            }
                            if (StringUtils.isBlank(data.getBrandName())) {
                                errData.errMsg = "商品的品牌名称不能为空！";
                                errData.errColIndex = 6;
                                errData.isError = true;
                            } else if (StringUtils.isNotBlank(data.getBrandName())) {
                                //判断品牌是否是三级类目关联的品牌
                                boolean checkBrand = importRpc.countBrand(data.getBrandName(), categoryLevel3VO.getId());
                                if (!checkBrand) {
                                    errData.errMsg = "该三级类目下没有关联该品牌！";
                                    errData.errColIndex = 6;
                                    errData.isError = true;
                                }
                            }
                            useFiled = categoryLevel3VO.getUseFiled();
                        }
                    }
                }
            }
        }

        if (StringUtils.isBlank(data.getGoodsName())) {
            errData.errMsg = "商品标题不能为空！";
            errData.errColIndex = 4;
            errData.isError = true;
        } else {
            if (data.getGoodsName().length() > 40) {
                errData.errMsg = "商品名称长度不能超过40！";
                errData.errColIndex = 4;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getGoodsTitle())) {
            errData.errMsg = "商品副标题不能为空！";
            errData.errColIndex = 5;
            errData.isError = true;
        } else {
            if (data.getGoodsTitle().length() > 120) {
                errData.errMsg = "商品副标题长度不能超过120！";
                errData.errColIndex = 5;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getSalePrice())) {
            errData.errMsg = "商品的sku的销售价格不能为空!";
            errData.errColIndex = 7;
            errData.isError = true;
        } else {
            if (!checkNumber(data.getSalePrice())) {
                errData.errMsg = "商品sku的售价格式错误且价格最多为3位小数！";
                errData.errColIndex = 7;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getOldPrice())) {
            errData.errMsg = "商品的sku原价不能为空！";
            errData.errColIndex = 8;
            errData.isError = true;
        } else {
            if (!checkNumber(data.getOldPrice())) {
                errData.errMsg = "商品sku的原价价格式错误且价格最多为3位小数！";
                errData.errColIndex = 8;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getCostPrice())) {
            errData.errMsg = "商品的sku成本价不能为空！";
            errData.errColIndex = 9;
            errData.isError = true;
        } else {
            if (!checkNumber(data.getCostPrice())) {
                errData.errMsg = "商品sku的成本价格式错误且价格最多为3位小数！";
                errData.errColIndex = 9;
                errData.isError = true;
            }
        }


        if (StringUtils.isBlank(data.getStockNum())) {
            errData.errMsg = "sku商品库存不能为空！";
            errData.errColIndex = 10;
            errData.isError = true;
        } else {
            if (!checkRoundNum(data.getStockNum())) {
                errData.errMsg = "商品库存数量必须为正整数！";
                errData.errColIndex = 10;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getStockSubtractType())) {
            errData.errMsg = "商品的库存计数方式不能为空！";
            errData.errColIndex = 11;
            errData.isError = true;
        } else {
            if (!checkRoundNum(data.getStockSubtractType())) {
                errData.errMsg = "商品库存计数方式必须为正整数！";
                errData.errColIndex = 11;
                errData.isError = true;
            } else {
                Integer ways = Integer.parseInt(data.getStockSubtractType());
                if ((!ways.equals(GoodsStockSubtractTypeEnum.下单减库存.getCode()))
                        && (!ways.equals(GoodsStockSubtractTypeEnum.支付减库存.getCode()))) {
                    errData.errMsg = "商品库存计数方式值为10或者20！";
                    errData.errColIndex = 11;
                    errData.isError = true;
                }
            }
        }

        if (StringUtils.isBlank(data.getGoodsWeight())) {
            errData.errMsg = "商品的重量不能为空！";
            errData.errColIndex = 12;
            errData.isError = true;
        } else {
            if (!checkNumber(data.getGoodsWeight())) {
                errData.errMsg = "商品的重量格式错误且最多为3位小数！";
                errData.errColIndex = 12;
                errData.isError = true;
            }
        }
        if (StringUtils.isBlank(data.getGoodsValidDays())) {
            errData.errMsg = "商品的有效期不能为空！";
            errData.errColIndex = 14;
            errData.isError = true;
        } else {
            if (!checkRoundNum(data.getGoodsValidDays())) {
                errData.errMsg = "商品有效期必须为正整数！";
                errData.errColIndex = 14;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getSpecValue())) {
            errData.errMsg = "商品的规格值不能为空！";
            errData.errColIndex = 15;
            errData.isError = true;
        } else if (StringUtils.isNotBlank(data.getSpecValue())) {
            try {
                getSpecInfoList(data.getAttributeValue());
            } catch (Exception e) {
                e.printStackTrace();
                errData.errMsg = "商品规格值格式错误应为(规格名称a:规格值a,规格名称b:规格值b,....)！";
                errData.errColIndex = 15;
                errData.isError = true;
            }
        }

        if (StringUtils.isNotBlank(data.getAttributeValue())) {
            try {
                getAttributeList(data.getAttributeValue());
            } catch (Exception e) {
                e.printStackTrace();
                errData.errMsg = "商品属性值格式错误应为(属性名称a:属性值a,属性名称b:属性值b,...)！";
                errData.errColIndex = 16;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getTemplateName())) {
            errData.errMsg = "运费模板名称不能为空！";
            errData.errColIndex = 17;
            errData.isError = true;
        } else {
            //校验运费模板名称
            int count = importRpc.checkTemplate(shopId, data.getTemplateName());
            if (count <= 0) {
                errData.errMsg = "该店铺下不支持该运费模板名称！";
                errData.errColIndex = 17;
                errData.isError = true;
            }

        }

        if (StringUtils.isBlank(data.getShopNavigationName()) && (!useFiled.equals(GoodsUsePlatformEnums.C商城.getCode()))) {
            errData.errMsg = "店铺自定义2b名称不能为空！";
            errData.errColIndex = 18;
            errData.isError = true;
        } else {
            //校验店铺自定义类目名称
            int shopNavigationCount = importRpc.checkShopNavigation(shopId, data.getShopNavigationName(), TerminalEnum.BBB.getCode());
            if (shopNavigationCount <= 0) {
                errData.errMsg = "该店铺下没有该店铺2b自定义类目名称！";
                errData.errColIndex = 18;
                errData.isError = true;
            }
        }

        if (StringUtils.isBlank(data.getShopNavigation2cName()) && (!useFiled.equals(GoodsUsePlatformEnums.B商城.getCode()))) {
            errData.errMsg = "店铺自定义2c名称不能为空！";
            errData.errColIndex = 19;
            errData.isError = true;
        } else {
            //校验店铺自定义类目名称
            int shopNavigationCount = importRpc.checkShopNavigation(shopId, data.getShopNavigation2cName(), TerminalEnum.BBC.getCode());
            if (shopNavigationCount <= 0) {
                errData.errMsg = "该店铺下没有该店铺2c自定义类目名称！";
                errData.errColIndex = 19;
                errData.isError = true;
            }
        }
        if (StringUtils.isBlank(data.getGoodsNo())) {
            errData.errMsg = "商品的同一标识不能为空！";
            errData.errColIndex = 20;
            errData.isError = true;
        }

        return errData;
    }

    private static boolean checkNumber(String str) {
        // 判断小数点后3位的数字的正则表达式
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,3})?$");
        Matcher match = pattern.matcher(str);
        return match.matches();
    }

    private static boolean checkRoundNum(String num) {
        //正整数
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        Matcher match = pattern.matcher(num);
        return match.matches();
    }

    private List<PCMerchGoodsAttributeInfoDTO.ETO> getAttributeList(String attributeInfos) {
        List<String> stringList = Arrays.asList(attributeInfos.split(",")).stream().distinct().collect(toList());
        List<PCMerchGoodsAttributeInfoDTO.ETO> etoList = stringList.parallelStream()
                .map(e -> {
                    PCMerchGoodsAttributeInfoDTO.ETO eto = new PCMerchGoodsAttributeInfoDTO.ETO();
                    String[] arr = e.split(":");
                    eto.setAttributeName(arr[0]);
                    eto.setAttributeValue(arr[1]);
                    return eto;
                }).collect(toList());
        return etoList;
    }

    private List<PCMerchGoodsSpecInfoDTO.ETO> getSpecInfoList(String specInfos) {
        List<String> stringList = Arrays.asList(specInfos.split(",")).stream().distinct().collect(toList());
        List<PCMerchGoodsSpecInfoDTO.ETO> etoList = stringList.parallelStream()
                .map(e -> {
                    PCMerchGoodsSpecInfoDTO.ETO eto = new PCMerchGoodsSpecInfoDTO.ETO();
                    String[] arr = e.split(":");
                    eto.setSpecName(arr[0]);
                    eto.setSpecValue(arr[1]);
                    return eto;
                }).collect(toList());
        return etoList;
    }


    @Data
    @ApiModel("商品导入错误数据信息")
    public static class GoodsExcelErrorInfoData implements Serializable {

        @ApiModelProperty("数据是否错误 true = 错的 false = 对的")
        private Boolean isError;

        @ApiModelProperty("错误信息")
        private String errMsg;

        @ApiModelProperty("错误列数")
        private Integer errColIndex;

        @ApiModelProperty("错误行数")
        private Integer errRowIndex;

        @ApiModelProperty("商品错误数据")
        private GoodsInfoExcelData failData;
    }

    @Data
    @ApiModel("商品导入")
    public static class GoodsInfoExcelData extends BaseDTO {

        @ExcelProperty("一级类目")
        @ApiModelProperty(value = "一级类目", position = 1)
        private String categoryLevel1Name;

        @ExcelProperty("二级类目")
        @ApiModelProperty(value = "二级类目", position = 2)
        private String categoryLevel2Name;

        @ExcelProperty("三级类目")
        @ApiModelProperty(value = "三级类目", position = 3)
        private String categoryLevel3Name;

        @ExcelProperty("商品标题")
        @ApiModelProperty(value = "商品标题", position = 4)
        private String goodsName;

        @ExcelProperty("商品副标题")
        @ApiModelProperty(value = "商品副标题", position = 5)
        private String goodsTitle;

        @ExcelProperty("品牌")
        @ApiModelProperty(value = "品牌", position = 6)
        private String brandName;

        @ExcelProperty("sku销售价")
        @ApiModelProperty(value = "sku销售价", position = 7)
        private String salePrice;

        @ExcelProperty("sku原价")
        @ApiModelProperty(value = "sku原价", position = 8)
        private String oldPrice;

        @ExcelProperty("sku成本价")
        @ApiModelProperty(value = "sku成本价", position = 9)
        private String costPrice;

        @ExcelProperty("sku商品库存")
        @ApiModelProperty(value = "sku商品库存", position = 10)
        private String stockNum;

        @ExcelProperty("库存计数方式")
        @ApiModelProperty(value = "库存计数方式", position = 11)
        private String stockSubtractType;

        @ExcelProperty("重量")
        @ApiModelProperty(value = "重量", position = 12)
        private String goodsWeight;

        @ExcelProperty("计价单位")
        @ApiModelProperty(value = "计价单位", position = 13)
        private String chargeUnit;

        @ExcelProperty("商品有效期")
        @ApiModelProperty(value = "商品有效期", position = 14)
        private String goodsValidDays;

        @ExcelProperty("规格值")
        @ApiModelProperty(value = "规格值", position = 15)
        private String specValue;

        @ExcelProperty("属性值")
        @ApiModelProperty(value = "属性值", position = 16)
        private String attributeValue;

        @ExcelProperty("运费模板名称")
        @ApiModelProperty(value = "运费模板名称", position = 17)
        private String templateName;

        @ExcelProperty("店铺自定义2b类目名称")
        @ApiModelProperty(value = "店铺自定义2b类目名称", position = 18)
        private String shopNavigationName;

        @ExcelProperty("店铺自定义2c类目名称")
        @ApiModelProperty(value = "店铺自定义2c类目名称", position = 19)
        private String shopNavigation2cName;

        @ExcelProperty("同一商品标识")
        @ApiModelProperty(value = "同一商品标识", position = 20)
        private String goodsNo;

        @ApiModelProperty(value = "办理备注", position = 21)
        private String remarks;

        @ApiModelProperty(value = "是否是积分商品", position = 22)
        private String isPointGood;

        @ApiModelProperty(value = "积分价格", position = 23)
        private String pointPrice;

        @ApiModelProperty(value = "是否是in会员礼品", position = 24)
        private String isInMemberGift;

        @ApiModelProperty(value = "in会员积分价格", position = 25)
        private String inMemberPointPrice;

        @ApiModelProperty(value = "出售类型（0普通，1活动）", position = 26)
        private String saleType;

        @ApiModelProperty(value = "信天游产品号", position = 27)
        private String thirdProductId;

        @ApiModelProperty(value = "兑换类型（0实物，1虚拟）", position = 28)
        private String exchangeType;
    }


}
