package com.vw.cleaningrobot.application.gateway

import com.vw.cleaningrobot.domain.service.RobotDomainService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest
@ComponentScan("com.vw.cleaningrobot")
class RobotControllerIT {

	@Autowired
	lateinit var mockMvc: MockMvc

	@Autowired
	var robotDomainService: RobotDomainService = RobotDomainService()

	@Test
	fun `when call start with happy path returns 201`() {
		mockMvc.perform(MockMvcRequestBuilders.post("/cleaning/start")
			.contentType(MediaType.TEXT_PLAIN_VALUE)
			.content(generateHappyPathDataInput())
		).andExpect(status().isCreated)
	}

	private fun generateHappyPathDataInput() = """
        5 5
        1 2 N
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
    """.trimIndent()
}
