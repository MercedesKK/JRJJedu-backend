package com.company.project.common;


import javax.persistence.Transient;

public class PageParam extends BaseParam{
    @Transient
    private Integer page;

    // 分页参数
    @Transient
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page == null ? 0 : page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit == null ? 10 : limit;
    }
}
