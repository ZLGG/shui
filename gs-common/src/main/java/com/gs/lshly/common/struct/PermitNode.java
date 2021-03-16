package com.gs.lshly.common.struct;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 功能模块
 * @author lxus
 * @since 2020-12-08
 */
@Data
@Accessors(chain = true)
@Api("功能模块")
public class PermitNode implements Serializable {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("code")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("显示顺序")
    private Integer index;

    @ApiModelProperty("类型(10-根节点；20-模块；30-功能)")
    private Integer type;

    @ApiModelProperty("父节点code")
    private String parent;

    @ApiModelProperty(value = "是否选中", hidden = true)
    private Boolean checked;

    @ApiModelProperty(value = "前端路由", hidden = true)
    private String frontRouter;

    @ApiModelProperty("路径")
    private Set<String> paths;

    @ApiModelProperty("所在位置")
    private Set<String> locations;

    @ApiModelProperty("子模块/子功能")
    private List<PermitNode> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PermitNode that = (PermitNode) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

