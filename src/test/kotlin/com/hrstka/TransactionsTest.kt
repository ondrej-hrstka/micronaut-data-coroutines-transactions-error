package com.hrstka

import io.micronaut.runtime.EmbeddedApplication
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation

@MicronautTest(transactional = false, rollback = false)
@TestMethodOrder(OrderAnnotation::class)
class TransactionsTest(
    val normalRepository: NormalRepository,
    val recordService: RecordService,
    ) {

    @AfterEach
    fun tearDown() {
        normalRepository.deleteAll()
    }

    @Test
    @Order(1)
    fun normal() { // passes
        assertThrows<RuntimeException>() {
            recordService.normalStore()
        }

        Assertions.assertEquals(0, recordService.count())
    }

    @Test
    @Order(2)
    fun normal1() { // passes
        assertThrows<RuntimeException>() {
            recordService.normalStore()
        }

        Assertions.assertEquals(0, recordService.count())
    }

    @Test
    @Order(3)
    fun coroutines() = runBlocking() { // fails
        assertThrows<RuntimeException>() {
            recordService.coroutinesStore()
        }

        Assertions.assertEquals(0, recordService.count())
    }

    @Test
    @Order(4)
    fun coroutinesGeneric() = runBlocking() { // fails
        assertThrows<RuntimeException>() {
            recordService.coroutinesGenericStore()
        }

        Assertions.assertEquals(0, recordService.count())
    }

    @Test
    @Order(5)
    fun normal2() { // Fails because coroutines error messes up something
        assertThrows<RuntimeException>() {
            recordService.normalStore()
        }

        Assertions.assertEquals(0, recordService.count())
    }
}
