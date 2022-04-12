package com.hrstka

import jakarta.inject.Singleton
import org.slf4j.LoggerFactory
import java.util.UUID
import javax.transaction.Transactional

@Singleton
open class RecordService(
    private val normalRepository: NormalRepository,
    private val coroutinesRepository: CoroutinesRepository,
    private val genericCoroutinesRepository: GenericCoroutinesRepository
) {

    @Transactional
    open fun normalStore() {
        val id = UUID.randomUUID()

        normalRepository.save(Record(id))
        throw RuntimeException("exception")
    }

    @Transactional
    open suspend fun coroutinesStore() {
        val id = UUID.randomUUID()

        coroutinesRepository.save(Record(id))
        throw RuntimeException("exception")
    }

    @Transactional
    open suspend fun coroutinesGenericStore() {
        val id = UUID.randomUUID()

        genericCoroutinesRepository.save(Record(id))
        throw RuntimeException("exception")
    }

    @Transactional
    open suspend fun storeCoroutines() {
        val id = UUID.randomUUID()
        coroutinesRepository.save(Record(id))
    }

    open fun count(): Long {
        val count = normalRepository.count()
        LoggerFactory.getLogger(this::class.java).info("Stored $count records")
        return count
    }
}
