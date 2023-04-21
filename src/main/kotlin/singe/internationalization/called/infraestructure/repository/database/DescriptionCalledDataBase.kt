package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object DescriptionCalledDataBase : Table("description_called") {
    val uuid = uuid("uuid").uniqueIndex()
    val calledUUID = reference("called_uuid", CalledDataBase.uuid)
    val title = varchar("title", 250)
    val priority = varchar("priority", 200)
    val typeSystemUUID = reference("type_system_uuid", TypeSystemDataBase.uuid)
    val situation = integer("situation")
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}