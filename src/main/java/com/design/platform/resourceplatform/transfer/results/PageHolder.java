package com.design.platform.resourceplatform.transfer.results;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageHolder<T> {

    public List<T> content;

    public int pageCount;
    public long itemCount;

    public int page;
    public int size;

    public PageHolder(Page<T> page) {
        content = page.getContent();
        pageCount = page.getTotalPages();
        itemCount = page.getTotalElements();
        this.page = page.getNumber();
        size = page.getSize();
    }

    public static <T> PageHolder<T> FromPage(Page<T> page) {
        return new PageHolder<>(page);
    }
}
