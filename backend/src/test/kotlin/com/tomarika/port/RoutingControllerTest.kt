package com.tomarika.port

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
class RoutingControllerTest {
    private fun makeSubject(): MockMvc {
        return MockMvcBuilders.standaloneSetup(
            RoutingController()
        ).build()
    }

    @Test
    fun `when access to route, response 200OK`() {
        // WHEN
        val result = makeSubject().perform(MockMvcRequestBuilders.get("/"))

        // THEN
        result.andExpect(MockMvcResultMatchers.status().isOk)
    }
}
