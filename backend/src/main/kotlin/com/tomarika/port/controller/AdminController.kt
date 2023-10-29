package com.tomarika.port.controller

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime
import java.util.Date

data class HelloResponse(
    val message: String,
    val datetime: Date,
    val userAgent: String,
    val clientIp: String,
)

@RestController
@RequestMapping("/api/admin")
class AdminController {
    @GetMapping("/health")
    fun getHealth() {
    }

    @GetMapping("/hello")
    fun getHello(@RequestHeader("User-Agent") userAgent: String, request: HttpServletRequest): HelloResponse {
        return HelloResponse(
            message = "Hi, this is tomarika port api!",
            datetime = Date.from(ZonedDateTime.now().toInstant()),
            userAgent = userAgent,
            clientIp = request.remoteAddr,
        )
    }
}
