package com.gs.lshly.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 * @since 2020/09/14
 */
@ApiModel(value="Page", description="分页信息")
public class PageData<T> implements Serializable {

    @ApiModelProperty("当前页")
    private Integer pageNum;

    @ApiModelProperty("每页的数量")
    private Integer pageSize;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("数据")
    private List<T> content;

    public PageData() {

    }

    public PageData(List<T> content, int pageNum, int pageSize, long total) {
        this.content = content;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
