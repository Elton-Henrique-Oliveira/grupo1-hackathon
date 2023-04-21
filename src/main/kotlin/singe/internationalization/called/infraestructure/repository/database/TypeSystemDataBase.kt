package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object TypeSystemDataBase : Table("type_system") {
    val uuid = uuid("uuid")
    val label = varchar("label", 60)
    val calledType = integer("called_type")
    val statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
        uniqueIndex(uuid)
    }
}
