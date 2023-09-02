package projetoL.internationalization.user.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import java.time.LocalDateTime
import java.util.*

@JsonIgnoreProperties(value = ["passwordHash"])
data class User(
    /**
     * The Universally Unique Identifier of this type
     */
    var uuid: UUID? = null,

    /**
     * The name of the user
     */
    var name: String? = null,

    /**
     * The type of the user(Owner, admin, default)
     */
    var userType: UserType? = null,

    /**
     * If is true, user is active else user is inactive
     */
    var isActive: Boolean? = false,

    /**
     * The e-mail of the user
     */
    var email: String? = null,

    var hash: String? = null,

    var enterprise: Enterprise? = null,

    /**
     * The credential of login to access the system
     */
    var authenticationRecord: String? = null,

    /**
     * The password used to access the system
     */
    var password: String? = null,

    /**
     * The password hash used to the authentication and authorization of the users in the system
     */
    var passwordHash: String? = null,

    /**
     * The contact of the user, can be cellphone, fax, personal email...
     */
    var contact: String? = null,

    /**
     * Whether the user password needs to be changed
     */
    var isPasswordExpired: Boolean = false,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null
)

/**
 * Represents a user type in the system
 */
data class UserType(
    /**
     * The Universally Unique Identifier of this type
     */
    var uuid: UUID,

    /**
     * The external identifier to user type
     */
    var code: Int,

    /**
     * The label of the type
     */
    var label: String,
)

enum class EnumUserType(val value: Int) {
    Owner(1),
    Admin(2),
    Employee(3),
}