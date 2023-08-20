package projetoL.internationalization.enterprise.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object ServerDataBase : Table("server") {
    val uuid = uuid("uuid").uniqueIndex()
    val code = integer("code").autoIncrement()
    val name = varchar("name", 200)
    val ip = varchar("ip", 200)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}
