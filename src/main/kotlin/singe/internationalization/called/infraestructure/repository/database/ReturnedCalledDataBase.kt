package singe.internationalization.called.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime

object ReturnedCalledDataBase : Table("returned_called") {
    val uuid = uuid("uuid").uniqueIndex()
    val returnedText = text("returned_text")
    val descriptionCalledUUID = reference("description_called_uuid", DescriptionCalledDataBase.uuid)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}
