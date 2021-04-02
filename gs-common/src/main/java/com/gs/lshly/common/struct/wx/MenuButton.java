package com.gs.lshly.common.struct.wx;

import com.alibaba.fastjson.annotation.JSONField;
import com.gs.lshly.common.exception.BusinessException;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 菜单按钮对象
 *
 * @author peiyu
 */
@Data
@Accessors(chain = true)
public class MenuButton extends BaseModel {

    /**
     * 菜单类别
     */
    private MenuType type;

    /**
     * 菜单上显示的文字
     */
    private String name;

    /**
     * 菜单key，当MenuType值为CLICK时用于区别菜单
     */
    private String key;

    /**
     * 菜单跳转的URL，当MenuType值为VIEW时用
     */
    private String url;

    /**
     * 菜单显示的永久素材的MaterialID,当MenuType值为media_id和view_limited时必需
     */
    @JSONField(name = "media_id")
    private String mediaId;

    private String appid;

    private String pagepath;

    /**
     * 二级菜单列表，每个一级菜单下最多5个
     */
    @JSONField(name = "sub_button")
    private List<MenuButton> subButton;

    public void setSubButton(List<MenuButton> subButton) {
        if (null == subButton || subButton.size() > 5) {
            throw  new BusinessException("子菜单最多只有5个");
        }
        this.subButton = subButton;
    }
}
