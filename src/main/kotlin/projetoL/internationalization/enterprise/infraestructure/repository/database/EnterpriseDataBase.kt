package projetoL.internationalization.enterprise.infraestructure.repository.database

import projetoL.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object EnterpriseDataBase : Table("enterprise") {
    val uuid = uuid("uuid").uniqueIndex()
    val code = integer("code").autoIncrement()
    val name = varchar("name", 200)
    val serverUUID = reference("server_uuid", ServerDataBase.uuid).nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

fun Query.withEnterpriseFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}
