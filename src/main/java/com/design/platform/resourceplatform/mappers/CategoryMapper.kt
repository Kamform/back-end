package com.design.platform.resourceplatform.mappers

import com.design.platform.resourceplatform.entities.Category
import com.design.platform.resourceplatform.transfer.CategoryBooth

fun Category.map(): CategoryBooth = CategoryBooth(id, name, sort)
