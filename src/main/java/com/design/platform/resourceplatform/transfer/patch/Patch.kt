package com.design.platform.resourceplatform.transfer.patch

data class Patch(
    var target: Int,
    var cancel: Boolean = false
)
