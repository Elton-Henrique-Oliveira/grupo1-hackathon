package projetoL.internationalization.user.domain.entities

import java.time.LocalDateTime

/**
 * Contains the data required to start a password reset request.
 *
 * The authentication is used to check if the user exists in the system.
 *
 * The provided email must match with the value registered for this user in the repository, otherwise the request will
 * fail with due to invalid credential.
 */
data class StartPasswordResetRequest(
    /**
     * The user e-mail
     */
    val email: String,
)

/**
 * Response returned when asked to start the password reset method
 */
data class StartPasswordResetResponse(
    /**
     * Whether the requ est was successful
     */
    val success: Boolean = true,

    /**
     * The time limit when this request will expire
     */
    val expiresAt: LocalDateTime? = null,

)

/**
 * Data required to reset a user password from a reset request.
 *
 * This data must be used in together with a password request UUID, to execute the reset password method
 */
data class PasswordResetChangeRequest(
    /**
     * The user authentication record
     */
    val password: String,

    /**
     * The user authentication record
     */
    val passwordConfirmation: String
)

/**
 * Response returned when asked to start the password reset method
 */
data class ExecutePasswordResetResponse(
    /**
     * Whether the request was successful
     */
    val success: Boolean = true,
)