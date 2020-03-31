package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.entities.Category
import com.design.platform.resourceplatform.transfer.CategoryBooth

fun Category.auto(): CategoryBooth = CategoryBooth(id, name, sort)
