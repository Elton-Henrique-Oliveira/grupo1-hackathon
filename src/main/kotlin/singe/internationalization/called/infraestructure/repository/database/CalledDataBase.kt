package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import singe.internationalization.called.domain.entities.DescriptionCalled

object CalledDataBase : Table("called") {
    val uuid = uuid("uuid").uniqueIndex()
    val identifier = long("identifier").autoIncrement()
    val userName = varchar("name", 200)
    val flowUUID = reference("flow_uuid", FlowDataBase.uuid)
    val situation = integer("situation")
    val branch = long("branch").nullable()
    val telephone = long("telephone").nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}