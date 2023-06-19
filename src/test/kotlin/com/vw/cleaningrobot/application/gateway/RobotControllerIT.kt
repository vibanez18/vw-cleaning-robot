package com.vw.cleaningrobot.application.gateway

import com.vw.cleaningrobot.domain.repository.RobotCleaningDomainRepository
import com.vw.cleaningrobot.domain.service.RobotDomainService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@ComponentScan("com.vw.cleaningrobot.*")
class RobotControllerIT {

	@Autowired
	lateinit var mockMvc: MockMvc

    @Autowired
	lateinit var robotDomainService: RobotDomainService

	@Autowired
    lateinit var robotCleaningRepository: RobotCleaningDomainRepository

	@Test
	fun `when call start endpoint with happy path returns 201`() {
		mockMvc.perform(MockMvcRequestBuilders.post("/cleaning/start")
			.contentType(MediaType.TEXT_PLAIN_VALUE)
			.content(generateHappyPathDataInput())
		).andExpect(status().isCreated)
	}

	@Test
	fun `when call start endpoint with wrong data input returns 400`() {
		mockMvc.perform(MockMvcRequestBuilders.post("/cleaning/start")
			.contentType(MediaType.TEXT_PLAIN_VALUE)
			.content(generateWrongInitialPositionDataInput())
		).andExpect(status().is4xxClientError)
	}

	@Test
	fun `when call start endpoint with incorrect file input returns 400`() {
		mockMvc.perform(MockMvcRequestBuilders.post("/cleaning/start")
			.contentType(MediaType.TEXT_PLAIN_VALUE)
			.content(generateWrongInitialPositionDataInput_2())
		).andExpect(status().is4xxClientError)
	}

	private fun generateHappyPathDataInput() = """
        5 5
        1 2 N
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
    """.trimIndent()

	private fun generateWrongInitialPositionDataInput() = """
        5 5
        1 2 N 1 2
        LMLMLMLMM
        3 3 E
        MMRMMRMRRM
    """.trimIndent()

	private fun generateWrongInitialPositionDataInput_2() = """
        5 5
        1 2 N 1 2
		5 5
        LMLMLMLMM
		5 5
        3 3 E
        MMRMMRMRRM
    """.trimIndent()
}
