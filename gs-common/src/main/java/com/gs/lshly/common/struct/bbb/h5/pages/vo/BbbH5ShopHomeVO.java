package com.gs.lshly.common.struct.bbb.h5.pages.vo;

import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbbH5ShopHomeVO implements Serializable {

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopHomeVO")
    @Accessors(chain = true)
    public static class ShopHomeVO implements Serializable{

        @ApiModelProperty(value = "店铺信息")
        private ShopInfoVo shopInfo;

        @ApiModelProperty(value = "店招信息")
        private ShopSignboardVo signboardVo;

        @ApiModelProperty(value = "店铺通栏广告图")
        private ShopAdvertVo  shopAdvertVo;

        @ApiModelProperty(value = "店铺自定义区域")
        private CustomAreaVo customAreaVo;

        @ApiModelProperty(value = "店铺菜单")
        private List<ShopMenuVo> menuList = new ArrayList<>();

        @ApiModelProperty(value = "店铺分类")
        private List<ShopNavigationVo> navigationList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopInfoVo")
    @Accessors(chain = true)
    public static class ShopInfoVo implements Serializable{

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String id;

        @ApiModelProperty("店铺类型[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]")
        private Integer shopType;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺地址")
        private String shopAddress;

        @ApiModelProperty("评分")
        private BigDecimal score;

        @ApiModelProperty("评分")
        private Integer sales;

        @ApiModelProperty("是否收藏[0=否 1=是]")
        private Integer favoriteState = TrueFalseEnum.否.getCode();
    }


    @Data
    @ApiModel("PCBbbShopHomeVO.ShopMenuVo")
    @Accessors(chain = true)
    public static class ShopMenuVo implements Serializable{

        @ApiModelProperty("菜单名称")
        private String id;

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("菜单类型[10=店铺分类 30=自定义文本]")
        private Integer menuType;

        @ApiModelProperty("二级菜单数组(只有一级菜单为店铺分类的时候有)")
        private List<ShopChildMenuVo> childMenu = new ArrayList<>();

    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopChildMenuVo")
    @Accessors(chain = true)
    public static class ShopChildMenuVo implements Serializable{

        @ApiModelProperty("菜单名称")
        private String id;

        @ApiModelProperty("菜单名称")
        private String menuName;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("菜单类型[10=店铺分类]")
        private Integer menuType;

    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopNavigationVo")
    @Accessors(chain = true)
    public static class ShopNavigationVo implements Serializable{

        @ApiModelProperty("店铺分类ID")
        private String id;

        @ApiModelProperty("店铺分类名称")
        private String navName;

        @ApiModelProperty("店铺分类层级")
        private Integer leve;

        @ApiModelProperty("店铺分类层级")
        private  List<ShopChildNavigationVo> child = new ArrayList<>();
    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopChildNavigationVo")
    @Accessors(chain = true)
    public static class ShopChildNavigationVo implements Serializable{

        @ApiModelProperty("店铺分类ID")
        private String id;

        @ApiModelProperty("店铺分类名称")
        private String navName;

        @ApiModelProperty("店铺分类层级")
        private Integer leve;

    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopSignboardVo")
    @Accessors(chain = true)
    public static class ShopSignboardVo implements Serializable{

        @ApiModelProperty("店招图片")
        private String columuPicture;

        @ApiModelProperty("是否显示店铺logo[10=是 20=否]")
        private Integer isShowLogo;

        @ApiModelProperty("是否显示店铺名称[10=是 20=否]")
        private Integer isShowName;

        @ApiModelProperty("是否显示店铺描述[10=是 20=否]")
        private Integer isShowDescription;

    }

    @Data
    @ApiModel("PCBbbShopHomeVO.ShopAdvertVo")
    @Accessors(chain = true)
    public static class ShopAdvertVo implements Serializable{

        @ApiModelProperty("跳转链接")
        private String jumpUrl;

        @ApiModelProperty("图片地址")
        private String imageUrl;

    }

    @Data
    @ApiModel("PCBbbShopHomeVO.CustomAreaVo")
    @Accessors(chain = true)
    public static class CustomAreaVo implements Serializable{

        @ApiModelProperty("自定义区域内容")
        private String content;

    }


    @Data
    @ApiModel("PCMerchShopFloorVO.FloorVO")
    @Accessors(chain = true)
    public static class FloorVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最多显示数量")
        private Integer showNum;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("商品列表")
        private List<PCBbbGoodsInfoVO.ShopInnerServiceVO> goodsList;

    }

}
