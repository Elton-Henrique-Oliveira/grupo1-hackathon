package projetoL.internationalization.user.infraestructure.repository.database

import projetoL.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.util.*

object UserDataBase : Table("user") {
    val uuid = uuid("uuid").uniqueIndex()
    val name = varchar("name", 200)
    val userType = reference("user_type", UserTypeTable.uuid)
    val isActive = bool("is_active")
    val email = varchar("email", 200).nullable()
    val authenticationRecord = varchar("authentication_record", 200).uniqueIndex()
    val password = text("password")
    val lastPasswordModified = datetime("last_password_modified").nullable()
    val passwordValidate = datetime("password_validate").nullable()
    val updatePassword = bool("update_password").default(true)
    val contact = varchar("contact", 120).nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

/**
 * The exposed definition of table used to store user type
 */
object UserTypeTable : Table("user_type") {
    val uuid = uuid("uuid")
    val label = varchar("label", 60)
    val code = integer("code").uniqueIndex()
    val statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
        uniqueIndex(uuid)
    }
}

fun Query.withUserFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "name" -> Op.build { UserDataBase.name.lowerCase() like "%" + it.value.lowercase() + "%" }
            "userType" -> Op.build { UserDataBase.userType eq UUID.fromString(it.value) }
            "isActive" -> Op.build { UserDataBase.isActive eq it.value.toBoolean() }
            "email" -> Op.build { UserDataBase.email.lowerCase() like "%" + it.value.lowercase() + "%" }
            "authenticationRecord" -> Op.build { UserDataBase.authenticationRecord.lowerCase() eq it.value.lowercase() }
            "contact" -> Op.build { UserDataBase.contact.lowerCase() like "%" + it.value.lowercase() + "%" }
            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}

/**
 * The exposed definition of the table used to manage password reset requests.
 * We store
 */
object PasswordResetRequestTable : Table("password_reset_request") {
    val uuid = uuid("uuid")
    val uuidUser = reference("user", UserDataBase.uuid)
    val expiresAt = datetime("expires_at")
    val createAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}