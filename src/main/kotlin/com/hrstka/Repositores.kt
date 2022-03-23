package com.hrstka

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import io.micronaut.data.repository.GenericRepository
import io.micronaut.data.repository.kotlin.CoroutineCrudRepository
import java.util.*

@MappedEntity
data class Record(@field:Id val id: UUID)

@JdbcRepository(dialect = Dialect.H2)
interface NormalRepository: CrudRepository<Record, UUID>

@JdbcRepository(dialect = Dialect.H2)
interface CoroutinesRepository: CoroutineCrudRepository<Record, UUID>

@JdbcRepository(dialect = Dialect.H2)
interface GenericCoroutinesRepository: GenericRepository<Record, UUID> {

    suspend fun save(record: Record): Record
}
