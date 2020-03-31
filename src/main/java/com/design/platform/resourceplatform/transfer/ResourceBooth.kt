package com.design.platform.resourceplatform.transfer

import java.sql.Timestamp

data class ResourceBooth(
    var id: Int,
    var title: String,
    var category: CategoryBooth,
    var author: UserBooth,

    var summary: String,

    var created: Timestamp,
    var updated: Timestamp
)
