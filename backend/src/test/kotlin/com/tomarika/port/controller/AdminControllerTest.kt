package com.tomarika.port.controller

import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.ZonedDateTime
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when get to api admin health, response 200 OK`() {
        // WHEN
        val res = mockMvc.perform(get("/api/admin/health"))

        // THEN
        res.andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `when get to api admin hello, get greeting data`(){
        // GIVEN
        val expectedDateTime = ZonedDateTime.parse("2023-12-31T14:24:32.000Z")
        mockkStatic(ZonedDateTime::class)
        every { ZonedDateTime.now() } returns expectedDateTime

        // WHEN
        val res = mockMvc.perform(
            get("/api/admin/hello")
                .header(HttpHeaders.USER_AGENT, "Testing-Agent")
                .remoteAddress("10.20.30.40")
        )

        // THEN
        res.andExpect(MockMvcResultMatchers.status().isOk)
        res.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Hi, this is tomarika port api!"))
        res.andExpect(MockMvcResultMatchers.jsonPath("$.datetime").value("2023-12-31T14:24:32Z"))
        res.andExpect(MockMvcResultMatchers.jsonPath("$.userAgent").value("Testing-Agent"))
        res.andExpect(MockMvcResultMatchers.jsonPath("$.clientIp").value("10.20.30.40"))

        // CLEAN
        unmockkStatic(ZonedDateTime::class)
    }
}
