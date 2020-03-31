package com.design.platform.resourceplatform.interfaces

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class WebsiteController {

    @GetMapping
    fun init() {
    }
}
