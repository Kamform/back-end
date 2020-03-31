package com.design.platform.resourceplatform.transfer.patch

data class Follow(
    var target: Int,
    var cancel: Boolean = false
)
