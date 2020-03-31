package com.design.platform.resourceplatform.utils

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

data class PageParam(
    var page: Int = 0,
    var size: Int = 30,
    var orders: List<OrderParam> = emptyList()
)

data class OrderParam(
    var property: String,
    var direction: Sort.Direction? = Sort.Direction.DESC
)

val PageParam.auto: PageRequest
    get() = when {
        orders.isEmpty() -> PageRequest.of(page, size)
        else -> PageRequest.of(page, size, Sort.by(orders.map { it.auto }))
    }

val OrderParam.auto: Sort.Order
    get() = Sort.Order(direction, property)
