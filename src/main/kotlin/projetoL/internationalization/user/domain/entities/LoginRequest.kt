package projetoL.internationalization.user.domain.entities

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
    val password: String
)