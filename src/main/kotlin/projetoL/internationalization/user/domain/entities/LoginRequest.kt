package projetoL.internationalization.user.domain.entities

import java.util.*

data class LoginRequest(
    /**
     * The identifier of this user, can be one of the following:
     *  - UUID
     *  - E-mail
     *  - Authentication record
     */
    val username: String,

    /**
     * The password of this user
     */
    val password: String,

    val enterpriseUUID: UUID
)