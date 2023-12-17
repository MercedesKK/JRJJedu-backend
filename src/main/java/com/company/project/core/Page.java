package com.company.project.core;

import java.util.HashMap;
import java.util.Map;

public class Page<T> {
    private Integer page=0;
    private Integer size=20;
    private T model;
    private Map<String,String> params=new HashMap<>();

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
