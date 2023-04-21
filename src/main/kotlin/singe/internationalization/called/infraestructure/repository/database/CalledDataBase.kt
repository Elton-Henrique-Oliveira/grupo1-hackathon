package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object CalledDataBase : Table("called") {
    val uuid = uuid("uuid").uniqueIndex()
    val identifier = varchar("identifier", 100)
    val userName = varchar("name", 200)
    val type = integer("type")
    val situation = integer("situation")
    val branch = integer("branch").nullable()
    val telephone = integer("telephone").nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}