package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object PriorityDataBase : Table("priority") {
    val uuid = uuid("uuid")
    val label = varchar("label", 60)
    val statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
        uniqueIndex(uuid)
    }
}