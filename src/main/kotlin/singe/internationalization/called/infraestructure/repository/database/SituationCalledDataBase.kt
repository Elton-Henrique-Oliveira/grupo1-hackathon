package singe.internationalization.called.infraestructure.repository.database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object SituationCalledDataBase : Table("called_situation") {
    val uuid = uuid("uuid").uniqueIndex()
    val label = varchar("label", 100)
    val statusCode = integer("status_code")
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

