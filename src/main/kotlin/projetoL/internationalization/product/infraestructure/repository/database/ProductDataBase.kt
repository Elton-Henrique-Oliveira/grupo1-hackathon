package projetoL.internationalization.product.infraestructure.repository.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import projetoL.internationalization.enterprise.infraestructure.repository.database.EnterpriseDataBase
import projetoL.internationalization.user.infraestructure.repository.database.UserDataBase

object ProductDataBase : Table("product") {
    val uuid = uuid("uuid").uniqueIndex()
    val code = integer("code").autoIncrement()
    val name = varchar("name", 200)
    val enterprise = reference("enterprise_uuid", EnterpriseDataBase.uuid)
    val userUUID = reference("user_uuid", UserDataBase.uuid)
    val weight = double("weight").default(defaultValue = 0.0)
    val validity = integer("validity").nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}