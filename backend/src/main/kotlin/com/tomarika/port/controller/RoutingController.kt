package com.tomarika.port.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class RoutingController {
    @GetMapping(value = ["/"])
    fun index(): String {
        return "forward:/index.html"
    }
}
