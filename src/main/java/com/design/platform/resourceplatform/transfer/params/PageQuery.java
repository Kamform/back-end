package com.design.platform.resourceplatform.transfer.params;

import org.springframework.data.domain.PageRequest;

public class PageQuery {

    public int page = 0;
    public int size = 30;

    public PageRequest toRequest() {
        return PageRequest.of(page, size);
    }
}
