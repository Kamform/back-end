package com.design.platform.resourceplatform.utils

import org.springframework.data.domain.Page

class PageHolder<T>(page: Page<T>) {
    var content: List<T> = page.content
    var pageCount: Int = page.totalPages
    var itemCount: Long = page.totalElements
    var page: Int = page.number
    var size: Int = page.size
}

fun <T> Page<T>.auto(): PageHolder<T> {
    return PageHolder(this)
}
