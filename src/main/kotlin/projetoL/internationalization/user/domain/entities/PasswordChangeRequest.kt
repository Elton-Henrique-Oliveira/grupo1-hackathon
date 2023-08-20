package projetoL.internationalization.user.domain.entities
/**
 * Data required to reset a user password.
 *
 * This data must be used in together with either:
 *  - the current authentication scope, to reset the current user password
 */
data class PasswordChangeRequest(
    /**
     * The current password of this user.
     */
    val currentPassword: String,

    /**
     * The user authentication record
     */
    val newPassword: String,

    /**
     * The user authentication record
     */
    val newPasswordConfirmation: String
)
